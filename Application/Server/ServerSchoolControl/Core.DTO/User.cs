using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Threading.Tasks;

namespace Core.DTO
{
    public class User
    {
        [DataMember(Name = "Usuario")]
        public string Usuario
        {
            get;
            set;
        }

        [DataMember(Name = "Passw")]
        public string Passw
        {
            get;
            set;
        }
    }
}
