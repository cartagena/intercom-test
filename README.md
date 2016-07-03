Building and running
====

## Dependencies
All you need is Java 8 installed.

### What's your proudest achievement?
When I started to work at Casan, there was not software development process defined, so the relationship and task prioritization with the clients wasn't quite good. I talked with my manager how I used to deal with those demands in my previous roles and I convinced him to deploy a new project management software (Redmine) and try a new the development process. The team took a Scrum course and started to follow the Agile principles when dealing with the client. The outcome was a closer relationship with the clients and better team management.

### Nested arrays function
The solution for the nested arrays is in class `com.github.cartagena.intercom.array.ArrayOperations` and the tests are in `com.github.cartagena.intercom.array.ArrayOperationsTest`.

### Running the Customers application
Before run the application you can configure your customers file location. Open the file `src/main/resources/config.properties`, uncomment the **`customers.file`** property fill it with your data.

Once all is configured, you can run the application using the following command:

    $> ./gradlew run
    
Or the following if you are using Windows:
    
    $> ./gradlew.bat run