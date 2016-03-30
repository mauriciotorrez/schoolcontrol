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
    public class AsistenciaDA
    {
        public Guid SaveAsistencia(Asistencia asistencia)
        {
            try
            {

                var parser = new GenericArrayParser() { AsynchParseEnabled = false };

                //var parser = new GenericArrayParser() { AsynchParseEnabled = false };
                Dictionary<string, object> parameters = new Dictionary<string, object>();
                parameters.Add("@estudianteId", asistencia.GuidEstudiante);
                parameters.Add("@falta", asistencia.Falta);
                parameters.Add("@retrazo", asistencia.Retrazo);
                parameters.Add("@justificarion", asistencia.Justificacion);
                parameters.Add("@fecha", asistencia.Fecha);
                parameters.Add("@fechaEnviado", asistencia.FechaEnviado);

                SQLDataAccess.Instance.ExecuteReader("Save_Asistencia", parser, parameters);
                if (parser.Result.Count > 0)
                {
                    return (Guid)parser.Result[0][0];
                }
                else
                {
                    AsyncState errorState = new AsyncState();
                    throw new Exception("DALConstants.ERROR_CREATING_STORE   no data");
                }
            }
            catch (Exception ex)
            {
                AsyncState errorState = new AsyncState();
                throw new Exception("DALConstants.ERROR_CREATING_STORE  " + ex.Message);
            }
        }
    }
}
