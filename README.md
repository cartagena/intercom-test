Building and running
====

## Dependencies
All you need is Java 8 installed.

### What's your proudest achievement?
...

### Nested arrays function
The solution for the nested arrays is in class `com.github.cartagena.intercom.array.ArrayOperations` and the tests are in `com.github.cartagena.intercom.array.ArrayOperationsTest`.

### Running the Customers application
Before run the application you can configure your customers file location. Open the file `src/main/resources/config.properties`, uncomment the **`customers.file`** property fill it with your data.

Once all is configured, you can run the application using the following command:

    $> ./gradlew run
    
Or the following if you are using Windows:
    
    $> ./gradlew.bat run