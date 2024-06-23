
### **Task Execution Report Microservice**

This microservice exposes REST APIs to manage task execution reports and task step execution reports.

### Models
1. **TaskExecutionReport** - Represents a report for a task execution. Contains attributes like taskId, executionTimeSeconds, status etc.
2. **TaskStepExecutionReport** - Represents a report for a step in a task execution. Contains attributes like stepName, executionTimeSeconds, status etc.

### Endpoints

executeTaskReport

* ####  **TaskExecutionReport**
     1. POST /taskExecutionReports?taskId - Creates a new task execution report for specific task 
     2. POST /taskExecutionReports/executeTaskReport/{taskId} - Execute task execution report for specific task set end date time

     3. GET /taskExecutionReports - Gets all task execution reports

     4. GET /taskExecutionReports/{id} - Gets a task execution report by id

     5. PUT /taskExecutionReports/{id} - Updates an existing task execution report

     6. DELETE /taskExecutionReports/{id} - Deletes a task execution report
     7. DELETE /taskExecutionReports - Delete All task execution reports

     8. GET /taskExecutionReports/status/{status} - Gets all task execution reports with a given status

     9. GET /taskExecutionReports/findAllSortedByExecutionTime - Gets all task execution reports sorted by execution time
     
     10. GET /taskExecutionReports/{TaskExecutionReportId}/taskSteps - Gets all task steps within a specific TaskExecutionReport

* ####  **TaskStepExecutionReport**


Similar CRUD endpoints for TaskStepExecutionReport

   1. GET /taskStepExecutionReports/findByTaskExecutionIdOrderByStartDateTime/{taskExecutionReportId} - fetch all the TaskStepExecutionReport within a specific TaskExecutionReport sorted by startDateTime
   2. GET /taskStepExecutionReports/findByTaskExecutionIdOrderByExecutionTimeSeconds/{taskExecutionReportId} - fetch all the TaskStepExecutionReport within a specific TaskExecutionReport sorted by executionTimeSeconds

###  **Usage**

The service can be built using Maven and run using the Spring Boot Maven plugin.

Unit tests can be run using mvn test   // faced some issue on my local so but should be running 


### **To run the application**
navigate to the project directory and run the following command:

`mvn spring-boot:run`

**Postman collection to run the test steps available at ./resources/postman**

The application will start at http://localhost:8080.

The application   H2 console available at 'http://localhost:8080/h2-console' .

**Dependencies:**

   1. Spring Boot
   2. Spring Web 
   3. 3.Spring Data JPA
   4. H2 Database
   5. Lombok
