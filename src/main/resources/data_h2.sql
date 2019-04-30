--Add Customers
INSERT INTO CUSTOMERS(CUSTOMER_ID,CUSTOMER_UID,name,age) values (0,'anishkumar@in.com','Anish Kumar',30);

--Add Categories
INSERT INTO categories(id,CATEGORY_CODE) values(0,'Produce');
INSERT INTO categories(id,CATEGORY_CODE) values(1,'Fruits');
INSERT INTO categories(id,CATEGORY_CODE) values(2,'Veg');

INSERT INTO categories(id,CATEGORY_CODE) values(3,'Dairy');
INSERT INTO categories(id,CATEGORY_CODE) values(4,'Milk');
INSERT INTO categories(id,CATEGORY_CODE) values(5,'Cheese');

--Add category to category relation
INSERT INTO CAT2CATREL(CATEGORY_ID,SUB_CATEGORY_ID) values (0,1);
INSERT INTO CAT2CATREL(CATEGORY_ID,SUB_CATEGORY_ID) values (0,2);

INSERT INTO CAT2CATREL(CATEGORY_ID,SUB_CATEGORY_ID) values (3,4);
INSERT INTO CAT2CATREL(CATEGORY_ID,SUB_CATEGORY_ID) values (3,5);

--Add units
INSERT INTO UNIT(id,code,conversion) values (0,'Kilogram',1);
INSERT INTO UNIT(id,code,conversion) values (1,'Gram',1);
INSERT INTO UNIT(id,code,conversion) values (2,'Litres',1);
INSERT INTO UNIT(id,code,conversion) values (3,'Mililitres',1);

--Add Currencies
INSERT INTO CURRENCY(id,iso_code,active,symbol) values (0,'INR',true,'₹');

--Add Products
INSERT INTO PRODUCTS(ID,PRODUCT_CODE,PRODUCT_DESCRIPTION,UNIT) values (0,'Apple','Kinoor Apples',0);
INSERT INTO PRODUCTS(ID,PRODUCT_CODE,PRODUCT_DESCRIPTION,UNIT) values (1,'Orange','Nagpur Oranges',0);
INSERT INTO PRODUCTS(ID,PRODUCT_CODE,PRODUCT_DESCRIPTION,UNIT) values (2,'Potato','Hybrid Potatoes',0);
INSERT INTO PRODUCTS(ID,PRODUCT_CODE,PRODUCT_DESCRIPTION,UNIT) values (3,'Tomato','Desi Tomatoes',0);

INSERT INTO PRODUCTS(ID,PRODUCT_CODE,PRODUCT_DESCRIPTION,UNIT) values (4,'Cow Milk','Country Delight Cow Milk',2);
INSERT INTO PRODUCTS(ID,PRODUCT_CODE,PRODUCT_DESCRIPTION,UNIT) values (5,'Soy Milk','Soy Milk',2);
INSERT INTO PRODUCTS(ID,PRODUCT_CODE,PRODUCT_DESCRIPTION,UNIT) values (6,'Cheddar','Cheddar',0);
INSERT INTO PRODUCTS(ID,PRODUCT_CODE,PRODUCT_DESCRIPTION,UNIT) values (7,'Gouda','Gouda',0);

--Add Prices
INSERT INTO PRICES(price_id,unit_factor,price_value,currency_id,product_id,unit_id) values (0,1,50,0,0,0);
INSERT INTO PRICES(price_id,unit_factor,price_value,currency_id,product_id,unit_id) values (1,1,80,0,1,0);
INSERT INTO PRICES(price_id,unit_factor,price_value,currency_id,product_id,unit_id) values (2,1,30,0,2,0);
INSERT INTO PRICES(price_id,unit_factor,price_value,currency_id,product_id,unit_id) values (3,1,70,0,3,0);

INSERT INTO PRICES(price_id,unit_factor,price_value,currency_id,product_id,unit_id) values (4,1,50,0,4,2);
INSERT INTO PRICES(price_id,unit_factor,price_value,currency_id,product_id,unit_id) values (5,1,40,0,5,2);
INSERT INTO PRICES(price_id,unit_factor,price_value,currency_id,product_id,unit_id) values (6,1,50,0,6,0);
INSERT INTO PRICES(price_id,unit_factor,price_value,currency_id,product_id,unit_id) values (7,1,80,0,7,0);

--Add category to product relations
INSERT INTO CAT2PRODREL(CATEGORY_ID,PRODUCT_ID) values (1,0);
INSERT INTO CAT2PRODREL(CATEGORY_ID,PRODUCT_ID) values (1,1);
INSERT INTO CAT2PRODREL(CATEGORY_ID,PRODUCT_ID) values (2,2);
INSERT INTO CAT2PRODREL(CATEGORY_ID,PRODUCT_ID) values (2,3);

INSERT INTO CAT2PRODREL(CATEGORY_ID,PRODUCT_ID) values (4,4);
INSERT INTO CAT2PRODREL(CATEGORY_ID,PRODUCT_ID) values (4,5);
INSERT INTO CAT2PRODREL(CATEGORY_ID,PRODUCT_ID) values (5,6);
INSERT INTO CAT2PRODREL(CATEGORY_ID,PRODUCT_ID) values (5,7);