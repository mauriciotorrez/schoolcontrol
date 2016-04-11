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
    public class LoginDA
    {
        public Dictionary<string, object> Login(User user)
        {
            Guid personaGuid = Guid.Empty;
            Guid administradorGuid = Guid.Empty;
            Guid profesorGuid = Guid.Empty;
            Guid tutorGuid = Guid.Empty;
            Guid estudianteGuid = Guid.Empty;
            Dictionary<string, object> res = new Dictionary<string, object>();
            try
            {
                var parser = new GenericArrayParser() { AsynchParseEnabled = false };
                Dictionary<string, object> parameters = new Dictionary<string, object>();
                parameters.Add("@usuario", user.Usuario);
                parameters.Add("@passw", user.Passw);

                SQLDataAccess.Instance.ExecuteReader("Login", parser, parameters);
                if (parser.Result.Count > 0)
                {
                    personaGuid = (Guid)parser.Result[0][0];
                }
                if (personaGuid != Guid.Empty)
                {
                    parameters.Clear(); 
                    var parser1 = new GenericArrayParser() { AsynchParseEnabled = false };
                    var parser2 = new GenericArrayParser() { AsynchParseEnabled = false };
                    parameters.Add("@personaId", personaGuid);

                    SQLDataAccess.Instance.ExecuteReader("AdministradorIdByPersonaId", parser1, parameters);
                    if (parser1.Result.Count > 0)
                    {
                        administradorGuid = (Guid)parser1.Result[0][0];
                        res.Add("UserType", "Administrador");
                        res.Add("administradorGuid", administradorGuid);
                        return res;
                    }
                    SQLDataAccess.Instance.ExecuteReader("ProfesorIdByPersonaId", parser1, parameters);
                    if (parser1.Result.Count > 0)
                    {
                        profesorGuid = (Guid)parser1.Result[0][0];
                        res.Add("UserType", "Profesor");
                        res.Add("profesorGuid", profesorGuid);
                        return res;
                    }
                    SQLDataAccess.Instance.ExecuteReader("TutorIdByPersonaId", parser1, parameters);
                    if (parser1.Result.Count > 0)
                    {
                        tutorGuid = (Guid)parser1.Result[0][0];
                        res.Add("UserType", "tutor");
                        res.Add("tutorGuid", tutorGuid);

                        parameters.Clear();
                        parameters.Add("@tutorId", tutorGuid);
                        SQLDataAccess.Instance.ExecuteReader("StudentIdByTutorId", parser2, parameters);
                        if (parser2.Result.Count > 0)
                        {
                            estudianteGuid = (Guid)parser2.Result[0][0];
                            res.Add("studentGuid", estudianteGuid);
                        }

                        return res;
                    }
                }
                return res;
            }
            catch (Exception ex)
            {
                AsyncState errorState = new AsyncState();
                throw new Exception("DALConstants.ERROR_CREATING_STORE " + ex.Message);
            }
        }
    }
}
