# T<sup>3</sup>
## Travel fare service
---
### 1. Purpose
This solution is a prototype of fare prices calculation service. Project contains service for management of taxi drivers information and service for price calculation.

My goals for this solution are:
* To check use of ASP.NET and JavaEE in same solution.
* To learn configuring JavaEE and WildFly to work with MS SQL datasource.
* To learn more about Angular.

The technology stack for this project:
* Database: MS SQL Server (tested on version 15.0.2080)
* Drivers management back-end: ASP.NET Core MVC (tested on version 5.0.9) with IIS server
* Fare prices calculation back-end: JavaEE (compiled with Java 11.0.12, Maven 3.6.1) with WildFly server (version preview-26.0.0.Final)
* Both service's frontends: Angular (cli 12.2.14, typescript 4.3.5)
---
### 2. Drivers management - T3Drivers
It's done by simple crud application. On client side it utilizes Angular application with usage of FormsModule and Twitter Bootstrap's styles. Server side hosts angular application and handles WebAPI interface. It connects to MS SQL database.

All fields are required and e-mail address must be provided in correct format.
Validation is done using Angular's FormsModule embedded functionality.

Serverside is used just as "data brocker" between client and the database.
Beside of WebAPI handling, service is configured to redirect main page to angular's _index.html_, as well as to set proper resources paths (on angular config). 
Angular projects needs to be build manually.

The database scripts can be found in text file in drivers management catalogue.

#### Taxi driver base data format for solution's projects
|Field name |Client type | Server type(C#/J)|DB type(MSSQL)
|:----------|---|---|---
|Id|number|int/int|int
|Name       |string|string/String|varchar(max)
|Surname    |string|string/String|varchar(max)
|E-mail     |string|string/String|varchar(256)
|Vehicle Type|enum/enumish ts class*|string/String|varchar(50)
|Base Fare Price|number|double/double|float
|Base Fare Distance|number|double/double|float

*explained later
#### Taxi driver additional fields due to region specific
|Field name |Client type | Server type(C#/J)|DB type(MSSQL)
|:----------|---|---|---
|Name's Furigana|string|string/String|varchar(max)
|Surname's Furigana|string|string/String|varchar(max)
#### Taxi driver additional database columns
|Field name |DB type(MSSQL) | Server type(C#/J)|
|:----------|---|---
|Creation timestamp|DATETIME2|none*/String|
|Modification timestamp|DATETIME2|none*/String|

*not provided in drivers management panel yet 

Creation timestamp is intended for future use.

---
### 3. Fare prices calculation - fareinfo
It's implemented in JavaEE with Angular frontend. In prototype version it only supports fare calculation for single distance value provided in the fare information CSV file (embedded within EJB). 
#### CSV file format
````
Distance traveled,Traveled unit, Cost per distance traveled
````
Calculation is done for each taxi driver that:
* has no entry in fare prices history 
* modification timestamp in fare prices history is later than drivers record modification timestamp

For the rest of taxi drivers, records from fare prices history are used.
#### Fare prices history table
|Field name|DB type(MSSQL)|Server type (Java)
|---|---|---
|DriverId|int|Driver(model)
|FareData|varchar(64)|String
|PersistedFare|float|double
|Creation timestamp|DATETIME2|none*/String
|Modification timestamp|DATETIME2|none*/String

Creation timestamp is intended for future use.

### Calculation algorithm
Calculation of fare's price is done in a few steps:
1. If driver's base fare distance is greather or equal to traveled distance, as a price return driver's base fare price.
2. Else calculate diference in traveled distance and base fare deistance (D = TD - BFD)
3. Difference should be divided by travled distance units to get number of traveled units (No = D / TU)
4. The fare's price is calcualted from formula:
```
Fare's Price = Driver's base fare price + Number of traveled units * Cost per distance traveled
```
Every calculated price is then stored to Fares History in the database.

The client side recieves data of a separate model type, which is combination of neccessary driver information and fare price itself. The send data is unordered (to save server's resources), and sorted on a client's side.

Service is ready to extend fare prices calculation. By adding new EE bean to Resource class, and creating new data model implementing FareInfoI interface. 
However those changes have to be impelented also on the client side.

Maven is configured to build Angular client during install. Client application is in separate Maven project, but during installation it's is build directly in servlet's webapp folder (check client angular.json and pom.xml for details).

---
### 4. Handling of the Vehicle Type accross solution
The vehicle type is field that needs representation as an enum.
In this solution enums are only recognized on a client side, which is written in TypeScript - a language with rather basic enum support, in my humble opinion. Each client is implementing this enum in two different approaches(enum with util class and static class with public "enum" fileds).
Since currently server's side bussines logic does not process this field, it treats it as a string.

It's arguable if such approach should be implemented here, but here is why I decided to do so:

* Each project treats enums differently - e.g. in this prototype, drivers managment and fare price calculations clients uses produces different descriptions. With such approach each client project only processes enum as it needs.

* Enums could be handled on server side, with a proper implementation, **but** especialy in this solution mixing C# with Java, there will be still need to handle enum on both sides. Also doubts from point above still aplies here.

* Getting good grip on handling enums on client's side seems like a good goal itself. 

To summarize: I would really think about current approach in final version, but for a prototype it may suffice.

---
### 5. Log4j warning
Fare price calculation project is still in phase of checking what to do with logging. It contains dependencies which are using log4j versions with well known vulnerability. 


