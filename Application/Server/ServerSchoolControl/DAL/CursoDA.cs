using Core.DTO;
using Core.DTO.SQLDataAccess;
using Core.Parser;
using DataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DAL
{
    public class CursoDA
    {
        public List<Curso> GetCourses()
        {
            var parser = new CursoParser() { AsynchParseEnabled = false };
            try
            {
                Dictionary<string, object> parameters = new Dictionary<string, object>();
                SQLDataAccess.Instance.ExecuteReader("Curso_Get", parser, parameters);

                return parser.Result;

            }
            catch (Exception ex)
            {
                AsyncState errorState = new AsyncState();
                throw new Exception("DALConstants.ERROR_CREATING_STORE");
            }
        }

    }
}
