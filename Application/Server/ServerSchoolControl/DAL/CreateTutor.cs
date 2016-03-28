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
    public class CreateTutor
    {
        public Guid CreateTutorDB(Tutor tut)
        {
            try
            {

                var parser = new GenericArrayParser() { AsynchParseEnabled = false };
                Dictionary<string, object> parameters = new Dictionary<string, object>();
                parameters.Add("@primer_nombre", tut.Nombre1);
                parameters.Add("@segundo_nombre", tut.Nombre2);
                parameters.Add("@apellido_paterno", tut.Apellido_paterno);
                parameters.Add("@apellido_materno", tut.Apellido_materno);
                parameters.Add("@email_principal", tut.Mail1);
                parameters.Add("@email_secundario", tut.Mail2);
                parameters.Add("@telefono", tut.fono);
                parameters.Add("@celular", tut.Cel);
                parameters.Add("@direccion1", tut.Dir1);
                parameters.Add("@direccion2", tut.Dir2);
                parameters.Add("@usuario", tut.Usuario);
                parameters.Add("@passw", tut.Passw);
                parameters.Add("@estado", tut.Estado);


                SQLDataAccess.Instance.ExecuteReader("Tutor_Create", parser, parameters);
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
