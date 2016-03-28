using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Threading.Tasks;

namespace Core.DTO
{
    public class Administrador
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

        [DataMember(Name = "Apellido_paterno")]
        public string Apellido_paterno
        {
            get;
            set;
        }

        [DataMember(Name = "Apellido_materno")]
        public string Apellido_materno
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

        [DataMember(Name = "Mail2")]
        public string Mail2
        {
            get;
            set;
        }

        [DataMember(Name = "fono")]
        public string fono
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

        [DataMember(Name = "Dir1")]
        public string Dir1
        {
            get;
            set;
        }

        [DataMember(Name = "Dir2")]
        public string Dir2
        {
            get;
            set;
        }

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

        [DataMember(Name = "Estado")]
        public int Estado
        {
            get;
            set;
        }

        [DataMember(Name = "cargo")]
        public string cargo
        {
            get;
            set;
        }
    }
}
