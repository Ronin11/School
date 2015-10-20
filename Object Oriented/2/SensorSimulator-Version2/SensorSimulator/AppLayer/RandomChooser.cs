using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SensorSimulator.AppLayer
{
    public static class RandomChooser
    {
        public static Random randomizer = new Random();

        public static int ChooseInt(int min, int exclusiveMax)
        {
            return randomizer.Next(min, exclusiveMax);
        }
    }
}
