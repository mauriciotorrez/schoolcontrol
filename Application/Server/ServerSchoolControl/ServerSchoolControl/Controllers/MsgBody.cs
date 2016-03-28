using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Runtime.Serialization;

namespace ServerSchoolControl.Controllers
{
    //[CollectionDataContract(Namespace = "", Name = "body")]
    [DataContract(Namespace = "", Name = "Body")]
    public class MsgBody 
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