-------** Order Processing Service : E-Commerce **------
## Overview ##
* This is a Spring Boot microservice that handles order processing.  
* It exposes REST APIs to create single orders, upload batch orders from CSV,  
  publishes simulated order events, and generates daily summary files.

## Tech Stack ##

* Java 17
* Spring Boot (Web, JPA, H2, Scheduling)
* Maven
* H2 In-Memory Database

## STEPS TO FOLLOW ##

1. Clone or open in IntelliJ

     git clone <your_repo_url>
     cd order-processing-service

2. Build and run
   * mvn spring-boot:run
   * or run `OrderProcessingServiceApplication.java` in your IDE.

3. The service runs on *http://localhost:8080*
   4. Example requests,
      * Single orders: - POST http://localhost:8080/api/orders

                    - Content-Type: application/json

                
                    - "productCode": "P001",
                      "quantity": 2
      * Response:
      ```json
         {
              "id": 1,
               "productCode": "P001",
               "quantity": 2,
               "price": 100.0,
               "status": "CONFIRMED",
               "createdAt": "2025-10-16T20:30:15"
         }
5. Batch Upload from CSV
   navigate to this link : POST http://localhost:8080/api/orders/batch
   Form-data key → file (type: File) → select orders.csv

   Example CSV: productCode,quantity
                P001,2 
                P002,3
                P001,1

 6. Daily Summary: A scheduled task runs every minute (demo mode) and generates " uploads/order-summary-YYYY-MM-DD.txt "       
 
 7. Access H2 Console

    URL → http://localhost:8080/h2-console

    JDBC URL → jdbc:h2:mem:ordersdb

    User → sa, Password → none
8. Postman Validation:
   1. Open Postman → create a new **Collection** called “Order Processing API”.
   2. Add 2 requests:
     - `Create Single Order` → `POST /api/orders`
     - `Batch Upload Orders` → `POST /api/orders/batch`
   
-----------END--------------      



