<h1 align="center">Account App</h1>

### 📝 Responsibilities
---

✅ The API will expose an endpoint which accepts the user information(customerID, initialCredit)

✅ Once the endpoint is called, a new account will be opened connected to the user whose ID is customerID.

✅ Also, if initialCredit is not 0, a transaction will be sent to the new account.

✅ Another endpoint will output the user information showing Name, Surname, Balance and Transactions of the accounts.

✅ Attention to CI/CD

### 💻 Technologies used in the project
---

*   Java
*   Spring Boot
*   Spring Data JPA
*   N-Tier Architecture
*   Maven
*   AOP 
*   H2 Database
*   JUnit, Mockito(Unit Testing)
*   Postman
*   Docker

### 🚀 Run & Build
---

*   Docker
    $ cd account
    $ docker build -t account:1.0 .
    $ docker run --name account -d -p $PORT:8080 account:1.0

<h2>📧 Contact</h2>

Görkem Yelken - [gorkemyelken@gmail.com](mailto:gorkemyelken@gmail.com)
