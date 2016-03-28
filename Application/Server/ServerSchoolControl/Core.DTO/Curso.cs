using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Threading.Tasks;

namespace Core.DTO
{
    public class Curso
    {
        [DataMember(Name = "GuidCurso")]
        public Guid GuidCurso
        {
            get;
            set;
        }

        [DataMember(Name = "NomCurso")]
        public string NomCurso
        {
            get;
            set;
        }

        [DataMember(Name = "GradoCurso")]
        public string GradoCurso
        {
            get;
            set;
        }

        [DataMember(Name = "Nivel")]
        public int Nivel
        {
            get;
            set;
        }
    }
}
