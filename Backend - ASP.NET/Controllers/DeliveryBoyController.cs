using OnlineFoodOrderingSystem.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Web.Http;

namespace OnlineFoodOrderingSystem.Controllers
{
    public class DeliveryBoyController : ApiController
    {
        [HttpPost]
        public int LoginDeliveryBoy(string db_email, string db_password)
        {
            using (SqlConnection con = new SqlConnection(AppConnection.GetConnectionString()))
            {
                using (SqlCommand cmd = new SqlCommand("USP_DELIVERYBOYS_LOGIN_DELIVERYBOY", con))
                {
                    con.Open();
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.AddWithValue("db_email", db_email);
                    cmd.Parameters.AddWithValue("db_password", db_password);

                    SqlDataReader sdr = cmd.ExecuteReader();
                    if (sdr.Read())
                    {
                        return Convert.ToInt32(sdr["db_id"].ToString());
                    }
                }
            }
            return 0;
        }

        [HttpGet]
        public IEnumerable<DeliveryBoys> GetDeliveryBoys()
        {
            List<DeliveryBoys> deliveryBoys = new List<DeliveryBoys>();
            using (SqlConnection con = new SqlConnection(AppConnection.GetConnectionString()))
            {
                using (SqlCommand cmd = new SqlCommand("USP_DELIVERYBOYS_VIEW_DELIVERYBOYS", con))
                {
                    con.Open();
                    cmd.CommandType = CommandType.StoredProcedure;

                    SqlDataReader sdr = cmd.ExecuteReader();
                    while (sdr.Read())
                    {
                        DeliveryBoys delivery = new DeliveryBoys();
                        delivery.db_id = Convert.ToInt32(sdr["db_id"].ToString());
                        delivery.db_name = sdr["db_name"].ToString();
                        delivery.db_email = sdr["db_email"].ToString();
                        delivery.db_password = sdr["db_password"].ToString();
                        delivery.db_shiftstart = sdr["db_shiftstart"].ToString();
                        delivery.db_shiftend = sdr["db_shiftend"].ToString();
                        delivery.CreatedDate = sdr["CreatedDate"].ToString();
                        //product.p_pic = sdr[""].ToString();
                        deliveryBoys.Add(delivery);
                    }
                }
            }
            return deliveryBoys;
        }

        [HttpPut]
        public bool PutOrderCompleted(int oa_u_id)
        {
            using (SqlConnection con = new SqlConnection(AppConnection.GetConnectionString()))
            {
                using (SqlCommand cmd = new SqlCommand("USP_ORDERASSIGNS_COMPLETED_ORDER", con))
                {
                    con.Open();
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.AddWithValue("oa_u_id", oa_u_id);

                    int rowEffected = cmd.ExecuteNonQuery();
                    if (rowEffected > 0)
                    {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
