using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Runtime.Serialization;

namespace ServerSchoolControl.Controllers
{
    //[CollectionDataContract(Namespace = "", Name = "body")]
    [DataContract(Namespace = "", Name = "Body")]
    public class MsgBody : List<KeyValuePairs>
    {

    }
}