BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `Place` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`place`	TEXT NOT NULL,
	`lat`	TEXT NOT NULL,
	`long`	TEXT NOT NULL
);
INSERT INTO `Place` (id,place,lat,long) VALUES (1,'Bashundhara Gate','23.811924','90.421480'),
 (2,'Bank Asia','23.8129022','90.427968'),
 (3,'Welfare Society','23.8134568','90.4275442'),
 (4,'Apollo Hospital','23.8099115','90.4288636'),
 (5,'Convention Center-5','23.8194649','90.4256957'),
 (6,'Mehedi Mart','23.8166777','90.4337441'),
 (7,'60 Feet Security Point','23.812716','90.425523'),
 (8,'North South University','23.8150425','90.4247449'),
 (9,'NSU','23.8150425','90.4247449'),
 (10,'C block Mosjid','23.8195695','90.4258841'),
 (11,'Media Center','23.812744','90.4310231'),
 (12,'IUB','23.8155366','90.4272359');
CREATE TABLE IF NOT EXISTS `Fare` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`source`	TEXT NOT NULL,
	`destination`	TEXT NOT NULL,
	`fare`	INTEGER NOT NULL
);
INSERT INTO `Fare` (id,source,destination,fare) VALUES (1,'Bashundhara Gate','Bank Asia',20),
 (2,'Bashundhara Gate','Welfare Society',20),
 (3,'Bashundhara Gate','Apollo Hospital',25),
 (4,'Bashundhara Gate','Convention Center-5',25),
 (6,'Bashundhara Gate','60 Feet Security Point',35),
 (7,'Bashundhara Gate','NSU',20),
 (8,'Bashundhara Gate','North South University',20),
 (9,'Bashundhara Gate','C block Mosjid',25),
 (10,'Bashundhara Gate','Media Center',25),
 (11,'Mehedi Mart','NSU',20),
 (12,'Mehedi Mart','North South University',20),
 (13,'Mehedi Mart','60 Feet Security Point',20),
 (14,'Mehedi Mart','C block Mosjid',20),
 (15,'Mehedi Mart','Bank Asia',50),
 (16,'Apollo Hospital','60 Feet Security Point',30),
 (17,'Apollo Hospital','Mehedi Mart',25),
 (18,'Apollo Hospital','NSU',20),
 (19,'Apollo Hospital','North South University',20),
 (20,'VNS','North South University',15),
 (21,'VNS','60 Feet Security Point',20),
 (22,'VNS','C block Mosjid',10),
 (23,'VNS','IUB',15),
 (24,'Bashundhara Gate','Mehedi Mart',30);
COMMIT;
