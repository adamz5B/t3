using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using Newtonsoft.Json;

using T3Drivers.Models;
using T3Drivers.Data;
using System.Data.Common;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace T3Drivers.Controllers
{
    /// <summary>
    /// API controler for Dirver model
    /// </summary>
    [Route("api/[controller]")]
    [ApiController]
    public class DriverController : ControllerBase
    {
        private readonly DriversContext _dbContext;
        public DriverController(DriversContext context)
        {
            _dbContext = context;
        }
        // GET: api/<DriverController>
        [HttpGet]
        public JsonResult Get()
        {             
            return new JsonResult(_dbContext.Drivers.ToList());
        }

        // GET api/<DriverController>/5
        [HttpGet("{id:int}")]
        public JsonResult Get(int id)
        {
            return new JsonResult(_dbContext.Drivers.FirstOrDefault(d => d.Id == id));
        }

        // POST api/<DriverController>
        [HttpPost]
        public async Task<JsonResult> Post([FromBody] Driver driver)
        {
            try
            {
                _dbContext.Add(driver);
                await _dbContext.SaveChangesAsync();
                return new JsonResult(new { res = "OK" });
            }
            catch(DbException dex)
            {
                return new JsonResult(new { res = "Error", msg = dex.Message });
            }
        }

        // PUT api/<DriverController>/5
        [HttpPut("{id:int}")]
        public JsonResult Put(int id, [FromBody] Driver driver)
        {
            if(driver == null)
            {
                return new JsonResult(new { res = "Error", msg = "Null data payload" });
            }
            try { 
                var toEditDriver = _dbContext.Drivers.FirstOrDefault(d => d.Id == id);
                _dbContext.Entry(toEditDriver).CurrentValues.SetValues(driver);
                _dbContext.SaveChanges();
                return new JsonResult(new { res = "OK" });
            }
            catch (DbException dex)
            {
                return new JsonResult(new { res = "Error", msg = dex.Message });
            }            
        }

        // DELETE api/<DriverController>/5
        [HttpDelete("{id:int}")]
        public JsonResult Delete(int id)
        {            
            try
            {
                var toDeleteDriver = _dbContext.Drivers.FirstOrDefault(d => d.Id == id);
                _dbContext.Drivers.Remove(toDeleteDriver);
                _dbContext.SaveChanges();
                return new JsonResult(new { res = "OK" });
            }
            catch (DbException dex)
            {
                return new JsonResult(new { res = "Error", msg = dex.Message });
            }
        }
    }
}
