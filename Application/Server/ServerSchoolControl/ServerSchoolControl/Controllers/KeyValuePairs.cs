using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;

namespace ServerSchoolControl.Controllers
{
    [CollectionDataContract(Namespace = "", Name = "KeyValuePairs")]
    public class KeyValuePairs : List<StringObjectPair>
    {
        public bool Contains(string key)
        {
            return this.Count(x => x.Key == key) > 0;
        }

        public object this[string key]
        {
            get
            {
                return this.First(x => x.Key == key).Value;
            }
        }
    }
}
