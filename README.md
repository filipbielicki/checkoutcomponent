# checkoutcomponent
Web App / Spring Boot / REST API / PostgreSQL / JPA / Lombok / UIATests

SUMMARY:<br>
Web application with RESTful API developed with MVC design pattern using Spring Boot 1.5.8.<br>
Annotation-based configuration.<br>
Build tool: Maven.<br>

DB & SERVER CONFIGURATION:<br>
database: postgresql<br>
schema: public<br>
table: item<br>
column1: id (bigint) pkey not null<br>
column2: price (double precision)<br>
column3: quantity_for_special_price (integer)<br>
column4: special_price (double precision)<br>

Database configuration available below and in: resources/application.properties<br>

spring.jpa.database=POSTGRESQL<br>
spring.datasource.platform=postgres<br>
spring.jpa.show-sql=true<br>
spring.jpa.hibernate.ddl-auto=validate<br>
spring.datasource.url=jdbc:postgresql://localhost:5432/mynewdatabase<br>
spring.datasource.username=postgres<br>
spring.datasource.password=postgres<br>
server.port=8080<br>
server.tomcat.max-connections=30<br>

Recommended for start up:<br>
spring.jpa.hibernate.ddl-auto=create-drop<br>

Once the table is created and data are inserted change to:<br>
spring.jpa.hibernate.ddl-auto=validate<br>

ASSUMPTIONS:<br>
Initial data to insert into DB (id generated automatically):<br>
(column2, column3, column4) VALUES (40,3,70),(10,2,15),(30,4,60),(25,2,40).<br>
Acceptance test cases assumptions are described as comments to test methods.<br>

BUILD & EXECUTE:<br>
1. Go to the project folder and launch command line window <br>
2. Enter: mvnw clean install<br>
3. Once the project is build enter: java -jar target\nameofjarfile.jar (in this case: checkoutComponent-0.0.1-SNAPSHOT.jar)<br>
4. Application is launched and ready to use with a browser<br>
5. Have fun and grab a beer<br>

URL:<br>
Get all items: http://localhost:8080/allrecords<br>
Get one item: http://localhost:8080/allrecords/items/ids/{itemid}<br>
Calculate total price of n items: http://localhost:8080/allrecords/items/ids/{itemid}/{qty}<br>
Insert new item into database: http://localhost:8080/allrecords/insert/{price}/{qty}/{specprice}<br>
