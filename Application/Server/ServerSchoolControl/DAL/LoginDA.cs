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
        public Guid Login(User user)
        {
            try
            {
                var parser = new GenericArrayParser() { AsynchParseEnabled = false };
                Dictionary<string, object> parameters = new Dictionary<string, object>();
                parameters.Add("@user", user.Usuario);
                parameters.Add("@pass", user.Passw);

                SQLDataAccess.Instance.ExecuteReader("UserLogin", parser, parameters);
                if (parser.Result.Count > 0)
                {
                    return (Guid)parser.Result[0][0];
                }
                else
                {
                    return Guid.Empty;
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
