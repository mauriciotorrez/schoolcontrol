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
    public class CreatePersona
    {
        public int CreatePersonaDB(Persona per)
        {
            try
            {                

                var parser = new GenericArrayParser() { AsynchParseEnabled = false };

                //var parser = new GenericArrayParser() { AsynchParseEnabled = false };
                Dictionary<string, object> parameters = new Dictionary<string, object>();
                parameters.Add("@nom1", per.Nombre1);
                parameters.Add("@nom2", per.Nombre2);
                parameters.Add("@ap_paterno", per.Apellido);
                parameters.Add("@mail1", per.Mail1);
                parameters.Add("@dir1", per.Dir1);
                parameters.Add("@cel", per.Cel);
            
                SQLDataAccess.Instance.ExecuteReader("CREATE_Persona", parser, parameters);
                if (parser.Result.Count > 0)
                {
                    return Int32.Parse(parser.Result[0][0].ToString());
                }
                else
                {
                    AsyncState errorState = new AsyncState();
                    throw new Exception("DALConstants.ERROR_CREATING_STORE");
                }
            }
            catch (Exception ex)
            {           
                    AsyncState errorState = new AsyncState();
                    throw new Exception("DALConstants.ERROR_CREATING_STORE");
            }
        }
    }
}
