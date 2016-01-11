using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Threading.Tasks;

namespace SensorSimulator.AppLayer
{
    public class RaceGroup : IEnumerable<Racer>
    {
        private List<Racer> racerList;
        private Random randomizer = new Random(DateTime.Now.Millisecond);

        // The following properties characterize the group
        public int Id { get; set; }
        public string Label { get; set; }
        public int StartTime { get; set; }      // Milliseconds after race start time
        public int MinBibNumber { get; set; }
        public int MaxBibNumber { get; set; }

        // The following are only need for the simulations.
        public double AverageSpeed { get; set; }
        public int MinNumberOfRacers { get; set; }
        public int MaxNumberOfRacers { get; set; }
        public List<Racer> Racers { get { return racerList; } }
        public string Gender { get; set; }

        public void Setup()
        {
            racerList = new List<Racer>(); ;

            int numberOfPlayers = RandomChooser.ChooseInt(MinNumberOfRacers, MaxNumberOfRacers + 1);
            for (int i = 0; i < numberOfPlayers; i++)
            {
                double relSpeed = 0.0;
                if (RandomChooser.ChooseInt(0, 10) > 5)
                    relSpeed = 0.0005 - randomizer.NextDouble()*0.001;
                else if (RandomChooser.ChooseInt(0, 10) > 5)
                    relSpeed =0.005 - randomizer.NextDouble()*0.01;
                else if (RandomChooser.ChooseInt(0, 10) > 5)
                    relSpeed = 0.05 - randomizer.NextDouble() * 0.1;

                Racer racer = new Racer()
                    {
                        RaceBibNumber = MinBibNumber + i,
                        LastName = Racer.GetRandomLastName(),
                        FirstName = Racer.GetRandomFirstName(Gender),
                        RelativeSpeed = relSpeed
                    };
                racerList.Add(racer);
            }
        }

        public void Write(StreamWriter groupWriter, StreamWriter racerWriter)
        {
            groupWriter.WriteLine("{0},{1},{2},{3},{4}", Id, Label, StartTime, MinBibNumber, MaxBibNumber);
            foreach (Racer racer in racerList)
                racer.Write(racerWriter, Id);
        }

        public int Count
        {
            get { return racerList.Count; }
        }

        public Racer this[int index]
        {
            get
            {
                Racer result = null;
                if (index >= 0 && index < racerList.Count)
                    result = racerList[index];
                return result;
            }
        }

        public IEnumerator<Racer> GetEnumerator()
        {
            return racerList.GetEnumerator();
        }

        System.Collections.IEnumerator System.Collections.IEnumerable.GetEnumerator()
        {
            return racerList.GetEnumerator();
        }


    }
}
