using Core.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Utils;

namespace Core.Parser
{
    public class CursoParser : DTOParser<Curso>, IDTOBaseParser
    {
        public override void ParseWorker()
        {
            while (Reader.Read())
            {
                var row = new Curso
                {
                    GuidCurso = Reader.GetGuid("id_curso"),
                    NomCurso = Reader.GetString("nombre"),
                    GradoCurso = Reader.GetString("grado"),
                    Nivel = Reader.GetInt32("nivel")
                };
                Result.Add(row);
            }
        }
    }
}
