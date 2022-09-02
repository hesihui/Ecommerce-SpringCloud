## What I have done:
- Designed and implemented the microservice architecture for the e-commerce backend system
- Integrated microservices with Alibaba Nacos to enable microservice registry and discovery
- Configured and Utilized SpringCloud Gateway to manage different APIs provided by the microservies while implementing SpringCloud Gateway filters to handle different HTTP requests from the client and internal calls
    - Some nacos alternative procuct: netflix eureka, coreDNS
    - Why choose nacos: nacos focuses more on constructing a service plantform and forming a service sharing community for open source projects, not only for the usage of service discovery
- Developed the user registration, authentication, and authority management microservice using JWT and RSA256 with Spring Security Framework
- Designed and implemented the data model and data access objects
using Hibernate
- Utilized Redis to enable instant data accessing (paginated product info)
- Implement distributed tracing for microservices with Spring Cloud Sleuth and Zipkin
- Utilized Junit to perform unit tests on REST APIs and main functionalities of the microservices

## Nacos Set up and Configuration
- For setting up nacos: see Nacos official guideline https://github.com/alibaba/nacos
- nacos version: 2.0.0
- sql script for setting up nacos user: see e-commerce-springcloud/sql/nacos_config.sql
- mysql version: 5.7

## Distributed Tracing setup:
- zipkin version: 2.21.7 https://repo1.maven.org/maven2/io/zipkin/zipkin-server/2.21.7/
- for more information, check: https://zipkin.io/pages/quickstart.html
- kafka version 2.7.0, download: https://kafka.apache.org/downloads
- For running service each time:
```shell 
# running kafka
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties
# running zipkin 
java -DKAFKA_BOOTSTRAP_SERVERS=127.0.0.1:9092 -jar zipkin-server-2.21.7-exec.jar --STORAGE_TYPE=mysql --MYSQL_HOST=127.0.0.1 --MYSQL_TCP_PORT=3306 --MYSQL_USER=root --MYSQL_PASS=Admin123 --MYSQL_DB=imooc_zipkin
```

## e-commerce-alibaba-nacos-client
- A module for testing nacos setup and getting the configuration of nacos

## e-commerce-common
- Provides the basic utils methods and constants for every microservice to access

## e-commerce-admin
- This microservice is aim to manage the microservice endpoints and send alarm when the microserve is 
- setting up spring boot actuator to manage the microservice endpoints
- the default context path is: http://127.0.0.1:7001/e-commerce-admin/ 
- username for spring admin service: 
  - username: admin
  - test password: 123456789

## ecommerce-authority-center
- This microservice provides authentication and authority management service using JWT and RSA256
- The data object for JWT Token, Login User information (id and username), user password and username are in src/main/java/com/ecommerce/vo
- For test purpose, 
  - the generated public key for testing is hard coded in /e-commerce-common/src/main/java/com/ecommerce/constant/CommonConstant.java
  - the generated private key for testing is hard coded in /e-commerce-authority-center/src/main/java/com/ecommerce/constant/AuthorityConstant.java
- Database tables documentation generator (only in Chinese version):
  - run /e-commerce-authority-center/src/test/java/com/ecommerce/DBDocTest.java
- Environment setup:
  - need to run /e-commerce-authority-center/src/main/resources/sql/t_ecoomerce_user.sql to create t_ecommerce_user database in MySql

## e-commerce-gateway
- This microservice serves as an API Gateway using SpringCloud gateway. 
- /config/DynamicRouteServiceImpl and /config/DynamicRouteServiceImplByNacos provides basic dynamic configuration sample
- Two global filters and one gateway filter provided:
  - GlobalCacheRequestBodyFilter: saves request body for future usage
  - GlobalLoginOrRegisterFilter: global authentication filter, check if a request is login or register, if not, forward to the corresponding mircrosrvice
  - HeaderTokenGatewayFilter: need to config through Nacos. check if header carries token

## e-commerce-service
- This microservice is the parent module of e-commerce services
  - ## e-commerce-service-config
    - setup swagger and web interceptors for user authentication 
  - ## e-commerce-service-sdk
    - define the general data objects passing via different microservices
      - for account data: AddressInfo, BalanceInfo, UserAddress
      - for common data accessing: tableId, read the primary key of a table
  - ## e-commerce-service-account-service:
    - provides the basic account service and balance service
    - 
