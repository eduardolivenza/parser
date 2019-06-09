I configured the project as a spring-boot application with Maven. To compile it and run the test please mvn clean install

**Assumptions**

- I assumed that the ATM is empty in beginning and the first operation that needs to work is a refurbish. I give him some money in tests
- I assumed that the 3 accounts exists on the system. I set up the creation of them in the constructor of the AccountService.
- In account I assumed that there is just one only currency and treated it like this. All values in system are doubles 
