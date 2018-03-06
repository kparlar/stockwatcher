# Stockwatcher
Dummy Stock Watch Application with Front-end written in VUE2 framework.
This is an example project for implementing a given use cases.
I have given a name Stockwatcher.
Have fun.

# Tech Stack Card

## Back-End
* Java Spring Boot 1.5.9.RELEASE
* JPA: Hibernate 
* Database:   H2 Database
* Unit-Test:  Junit 4
## Front-End
* FrontEnd: VueJS 2.5.2
* WebPack: 
* Eslint: 
* Unit Test:
    * Test-Runner: Karma
    * Test-Luncher: PhantomJS
    * Unit Framework: Mocha
    * Stubbing : SinonJS, sinon-stub-promise
    * Assertion: Sinon-Chai
    
## Api End Points
Swagger annotation is used in this project.
You can check it from this link, http://localhost:8010/swagger-ui.html#/
Also here is the screenshot for swagger.

![Swagger](img/swagger.PNG?raw=true "Swagger")

## Postman Collections 
Postman Collections are in the postman folder. There is a screenshot below.


![Postman Request Samples](img/postman.PNG?raw=true "Postman Request Samples")


## Entity Model
Given below Stockwatcher Project entity model. JPA is used as an api, and InheritanceType.TABLE_PER_CLASS type
strategy is used to make it generic for each entity classes, so inheritance is easily implemented to this
entity model.

![Entity Model](img/entity_model.PNG?raw=true "Entity Model")

## Database
H2 db is used, given below there is a image how to login the H2 db after starting the application
Tables are also given below.

![H2 Console for Powerfiler](img/h2_console.PNG?raw=true "H2 Console for Powerfiler")
![Dtabase Tables](img/db_tables.PNG?raw=true "Dtabase Tables")



## Back-End UnitTest
21 Unit Tests are written with %68 class coverage, %86 line coverage. This will be leverage but these tests will show how i handle test cases.

![Back-End Unit Test Coverage](img/test-coverage-back-end.PNG?raw=true "Unit Test Coverage")

## Front-End UnitTest
3 Unit Tests are written with %37 line coverage. This is just to show how i handle the test cases.
There is a mock framework library used (SinonJS) and also assertion library used (Chai). For Test Runner Karma is prefered and Mocha Test Framework JS library is used for writing unit test in javascript.  Sinon-stub-promise is used for Service testing stubbing promise operations.

![Front-End Unit Test Coverage](img/test-coverage-back-end.PNG?raw=true "Unit Test Coverage")

## How To Run
After git clone, you can directly run from your ide as any spring-boot project. When the application stated
you can use the postman request to insert data or get data.
Or if you want to run jar, first you have to clone the project from repository, inside the powerfiler folder run first
**mvn package** (run this under services-stockwatcher folder)
this will build a jar called **stockwatcher-0.0.1-SNAPSHOT.jar** under target folder. 
Than you can run the application with  **java -jar stockwatcher-0.0.1-SNAPSHOT.jar** command.

when the service is on, it will host on port 8010. Here I use Flyway to ingest data under folder
\services-stockwatcher\src\resources\db\migration\V1_2__init.sql. I will also use liquibase but prefer this one, this is much more easy implementation, but i admire Liquibase.

For Service query please take a look to Postman Section
 
 
 For Front-end  under \stockwatcher\web\web-stockwatcher-vue   folder from command line run
**npm run dev** this will run webpack first build the VUE project and if there is no error gives the path of the application like given below.  ( you can display the web ui from http://localhost:8080) 

![Start Front-End](img/start-front-end.PNG?raw=true "Start Front End")

I have use Eslint for standardization in Javascript: 
please take a look to this page for standardization that i used.
https://github.com/standard/standard/blob/master/docs/RULES-en.md

Used Babel for compiler.

Not added Sass cause it is a small project. maybe added later.

Used Webpack for bundling javascript, CSS and template staffs.

If you have any question feel free to ask.

## Code Quality
There are 8 sonar issues left to resolve , they are not a big issue but needs time to solve them.
![SONAR](img/sonar.PNG?raw=true "SONAR")


## Screens

Here some screens from application given below.
![Screen 1](img/screen_1.PNG?raw=true "Screen 1")

![Screen 2](img/screen_2.PNG?raw=true "Screen 2")

![Screen 3](img/screen_1.PNG?raw=true "Screen 3")

![Screen 4](img/screen_4.PNG?raw=true "Screen 4")
 