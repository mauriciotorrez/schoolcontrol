using Core.DTO;
using Core.DTO.SQLDataAccess;
using Core.Parser;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;

namespace DataAccess
{
    public class SQLDataAccess
    {
        private static SQLDataAccess innerInstance = null;
        private const int COMMAND_TIMEOUT = 120;
        public string dbConnectionString = String.Empty;

        protected SQLDataAccess()
        {
            var connStringName = string.Empty;

            connStringName = "SchoolControlConnection";


            dbConnectionString = ConfigurationManager.ConnectionStrings[connStringName].ConnectionString;
        }

        public static SQLDataAccess Instance
        {
            get
            {
                if (innerInstance == null)
                {
                    innerInstance = new SQLDataAccess();
                }
                return innerInstance;
            }
        }

        public void AsyncExecuteNonQuery(string commandText,
                                    Dictionary<string, object> parameters = null,
                                    Action<AsyncState, int> resultCallback = null,
                                    CommandType commandType = CommandType.StoredProcedure,
                                    AsyncState asyncParameters = null)
        {

            SqlConnection connection;
            if (asyncParameters == null)
            {
                connection = new SqlConnection(dbConnectionString);
                connection.Open();
            }
            else
            {
                connection = asyncParameters.SQLTransaction.Connection;
            }

            using (var command = connection.CreateCommand())
            {
                command.CommandType = commandType;
                command.CommandTimeout = COMMAND_TIMEOUT;
                command.CommandText = commandText;
                command.Parameters.Clear();
                if (asyncParameters != null)
                {
                    command.Transaction = asyncParameters.SQLTransaction;
                }
                if (null != parameters)
                {
                    foreach (var parameter in parameters)
                    {
                        command.Parameters.Add(new SqlParameter(parameter.Key, parameter.Value));
                    }
                }
                AsyncCallback callback = new AsyncCallback(NonQueryProcessed);
                asyncParameters.Command = command;
                asyncParameters.AsyncCaller = resultCallback;
                command.BeginExecuteNonQuery(callback, asyncParameters);
            }
        }

        public void ExecuteNonQuery(string commandText,
                                    Dictionary<string, object> parameters = null,
                                    SqlTransaction transaction = null,
                                    CommandType commandType = CommandType.StoredProcedure)
        {
            SqlConnection connection;
            if (null == transaction)
            {
                connection = new SqlConnection(dbConnectionString);
                connection.Open();
            }
            else
            {
                connection = transaction.Connection;
            }

            using (var command = connection.CreateCommand())
            {
                command.CommandType = commandType;
                command.CommandTimeout = COMMAND_TIMEOUT;
                command.CommandText = commandText;
                command.Parameters.Clear();
                if (null != transaction)
                {
                    command.Transaction = transaction;
                }

                if (null != parameters)
                {
                    foreach (var parameter in parameters)
                    {
                        command.Parameters.Add(new SqlParameter(parameter.Key, parameter.Value));
                    }
                }
                command.ExecuteNonQuery();
            }
        }

        public void NonQueryProcessed(IAsyncResult result)
        {
            AsyncState arguments = result.AsyncState as AsyncState;
            int noQueryResult = arguments.Command.EndExecuteNonQuery(result);
            arguments.Command.Dispose();
            if (arguments.SQLTransaction == null)
            {
                arguments.SQLTransaction.Connection.Close();
                arguments.SQLTransaction.Connection.Dispose();
            }
            if (null != arguments.AsyncCaller)
            {
                (arguments.AsyncCaller as Action<AsyncState, int>)(arguments, noQueryResult);
            }
        }

        public TResult ExecuteScalar<TResult>(string commandText,
                                     CommandType commandType,
                                     Dictionary<string, object> parameters)
        {
            using (var connection = new SqlConnection(dbConnectionString))
            {
                connection.Open();

                using (var command = connection.CreateCommand())
                {
                    command.CommandType = commandType;
                    command.CommandTimeout = COMMAND_TIMEOUT;
                    command.CommandText = commandText;
                    command.Parameters.Clear();
                    if (parameters == null)
                    {
                        return (TResult)command.ExecuteScalar();
                    }
                    foreach (var parameter in parameters)
                    {
                        command.Parameters.Add(new SqlParameter(parameter.Key, parameter.Value));
                    }
                    return (TResult)command.ExecuteScalar();
                }
            }
        }

        public void ExecuteReader(string commandText, IDTOBaseParser parser,
            Dictionary<string, object> parameters = null,
            CommandType commandType = CommandType.StoredProcedure,
            SqlTransaction transaction = null)
        {
            SqlConnection connection = null;
            if (null == transaction)
            {
                connection = new SqlConnection(dbConnectionString);
                connection.Open();
            }
            else
            {
                connection = transaction.Connection;
            }

            using (var command = connection.CreateCommand())
            {
                command.CommandType = commandType;
                command.CommandTimeout = COMMAND_TIMEOUT;
                command.CommandText = commandText;
                command.Parameters.Clear();

                if (null != transaction)
                {
                    command.Transaction = transaction;
                }

                if (null != parameters)
                {
                    foreach (var parameter in parameters)
                    {
                        command.Parameters.Add(new SqlParameter(parameter.Key, parameter.Value));
                    }
                }

                using (var reader = command.ExecuteReader())
                {
                    parser.Parse(reader);
                }
            }
            if (null == transaction)
            {
                connection.Close();
                connection.Dispose();
            }
        }


        public void ExecuteReaderOutput(string commandText, IDTOBaseParser parser,
    Dictionary<string, object> parameters = null,
    CommandType commandType = CommandType.StoredProcedure,
    SqlTransaction transaction = null)
        {
            SqlConnection connection = null;
            if (null == transaction)
            {
                connection = new SqlConnection(dbConnectionString);
                connection.Open();
            }
            else
            {
                connection = transaction.Connection;
            }

            using (var command = connection.CreateCommand())
            {
                command.CommandType = commandType;
                command.CommandTimeout = COMMAND_TIMEOUT;
                command.CommandText = commandText;
                command.Parameters.Clear();

                if (null != transaction)
                {
                    command.Transaction = transaction;
                }

                if (null != parameters)
                {
                    foreach (var parameter in parameters)
                    {
                        command.Parameters.Add(parameter.Key, SqlDbType.BigInt);
                        command.Parameters[parameter.Key].Direction = ParameterDirection.Output;
                    }
                }

                using (var reader = command.ExecuteReader())
                {
                    object test = reader["@Anchor"];
                }
            }
            if (null == transaction)
            {
                connection.Close();
                connection.Dispose();
            }
        }


        /// <summary>
        /// Executes asynchronously a query to db
        /// </summary>
        /// <param name="commandText"></param>
        /// <param name="parser"></param>
        /// <param name="parameters"></param>
        /// <param name="commandType"></param>
        /// <param name="dbConnection">A connection to db, generally used to execute queries inside
        /// transaction</param>
        public void AsyncExecuteReader(string commandText,
            IDTOBaseParser parser,
            Dictionary<string, object> parameters = null,
            CommandType commandType = CommandType.StoredProcedure,
            AsyncState asyncState = null)
        {

            SqlConnection connection;
            if (null == asyncState || null == asyncState.SQLTransaction)
            {
                connection = new SqlConnection(dbConnectionString);
                connection.Open();
            }
            else
            {
                connection = asyncState.SQLTransaction.Connection;
            }

            var command = connection.CreateCommand();
            command.CommandType = commandType;
            command.CommandTimeout = COMMAND_TIMEOUT;
            command.CommandText = commandText;
            command.Parameters.Clear();

            if (asyncState != null)
            {
                command.Transaction = asyncState.SQLTransaction;
            }

            if (null != parameters)
            {
                foreach (var parameter in parameters)
                {
                    command.Parameters.Add(new SqlParameter(parameter.Key, parameter.Value));
                }
            }

            AsyncCallback callback = new AsyncCallback(QueryProcessed);
            if (null == asyncState)
            {
                asyncState = new AsyncState();
            }
            asyncState.Command = command;
            asyncState.Parser = parser;
            command.BeginExecuteReader(callback, asyncState);

        }

        private void QueryProcessed(IAsyncResult result)
        {
            var asyncState = result.AsyncState as AsyncState;
            var reader = asyncState.Command.EndExecuteReader(result);
            asyncState.Parser.Parse(reader, asyncState);
        }

        public SqlTransaction BeginTransaction()
        {
            var connection = new SqlConnection(dbConnectionString);
            connection.Open();
            return connection.BeginTransaction(IsolationLevel.ReadCommitted);
        }

        private void CommitTransaction(SqlTransaction transaction)
        {
            try
            {
                transaction.Commit();
                transaction.Dispose();
            }
            catch (Exception ex)
            {

            }
        }

        private void RollbackTransaction(SqlTransaction transaction)
        {
            if (transaction != null)
            {
                transaction.Rollback();
                transaction.Dispose();
            }
        }
    }
}
