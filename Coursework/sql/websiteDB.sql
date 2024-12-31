/* Student name: Aman Messinezis               
Student username: adbt065             
*/

/* SECTION 1 CREATE TABLE STATEMENTS */

CREATE TABLE Customer(
username VARCHAR(20) PRIMARY KEY,
password VARCHAR(32) NOT NULL,
fName VARCHAR(50) NOT NULL,
lName VARCHAR(100) NOT NULL,
mobileNumber VARCHAR(11) UNIQUE NOT NULL,
email VARCHAR(40) UNIQUE NOT NULL
);

