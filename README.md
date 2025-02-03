This is the gateway for the csms system where client can send their token in form of id to be verified and authenticated.

the repository for transactionms
- [authenticationms](https://github.com/raka-bakar88/authenticationms)

How to run the application:
1. clone both of the projects to your local machine
2. using terminal, go the root directory of each project, 
   - in root project transactionms, run this command in terminal "docker build -t transactionms ."
   - in root project authenticationms, run this command in terminal "docker build -t authenticationms ."
3. go to root project of transactionms
4. run this command in terminal to run the docker compose : docker-compose up -d
5. once the containers are up, tests the rest api in postman
   POST http://localhost:8080/v1/transaction/authorize
   request body :
   {
    "stationUuid": "25aac66b-6051-478a-95e2-6d3aa343b025",
    "driverIdentifier": {"id": "id1abcdefghijklnmnopqrstuvwxy"}
}
   id that can be tested : 
   - id1abcdefghijklnmnopqrstuvwxy for ACCEPTED status
   - id29876543210987654321 for REJECTED status
   - aaaaaaaaaaaaaaaaaaaaa for UNKNOWN status
   - shortid for INVALID status

   
techstack:
- Spring boot java 17
- Mysql database
- Apache Kafka
- Mockito

Thoughts on code improvements:
- More unit test to increase the test coverage are needed to reduce bugs and unlikely case of errors
- implements CircuitBreaker patterns

Thoughts on Scaling :
As the system could expand, some recommendations for expansion as follow :
- The implementation of Spring Eureka is necessary for registering and discovering other microservices. it will help maintaining the status of microservices
- Spring cloud gateway would give benefit to provide single entry point to the microservice, strengthen the security too
