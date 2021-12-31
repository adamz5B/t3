using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace T3Drivers.Models
{
    /// <summary>
    /// Model class for taxi drivers.
    /// </summary>
    public class Driver
    {
        public int Id { get; set; }
        public String Name { get; set; }
        public String Surname { get; set; }
        public String NameFurigana { get; set; }
        public String SurnameFurigana { get; set; }
        public String Email { get; set; }
        public String VehicleType { get; set; }
        public double BaseFarePrice { get; set; }
        public double BaseFareDistance { get; set; }
    }
}
