using OnlineFoodOrderingSystem.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Web.Http;

namespace OnlineFoodOrderingSystem.Controllers
{
    public class AdminController : ApiController
    {
        [HttpPost]
        public int AdminLogin(string email, string password)
        {
            using (SqlConnection con = new SqlConnection(AppConnection.GetConnectionString()))
            {
                using (SqlCommand cmd = new SqlCommand("USP_ADMIN_LOGIN_ADMIN", con))
                {
                    con.Open();
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.AddWithValue("a_email", email);
                    cmd.Parameters.AddWithValue("a_password", password);

                    SqlDataReader sdr = cmd.ExecuteReader();
                    if (sdr.Read())
                    {
                        return Convert.ToInt32(sdr["a_id"].ToString());
                    }
                }
            }
            return 0;
        }

        [HttpPost]
        public bool InsertDeliveryBoy(string db_name, string db_email, string db_password, string db_shiftstart, string db_shiftend)
        {
            using (SqlConnection con = new SqlConnection(AppConnection.GetConnectionString()))
            {
                using (SqlCommand cmd = new SqlCommand("USP_DELIVERYBOYS_INSERT_DELIVERYBOYS", con))
                {
                    con.Open();
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.AddWithValue("db_name", db_name);
                    cmd.Parameters.AddWithValue("db_email", db_email);
                    cmd.Parameters.AddWithValue("db_password", db_password);
                    cmd.Parameters.AddWithValue("db_shiftstart", db_shiftstart);
                    cmd.Parameters.AddWithValue("db_shiftend", db_shiftend);

                    int rowEffected = cmd.ExecuteNonQuery();
                    if (rowEffected > 0)
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        [HttpPost]
        public bool InsertCategory(string category)
        {
            using (SqlConnection con = new SqlConnection(AppConnection.GetConnectionString()))
            {
                using (SqlCommand cmd = new SqlCommand("USP_CATEGORIES_INSERT_CATEGORY", con))
                {
                    con.Open();
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.AddWithValue("c_categorytype", category);

                    int rowEffected = cmd.ExecuteNonQuery();
                    if (rowEffected > 0)
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        [HttpPost]
        public bool InsertProduct(string p_name, int p_category, decimal p_unitprice, int p_qty, string p_weight)
        {
            using (SqlConnection con = new SqlConnection(AppConnection.GetConnectionString()))
            {
                using (SqlCommand cmd = new SqlCommand("USP_PRODUCTS_INSERT_PRODUCT", con))
                {
                    con.Open();
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.AddWithValue("p_name", p_name);
                    cmd.Parameters.AddWithValue("p_category", p_category);
                    cmd.Parameters.AddWithValue("p_unitprice", p_unitprice);
                    cmd.Parameters.AddWithValue("p_qty", p_qty);
                    cmd.Parameters.AddWithValue("p_weight", p_weight);

                    int rowEffected = cmd.ExecuteNonQuery();
                    if (rowEffected > 0)
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        [HttpPut]
        public bool UpdateStock(int od_user_id, int od_pid)
        {
            using (SqlConnection con = new SqlConnection(AppConnection.GetConnectionString()))
            {
                using (SqlCommand cmd = new SqlCommand("USP_PRODUCTS_UPDATE_STOCK", con))
                {
                    con.Open();
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.AddWithValue("od_user_id", od_user_id);
                    cmd.Parameters.AddWithValue("od_pid", od_pid);

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