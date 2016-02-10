using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Threading;
using System.Threading.Tasks;
using System.Net;
using System.Net.Sockets;

using SensorSimulator;
using Messages;

namespace SensorSimulator.AppLayer
{
    public class Simlulator
    {
        private List<Sensor> sensors { get; set; }
        private List<RaceGroup> groups { get; set; }
        private UdpClient udpClient { get; set; }
        private List<RacerStatus> messagesToSend { get; set; }

        public IPEndPoint ServerEndPoint { get; set; }
        public int NumberOfSensors { get; set; }
        public int LengthOfRace { get; set; }

        public void Setup()
        {
            SetupDefaults();

            SetupSensors();

            CreateRaceGroups();

            foreach (RaceGroup group in groups)
                group.Setup();

            SetupCheaters();

            ComputeRaceTimes();

            messagesToSend.Sort(delegate(RacerStatus x, RacerStatus y)
            {
                if (x.Timestamp < y.Timestamp) return -1;
                else if (x.Timestamp > y.Timestamp) return 1;
                return 0;
            });
        }

        public void Export(string sensorFileName, string groupFileName, string racerFileName)
        {
            StreamWriter sensorWriter = new StreamWriter(sensorFileName);
            StreamWriter groupWriter = new StreamWriter(groupFileName);
            StreamWriter racerWriter = new StreamWriter(racerFileName);

            foreach (Sensor sensor in sensors)
                sensor.Write(sensorWriter);

            foreach (RaceGroup group in groups)
                group.Write(groupWriter, racerWriter);

            groupWriter.Close();
            racerWriter.Close();
            sensorWriter.Close();
        }

        public void SendData()
        {
            // Create socket
            udpClient = new UdpClient();

            int lastTime = messagesToSend[0].Timestamp;
            for (int index = 0; index < messagesToSend.Count; index++)
            {
                RacerStatus msg = messagesToSend[index];

                int deltaTime = Math.Max(0, msg.Timestamp - lastTime) / 300;
                // Console.WriteLine("Sleep {0} ms and then send msg: Bib #={1}, Sensor Id={2}, Time={3}", deltaTime, msg.RacerBibNumber, msg.SensorId, msg.Timestamp);
                Thread.Sleep(Convert.ToInt32(deltaTime));

                byte[] messageByte = msg.Encode();
                udpClient.Send(messageByte, messageByte.Length, ServerEndPoint);

                lastTime = msg.Timestamp;
            }
        }

        #region Private Methods
        private void SetupDefaults()
        {
            // Setup defaults
            if (NumberOfSensors <= 0)
                NumberOfSensors = 20;
            if (LengthOfRace <= 0)
                LengthOfRace = 100;
        }

        private void SetupSensors()
        {
            // Setup sensors
            sensors = new List<Sensor>();
            for (int i = 0; i < NumberOfSensors; i++)
            {
                double mileMarker = (i + 1) * Convert.ToDouble(LengthOfRace) / Convert.ToDouble(NumberOfSensors);
                Sensor sensor = new Sensor() { Id = i, MileMarker = mileMarker };
                sensors.Add(sensor);
            }
        }

        private void CreateRaceGroups()
        {
            // Create race groups
            groups = new List<RaceGroup>()
            {
                new RaceGroup() { Id=1, Label="Mens Cat 1-2", MinBibNumber=1, MaxBibNumber=99, StartTime=0, AverageSpeed=25.0, MinNumberOfRacers = 20, MaxNumberOfRacers=50, Gender="M" },
                new RaceGroup() { Id=2, Label="Womens Cat 1-3", MinBibNumber=100, MaxBibNumber=199, StartTime=5*60*1000, AverageSpeed=25.0, MinNumberOfRacers = 20, MaxNumberOfRacers=50, Gender="F" },
                new RaceGroup() { Id=3, Label="Womens Cat 4", MinBibNumber=200, MaxBibNumber=299, StartTime=10*60*1000, AverageSpeed=25.0, MinNumberOfRacers = 20, MaxNumberOfRacers=50, Gender="F" },
                new RaceGroup() { Id=4, Label="Mens Cat 3", MinBibNumber=300, MaxBibNumber=399, StartTime=15*60*1000, AverageSpeed=24.0, MinNumberOfRacers = 20, MaxNumberOfRacers=50, Gender="M" },
                new RaceGroup() { Id=5, Label="Mens Cat 4", MinBibNumber=400, MaxBibNumber=499, StartTime=20*60*1000, AverageSpeed=22.0, MinNumberOfRacers = 20, MaxNumberOfRacers=50, Gender="M" },
                new RaceGroup() { Id=6, Label="Mens Cat 5A", MinBibNumber=5000, MaxBibNumber=5099, StartTime=25*60*1000, AverageSpeed=20.0, MinNumberOfRacers = 40, MaxNumberOfRacers=60, Gender="M" },
                new RaceGroup() { Id=7, Label="Mens Cat 5B", MinBibNumber=5100, MaxBibNumber=5199, StartTime=30*60*1000, AverageSpeed=20.0, MinNumberOfRacers = 40, MaxNumberOfRacers=60, Gender="M" },
                new RaceGroup() { Id=8, Label="Mens Cat 5C", MinBibNumber=5200, MaxBibNumber=5299, StartTime=35*60*1000, AverageSpeed=20.0, MinNumberOfRacers = 40, MaxNumberOfRacers=60, Gender="M" },
            };
        }

        private void SetupCheaters()
        {
            for (int raceGroupA = 0; raceGroupA < groups.Count - 1; raceGroupA++)
            {
                int raceGroupB = raceGroupA + 1;
                int cheaterIndex1 = RandomChooser.ChooseInt(0, groups[raceGroupA].Count);
                Racer cheater1 = groups[raceGroupA][cheaterIndex1];
                int cheaterIndex2 = RandomChooser.ChooseInt(0, groups[raceGroupB].Count);
                Racer cheater2 = groups[raceGroupB][cheaterIndex2];

                cheater1.WillTryToCheatWith = cheater2;
                cheater2.WillTryToCheatWith = cheater1;
            }
        }

        private void ComputeRaceTimes()
        {
            messagesToSend = new List<RacerStatus>();
            double previousMilageMarker = 0;
            for (int sensorIndex = 0; sensorIndex < sensors.Count; sensorIndex++)
            {
                Sensor sensor = sensors[sensorIndex];
                double deltaMilage = sensor.MileMarker - previousMilageMarker;

                ComputerRacerTimes(deltaMilage);
                ConvergeCheaters();
                CreateRaceStatusObjects(sensor);

                previousMilageMarker = sensor.MileMarker;
            }
        }

        private void ComputerRacerTimes(double deltaMilage)
        {
            foreach (RaceGroup group in groups)
            {
                for (int i = 0; i < group.Count; i++)
                {
                    Racer racer = group[i];
                    if (racer.Time == 0)
                        racer.Time = group.StartTime;

                    double speed = racer.RelativeSpeed + group.AverageSpeed;
                    racer.Time += Convert.ToInt32(60 * 60 * 1000 * deltaMilage / speed);
                }
            }
        }

        private void ConvergeCheaters()
        {
            foreach (RaceGroup group in groups)
            {
                foreach (Racer racer in group)
                {
                    if (racer.WillTryToCheatWith != null && racer.Time > racer.WillTryToCheatWith.Time)
                    {
                        int delta = racer.Time - racer.WillTryToCheatWith.Time;

                        // Simulate flat tire
                        if (delta >= 60000 && RandomChooser.ChooseInt(0,100)>50)
                            racer.Time = racer.WillTryToCheatWith.Time + RandomChooser.ChooseInt(0,60000);
                        else
                        {
                            delta = delta / 4;
                            racer.Time -= delta;
                            racer.WillTryToCheatWith.Time += delta;
                        }
                    }
                }
            }
        }

        private void CreateRaceStatusObjects(Sensor sensor)
        {
            foreach (RaceGroup group in groups)
            {
                foreach (Racer racer in group)
                {
                    RacerStatus status = new RacerStatus() { SensorId = sensor.Id, RacerBibNumber = racer.RaceBibNumber, Timestamp = racer.Time };
                    sensor.RacerTimes.Add(status);
                    messagesToSend.Add(status);
                }
            }
        }
        #endregion

    }
}
