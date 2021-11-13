# Open Bank Beneficiary Api

- Overview
- Files
- Apis
- Docker
- Kafka
- Postgres & Adminer
- Docker-Compose
- JWT
- Testing
- Kubernetes


## Overview

---
The beneficiaries' resource is used by an AISP to retrieve the account beneficiaries information for a specific AccountId or to retrieve the beneficiaries information in bulk for account(s) that the PSU has authorised to access.

## Apis

---
This Beneficiary service allow you accessing below objects: 
- beneficiary
- creditorAccount
- creditorAgent
- postalAddress


### Api Endpoints 

The beneficiary contains creditorAccount object and creditorAgent object; and creditorAgent contains postalAddress.
You can access to endpoints as describing below: 
- Get /beneficiaries, and this end point for retrieving all beneficiaries.
- Post /beneficiary{?topic,key}, this endpoint for create single beneficiary object, the topic and key should not be null to emit the object to kafka.
- Put /beneficiary{/BeneficiaryId}{?topic,key}, this endpoint for the update a beneficiary object or updating related object depending on a beneficiary id, and the topic and key should not be null to emit the object to kafka.
- Delete /beneficiary{/BeneficiaryId}, this endpoint for delete beneficiary by the id.
- Get /account{/AccountId}/beneficiaries, this endpoint will retrieves beneficiary depending on account id.

### Api Endpoints Status
| Http Method | Endpoint        | Http Status     | Description     |
| ----------- | --------------- | --------------- | --------------- |
| Get         | /account        | 200             |  will retrieve 200 in success process and may retrieve empty data object or data object with list of beneficiaries in the system related to th account |
| Get         | /beneficiaries  | 200             |  will retrieve 200 in success process and may retrieve empty data object or data object with list of beneficiaries in the system |
| Post        | /beneficiary    | 201             |  will retrieve 201 in success process and created object |
| Post        | /beneficiary    | 409             |  will retrieve 409 in case the object already created in exist in the system|
| Post        | /beneficiary    | 400             |  will retrieve 400 in you send invalid data |
| Post        | /beneficiary    | 500             |  will retrieve 500 in case we have internal server error |
| Put         | /beneficiary    | 200             |  will retrieve 200 in success process and will updated object |
| Put         | /beneficiary    | 400             |  will retrieve 400 in you send invalid data |
| Put         | /beneficiary    | 500             |  will retrieve 500 in case we have internal server error |
| Delete      | /beneficiary    | 204             |  will retrieve 204 in case the object deleted and data to retrieve|
| Delete      | /beneficiary    | 400             |  will retrieve 400 in case we the object not found |
| Delete      | /beneficiary    | 409             |  will retrieve 409 in case you try to delete a deleted object. |
| Delete      | /beneficiary    | 500             |  will retrieve 500 in case we have internal server error |

#### for more information about the service visit [SWAGGER-UI](http://localhost:8080/swagger-ui#/default/deleteBeneficiary) after run the server.

## Kafka

---
We used Kafka in two operations in this service to emit the events to other systems 
- create 
- update

you can run kafka from docker by using below docker-compose.yml file
```

kafka-cluster:
image: lensesio/fast-data-dev:2.2
environment:
      #ADV_HOST: host.docker.internal # when trying to run the service and kafka in docker same time
      ADV_HOST: 127.0.0.1 # trying to run the service and kafka sepratly.
      RUNTESTS: 0
      FORWARDLOGS: 0
      SAMPLEDATA: 0
    ports:
      - 2181:2181
      - 3030:3030
      - 8085-8087:8085-8087
      - 9581-9585:9581-9585
      - 9092:9092


```

### you can access to kafka interface using (http://localhost:3030) and Broker works on port (9092)

## Postgres & Adminer

--- 
We are using Postgres as database and can run it using below docker-compose.yml file with user interface called adminer.

```

  # Database:
  database:
    container_name: open-bank-beneficiary-db
    image: postgres:12.4-alpine
    environment: 
      POSTGRES_DB: open-bank-beneficiary
      POSTGRES_PASSWORD: secret
    ports: 
      - 5432:5432
  
  # Database Interface:
  adminer: 
    container_name:  open-bank-beneficiary-adminer
    image: adminer:4
    ports: 
      - 8082:8080

```


## Docker

---
We are using Docker to run the service and related technology,
you can run each technology separately as describing in kafka and  Postgres & Adminer, or
you can run the service and all technology together after build docker images.


### Dockerfile
#### Note Jar file accept argument for adapting with environments.
```

FROM openjdk:8-jdk-alpine
WORKDIR /com-openbank-service-beneficiaries
COPY ./build/libs/com-open-bank-service-beneficiaries-0.1-all.jar  /com-open-bank-service-beneficiaries-0.1-all.jar

ENV PG_SERVER_URL=localhost:5432
ENV PG_DB_NAME=open-bank-beneficiary
ENV PG_USERNAME=postgres
ENV PG_PASSWORD=secret
ENV KAFKA_SERVERS=localhost:9092
ENV JWT_TOKEN=pleaseChangeThisSecretForANewOne

ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -DPG_SERVER_URL=${PG_SERVER_URL} -DPG_DB_NAME=${PG_DB_NAME} -DPG_USERNAME=${PG_USERNAME} -DPG_PASSWORD=${PG_PASSWORD} -DKAFKA_SERVERS=${KAFKA_SERVERS} -DJWT_TOKEN=${JWT_TOKEN} -jar /com-open-bank-service-beneficiaries-0.1-all.jar

```

after config docker file you should build image using below command and make sure you are in downloaded service folder,
then run below command in CMD.
> docker build -t com-openbank-service-beneficiaries:007 .

then run image using below command and passing arguments
>  docker run -e "PG_SERVER_URL=asdddd" -it com-openbank-service-beneficiaries:007


## Docker-Compose 

--- 
You can run Kafka, Postgres, Adminer and the service all together using below docker-compose.yml file.
You can pass arguments to docker images files as below ...
```

version: '3.8'
services: 
  
  # Database:
  database:
    container_name: open-bank-beneficiary-db
    image: postgres:12.4-alpine
    environment: 
      POSTGRES_DB: open-bank-beneficiary
      POSTGRES_PASSWORD: secret
    ports: 
      - 5432:5432
  
  # Database Interface:
  adminer: 
    container_name:  open-bank-beneficiary-adminer
    image: adminer:4
    ports: 
      - 8082:8080

  # Kafka
  kafka-cluster:
    image: lensesio/fast-data-dev:2.2
    environment:
      ADV_HOST: host.docker.internal
#      ADV_HOST: 127.0.0.1
      RUNTESTS: 0
      FORWARDLOGS: 0
      SAMPLEDATA: 0
    ports:
      - 2181:2181
      - 3030:3030
      - 8085-8087:8085-8087
      - 9581-9585:9581-9585
      - 9092:9092

  # Backend Service: 
  beneficiary-service:
    container_name: open-bank-beneficiary-service
    # build: ./com-open-bank-service-beneficiaries
    image: com-openbank-service-beneficiaries:007
    ports:
      - 8080:8080
    depends_on:
      - database
      - kafka-cluster
    environment:
      - PG_SERVER_URL=host.docker.internal:5432
      - PG_DB_NAME=open-bank-beneficiary
      - PG_USERNAME=postgres
      - PG_PASSWORD=secret
      - KAFKA_SERVERS=host.docker.internal:9092
      - JWT_TOKEN=pleaseChangeThisSecretForANewOne
    
```

after create this docker-compose.yml file run below command to start the services: 
> docker-compose -f docker-compose-file-name.yml up -d

and for shutdown run below command
> docker-compose -f docker-compose-file-name.yml down

#### Note The files already created inside the project with name [/full-docker-compose.yaml]


## JWT

---
Not Configured yet.

## Testing

--- 
in Testing java folder, contains some test case for Authorized endpoints and  unauthorized endpoints.
to test the clases please remove @Disabled annotation from a header of class.

for example 
```

@Disabled // please remove this when try test it.
@MicronautTest
class UnAuthBeneficiariesControllerTest {


    private static final Logger log = LoggerFactory.getLogger(UnAuthBeneficiariesControllerTest.class.getName());


    @BeforeAll
    public static void setupServer () {

        TestServer.setupServer();
    }

```

## Kubernetes

--- 
The service and deployments file will include inside the projects
go to downloaded service project > yaml will find yml files for each technology, 
the file is contain deployment and service kubernetes resources types