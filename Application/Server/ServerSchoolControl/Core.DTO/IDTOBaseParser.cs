using Core.DTO.SQLDataAccess;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Core.DTO
{
    public interface IDTOBaseParser
    {
        void Parse(SqlDataReader reader, AsyncState arguments = null);
    }
}
