using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;

namespace ServerSchoolControl.Controllers
{
    [DataContract(Namespace = "", Name = "StringObjectPair")]
    public class StringObjectPair
    {
        [DataMember(Name = "Key")]
        public string Key
        {
            get;
            set;
        }

        [DataMember(Name = "Value")]
        public object Value
        {
            get;
            set;
        }
    }
}
