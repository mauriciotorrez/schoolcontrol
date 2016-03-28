using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Threading.Tasks;

namespace Core.DTO
{
    public class Asistencia
    {
        [DataMember(Name = "GuidAsistencia")]
        public Guid GuidAsistencia
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

        [DataMember(Name = "Fecha")]
        public DateTime Fecha
        {
            get;
            set;
        }

        [DataMember(Name = "Falta")]
        public int Falta
        {
            get;
            set;
        }

        [DataMember(Name = "Retrazo")]
        public int Retrazo
        {
            get;
            set;
        }

        [DataMember(Name = "Justificacion")]
        public int Justificacion
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
    }
}
