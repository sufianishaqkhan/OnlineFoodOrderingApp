use OnlineFoodOrderingSystem;


---	INSERT ADMIN STORE PROCEDURE
CREATE PROC USP_ADMIN_INSERT_ADMIN
AS 
BEGIN
			INSERT INTO [OnlineFoodOrderingSystem].[dbo].[Admin]
			           ([a_name]
			           ,[a_email]
			           ,[a_password])
			     VALUES
			           ('ALI'
			           ,'ali321@gmail.com'
			           ,'ali321')
END

EXEC USP_ADMIN_INSERT_ADMIN

---	DISPLAYING ADMINS
SELECT
	* 
FROM [ADMIN]



--- LOGIN ADMIN STORE PROCEDURE
ALTER PROC USP_ADMIN_LOGIN_ADMIN
(
@a_email NVARCHAR(200)
,@a_password NVARCHAR(100)
)
AS
BEGIN
		SELECT [a_id]
		  FROM [OnlineFoodOrderingSystem].[dbo].[Admin]
		  WHERE @a_email = a_email AND @a_password = a_password
END

EXEC USP_ADMIN_LOGIN_ADMIN 'ali321@gmail.com','ali321'



---	INSERT PRODUCT CATEGORY STORE PROCEDURE
CREATE PROC USP_CATEGORIES_INSERT_CATEGORY
(
@c_categorytype NVARCHAR(50)
)
AS
BEGIN

				INSERT INTO [OnlineFoodOrderingSystem].[dbo].[Categories]
						   ([c_categorytype])
					 VALUES
						   (@c_categorytype)
END

EXEC USP_CATEGORIES_INSERT_CATEGORY 'FRUITS'

---	DISPLAYING ALL CATEGORY
SELECT
	* 
FROM CATEGORIES;

--- VIEW CATEGORY STORE PROCEDURES
CREATE PROC USP_CATEGORIES_VIEW_CATEGORIES
AS
BEGIN
		SELECT [c_id]
			  ,[c_categorytype]
		  FROM [OnlineFoodOrderingSystem].[dbo].[Categories]
END

---	INSERT PRODUCTS STORE PROCEDURE
ALTER PROC USP_PRODUCTS_INSERT_PRODUCT
(
@p_name NVARCHAR(50)
,@p_category INT
,@p_unitprice DECIMAL(18,2)
,@p_qty INT
,@p_weight NVARCHAR(50) 
)
AS
BEGIN
		INSERT INTO [OnlineFoodOrderingSystem].[dbo].[Products]
				   ([p_name]
				   ,[p_category]
				   ,[p_unitprice]
				   ,[p_qty]
				   ,[p_weight]
				   ,[CreatedDate])
			 VALUES
				   (@p_name
				   ,@p_category
				   ,@p_unitprice
				   ,@p_qty
				   ,@p_weight
				   ,GETDATE())
END

EXEC USP_PRODUCTS_INSERT_PRODUCT 'APPLE',1,2,120,'KG'

---	DISPLAYING PRODUCTS
SELECT 
	* 
FROM PRODUCTS;


---	DISPLAYING PRODUCTS WITH CATEGORIES STORE PROCEDURE
ALTER PROC USP_PRODUCTS_VIEW_PRODUCTS
AS
BEGIN
		SELECT [p_id]
			  ,[p_name]
			  ,C.C_CATEGORYTYPE AS [p_category]
			  ,[p_unitprice]
			  ,[p_qty]
			  ,[p_weight]
			  ,[CreatedDate]
		  FROM [OnlineFoodOrderingSystem].[dbo].[Products] P
		  LEFT JOIN CATEGORIES C ON C.C_ID = P.p_category
END

EXEC USP_PRODUCTS_VIEW_PRODUCTS


--- INSERT DELIVERY BOY STORE PROCEDURE
ALTER PROC USP_DELIVERYBOYS_INSERT_DELIVERYBOYS
(
@db_name nvarchar(50)
,@db_email nvarchar(200)
,@db_password nvarchar(100)
,@db_shiftstart nvarchar(50)
,@db_shiftend nvarchar(50)
)
AS
BEGIN
		INSERT INTO [OnlineFoodOrderingSystem].[dbo].[DeliveryBoys]
				   ([db_name]
				   ,[db_email]
				   ,[db_password]
				   ,[db_shiftstart]
				   ,[db_shiftend]
				   ,[CreatedBy]
				   ,[CreatedDate])
			 VALUES
				   (@db_name
				   ,@db_email
				   ,@db_password
				   ,@db_shiftstart
				   ,@db_shiftend
				   ,'Admin'
				   ,GETDATE())
END

EXEC USP_DELIVERYBOYS_INSERT_DELIVERYBOYS 'BASIT','basit321@gmail.com',	'basit321','8AM','4PM','ADMIN'

--- DISPLAYING ALL DELIVERY BOYS
SELECT 
	*
FROM DELIVERYBOYS;

---	DISPLAYING ALL DELIVERY BOYS
CREATE PROC USP_DELIVERYBOYS_VIEW_DELIVERYBOYS
AS
BEGIN
		SELECT [db_id]
		      ,[db_name]
		      ,[db_email]
		      ,[db_password]
		      ,[db_shiftstart]
		      ,[db_shiftend]
		      ,[CreatedBy]
		      ,[CreatedDate]
		  FROM [OnlineFoodOrderingSystem].[dbo].[DeliveryBoys]
END

--- LOGIN DELIVERY BOY STORE PROCEDURE
ALTER PROC USP_DELIVERYBOYS_LOGIN_DELIVERYBOY
(
@db_email NVARCHAR(200)
,@db_password NVARCHAR(100)
)
AS
BEGIN
		SELECT [DB_ID]
		  FROM [OnlineFoodOrderingSystem].[dbo].[DeliveryBoys]
		  WHERE @db_email = db_email AND @db_password = db_password
END

EXEC USP_DELIVERYBOYS_LOGIN_DELIVERYBOY 'basit321@gmail.com','basit321'



--- INSERT USERS STORE PROCEDURES
CREATE PROC USP_USERS_INSERT_USERS
(
@u_name NVARCHAR(50)
,@u_email NVARCHAR(200)
,@u_pass NVARCHAR(100)
)
AS
BEGIN
		INSERT INTO [OnlineFoodOrderingSystem].[dbo].[Users]
				   ([u_name]
				   ,[u_email]
				   ,[u_pass]
				   ,[CreatedDate])
			 VALUES
				   (@u_name
				   ,@u_email
				   ,@u_pass
				   ,GETDATE())
END

EXEC USP_USERS_INSERT_USERS 'HAROON','HAROON321@GMAIL.COM','HAROON321'

--- DISPLAYING ALL USERS
SELECT
	*
FROM Users


--- LOGIN USER STORE PROCEDURE
ALTER PROC USP_USERS_LOGIN_USER
(
@u_email NVARCHAR(200)
,@u_pass NVARCHAR(100)
)
AS
BEGIN
		SELECT [u_id]
		  FROM [OnlineFoodOrderingSystem].[dbo].[Users]
		  WHERE u_email = @u_email AND u_pass = @u_pass
END

EXEC USP_USERS_LOGIN_USER 'HAROON321@GMAIL.COM','HAROON321'



ALTER PROC USP_ORDERDETAILS_BUY_PRODUCTS_DETAILS
(
 @od_user_id int
,@od_pid int
,@od_qty int
,@od_delivered_loc nvarchar(max)
,@od_price decimal(18,2)
)
AS
BEGIN
		INSERT INTO [OnlineFoodOrderingSystem].[dbo].[OrderDetails]
				   ([od_user_id]
				   ,[od_pid]
				   ,[od_qty]
				   ,[od_delivered_loc]
				   ,[od_price])
			 VALUES
				   (@od_user_id
				   ,@od_pid
				   ,@od_qty
				   ,@od_delivered_loc
				   ,@od_price)
END

EXEC USP_ORDERDETAILS_BUY_PRODUCTS_DETAILS 1,1,2,null,2


---	DISPLAYING ORDER DETAILS
SELECT 
	* 
FROM ORDERDETAILS

---	DISPLAYING ORDER DETAILS WITH PRODUCT NAMES & USER NAME STORE PROCEDURE
ALTER PROC USP_ORDERDETAILS_FULL_ORDER_DETAILS
AS
BEGIN
		SELECT [od_id]
		      ,U.U_NAME 
		      ,P.P_NAME 
		      ,[od_qty]
		      ,[od_delivered_loc]
		      ,[od_price]
		  FROM [OnlineFoodOrderingSystem].[dbo].[OrderDetails] OD
		  LEFT JOIN PRODUCTS P ON P.P_ID = OD.od_pid
		  LEFT JOIN Users U ON U.U_ID = OD.od_user_id  
END  

EXEC USP_ORDERDETAILS_FULL_ORDER_DETAILS


--- PLACE ORDER STORE PROCEDURE
CREATE PROC USP_ORDERASSIGNS_PLACE_ORDER
(
@oa_u_id int
)
AS
BEGIN
		INSERT INTO [OnlineFoodOrderingSystem].[dbo].[OrderAssigns]
				   ([oa_u_id]
				   ,[oa_status])
			 VALUES
				   (@oa_u_id
				   ,'PENDING')
END

EXEC USP_ORDERASSIGNS_PLACE_ORDER 1

--- DISPLAYING ALL ORDER ASSIGNS
SELECT 
	*
FROM OrderAssigns


--- DISPLAYING ALL ASSIGN ORDER WITH USER ORDER STORE PROCEDURE
CREATE PROC USP_ORDERASSIGNS_DISP_USER_ORDER
AS
BEGIN
		SELECT [oa_id]
			  ,U.u_name
			  ,P.p_name
			  ,OD.od_qty
			  ,OD.od_price
			  ,[oa_db_id]
			  ,[oa_status]
		  FROM [OnlineFoodOrderingSystem].[dbo].[OrderAssigns] OA
		  LEFT JOIN OrderDetails OD ON OD.od_user_id = OA.oa_u_id
		  LEFT JOIN Products P ON P.p_id = OD.od_pid
		  LEFT JOIN Users U ON U.u_id = OA.oa_u_id
END

EXEC USP_ORDERASSIGNS_DISP_USER_ORDER


--- ASSIGN ORDERS TO DELIVERY BOY STORE PROCEDURE
ALTER PROC USP_ORDERASSIGNS_ASSIGN_ORDER
(
 @oa_u_id INT
,@oa_db_id INT
)
AS
BEGIN
		UPDATE [OnlineFoodOrderingSystem].[dbo].[OrderAssigns]
		   SET [oa_db_id] = @oa_db_id
			  ,[oa_status] = 'ACCEPTTED'
		 WHERE oa_u_id = @oa_u_id
END

EXEC USP_ORDERASSIGNS_ASSIGN_ORDER 1,1

SELECT * FROM OrderAssigns

SELECT * FROM DeliveryBoys


---	COMPLETING ORDER STORE PROCEDURE
ALTER PROC USP_ORDERASSIGNS_COMPLETED_ORDER
(
@oa_u_id INT
)
AS
BEGIN
		UPDATE [OnlineFoodOrderingSystem].[dbo].[OrderAssigns]
		   SET [oa_status] = 'DELIVERED'
		 WHERE oa_u_id = @oa_u_id
END

EXEC USP_ORDERASSIGNS_COMPLETED_ORDER 1


---	UPDATING STOCK STORE PROCEDURE
CREATE PROC USP_PRODUCTS_UPDATE_STOCK
(
@od_user_id INT
,@od_pid INT
)
AS
BEGIN

DECLARE @od_qty INT = (SELECT 
							od_qty 
						FROM OrderDetails 
						WHERE od_user_id = @od_user_id AND od_pid = @od_pid)

		UPDATE [OnlineFoodOrderingSystem].[dbo].[Products]
		SET [p_qty] = (p_qty - @od_qty)
		WHERE p_id = @od_pid
END

EXEC USP_PRODUCTS_UPDATE_STOCK 1,1

---	FORGET USER PASSWORD
CREATE PROC USP_USERS_FORGET_PASSWORD
(
@u_email NVARCHAR(200)
,@u_pass NVARCHAR(50)
)
AS
BEGIN
		UPDATE [OnlineFoodOrderingSystem].[dbo].[Users]
		   SET [u_pass] = @u_pass
		 WHERE [u_email] = @u_email
END



SELECT * FROM OrderAssigns
SELECT * FROM OrderDetails
SELECT * FROM Products
SELECT * FROM Users

TRUNCATE TABLE [ADMIN]
TRUNCATE TABLE [PRODUCTS]
TRUNCATE TABLE [CATEGORIES]
TRUNCATE TABLE [DELIVERYBOYS]
TRUNCATE TABLE [USERS]
TRUNCATE TABLE [ORDERDETAILS]
TRUNCATE TABLE [ORDERASSIGNS]
