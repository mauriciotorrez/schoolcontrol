using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Threading.Tasks;

namespace Core.DTO
{
    public class Fault
    {
        [DataMember(Name = "GuidFault")]
        public Guid? GuidFault
        {
            get;
            set;
        }

        [DataMember(Name = "GuidEstudiante")]
        public Guid GuidEstudiante
        {
            get;
            set;
        }

        [DataMember(Name= "GuidProfesor")]
        public Guid? GuidProfesor
        {
            get;
            set;
        }

        [DataMember(Name = "GuidAdministrador")]
        public Guid? GuidAdministrador
        {
            get;
            set;
        }

        [DataMember(Name = "GuidTypoFault")]
        public Guid GuidTypoFault
        {
            get;
            set;
        }

        [DataMember(Name = "FechaEnviado")]
        public DateTime FechaEnviado
        {
            get;
            set;
        }

        [DataMember(Name = "Mensage")]
        public string Mensage
        {
            get;
            set;
        }

        [DataMember(Name = "FechaLeido")]
        public DateTime? FechaLeido
        {
            get;
            set;
        }

        [DataMember(Name = "Repuesta")]
        public string Repuesta
        {
            get;
            set;
        }
    }
}
