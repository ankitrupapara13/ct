DROP TABLE APP.ORDERS;
DROP TABLE APP.ORDERPRODUCTS;

create table APP.ORDERS (
    uniqueOrderId int,
    orderDate DATE DEFAULT CURRENT_DATE NOT NULL,
    customerId int NOT NULL,
    
--  customerShippingAddress varchar(100), **we can get this by referencing to customer table using customer table
--  listOfProducts int[], **multivalued... so new table will be craeted that will have joint primary key of orderId and productId
    
    totalOrderValue double NOT NULL, --**will be calculated on the go
    shippingCost double NOT NULL, --**will be calculated on the go 
    shippingAgency varchar(20) NOT NULL,
    status varchar(10) NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    
    primary key(uniqueOrderId)
);

--Separate table Created because 1 order can contain multiple Product. So new table created according to Normalisation Standard
create table APP.ORDERPRODUCTS(
    uniqueOrderId int,
    productId int,
    
    primary key (uniqueOrderId, productId)
);
 
--Foreign Keys
alter table APP.ORDERS add foreign key (customerId) references APP.customer(customerId);
alter table APP.ORDERS add foreign key (shippingAgency) references APP.company(companyName);

alter table APP.ORDERPRODUCTS add foreign key (uniqueOrderId) references APP.ORDERS(uniqueOrderId);
alter table APP.ORDERPRODUCTS add foreign key (productId) references APP.products(productId);


--Insert query
insert into APP.ORDERS values(101, DEFAULT, 1001, 1313.10, 100.50, 'Amazon.in', 'Completed', DEFAULT, DEFAULT);