using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using T3Drivers.Models;

namespace T3Drivers.Data
{
    public class DriversContext : DbContext
    {
        private static readonly string DRIVER_TABLE_NAME = "Driver";
        public DriversContext(DbContextOptions<DriversContext> options) : base(options) { }
        public DbSet<Driver> Drivers { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Driver>().ToTable(DRIVER_TABLE_NAME);
        }
    }
}
