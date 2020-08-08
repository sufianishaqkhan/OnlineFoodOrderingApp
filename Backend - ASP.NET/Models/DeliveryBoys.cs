using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace OnlineFoodOrderingSystem.Models
{
    public class DeliveryBoys
    {
        public int db_id { get; set; }
        public string db_name { get; set; }
        public string db_email { get; set; }
        public string db_password { get; set; }
        public string db_shiftstart { get; set; }
        public string db_shiftend { get; set; }
        public string CreatedDate { get; set; }

    }
}