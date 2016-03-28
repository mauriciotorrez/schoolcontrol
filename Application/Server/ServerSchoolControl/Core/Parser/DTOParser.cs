using Core.DTO.SQLDataAccess;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Core.Parser
{
    public abstract class DTOParser<T>
    {
        public bool AsynchParseEnabled = true;

        protected SqlDataReader Reader = null;

        public List<T> Result = new List<T>();

        public Action<AsyncState, List<T>> ParseCompleted
        {
            get;
            set;
        }

        public Action<AsyncState, List<T>> TransactionalParseCompleted
        {
            get;
            set;
        }

        public Action<int> ParseBegan
        {
            get;
            set;
        }

        public abstract void ParseWorker();

        public virtual void Parse(SqlDataReader reader, AsyncState asyncState = null)
        {
            Reader = reader;
            if (AsynchParseEnabled)
            {
                if (asyncState != null && asyncState.Error != null)
                {
                    if (AsynchParseEnabled)
                    {
                        if (ParseCompleted != null)
                        {
                            ParseCompleted(asyncState, new List<T>());
                        }
                        else if (TransactionalParseCompleted != null)
                        {
                            TransactionalParseCompleted(asyncState, new List<T>());
                        }
                    }
                }
                else
                {
                    AsyncParse(reader, asyncState);
                }
            }
            else
            {
                SynchParse(reader, asyncState);
            }
        }

        private void SynchParse(SqlDataReader reader, AsyncState asyncState)
        {
            ParseWorker();
            if (AsynchParseEnabled)
            {
                ParseCompleted(asyncState, Result);
            }
        }

        private void AsyncParse(SqlDataReader reader, AsyncState asyncState)
        {
            AsyncCallback callback = new AsyncCallback(AsyncParseWorker);
            Action asyncParseWorker = new Action(ParseWorker);
            asyncState.AsyncCaller = asyncParseWorker;
            asyncParseWorker.BeginInvoke(callback, asyncState);
        }

        private void AsyncParseWorker(IAsyncResult result)
        {
            AsyncState asyncState = result.AsyncState as AsyncState;
            try
            {
                (asyncState.AsyncCaller as Action).EndInvoke(result);

                Reader.Close();
                Reader.Dispose();
                SqlCommand command = asyncState.Command;
                command.Dispose();

                if (null == asyncState.SQLTransaction)
                {
                    SqlConnection connection = asyncState.Command.Connection;
                    connection.Close();
                    connection.Dispose();
                    if (null != ParseCompleted)
                    {
                        ParseCompleted(asyncState, Result);
                    }
                }
                else
                {
                    if (null != TransactionalParseCompleted)
                    {
                        TransactionalParseCompleted(asyncState, Result);
                    }
                }
            }
            catch (Exception ex)
            {
                if (null != TransactionalParseCompleted)
                {
                    asyncState.Error = ex;
                    TransactionalParseCompleted(asyncState, Result);
                }
            }
        }
    }
}
