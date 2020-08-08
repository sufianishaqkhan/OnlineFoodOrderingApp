using OnlineFoodOrderingSystem.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace OnlineFoodOrderingSystem.Controllers
{
    public class ValuesController : ApiController
    {
        // GET api/values
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

        // GET api/values/5
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
        [HttpGet]
        public IEnumerable<Category> ViewCategory()
        {
            List<Category> categories = new List<Category>();
            using (SqlConnection con = new SqlConnection(AppConnection.GetConnectionString()))
            {
                using (SqlCommand cmd = new SqlCommand("USP_CATEGORIES_VIEW_CATEGORIES", con))
                {
                    con.Open();
                    cmd.CommandType = CommandType.StoredProcedure;

                    SqlDataReader sdr = cmd.ExecuteReader();
                    while (sdr.Read())
                    {
                        Category category = new Category();

                        category.c_id = Convert.ToInt32(sdr["c_id"].ToString());
                        category.c_categorytype = sdr["c_categorytype"].ToString();
                        categories.Add(category);
                    }
                }
            }
            return categories;
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

        [HttpGet]
        public List<Products> ViewProducts()
        {
            List<Products> products = new List<Products>();
            using (SqlConnection con = new SqlConnection(AppConnection.GetConnectionString()))
            {
                using (SqlCommand cmd = new SqlCommand("USP_PRODUCTS_VIEW_PRODUCTS", con))
                {
                    con.Open();
                    cmd.CommandType = CommandType.StoredProcedure;

                    SqlDataReader sdr = cmd.ExecuteReader();
                    while (sdr.Read())
                    {
                        Products product = new Products();
                        product.p_id = Convert.ToInt32(sdr["p_id"].ToString());
                        product.p_name = sdr["p_name"].ToString();
                        product.p_category = sdr["p_category"].ToString();
                        product.p_unitprice = Convert.ToDecimal(sdr["p_unitprice"].ToString());
                        product.p_qty = Convert.ToInt32(sdr["p_qty"].ToString());
                        product.p_weight = sdr["p_weight"].ToString();
                        product.CreatedDate = sdr["CreatedDate"].ToString();
                        //product.p_pic = sdr[""].ToString();
                        products.Add(product);
                    }
                }
            }
            return products;
        }

        [HttpPost]
        public bool InsertDeliveryBoys(string db_name, string db_email, string db_password, string db_shiftstart, string db_shiftend)
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
        public int GetLoginDB(string db_email, string db_password)
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
        public bool PostUsers(string u_name, string u_email, string u_pass)
        {
            using (SqlConnection con = new SqlConnection(AppConnection.GetConnectionString()))
            {
                using (SqlCommand cmd = new SqlCommand("USP_USERS_INSERT_USERS", con))
                {
                    con.Open();
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.AddWithValue("u_name", u_name);
                    cmd.Parameters.AddWithValue("u_email", u_email);
                    cmd.Parameters.AddWithValue("u_pass", u_pass);


                    int rowEffected = cmd.ExecuteNonQuery();
                    if (rowEffected > 0)
                    {
                        return true;
                    }
                }
            }
            return false;
        }
        public int GetLoginUser(string u_email, string u_pass)
        {
            using (SqlConnection con = new SqlConnection(AppConnection.GetConnectionString()))
            {
                using (SqlCommand cmd = new SqlCommand("USP_USERS_LOGIN_USER", con))
                {
                    con.Open();
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.AddWithValue("u_email", u_email);
                    cmd.Parameters.AddWithValue("u_pass", u_pass);

                    SqlDataReader sdr = cmd.ExecuteReader();
                    if (sdr.Read())
                    {
                        return Convert.ToInt32(sdr["u_id"].ToString());
                    }
                }
            }
            return 0;
        }
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
                        order.u_name = sdr["u_name"].ToString();
                        order.p_name = sdr["p_name"].ToString();
                        order.od_qty = Convert.ToInt32(sdr["od_qty"].ToString());
                        order.od_price = Convert.ToDecimal(sdr["od_price"].ToString());
                        var chk = sdr["oa_db_id"].ToString();
                        if ( chk == "")
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
        public bool PutOrderAssign(int oa_u_id ,int oa_db_id) 
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
        
        public bool PutUpdateStock(int od_user_id, int od_pid) 
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
        public bool PutForgetPassword(string u_email,string u_pass) 
        {
            using (SqlConnection con = new SqlConnection(AppConnection.GetConnectionString()))
            {
                using (SqlCommand cmd = new SqlCommand("USP_USERS_FORGET_PASSWORD", con))
                {
                    con.Open();
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.AddWithValue("u_email", u_email);
                    cmd.Parameters.AddWithValue("u_pass", u_pass);

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