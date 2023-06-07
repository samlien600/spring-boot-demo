# spring-boot-demo

1. Configuration
* DB
	* default use mysql database
	* sql script
		```
		create database myjdbc;
		use myjdbc;
		create table customers(
			id int auto_increment primary key,
			name varchar(30) null
		);
		```
		
* update application.propertirs
	* path：C:..\spring-boot-demo\src\main\resources\application.propertirs
	* update your datasource database、username、password

1. run spring boot
	* run only once
		```
		mvn install
		```
	* run server
		```
		mvn spring-boot:run
		```




