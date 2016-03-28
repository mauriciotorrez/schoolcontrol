using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Runtime.Serialization;

namespace ServerSchoolControl.Controllers
{
    [DataContract(Namespace = "", Name = "MsgHeader")]
    public class MsgHeader
    {
        public MsgHeader()
        {

        }

        [DataMember(Name = "MessageType")]
        public string MessageType
        {
            get;
            set;
        }

        [DataMember(Name = "DeviceId")]
        public string DeviceId
        {
            get;
            set;
        }

        [DataMember(Name = "UserGuid")]
        public Guid UserGuid
        {
            get;
            set;
        }

        [DataMember(Name = "ClientType")]
        public int ClientType
        {
            get;
            set;
        }

    }
}