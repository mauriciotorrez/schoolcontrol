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
    public class CreateEstudiante
    {
        public Guid CreateEstudianteDB(Estudiante per, string nombreCurso, string gradoCurso)
        {
            try
            {

                var parser = new GenericArrayParser() { AsynchParseEnabled = false };
                Dictionary<string, object> parameters = new Dictionary<string, object>();
                parameters.Add("@primer_nombre", per.Nombre1);
                parameters.Add("@segundo_nombre", per.Nombre2);
                parameters.Add("@apellido_paterno", per.Apellido_paterno);
                parameters.Add("@apellido_materno", per.Apellido_materno);
                parameters.Add("@email_principal", per.Mail1);
                parameters.Add("@email_secundario", per.Mail2);
                parameters.Add("@telefono", per.fono);
                parameters.Add("@celular", per.Cel);
                parameters.Add("@direccion1", per.Dir1);
                parameters.Add("@direccion2", per.Dir2);

                parameters.Add("@usuario", per.Usuario);
                parameters.Add("@passw", per.Passw);
                parameters.Add("@estado", per.Estado);


                //C9CAF86B-F114-4FDA-8973-9D0DE319A956
                parameters.Add("@CursoGuid", GetCursoGUID(nombreCurso, gradoCurso));


                SQLDataAccess.Instance.ExecuteReader("Estudiante_Create", parser, parameters);
                if (parser.Result.Count > 0)
                {
                    return (Guid)parser.Result[0][0];
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

        public Guid GetCursoGUID(string nombre, string grado)
        {
            try
            {


                var parser = new GenericArrayParser() { AsynchParseEnabled = false };

                Dictionary<string, object> parameters = new Dictionary<string, object>();
                parameters.Add("@curso", nombre);
                parameters.Add("@grado", grado);


                SQLDataAccess.Instance.ExecuteReader("GetCursoGuid", parser, parameters);
                if (parser.Result.Count > 0)
                {
                    return (Guid)parser.Result[0][0];
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

        public List<Estudiante> Estudiante_GetByCourseId(Guid cursoId)
        {
            List<Estudiante> result = new List<Estudiante>();
            var parser = new EstudianteParser() { AsynchParseEnabled = false };
            try
            {
                Dictionary<string, object> parameters = new Dictionary<string, object>();
                parameters.Add("@curso_GUID", cursoId);
                SQLDataAccess.Instance.ExecuteReader("Estudiante_GetByCourseId", parser, parameters);

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
