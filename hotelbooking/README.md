## API Description 
<strong>In the context of a technical test </strong>,  this is an application for booking a single room in hotel <br>
<strong> application is not secured</strong> <br> 
and there is a single room in the sql file to be added 
## INSTALL DATABASE 
Execute the script file "create.sql"

## BUILD AND TEST
mvn clean install

## RUN APPLICATION 
mvn spring-boot:run

## API STATE
To monitor api state: <br>
http://localhost:8080/api/actuator/health/ <br>
http://localhost:8080/api/actuator/health/liveness <br>
http://localhost:8080/api/actuator/health/readiness <br>
## API DESCRIPTION 
http://localhost:8080/api/swagger-ui/index.html#/