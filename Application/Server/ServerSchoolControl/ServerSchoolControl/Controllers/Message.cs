using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Web;

namespace ServerSchoolControl.Controllers
{
    [KnownType(typeof(Message))]
    [DataContract(Namespace = "", Name = "Message")]
    public class Message
    {

        public Message()
        {
            Header = new MsgHeader();
            Body = new MsgBody();
        }

        [DataMember(Name = "Header")]
        public MsgHeader Header
        {
            get;
            set;
        }

        [DataMember(Name = "Body")]
        public MsgBody Body
        {
            get;
            set;
        }
       
    }
}