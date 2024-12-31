/* Student name:  Chris Smart             */

/* SECTION 1 CREATE TABLE STATEMENTS */

create table sg377Staff
(
staff_no integer primary key,
first_name char(15),
surname char(15),
headwaiter integer
);

create table sg377Room_management
(
room_name char(15),
room_date integer,
headwaiter integer,
foreign key (headwaiter) references sg377Staff (staff_no),
primary key (room_name, room_date)
);

create table sg377Rest_table
(
table_no integer primary key,
no_of_seats integer,
room_name char(5)
);

create table sg377Bill
(
bill_no integer primary key,
bill_date integer,
bill_total dec(6,2),
cust_name char(20),
covers integer,
table_no integer,
waiter_no integer,
foreign key (table_no) references sg377Rest_table (table_no),
foreign key (waiter_no) references sg377Staff (staff_no)
);

/* SECTION 2 INSERT STATEMENTS */

insert into sg377Staff values (001,'John','Paul',005),
                        (002,'Paul','Smith',006),    
                        (003,'Zoe','Ball',005),    
                        (004,'Sam','Pitt',006),    
                        (005,'Alphonso','Moss',null),    
                        (006,'Jack','Hunt',null),    
                        (007,'Jimmy','Smith',005),    
                        (008,'Tim','Jackson',005),    
                        (009,'David','Campbell',006),
                        (010, 'Charles','Watson',null),
                        (011, 'Chris', 'Smart', 010);

insert into sg377Room_management values ('Blue',160312,005),
                                ('Blue',160105,005),   
                                ('Blue',160210,006),
                                ('Blue',160215,005),
                                ('Blue',150614,005),
                                ('Red',160307,006),
                                ('Red',151231,006),
                                ('Red',160111,006),
                                ('Red',160312,005),
                                ('Red',160210,005),
                                ('Red',160115,006),
                                ('Green',160105,010),
                                ('Green',160111,010),
                                ('Green',160215,010),
                                ('Green',151231,010);

insert into sg377Rest_table values (001,7,'Blue'),
                            (002,6,'Blue'),
                            (003,10,'Blue'),
                            (004,7,'Red'),
                            (005,4,'Red'),
                            (006,7,'Red'),
                            (007,6,'Red'),
                            (008,6,'Green'),
                            (009,5,'Green');
       
insert into sg377Bill values (00001,160312,200.99,'Tony Beebee',3,001,002),
                    (00002,160105,500.47,'David Hookman',7,002,003),         
                    (00003,151231,400.33,'Jack Pitt',4,005,004),         
                    (00004,151231,600.91,'Bob Crow',2,004,004),             
                    (00005,150614,400.23,'Beck Smith',7,006,002),
                    (00007,160111,237.37,'Terry Jones',4,004,002),
                    (00008,160111,396.00,'Tony Beebee',2,005,004),
                    (00009,160111,101.00,'Tanya Singh',1,006,004),
                    (00010,160111,272.01,'Bob Crow',3,008,011),
                    (00011,160111,777.11,'Nerida Smith',5,009,011),
                    (00012,160312,1665.27,'David Hookman',10,003,003),
                    (00013,160312,831.00,'Tanya Singh',4,001,008),
                    (00014,151231,555.66,'Terry Jones',3,008,011),
                    (00015,151231,102.35,'Sunil Shah',2,009,011),
                     (00016,160111,232.11,'Bob Crow',4,009,011),
                     (00017,160210,311.11,'Tanya Singh',2,001,003),
                     (00018,160210,89.99,'Bob Crow',3,006,009),
                     (00019,160210,109.31,'Nerida Smith',2,008,011),
                     (00020,160215,444.44,'Bob Crow',4,001,007),
                     (00021,160215,131.11,'Nancy Smith',2,009,011),
                     (00022,160312,545.01,'Sunil Shah',4,006,004);
                     
/* SECTION 3 UPDATE STATEMENTS */


UPDATE sg377Staff SET first_name = 'Kam', surname = 'Pal'
WHERE Staff_no = 004;


/* SECTION 4 SINGLE TABLE QUERIES */


/* 1) List the dates and bill totals for customer Bob Crow. */

select bill_date, bill_total 
from sg377Bill 
where cust_name = 'Bob Crow'; 


/* 2) List the names of all customers whose last name is Smith. List each customer only once. */


select distinct(cust_name) 
from sg377Bill 
where cust_name like '% Smith';


/* 3) List the names of all customers whose second names begin with 'C'. List each customer only once. */

select distinct(cust_name) 
from sg377Bill 
where cust_name like '% C%';


/* 4) List the names of all the headwaiters. */

select first_name, surname 
from sg377Staff 
where headwaiter is null;


/* 5) List all the details of bills dated February 2016. */

select * 
from sg377Bill 
where bill_date >= 160201 and bill_date <= 160228;


/* 6) How much money did the sg377aurant take in February 2016? Change the name of the output column to 'Feb Income'. */

select sum(bill_total) as 'Feb Income' 
from sg377Bill
where bill_date >= 160201 and bill_date <= 160219;


/* SECTION 5 MULTIPLE TABLE QUERIES */


/* 1) List the names of the waiters who have served the customer Tanya Singh. */

select first_name, surname
from sg377Staff
where staff_no in
(select waiter_no
from sg377Bill
where cust_name = 'Tanya Singh');


/* 2) On which dates in February 2016 did the Headwaiter 'Charles' manage the 'Green' room? You may output dates in the format they are stored. */

select room_date
from sg377Staff s, sg377Room_management r
where r.headwaiter = s.staff_no
and first_name = 'Charles'
and room_date between 160201 and 160228
and room_name = 'Green';


/* 3) List the names of the waiters who serve tables in the same team as the waiter Zoe Ball. */

select first_name, surname
from sg377Staff
where headwaiter in
(select headwaiter from sg377Staff
where first_name = 'Zoe'
and surname = 'Ball');
 

/* 4) List the names of customers who spent more than 450.00 on a single bill on occasions when ‘Charles’ was their Headwaiter. */

select distinct b.cust_name
from sg377Bill b
where bill_total > 450
and waiter_no in
(select staff_no
from sg377Staff
where headwaiter in
(select staff_no
from sg377Staff
where first_name = 'Charles'));


/* 5) Which waiters have taken 2 or more bills on a single date? List the waiter names, the dates and the number of bills they have taken. */

select first_name, surname, bill_date, count(*)
from sg377Staff, sg377Bill
where sg377Staff.staff_no = sg377Bill.waiter_no
group by first_name, surname, bill_date
having count(*) >= 2;


/* 6) List the headwaiter’s name sequence and the total bill amount their waiters have taken. Order the list by total bill amount. */

select s2.first_name, s2.surname, sum(bill_total)
from sg377Bill b, sg377Staff s1, sg377Staff s2
where b.waiter_no = s1.staff_no
and s1.headwaiter = s2.staff_no
group by s2.first_name, s2.surname
order by sum(bill_total) desc;

/* SECTION 6 DELETE ROWS (make sure the SQL is commented out in this section)

DELETE FROM sg377Bill WHERE cust_name = 'Sunil Shah';

*/

/* SECTION 7 DROP TABLES (make sure the SQL is commented out in this section)

drop table sg377Staff;
drop table sg377Room_management;
drop table sg377Rest_table;
drop table sg377Bill;

*/