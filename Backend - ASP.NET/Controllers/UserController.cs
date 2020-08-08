using OnlineFoodOrderingSystem.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Web.Http;

namespace OnlineFoodOrderingSystem.Controllers
{
    public class UserController : ApiController
    {
        [HttpPost]
        public int LoginUser(string u_email, string u_password)
        {
            using (SqlConnection con = new SqlConnection(AppConnection.GetConnectionString()))
            {
                using (SqlCommand cmd = new SqlCommand("USP_USERS_LOGIN_USER", con))
                {
                    con.Open();
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.AddWithValue("u_email", u_email);
                    cmd.Parameters.AddWithValue("u_pass", u_password);

                    SqlDataReader sdr = cmd.ExecuteReader();
                    if (sdr.Read())
                    {
                        return Convert.ToInt32(sdr["u_id"].ToString());
                    }
                }
            }
            return 0;
        }

        [HttpPost]
        public bool ForgotPassword(string u_email, string u_password)
        {
            using (SqlConnection con = new SqlConnection(AppConnection.GetConnectionString()))
            {
                using (SqlCommand cmd = new SqlCommand("USP_USERS_FORGET_PASSWORD", con))
                {
                    con.Open();
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.AddWithValue("u_email", u_email);
                    cmd.Parameters.AddWithValue("u_pass", u_password);

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
        public bool PostUser(string u_name, string u_email, string u_password)
        {
            using (SqlConnection con = new SqlConnection(AppConnection.GetConnectionString()))
            {
                using (SqlCommand cmd = new SqlCommand("USP_USERS_INSERT_USERS", con))
                {
                    con.Open();
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.AddWithValue("u_name", u_name);
                    cmd.Parameters.AddWithValue("u_email", u_email);
                    cmd.Parameters.AddWithValue("u_pass", u_password);


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
        public bool PostOrderDetails(int od_user_id, int od_pid, int od_qty, string od_delivered_loc, decimal od_price)
        {
            using (SqlConnection con = new SqlConnection(AppConnection.GetConnectionString()))
            {
                using (SqlCommand cmd = new SqlCommand("USP_ORDERDETAILS_BUY_PRODUCTS_DETAILS", con))
                {
                    con.Open();
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.AddWithValue("od_user_id", od_user_id);
                    cmd.Parameters.AddWithValue("od_pid", od_pid);
                    cmd.Parameters.AddWithValue("od_qty", od_qty);
                    cmd.Parameters.AddWithValue("od_delivered_loc", od_delivered_loc);
                    cmd.Parameters.AddWithValue("od_price", od_price);


                    int rowEffected = cmd.ExecuteNonQuery();
                    if (rowEffected > 0)
                    {
                        return true;
                    }
                }
            }
            using (SqlConnection con = new SqlConnection(AppConnection.GetConnectionString()))
            {
                using (SqlCommand cmd = new SqlCommand("USP_ORDERASSIGNS_PLACE_ORDER", con))
                {
                    con.Open();
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.AddWithValue("oa_u_id", od_user_id);

                    cmd.ExecuteNonQuery();
                }
            }
            return false;
        }
    }
}