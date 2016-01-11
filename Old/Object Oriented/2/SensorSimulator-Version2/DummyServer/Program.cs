using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.Net.Sockets;

using Messages;

namespace DummyServer
{
    class Program
    {
        static void Main(string[] args)
        {
            // This dummy server receives RacerStatus Messages from the simulator
            // and simply prints them to the screen

            DataReceiver receiver = new DataReceiver();
            receiver.Start();

            // TODO: Launch my control interface here.

            // For now just read a line from the console 
            string tmp = Console.ReadLine();
            receiver.Stop();
        }


    }
}
