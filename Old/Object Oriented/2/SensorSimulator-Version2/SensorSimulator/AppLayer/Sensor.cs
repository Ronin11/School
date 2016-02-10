using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Threading.Tasks;

using Messages;

namespace SensorSimulator.AppLayer
{
    public class Sensor
    {
        public int Id { get; set; }
        public double MileMarker { get; set; }
        public List<RacerStatus> RacerTimes { get; set; }

        public Sensor()
        {
            RacerTimes = new List<RacerStatus>();
        }

        public void Write(StreamWriter writer)
        {
            writer.WriteLine("{0},{1}", Id, MileMarker);
        }
    }
}
