using Core.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Utils;

namespace Core.Parser
{
    public class FaultParser : DTOParser<Fault>, IDTOBaseParser
    {
        public override void ParseWorker()
        {
            while (Reader.Read())
            {
                var row = new Fault
                {
                    GuidFault = Reader.GetGuid("id_disciplina"),
                    GuidEstudiante = Reader.GetGuid("id_estudiante"),
                    GuidProfesor = Reader.GetGuidNullable("id_profesor"),
                    GuidAdministrador = Reader.GetGuidNullable("id_administrador"),
                    GuidTypoFault = Reader.GetGuid("id_tipo_indisciplina"),
                    FechaEnviado = Reader.GetDateTime("fecha_enviado"),
                    Mensage = Reader.GetStringNullable("mensaje_enviado"),
                    Repuesta = Reader.GetStringNullable("respuesta_padre"),
                    FechaLeido = Reader.GetDateTimeNullable("fecha_leido")
                };
                Result.Add(row);
            }

        }
    }
}
