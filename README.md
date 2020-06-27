I configured the project as a spring-boot application with Maven. To compile it and run the test please mvn clean install
If it was a real-production project I would added a yaml file for configuration of port of other parameters. In this case I let it by default and the API is mount in the 8080

**Assumptions**

- I assumed that the ATM is empty in beginning and the first operation that needs to work is a refurbish. I give him some money in tests
- I assumed that the 3 accounts exists on the system. I set up the creation of them in the constructor of the AccountService.
- In account I assumed that there is just one only currency and treated it like this. All values in system are doubles 
- I implemented an API rest but in order to don't lose time and don't test spring code, I controlled exceptions with a try/catch there. 
