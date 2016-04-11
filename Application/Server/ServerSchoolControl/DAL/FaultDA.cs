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
    public class FaultDA
    {

        public Guid SaveFault(Fault fault)
        {
            try
            {

                var parser = new GenericArrayParser() { AsynchParseEnabled = false };

                //var parser = new GenericArrayParser() { AsynchParseEnabled = false };
                Dictionary<string, object> parameters = new Dictionary<string, object>();
                parameters.Add("@estudianteId", fault.GuidEstudiante);
                parameters.Add("@administradorId", fault.GuidAdministrador);
                parameters.Add("@tipoIndisciplinaId", fault.GuidTypoFault);
                parameters.Add("@ProfesorId", fault.GuidProfesor);
                parameters.Add("@fechaEnviado", fault.FechaEnviado);
                parameters.Add("@mensaje", fault.Mensage);


                SQLDataAccess.Instance.ExecuteReader("Save_Disciplina", parser, parameters);
                if (parser.Result.Count > 0)
                {
                    return (Guid)parser.Result[0][0];
                }
                else
                {
                    AsyncState errorState = new AsyncState();
                    throw new Exception("DALConstants.ERROR_CREATING_STORE no data");
                }
            }
            catch (Exception ex)
            {

                AsyncState errorState = new AsyncState();
                throw new Exception("DALConstants.ERROR_CREATING_STORE " + ex.Message);

            }
        }

        public List<Fault> Fault_GetByUserId(Guid userId)
        {
            List<Fault> result = new List<Fault>();
            var parser = new FaultParser() { AsynchParseEnabled = false };
            try
            {
                Dictionary<string, object> parameters = new Dictionary<string, object>();
                parameters.Add("@user_GUID", userId);
                SQLDataAccess.Instance.ExecuteReader("Faul_GetByUserId", parser, parameters);

                return parser.Result;

            }
            catch (Exception ex)
            {
                AsyncState errorState = new AsyncState();
                throw new Exception("DALConstants.ERROR_CREATING_STORE  " + ex.Message);
            }
        }

        public List<Fault> Fault_GePendingtByUserId(Guid userId)
        {
            List<Fault> result = new List<Fault>();
            var parser = new FaultParser() { AsynchParseEnabled = false };
            try
            {
                Dictionary<string, object> parameters = new Dictionary<string, object>();
                parameters.Add("@user_GUID", userId);
                SQLDataAccess.Instance.ExecuteReader("Faul_GetPendingByUserId", parser, parameters);

                return parser.Result;

            }
            catch (Exception ex)
            {
                AsyncState errorState = new AsyncState();
                throw new Exception("DALConstants.ERROR_CREATING_STORE  " + ex.Message);
            }
        }
    }
}
