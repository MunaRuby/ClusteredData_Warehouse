
# ClusteredData Warehouse

This app is to accept deals details from and persist them into DB.

One can perform the following operations:
* Create new deals
* Find all created fx_deals 
* Get a particular deal using the unique id.

##  Quick Run - Docker
```sh
docker-compose up
```
* The first run will fail because mysql database is yet to download completely. This will reconnect to sync up with the database.
* Error may also occur if the port is in use, you may change the port on docker-compose.yml file.


## Project Run
#### How to run the project
* Go to application.yml in resources package and input your MYSQL username and password.

* Run this command
```sh
 mvn clean spring-boot:run
```

## Project Documentation

### Technology Used
* SPRINGBOOT
* MYSQL
* MAPSTRUCT
* DOCKER

## Project Packages
### Controller
* POST - /create - is used to create a new fx deal.
* GET - /get/{id} - is used to get a particular fx deal using unique Id.
* GET - /fx-deals - used to get all existing deals in the database.

### Exception
* This class handles custom and validation exceptions

### Logging
* Spring ASPECT was used to handle logging automatically.
* Errors from the controller, services and repositories are logged

### Model

* id - id for numbering on the database : Long
* uniqueId - Unique id is unique to a particular fx_deal: String-UUID
* fromCurrency - ISO Currency from deal: Currency
* toCurrency - ISO Currency to deal with Datatype: Currency
* dealTimestamp - Instant time of deal use epoch time: Instant
* dealAmount - Amount of fx deal or transaction: BigDecimal

### Repository
* Spring data JPA was used

### Service and Impl
* Fx Deal DTO(Data Transfer Object).
* Service Implementation which contains the business logic.
* Mapper for converting from model to DTO and vise versa
* Validation was also carried out to confirm that the to and from currencies are different.

### Request body
{
    "fromCurrency" : "USD",

    "toCurrency" : "NGN",

    "dealTimestamp" : "1665689375",

    "dealAmount": 50
}

### Success Response
```json
{
  "uniqueId": "eb298619-ee90-4138-8fc1-2c845be350c7",
  "fromCurrency": "USD",
  "toCurrency": "NGN",
  "dealTimestamp": "2022-10-13T19:29:35Z",
  "dealAmount": 50
  
}
```

### Error Response
```json
{
  "status": "Failed",
  "message": "An error has occurred at entity",
  "data": {
    "statusCode": 500,
    "message": ""
  }
}
```

TEST
- Unit test in the test folder
