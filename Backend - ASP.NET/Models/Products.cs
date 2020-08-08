using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace OnlineFoodOrderingSystem.Models
{
    public class Products
    {
        public int p_id { get; set; }
        public string p_name { get; set; }
        public string p_category { get; set; }
        public int p_categoryid { get; set; }
        public decimal p_unitprice { get; set; }
        public int p_qty { get; set; }
        public string p_weight { get; set; }
        public string p_pic { get; set; }
        public string CreatedDate { get; set; }

    }
}