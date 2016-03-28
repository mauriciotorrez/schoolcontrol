using System;
using System.Collections.Generic;
using System.Data.Odbc;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Core.DTO.SQLDataAccess
{
    public class AsyncState
    {
        public SqlCommand Command
        {
            get;
            set;
        }

        public SqlTransaction SQLTransaction
        {
            get;
            set;
        }

        public OdbcTransaction AS400Transaction
        {
            get;
            set;
        }

        public IDTOBaseParser Parser
        {
            get;
            set;
        }

        public object AsyncCaller
        {
            get;
            set;
        }

        public object AsyncCallBackResult
        {
            get;
            set;
        }

        public Exception Error
        {
            get;
            set;
        }

        public object StateData
        {
            get;
            set;
        }
    }
}
