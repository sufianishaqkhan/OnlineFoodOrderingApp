using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace OnlineFoodOrderingSystem.Models
{
    public class OrderAssigns
    {
        public int oa_id { get; set; }
        public string u_name { get; set; }
        public string p_name { get; set; }
        public int od_qty { get; set; }
        public decimal od_price { get; set; }
        public int oa_db_id { get; set; }
        public string oa_status { get; set; }
    }
}