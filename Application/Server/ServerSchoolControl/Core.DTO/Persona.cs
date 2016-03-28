using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Threading.Tasks;

namespace Core.DTO
{
    public class Persona
    {
        [DataMember(Name = "Nombre1")]
        public string Nombre1
        {
            get;
            set;
        }

        [DataMember(Name = "Nombre2")]
        public string Nombre2
        {
            get;
            set;
        }

        [DataMember(Name = "Apellido")]
        public string Apellido
        {
            get;
            set;
        }

        [DataMember(Name = "Mail1")]
        public string Mail1
        {
            get;
            set;
        }

        [DataMember(Name = "Dir1")]
        public string Dir1
        {
            get;
            set;
        }

        [DataMember(Name = "Cel")]
        public string Cel
        {
            get;
            set;
        }

    }
}
