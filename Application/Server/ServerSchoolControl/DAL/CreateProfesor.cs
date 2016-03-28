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
    public class CreateProfesor
    {
        public Guid CreateProfesorDB(Profesor prof)
        {
            try
            {

                var parser = new GenericArrayParser() { AsynchParseEnabled = false };
                Dictionary<string, object> parameters = new Dictionary<string, object>();
                parameters.Add("@primer_nombre", prof.Nombre1);
                parameters.Add("@segundo_nombre", prof.Nombre2);
                parameters.Add("@apellido_paterno", prof.Apellido_paterno);
                parameters.Add("@apellido_materno", prof.Apellido_materno);
                parameters.Add("@email_principal", prof.Mail1);
                parameters.Add("@email_secundario", prof.Mail2);
                parameters.Add("@telefono", prof.fono);
                parameters.Add("@celular", prof.Cel);
                parameters.Add("@direccion1", prof.Dir1);
                parameters.Add("@direccion2", prof.Dir2);
                parameters.Add("@usuario", prof.Usuario);
                parameters.Add("@passw", prof.Passw);
                parameters.Add("@estado", prof.Estado);


                SQLDataAccess.Instance.ExecuteReader("Profesor_Create", parser, parameters);
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
    }
}
