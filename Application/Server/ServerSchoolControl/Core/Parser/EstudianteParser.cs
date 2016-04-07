using Core.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Utils;

namespace Core.Parser
{
    public class EstudianteParser : DTOParser<Estudiante>, IDTOBaseParser
    {

        public override void ParseWorker()
        {
            while (Reader.Read())
            {
                var row = new Estudiante
                {
                    GuidEstudiante = Reader.GetGuid("id_estudiante"),
                    GuidPersona = Reader.GetGuid("id_persona"),
                    Nombre1 = Reader.GetString("nombre1"),
                    Nombre2 = Reader.GetStringNullable("nombre2"),
                    Apellido_paterno = Reader.GetString("apellido_paterno"),
                    Apellido_materno = Reader.GetStringNullable("apellido_materno"),
                    Mail1 = Reader.GetString("email1"),
                    Mail2 = Reader.GetStringNullable("emal2"),
                    fono = Reader.GetStringNullable("fono"),
                    Cel = Reader.GetString("cel"),
                    Dir1 = Reader.GetString("dir1"),
                    Dir2 = Reader.GetStringNullable("dir2")
                };
                Result.Add(row);
            }
        }

    }
}
