using OnlineFoodOrderingSystem.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Web.Http;

namespace OnlineFoodOrderingSystem.Controllers
{
    public class ProductController : ApiController
    {
        [HttpGet]
        public IEnumerable<Category> GetCategories()
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

        [HttpGet]
        public List<Products> GetProducts()
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
                        product.p_pic = sdr["p_pic"].ToString();
                        product.CreatedDate = sdr["CreatedDate"].ToString();
                        products.Add(product);
                    }
                }
            }
            return products;
        }
    }
}
