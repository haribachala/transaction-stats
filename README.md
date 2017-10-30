# transaction-stats
N26-code-challenge

purpose of the project:

    This project my best solution for code challenge assigned by N26.
    
    Technology
    ----------
    1. java8
    2. IntelliJ IDEA ( of course will work with any latest IDE)
    3. Gradle
    4. embedded Jetty
    5. spring-boot (ver: 1.5.X)
    
    How to build this project:
    ---------------------------
    1. download latest Gradle version (or use Gradle wrapper available in the project)
    2. import this project into any IDE as Gradle project
    3. run 'gradle clean build'  in root directory 
    4. above command will download and run required artefacts from maven central repo.
    
    How to Run this project:
    -----------------------
    1.. after importing into IDE open "TransactionStatsApplication.java"  then run as this class java ... main(), by the defult application will start on port  8080, the server may fail if any other processor already running on the port 8080.
    
                          *OR*
               
    1. once build success this project will be archived into transaction-stats-1.0.jar ( jar path:  Root\build\libs)
    2. execute above the jar .
    
            eg:  C:\WorkSpace\transaction-stats>java -jar build\libs\transaction-stats-1.0.jar
            
    3. if application starts successfully you may find console something like "Jetty started on port(s) 8080 (http/1.1)" 
    this mean application is ready to serve requests.
    
    
    REST-END Points
    ------------------
              This application is exposed with 2 End-points
              
              1. localhost:8080/transactions [POST]
              
              input:  Above Endpoints consumes JSON Body as input
              
              eg:
              
                   {
                      "amount": 100.10,
                      "timestamp": 1509354484890
                   }
                   
                   amount - double 
                   timestamp  - Epoch Millis
    
             2.  localhost:8080/statistics   [GET]
             
             
                     This end-points will give aggregation of the transactions that happened last 60 seconds.
    
            example output:
            
            {
                "totalNumberOfTransactions": 4,
                "lastOneMinuteTransactions": 2,
                "sumOfLast60secsTransactions": 200.2,
                "avgOfLast60SecsTransactions": 100.1,
                "minOfLast60SecsTransaction": 100.1,
                "maxOfLast60SecsTransactions": 100.1
            }
    
              
    
    
    
