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
	* path：..\src\main\resources\application.propertirs
	* update your datasource database、username、password

2. run spring boot
	* run only once
		```
		mvn install
		```
	* run server
		```
		mvn spring-boot:run
		```

3. Restful API
	* GET：http://localhost:8080/customers
	* GET：http://localhost:8080/customers/{id}
	* POST：http://localhost:8080/customers
		* example request body
			```
			 {
				"name": "sam"
			 }
			```
	* PUT：http://localhost:8080/customers
		* example request body
			```
			 {
				"name": "samLien",
				"id":1
			 }
			```
	* DELETE：http://localhost:8080/customers/{id}
