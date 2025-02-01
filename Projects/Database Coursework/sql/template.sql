/* Student name:  Aman Messinezis */

/* SECTION 1 CREATE TABLE STATEMENTS */

CREATE TABLE adbt065Customer(
username VARCHAR(20) PRIMARY KEY UNIQUE,
password VARCHAR(16) NOT NULL UNIQUE,
firstName VARCHAR(50) NOT NULL,
middleName VARCHAR(255),
lastName VARCHAR(100) NOT NULL,
dateOfBirth INTEGER(6),
addressFirstLine VARCHAR(30),
addressSecondLine VARCHAR(30),
addressThirdLine VARCHAR(30),
postcode VARCHAR(8) NOT NULL,
mobileNumber BIGINT(11) UNIQUE,
homeNumber BIGINT(11) UNIQUE,
workNumber BIGINT(11) UNIQUE,
cardNumber BIGINT(16) UNIQUE,
cardExpiryDate SMALLINT(4),
nameOnCard VARCHAR(50),
CVV SMALLINT(3),
subscriptionMember CHAR(1) NOT NULL,
email VARCHAR(40) UNIQUE 
);

CREATE TABLE adbt065Investor(
username VARCHAR(20) PRIMARY KEY UNIQUE,
password VARCHAR(16) NOT NULL UNIQUE,
firstName VARCHAR(50) NOT NULL,
middleName VARCHAR(255),
lastName VARCHAR(100) NOT NULL,
dateOfBirth INTEGER(6),
addressFirstLine VARCHAR(30),
addressSecondLine VARCHAR(30),
addressThirdLine VARCHAR(30),
postcode VARCHAR(8) NOT NULL,
mobileNumber BIGINT(11) UNIQUE,
homeNumber BIGINT(11) UNIQUE,
workNumber BIGINT(11) UNIQUE,
cardNumber BIGINT(16) UNIQUE,
cardExpiryDate SMALLINT(4) NOT NULL,
nameOnCard VARCHAR(50) NOT NULL,
CVV SMALLINT(3) NOT NULL,
subscriptionMember CHAR(1) NOT NULL,
email VARCHAR(40) UNIQUE, 
currentStocksOwned DECIMAL(12, 2) NOT NULL,
currentStockPrice DECIMAL(7, 2) NOT NULL,
FOREIGN KEY (username) REFERENCES adbt065Customer(username)
);

CREATE TABLE adbt065SubscriptionMember(
username VARCHAR(20) PRIMARY KEY UNIQUE,
password VARCHAR(16) NOT NULL UNIQUE,
firstName VARCHAR(50) NOT NULL,
middleName VARCHAR(255),
lastName VARCHAR(100) NOT NULL,
dateOfBirth INTEGER(6),
addressFirstLine VARCHAR(30),
addressSecondLine VARCHAR(30),
addressThirdLine VARCHAR(30),
postcode VARCHAR(8) NOT NULL,
mobileNumber BIGINT(11) UNIQUE,
homeNumber BIGINT(11) UNIQUE,
workNumber BIGINT(11) UNIQUE,
cardNumber BIGINT(16) UNIQUE,
cardExpiryDate SMALLINT(4) NOT NULL,
nameOnCard VARCHAR(50) NOT NULL,
CVV SMALLINT(3) NOT NULL,
subscriptionMember CHAR(1) NOT NULL,
email VARCHAR(40) UNIQUE, 
dateJoined INTEGER(6) NOT NULL,
subscriptionType VARCHAR(50) NOT NULL,
overdue CHAR(1),
FOREIGN KEY (username) REFERENCES adbt065Customer(username)  
);

CREATE TABLE adbt065Seller(
sellerID INTEGER(5) PRIMARY KEY,
dateJoined INTEGER(6) NOT NULL,
noOfCustomer INTEGER(5),
category VARCHAR(30) NOT NULL,
numberOfItems INTEGER(3),
companyName VARCHAR(255) NOT NULL
);

CREATE TABLE adbt065largeOrganisation(
sellerID INTEGER(5) PRIMARY KEY,
dateJoined INTEGER(6) NOT NULL,
noOfCustomer INTEGER(5),
category VARCHAR(30) NOT NULL,
numberOfItems INTEGER(3),
companyName VARCHAR(255) NOT NULL,
noOfEmployees INTEGER(5) NOT NULL,
CEO VARCHAR(100) NOT NULL,
owner VARCHAR(30) NOT NULL,
contactNumber BIGINT(11) UNIQUE,
email VARCHAR(40) NOT NULL UNIQUE,
FOREIGN KEY (sellerID) REFERENCES adbt065Seller(sellerID)
);

CREATE TABLE adbt065Individual(
sellerID INTEGER(5) PRIMARY KEY,
dateJoined INTEGER(6) NOT NULL,
noOfCustomer INTEGER(5),
category VARCHAR(30) NOT NULL,
numberOfItems INTEGER(3),
companyName VARCHAR(255) NOT NULL,
fName VARCHAR(30) NOT NULL,
lName VARCHAR(30),
mobileNo BIGINT(11) UNIQUE,
homeNo BIGINT(11) UNIQUE,
workNo BIGINT(11) UNIQUE,
FOREIGN KEY (sellerID) REFERENCES adbt065Seller(sellerID)
);

CREATE TABLE adbt065Item(
itemID INTEGER(3),
sellerID INTEGER(5),
name VARCHAR(255) NOT NULL UNIQUE,
purchPrice DECIMAL(6, 2) NOT NULL,
category VARCHAR(30) NOT NULL,
noOfStock INTEGER(7) NOT NULL,
PRIMARY KEY (itemID, sellerID),
FOREIGN KEY (sellerID) REFERENCES adbt065Seller(sellerID)
);

CREATE TABLE adbt065WarehouseandWarehouseManager(
warehouseID INTEGER(3) PRIMARY KEY,
noOfStaff INTEGER(4) NOT NULL,
location VARCHAR(25) NOT NULL,
stockNo INTEGER(5) NOT NULL,
minStaffhoursPerWeek INTEGER(2) NOT NULL,
managerID INTEGER(5) NOT NULL,
fullName VARCHAR(90) NOT NULL,
salary DECIMAL(7, 2) NOT NULL,
supervisorID INTEGER(5),
bonus DECIMAL(7, 2) DEFAULT 700.00,
workNumber BIGINT(11),
mobileNumber BIGINT(11),
officeHours VARCHAR(70) NOT NULL DEFAULT 'Monday to Friday 9 to 5', 
FOREIGN KEY (managerID) REFERENCES adbt065Manager(managerID),
FOREIGN KEY (supervisorID) REFERENCES adbt065Staff(supervisorID),
FOREIGN KEY (managerID) REFERENCES adbt065Manager(managerID)
);

CREATE TABLE adbt065Staff(
staffID INTEGER(5) PRIMARY KEY,
fullName VARCHAR(90) NOT NULL,
salary DECIMAL(7, 2) NOT NULL,
warehouseID INTEGER(3) NOT NULL,
supervisorID INTEGER(5),
managerID INTEGER(5),
FOREIGN KEY (warehouseID) REFERENCES adbt065WarehouseandWarehouseManager(warehouseID),
FOREIGN KEY (supervisorID) REFERENCES adbt065Staff(supervisorID),
FOREIGN KEY (managerID) REFERENCES adbt065Manager(managerID)
);

CREATE TABLE adbt065Secretary(
secretaryID INTEGER(5) PRIMARY KEY,
fullName VARCHAR(90) NOT NULL,
salary DECIMAL(7, 2) NOT NULL,
warehouseID INTEGER(3) NOT NULL,
supervisorID INTEGER(5),
managerID INTEGER(5),
typingSpeed DECIMAL(5, 2) NOT NULL,
FOREIGN KEY (warehouseID) REFERENCES adbt065WarehouseandWarehouseManager(warehouseID),
FOREIGN KEY (supervisorID) REFERENCES adbt065Staff(supervisorID),
FOREIGN KEY (managerID) REFERENCES adbt065Manager(managerID),
FOREIGN KEY (secretaryID) REFERENCES adbt065Staff(staffID)
);

CREATE TABLE adbt065Engineer(
engineerID INTEGER(5) PRIMARY KEY,
fullName VARCHAR(90) NOT NULL,
salary DECIMAL(7, 2) NOT NULL,
warehouseID INTEGER(3) NOT NULL,
supervisorID INTEGER(5),
managerID INTEGER(5),
degreePresent CHAR(1),
engineerType VARCHAR(30) NOT NULL,
FOREIGN KEY (warehouseID) REFERENCES adbt065WarehouseandWarehouseManager(warehouseID),
FOREIGN KEY (supervisorID) REFERENCES adbt065Staff(supervisorID),
FOREIGN KEY (managerID) REFERENCES adbt065Manager(managerID),
FOREIGN KEY (engineerID) REFERENCES adbt065Staff(staffID)
);

CREATE TABLE adbt065Manager(
managerID INTEGER(5) PRIMARY KEY,
fullName VARCHAR(90) NOT NULL,
salary DECIMAL(7, 2) NOT NULL,
warehouseID INTEGER(3) NOT NULL,
supervisorID INTEGER(5),
bonus DECIMAL(7, 2) DEFAULT 700.00,
workNumber BIGINT(11),
mobileNumber BIGINT(11),
officeHours VARCHAR(70) NOT NULL DEFAULT 'Monday to Friday 9 to 5', 
FOREIGN KEY (warehouseID) REFERENCES adbt065WarehouseandWarehouseManager(warehouseID),
FOREIGN KEY (supervisorID) REFERENCES adbt065Staff(supervisorID),
FOREIGN KEY (managerID) REFERENCES adbt065Staff(staffID)
);

CREATE TABLE adbt065SecretaryManager(
managerID INTEGER(5) PRIMARY KEY,
fullName VARCHAR(90) NOT NULL,
salary DECIMAL(7, 2) NOT NULL,
warehouseID INTEGER(3) NOT NULL,
supervisorID INTEGER(5),
bonus DECIMAL(7, 2) DEFAULT 700.00,
workNumber BIGINT(11),
mobileNumber BIGINT(11),
officeHours VARCHAR(70) NOT NULL DEFAULT 'Monday to Friday 9 to 5', 
secretaryID INTEGER(5) NOT NULL,
typingSpeed DECIMAL(5, 2) NOT NULL,
latestReportDate INTEGER(6),  
FOREIGN KEY (warehouseID) REFERENCES adbt065WarehouseandWarehouseManager(warehouseID),
FOREIGN KEY (supervisorID) REFERENCES adbt065Staff(supervisorID),
FOREIGN KEY (managerID) REFERENCES adbt065Manager(managerID),
FOREIGN KEY (secretaryID) REFERENCES adbt065Staff(staffID)
);

CREATE TABLE adbt065EngineerManager(
managerID INTEGER(5) PRIMARY KEY,
fullName VARCHAR(90) NOT NULL,
salary DECIMAL(7, 2) NOT NULL,
warehouseID INTEGER(3) NOT NULL,
supervisorID INTEGER(5),
bonus DECIMAL(7, 2) DEFAULT 700.00,
workNumber BIGINT(11),
mobileNumber BIGINT(11),
officeHours VARCHAR(70) NOT NULL DEFAULT 'Monday to Friday 9 to 5',
engineerID INTEGER(5) NOT NULL,
degreePresent CHAR(1),
engineerType VARCHAR(30) NOT NULL,
yearsOfExperience INTEGER(2) NOT NULL,
FOREIGN KEY (warehouseID) REFERENCES adbt065WarehouseandWarehouseManager(warehouseID),
FOREIGN KEY (supervisorID) REFERENCES adbt065Staff(supervisorID),
FOREIGN KEY (managerID) REFERENCES adbt065Manager(managerID),
FOREIGN KEY (engineerID) REFERENCES adbt065Staff(staffID)
);

CREATE TABLE adbt065Stock(
stockID INTEGER(7),
username VARCHAR(20),
itemID INTEGER(3),
warehouseID INTEGER(3) NOT NULL,
sellerID INTEGER(5) NOT NULL,
daysInWarehouse INTEGER(3) NOT NULL,
purchDate INTEGER(6),
PRIMARY KEY(stockID, itemID),
FOREIGN KEY(username) REFERENCES adbt065Customer(username),
FOREIGN KEY(itemID) REFERENCES adbt065Item(itemID),
FOREIGN KEY(warehouseID) REFERENCES adbt065WarehouseandWarehouseManager(warehouseID),
FOREIGN KEY(sellerID) REFERENCES adbt065Seller(sellerID)
);

/* SECTION 2 INSERT STATEMENTS */

						
INSERT INTO adbt065Customer values ('bigboyaman','strongpassword','Aman','Wolde Tennsay','Messinezis',020330,'12','Harrow Road','Edgware','HA1 3BS',07854234445,02094322212,07443329495,4727582858284444,2504,'MR A MESSINEZIS',238,'Y','amanm@gmail.com'),
('stacySols','weak','Stacy',null,'Solomon',940120,'78','Northolt Road','South Harrow','HA1 9RS',07953156982,02054133212,07852369741,4723695123456789,2501,'MISS S SOLOMONS',238,'Y','stace@gmail.com'),
('koalava','Cariboohoo','Anika',null,'Whittle',000115,'181A','Saxmundham Road','Crickadarn','GU33 7AU',07863221568,null,null,9017026483129769,2501,'MRS A WHITTLE',122,'Y','ekur@malchikzer.cf'),
('Windighost43','Pillagerman','Liberty','Cory','Hansen',120331,'9A','Ennisdale Drive','London','GU6 8SH',07435613224,02043124564,null,1799005153469755,2501,'MISS L C HANSEN',782,'N','orryrofel@yopmail.com'),
('Tabooccaneer','Laserpent','Esme',null,'Ferguson',841106,'33','Fountain Head Bungalow','Worthing','E12 5PB',07468975417,02041256878,07447714741,null,null,'MRS E FERGUSON',125,'N','sdjg@gmail.com'),
('Caribooboo','Soyster','Maxwell','Charly','Barnes',750906,'384','Portland Road','Stoke-On-Trent','SO45 2PD',07613425665,02020331245,07896541121,null,null,'MR M C BARNES',454,'N','themailman@mail.com'),
('thedumbest','IdioticSaint','Erik',null,'Yang',651007,'28','Coronation Road','Bournemouth','G42 8PY',07744432102,02040803321,null,null,null,'MR E YANG',220,'N','eggsfordinner@hotmail.co.uk'),
('AwkwardFalcon','LonelySatyr','Jasmine','Heaven','Luna',880818,'125','Grant Road','Bournemouth','CT18 7BP',07789546320,02084234456,null,1234834841466797,2708,'MS J H LUNA',143,'Y','flashycar@carmail.com'),
('NervousHatchling','PlasticPygmy','Phoebe','Hayden','Wilkins',010114,'5','Foscott Way','Coventry','SW9 8PA',07012321544,02084112101,null,8696900980049874,2708,'MS P H WILKINS',227,'N','ewds@yahoomail.co.uk'),
('JellyImmortal','MusicDolphin','Aysha','Haiden','Leonard',950909,'46','Royalthorn Road','Prestwood','RH10 3QB',07312435211,02078954563,null,2472898755545122,2708,'MISS A H LEONARD',753,'N','tptalfc@gmail.com'),
('Bansheep','ChangePorcupine','Madeleine','Hebron','Storn',841106,'43','Maes Yr Awel','Catcott','NR6 6AY',07123215897,null,null,3745940687084700,2708,'MRS M H STORN',783,'N','meterless@bern4love.com'),
('Emoo','Otterminate','Christa',null,'Gordon',060411,'43','Cedar Way','Bournemouth','BT70 2DD',07852132149,null,null,7844855206357413,2412,'MS C GORDON',321,'N','alia@gmail.com'),
('Jaguardo','Cobrag','Melissa','Jerelyn','Banks',040506,'19','Aberdeen Terrace','New Quay','BD12 0QN',07325370124,null,null,5896204257296048,2012,'MS M J BANKS',258,'N','pewds@gmail.com'),
('Fuguru','Snailment','Marie','Chesley','Lamb',070809,'9','Roseland Road','Sudbury','LL12 0AP',07441234567,02045621300,null,3256219646214581,2306,'MRS M C LAMB',885,'N','amadns@gmail.com'),
('IronPirate','Hyenada','Freya','Hermosa','Delacruz',101112,'49','Eastfield Road','New Quay','YO22 5BT',07788457895,null,null,4835431898778996,2811,'MS F H DELACRUZ',775,'N','reqscmm@hotmail.com');

INSERT INTO adbt065Investor values('AwkwardFalcon','LonelySatyr','Jasmine','Heaven','Luna',880818,'125','Grant Road','Bournemouth','CT18 7BP',07789546320,02084234456,null,1234834841466797,2708,'MS J H LUNA',143,'Y','flashycar@carmail.com', 45, 336.6),
('Caribooboo','Soyster','Maxwell','Charly','Barnes',750906,'384','Portland Road','Stoke-On-Trent','SO45 2PD',07613425665,02020331245,07896541121,null,null,'MR M C BARNES',454,'N','themailman@mail.com',12, 336.6),
('koalava','Cariboohoo','Anika',null,'Whittle',000115,'181A','Saxmundham Road','Crickadarn','GU33 7AU',07863221568,null,null,9017026483129769,2501,'MRS A WHITTLE',122,'Y','ekur@malchikzer.cf',454, 336.6);

INSERT INTO adbt065SubscriptionMember values('bigboyaman','strongpassword','Aman','Wolde Tennsay','Messinezis',020330,'12','Harrow Road','Edgware','HA1 3BS',07854234445,02094322212,07443329495,4727582858284444,2504,'MR A MESSINEZIS',238,'Y','amanm@gmail.com',200512, 'FREE DELIVERY', 'Y'),
('stacySols','weak','Stacy',null,'Solomon',940120,'78','Northolt Road','South Harrow','HA1 9RS',07953156982,02054133212,07852369741,4723695123456789,2501,'MISS S SOLOMONS',238,'Y','stace@gmail.com',191111, 'MOVIES AND TV SHOWS', 'N'),
('koalava','Cariboohoo','Anika',null,'Whittle',000115,'181A','Saxmundham Road','Crickadarn','GU33 7AU',07863221568,null,null,9017026483129769,2501,'MRS A WHITTLE',122,'Y','ekur@malchikzer.cf',151013, 'FREE MONTHLY GIFT', 'Y'),
('AwkwardFalcon','LonelySatyr','Jasmine','Heaven','Luna',880818,'125','Grant Road','Bournemouth','CT18 7BP',07789546320,02084234456,null,1234834841466797,2708,'MS J H LUNA',143,'Y','flashycar@carmail.com',121019, 'MOVIES AND TV SHOWS','Y');

INSERT INTO adbt065Seller values(0000001,090912,2000,'Games',5,'The Game Company'), 
(0000002,101218,1200,'Sports',50,'The Sports Company'),
(0000003,120103,10,'Electronics',12,'The Electronics Company'),
(0000004,110303,3200,'Food',35,'The Food Company'), 
(0000005,101218,1562,'Sports',752, 'Jay Dee'),
(0000006,111111,21000,'Car',352, 'The Car Company'), 
(0000007,121212,42000,'Health & Beauty',200,'The Health and Beauty Company'), 
(0000008,160623,1523,'Business',2, 'The Business Company'),
(0000009,110215,4288,'Science',10,'The Science Company'), 
(0000010,190822,1584,'Shower',350,'The Shower Company'), 
(0000011,090912,145,'Books',220, 'The Book Company'), 
(0000012,140518,120,'Electronics',50, 'Soup and Someones Computer World'), 
(0000013,051128,3,'Health & Beauty',12, 'The Soul Shop'), 
(0000014,131213,45,'Food',30, 'Kay Eff Cee'), 
(0000015,100309,899,'Games',75, 'VIDEOGAME'); 

INSERT INTO adbt065largeOrganisation values(0000001,090912,2000,'Games',5,'The Game Company',25,'MR CHRIS BLAKE','HOWARD BRANSON',07485833828,'tgc@gmail.com'),
(0000004,110303,3200,'Food',35,'The Food Company', 45, 'DINA MERKABA MESSINEZI', 'ZENDAYA', 02043245640, 'yorunda@gmail.com'),
(0000006,111111,21000,'Car',352, 'The Car Company', 500, 'MR DAN AUSTIN', 'STEVE BACKSHALL', 07483848221, '21sav@hotmail.com'),
(0000007,121212,42000,'Health & Beauty',200,'The Health and Beauty Company', 800, 'MEGHAN STALLION', 'CHRIS BROWN', null,'chrIsInnocent@hotmail.com');

INSERT INTO adbt065Individual values(0000002,101218,1200,'Sports',50,'The Sports Company','Liz','Mason',null,02656764321,null),
(0000003,120103,10,'Electronics',12,'The Electronics Company','Phil','Ali',07656554555,null,null),
(0000005,101218,1562,'Sports',752, 'Jay Dee','Rockerfeller','Bauer',078767776542,null,02040506070),
(0000008,160623,1523,'Business',2, 'The Business Company','Jane','Cooper',07112255112,null,null),
(0000009,110215,4288,'Science',10,'The Science Company','Joanna','Wolowitz',07898724156,null,null),
(0000010,190822,1584,'Shower',350,'The Shower Company','Jabba','Hutt',07676767670,null,null),
(0000011,090912,145,'Books',220, 'The Book Company','Katy','Cobb',null,02031132670,null),
(0000012,140518,120,'Electronics',50, 'Soup and Someones Computer World','Steve','Backshall',null,null,null),
(0000013,051128,3,'Health & Beauty',12, 'The Soul Shop','Backshall','Steve',null,null,null),
(0000014,131213,45,'Food',30, 'Kay Eff Cee','Sandy','Le',07665566321,null,02056432456),
(0000015,100309,899,'Games',75, 'VIDEOGAME','Viro','Booth',07613287408,null,null);


INSERT INTO adbt065Item values(001,00001,'Fallout 4',34.99,'Video Games',500),
(001,00002,'Goal Net',34.99,'Football',12),
(001,00003,'Logic Circuits',2.98,'System Architecture',2000),
(001,00004,'Coco Pops',2,'Breakfast',10000),
(001,00005,'Football',32,'Football',450),
(001,00006,'Pump',8,'Utilities',21),
(001,00007,'Lipstick',27.99,'Makeup',324873),
(001,00008,'The 4 Hour Work Week',6.99,'Books',1245),
(001,00009,'Test tubes',15,'School',878881),
(001,00010,'Radox Shower Gel',4,'Shower Gel',1300),
(001,00011,'The Jacqueline Wilson Collection',50,'Collections',18),
(001,00012,'Charger',14,'Phones',6575),
(001,00013,'Nivea Cream',7,'Daily Essentials',35541),
(001,00014,'A Live Chicken',87,'Miscellaneous',7272),
(001,00015,'The Incredibles Game',40,'PC',455),
(002,00001,'GTA VI: Brixton',76,'Video Games',686),
(003,00001,'Warzone',12,'Video Games',752432),
(002,00005,'Skipping Rope',5,'Home Exercise/Gym',4258),
(002,00009,'Microscope',130,'Biology',75321),
(002,00011,'Ali Baba and The Forty Theives',17.99,'Children',12);

INSERT INTO adbt065WarehouseandWarehouseManager values(215,2000,'London',50000,12,10000,'Ritik Hayden',4500,null,DEFAULT,07414787774,08001111,DEFAULT),
(452,3300,'Guis',14520,30,20000,'Yannis Walker',5000,null,DEFAULT,07889988754,02034454432,DEFAULT),
(741,7700,'Earl Shilton',30020,70,30000,'Nikki Reeve',8000,null,DEFAULT,07043457543,02034202020,DEFAULT),
(652,1000,'Amherst',13451,30,40000,'Jacques Barrett',74000,null,DEFAULT,07777777777,null,DEFAULT),
(732,230,'Kingston-upon-Thames',85201,18,50000,'Sufyaan Bouvet',8450,null,DEFAULT,07777777778,07860673200,DEFAULT),
(113,962,'Oil City',70000,20,60000,'Izabelle Wardle',9000,null,DEFAULT,07777777779,null,DEFAULT),
(963,999,'Brentwood',98000,24,70000,'Lena Chadwick',9000,null,DEFAULT,07777777710,07814298288,DEFAULT),
(231,8800,'Wellfleet',14236,40,80000,'Farhaan Donald',9200,null,DEFAULT,07654345678,07999999222,DEFAULT),
(123,9950,'Earl Shilton',41023,20,90000,'Alaya Dowling',9000,null,DEFAULT,07666666666,07212121212,'Thursday 12pm - 5pm'),
(005,4100,'Riverside',45232,10,11111,'Bo Vickers',9000,null,DEFAULT,07333333333,07654213212,DEFAULT),
(078,3000,'Moses Lake',741,42,11000,'Mercedes Tyler',9000,null,DEFAULT,07333333334,null,DEFAULT),
(030,6500,'Odessa',53200,48,12000,'Meerab Henry',9000,null,DEFAULT,07333333332,null,DEFAULT),
(951,790,'Massena',875,74,13000,'Tristan Fraser',9000,null,DEFAULT,07333333339,null,DEFAULT),
(131,2030,'Ossett',630,12,14000,'Jeff Bezos',9000,null,DEFAULT,07333333337,null,DEFAULT),
(928,163,'Shipston-on-Stour',100,9,15000,'Reeva Prince',9000,null,DEFAULT,07333333321,null,DEFAULT);

INSERT INTO adbt065Staff values(85211,'Axl Young',9000,030,null,null),
(45632,'Maddie Malone',9000,928,null,null), 
(11111,'Bo Vickers',9000,005,null,null), 
(52102,'Lilliana Woodard',1000,452,null,85211),
(41203,'Marwa Mccarthy',1000,123,null,85211),
(01354,'Matas Bonilla',500,452,41203,85211),
(63012,'Stevie Hills',500,005,52102,45632),
(15964,'Gabriella Matthews',500,005,52102,45632),
(75412,'Ferne Reilly',500,732,52102,45632),
(88888,'Keri Sheldon',500,030,52102,11111),
(77441,'Carrie Perez',250,030,41203,11111),
(22316,'Timur Keller',500,732,52102,11111),
(99511,'Ellisha Leech',500,030,41203,11111),
(74032,'Havin Thompson',500,030,41203,11111),
(99999,'Lilly-Grace Ventura',500,030,52102,11111);

INSERT INTO adbt065Secretary values(85211,'Axl Young',9000,030,null,null,120),
(41203,'Marwa Mccarthy',1000,123,null,85211,89),
(01354,'Matas Bonilla',500,452,41203,85211,91),
(52102,'Lilliana Woodard',1000,452,null,85211,69);

INSERT INTO adbt065Engineer values(45632,'Maddie Malone',9000,928,null,null,'Y','Software'), 
(63012,'Stevie Hills',500,005,52102,45632,'N','Mechanical'),
(15964,'Gabriella Matthews',500,005,52102,45632,'Y','Software'),
(75412,'Ferne Reilly',500,732,52102,45632,'Y','Mechanical');

INSERT INTO adbt065Manager values(85211,'Axl Young',9000,030,null,null,07456321023,07456321023,'All day Thursday'),
(45632,'Maddie Malone',9000,928,null,8500,02046547888,02046547888,'24/7'),
(11111,'Bo Vickers',9000,005,null,null,07862332115,02084521123,DEFAULT);

INSERT adbt065SecretaryManager values(85211,'Axl Young',9000,030,null,null,07456321023,07456321023,'All day Thursday',85211,120,201201);
              
INSERT adbt065EngineerManager values(45632,'Maddie Malone',9000,928,null,8500,02046547888,07048547888,'24/7',45632,'Y','Software',12);

INSERT adbt065Stock values(7412321,null,001,452,00002,84,null),
(1252325,'bigboyaman',001,020,00002,95,200412),
(0000220,'bigboyaman',001,221,00005,21,151011),
(1122012,null,001,078,00008,1,null),
(7754123,null,002,652,00001,21,null),
(4422112,null,001,078,00008,487,null),
(9933652,'AwkwardFalcon',001,652,00008,210,110110),
(0123456,'Emoo',001,963,00006,22,120415),
(1234567,'IronPirate',003,963,0000001,78,130604),
(2345678,'NervousHatchling',001,005,0000007,69,191014),
(3456789,'Jaguardo',003,005,0000001,96,110110),
(4567891,'koalava',001,963,0000008,38,110111),
(5678910,'NervousHatchling',001,963,0000004,83,121212),
(6789101,'stacySols',002,005,0000009,12,111111),
(7891011,null,002,963,0000009,7,101407);

/* SECTION 3 UPDATE STATEMENTS */

UPDATE adbt065Customer SET firstName = 'Amanda' WHERE username = 'Bansheep';
UPDATE adbt065Staff SET salary = 1500 WHERE staffID = 88888;

/* SECTION 4 SINGLE TABLE QUERIES */

/* 1) Display the most customers a company has AGGREGATE */

SELECT MAX(noOfCustomer) AS 'Most customers' FROM adbt065Seller;

/* 2) Display the least customers a company has AGGREGATE.*/

SELECT MIN(noOfCustomer) AS 'Least customers' FROM adbt065Seller;

/* 3) List all stockIDs purchased on January 10th 2011. VALUE COMPARISON*/

SELECT stockID FROM adbt065Stock WHERE purchDate = 110110;

/* 4) List all manager full names who's office hours are 'All day Thursday'. STRING SEARCH*/

SELECT fullName FROM adbt065Manager WHERE officeHours = 'All day Thursday';

/* 5) List the items that start with T in ascending order. STRING SEARCH AND ORDER BY CLAUSE*/

SELECT name FROM adbt065Item WHERE name LIKE 'T%' ORDER BY name ASC ;

/* 6)  Display the email addresses and the name (in that order) of all large organisations who have not given a contact number and group by number of customers.*/

SELECT email, companyName FROM adbt065largeOrganisation WHERE contactNumber IS NULL;

/* SECTION 5 MULTIPLE TABLE QUERIES */

/* 1) List all the customer records who bought an item from 'The Game Company' TABLE JOIN */

SELECT DISTINCT c.* FROM adbt065Customer c, adbt065Stock s, adbt065Seller se, adbt065Seller se2
WHERE c.username = s.username AND s.sellerID = se.sellerID AND se.sellerID = se2.sellerID
AND se2.companyName = 'The Game Company';

/* 2) What was the name of the company that sold an item to stacySols? SUB QUERIES*/

SELECT companyName FROM adbt065Seller WHERE sellerID IN (SELECT sellerID FROM adbt065Stock WHERE username IN (SELECT username FROM adbt065Customer WHERE username = 'stacySols'));

/* 3) List all staff names that are managed by staffID 45632 TABLE JOIN */

SELECT s2.fullName FROM adbt065Staff s, adbt065Staff s2 WHERE s.staffID = 45632 AND s.staffID = s2.managerID;

/* 4) List all customer usernames and their third addresses that have bought exactly one stock item. Group these customers by their third address */

SELECT c.username, c.addressThirdLine FROM adbt065Customer c, adbt065Stock s WHERE c.username = s.username GROUP BY s.username HAVING COUNT(s.username) = 1;

/* 5) List all investor first names that own more stocks than Maxwell Charly. */

SELECT i2.firstName FROM adbt065Investor i, adbt065Investor i2 WHERE i.firstName = 'Maxwell' AND i.lastName = 'Barnes'
AND i2.currentStocksOwned > i.currentStocksOwned;

/* 6) List all customer records that haven't bought an item from a seller in the Games category */

SELECT * FROM adbt065Customer WHERE username NOT IN (SELECT username FROM adbt065Stock WHERE sellerID IN (SELECT sellerID FROM adbt065Seller WHERE category = 'Games') AND username IS NOT NULL);

/* SECTION 6 DELETE ROWS (make sure the SQL is commented out in this section)

DELETE FROM adbt065Staff WHERE staffID = 74032;
DELETE FROM adbt065Staff WHERE staffID = 99999;

*/

/* SECTION 7 DROP TABLES (make sure the SQL is commented out in this section)

DROP TABLE adbt065Customer;
DROP TABLE adbt065Investor
DROP TABLE adbt065SubscriptionMember;
DROP TABLE adbt065Seller;
DROP TABLE adbt065largeOrganisation;
DROP TABLE adbt065Individual;
DROP TABLE adbt065Item;
DROP TABLE adbt065WarehouseandWarehouseManager;
DROP TABLE adbt065Staff;
DROP TABLE adbt065Secretary;
DROP TABLE adbt065Engineer;
DROP TABLE adbt065Manager;
DROP TABLE adbt065EngineerManager;
DROP TABLE adbt065SecretaryManager;
DROP TABLE adbt065Stock;

*/


