using Core.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Core.Parser
{
    public class GenericArrayParser : DTOParser<object[]>, IDTOBaseParser
    {
        public override void ParseWorker()
        {
            while (Reader.Read())
            {
                object[] values = new object[Reader.FieldCount];
                Reader.GetValues(values);
                Result.Add(values);
            }
        }
    }
}
