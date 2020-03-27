# transaction-api
Exemple APIs


#### With this exercise, I wanted to show you how i would start mounting a project from scratch.

#### Architecture cannot be fixed from the beginning because there may be changes that will make us change it, but the most important thing is to have a struture that is the base (It is the main function of an architect or tech leader ).

#### I have created a single project but it is possible to separate it into different layers (persistence, businese, backend , delivry ..)

#### I have not mocketed services with cucmber, this has taken me a while to think the logic.


**prerequisite**
--
To work on the project, you must use **Java 8**, either as an SDK in IntelliJ, or as a runner in Maven

**Creation h2 database locally**
--
I have not considered it necessary for this poc to create the sql scripts to create database.
With the option: 
 
      jpa:
        database-platform: org.hibernate.dialect.H2Dialect
        hibernate:
          ddl-auto: create-drop
        show-sql: true
 
The tables are created when starting Spring Boot and deleted at the end.
It was possible to have integrated **Flyway CLI** to do better.

**Started App**
--
I have not created the different profiles, you could have created different profiles with your own application-xxx.yml (dev, int , pre , prod , test).
I have only used application-test.yml to launch the tests ( Used a RANDOM_PORT in the test execution ).

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @RunWith(SpringJUnit4ClassRunner.class)
    @SpringBootTest(webEnvironment = RANDOM_PORT, classes = Launcher.class)
    @ActiveProfiles("test")
    @AutoConfigureMockMvc
    public @interface Step {
    
    }
    
Run Appli

![run_appli](run_appli.png)
    


**Started Test (Cucumber)**
--
I have done unit tests and ATDD  of business logic.

Run Test

![run_test](run_test.png)


Cucumber test results are found in target \ cucumber
