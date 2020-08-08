using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace OnlineFoodOrderingSystem.Models
{
    public class OrderDetails
    {
        public int od_id { get; set; }
        public int od_user_id { get; set; }
        public int od_pid { get; set; }
        public int od_qty { get; set; }
        public string od_delivered_loc { get; set; }
        public decimal od_price { get; set; }
        public string U_NAME { get; set; }

        public string P_NAME { get; set; }
    }
}