using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Threading.Tasks;

namespace Core
{
    public class CacahuateException : Exception
    {
        public CacahuateException()
            : base()
        { }

        public CacahuateException(string message)
            : base(message)
        { }

        public CacahuateException(string message, Exception innerException)
            : base(message, innerException)
        { }

        public CacahuateException(SerializationInfo info, StreamingContext context)
            : base(info, context)
        { }
    }
}
