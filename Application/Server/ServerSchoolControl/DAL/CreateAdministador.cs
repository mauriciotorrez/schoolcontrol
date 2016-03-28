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
    public class CreateAdministador
    {
        public Guid CreateAdministradorDB(Administrador admin)
        {
            try
            {

                var parser = new GenericArrayParser() { AsynchParseEnabled = false };
                Dictionary<string, object> parameters = new Dictionary<string, object>();
                parameters.Add("@primer_nombre", admin.Nombre1);
                parameters.Add("@segundo_nombre", admin.Nombre2);
                parameters.Add("@apellido_paterno", admin.Apellido_paterno);
                parameters.Add("@apellido_materno", admin.Apellido_materno);
                parameters.Add("@email_principal", admin.Mail1);
                parameters.Add("@email_secundario", admin.Mail2);
                parameters.Add("@telefono", admin.fono);
                parameters.Add("@celular", admin.Cel);
                parameters.Add("@direccion1", admin.Dir1);
                parameters.Add("@direccion2", admin.Dir2);
                parameters.Add("@usuario", admin.Usuario);
                parameters.Add("@passw", admin.Passw);
                parameters.Add("@estado", admin.Estado);

                parameters.Add("@cargo", admin.cargo);



                SQLDataAccess.Instance.ExecuteReader("Administrador_Create", parser, parameters);
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
