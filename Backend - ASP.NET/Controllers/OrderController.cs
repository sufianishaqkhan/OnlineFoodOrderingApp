using OnlineFoodOrderingSystem.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Web.Http;

namespace OnlineFoodOrderingSystem.Controllers
{
    public class OrderController : ApiController
    {
        [HttpPost]
        public bool PutOrderAssign(int oa_u_id, int oa_db_id)
        {
            using (SqlConnection con = new SqlConnection(AppConnection.GetConnectionString()))
            {
                using (SqlCommand cmd = new SqlCommand("USP_ORDERASSIGNS_ASSIGN_ORDER", con))
                {
                    con.Open();
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.AddWithValue("oa_u_id", oa_u_id);
                    cmd.Parameters.AddWithValue("oa_db_id", oa_db_id);

                    int rowEffected = cmd.ExecuteNonQuery();
                    if (rowEffected > 0)
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        [HttpGet]
        public IEnumerable<OrderDetails> GetOrderDetails()
        {
            List<OrderDetails> orderDetails = new List<OrderDetails>();
            using (SqlConnection con = new SqlConnection(AppConnection.GetConnectionString()))
            {
                using (SqlCommand cmd = new SqlCommand("USP_ORDERDETAILS_FULL_ORDER_DETAILS", con))
                {
                    con.Open();
                    cmd.CommandType = CommandType.StoredProcedure;

                    SqlDataReader sdr = cmd.ExecuteReader();
                    while (sdr.Read())
                    {
                        OrderDetails order = new OrderDetails();
                        order.od_id = Convert.ToInt32(sdr["od_id"].ToString());
                        order.U_NAME = sdr["u_name"].ToString();
                        order.P_NAME = sdr["p_name"].ToString();
                        order.od_qty = Convert.ToInt32(sdr["od_qty"].ToString());
                        order.od_delivered_loc = sdr["od_delivered_loc"].ToString();
                        order.od_price = Convert.ToDecimal(sdr["od_price"].ToString());
                        orderDetails.Add(order);
                    }
                }
            }
            return orderDetails;
        }

        [HttpGet]
        public List<OrderAssigns> GetOrderAssignDetails()
        {
            List<OrderAssigns> orderDetails = new List<OrderAssigns>();
            using (SqlConnection con = new SqlConnection(AppConnection.GetConnectionString()))
            {
                using (SqlCommand cmd = new SqlCommand("USP_ORDERASSIGNS_DISP_USER_ORDER", con))
                {
                    con.Open();
                    cmd.CommandType = CommandType.StoredProcedure;

                    SqlDataReader sdr = cmd.ExecuteReader();
                    while (sdr.Read())
                    {
                        OrderAssigns order = new OrderAssigns();
                        order.oa_id = Convert.ToInt32(sdr["oa_id"].ToString());
                        order.oa_u_id = Convert.ToInt32(sdr["oa_u_id"].ToString());
                        order.u_name = sdr["u_name"].ToString();
                        order.p_name = sdr["p_name"].ToString();
                        order.od_qty = Convert.ToInt32(sdr["od_qty"].ToString());
                        order.od_price = Convert.ToDecimal(sdr["od_price"].ToString());
                        var chk = sdr["oa_db_id"].ToString();
                        if (chk == "")
                        {
                            order.oa_db_id = 0;
                        }
                        else
                            order.oa_db_id = Convert.ToInt32(sdr["oa_db_id"].ToString());
                        order.oa_status = sdr["oa_status"].ToString();
                        orderDetails.Add(order);
                    }
                }
            }
            return orderDetails;
        }
    }
}
