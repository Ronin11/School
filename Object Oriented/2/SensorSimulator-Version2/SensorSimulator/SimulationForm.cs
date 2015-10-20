using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Net;

using SensorSimulator.AppLayer;

namespace SensorSimulator
{
    public partial class SimulationForm : Form
    {
        private Simlulator simulator;

        public SimulationForm()
        {
            InitializeComponent();
        }

        private IPEndPoint ParseIpEndPoint(string hostAndPort)
        {
            IPEndPoint ep = null;
            string host = string.Empty;
            int port = 0;
            if (!string.IsNullOrWhiteSpace(hostAndPort))
            {
                string[] tmp = hostAndPort.Split(':');
                if (tmp.Length == 1)
                    host = hostAndPort;
                else if (tmp.Length >= 2)
                {
                    host = tmp[0].Trim();
                    Int32.TryParse(tmp[1].Trim(), out port);
                }
            }

            if (!string.IsNullOrWhiteSpace(host))
            {
                IPAddress ipAddress = LookupAddress(host);
                ep = new IPEndPoint(ipAddress, port);
            }
            return ep;
        }

        private static IPAddress LookupAddress(string host)
        {
            IPAddress result = null;
            if (!string.IsNullOrWhiteSpace(host))
            {
                IPAddress[] addressList = Dns.GetHostAddresses(host);
                for (int i = 0; i < addressList.Length && result == null; i++)
                    if (addressList[i].AddressFamily == System.Net.Sockets.AddressFamily.InterNetwork)
                        result = addressList[i];
            }
            return result;
        }

        private void setupButton_Click(object sender, EventArgs e)
        {
            int myLengthOfRace = 0;
            int myNumberOfSensors = 0;
            IPEndPoint myServerEndPoint = ParseIpEndPoint(serverEndPoint.Text);
            if (!Int32.TryParse(numberOfSensors.Text, out myNumberOfSensors))
                MessageBox.Show("Invalid number of sensors");
            else if (myNumberOfSensors<=1)
                MessageBox.Show("The number of sensors needs to greater than 1");
            else if (!Int32.TryParse(lengthOfRace.Text, out myLengthOfRace))
                MessageBox.Show("Invalid race length");
            else if (myLengthOfRace<=1)
                MessageBox.Show("The race needs to greater than 1");

            simulator = new Simlulator() { NumberOfSensors = myNumberOfSensors, LengthOfRace = myLengthOfRace, ServerEndPoint = myServerEndPoint };
            simulator.Setup();
            MessageBox.Show("Simulator Setup Complete");
        }

        private void exportButton_Click(object sender, EventArgs e)
        {
            simulator.Export("Sensors.csv", "Groups.csv", "Racers.csv");
            MessageBox.Show("Export to Sensor.csv, Group.csv and Racers.csv Complete");
        }

        private void startStopButton_Click(object sender, EventArgs e)
        {
            simulator.SendData();
            MessageBox.Show("Simulation Done");
        }

    }
}
