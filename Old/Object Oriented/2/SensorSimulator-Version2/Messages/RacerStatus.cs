using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.IO;
using System.Threading.Tasks;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;

namespace Messages
{
    [DataContract]
    public class RacerStatus
    {
        [DataMember]
        public int SensorId { get; set; }
        [DataMember]
        public int RacerBibNumber { get; set; }
        [DataMember]
        public int Timestamp { get; set; }

        public byte[] Encode()
        {
            DataContractJsonSerializer serializer = new DataContractJsonSerializer(typeof(RacerStatus));

            MemoryStream mstream = new MemoryStream();
            serializer.WriteObject(mstream, this);

            return mstream.ToArray();
        }

        public static RacerStatus Decode(byte[] bytes)
        {

            MemoryStream mstream = new MemoryStream(bytes);
            DataContractJsonSerializer serializer = new DataContractJsonSerializer(typeof(RacerStatus));
            RacerStatus result = (RacerStatus) serializer.ReadObject(mstream);

            return result;
        }
    }
}
