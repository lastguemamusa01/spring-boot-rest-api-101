# spring-boot-rest-api-101


Knowing rest api is key to build good microservices
Rest is a style of software architecture for distributed hypermedia systems. Design resoruces y exposure by http
Resources can ben xml, json , html
Social Media app

User -> posts

-	Retrieve all users – get /users
-	Create a user – post /users
-	Retrieve one user – get /users/{id} -> /users/1
-	Delete a user – delete  /users/{id} -> /users/1

-	Retrieve all posts for a user – get /users/{id}/posts
-	Create a posts for a User – post /users/{id}/posts
-	Retrieve details of a post – get  /users/{id}/posts/{post_id}

Validations, internationalization, exception handling, versioning services, content negotiation, generating documentation for theses services, monitoring services and HATEOAS

Debug mode
Auto configuration report - 
Dispatcher servlet auto configuration
Httpmessageconverterautoconfiguration with Jacksonautoconfiguration(convert bean to json and viceversa)
Error mapping

Dispatcher servlet is the front controller for spring web mvc framework(handle the request)

@RestControoler annotation has @ResponseBody


Resource mappings – some values pass by path parameter
/hello-world/path-variable/{name}

User DAO – STATIC ARRAY LIST From user dao services.   

The Data Access Object (DAO) pattern is a structural pattern that allows us to isolate the application/business layer from the persistence layer (usually a relational databas,.,jjjje, but it could be any other persistence mechanism) using an abstract API.

Id is calculated by the backend and primary key is determined by the database
Using get return 200 – means successful request
Use rest client to do post request like postman.
 ResponseEntity you can return response status
201 – created for the post
Returning correct responses always
You need implement exception like if the user not exist, no return 200 response of empty body.
404 – not found , in case when you search someone but not exist In the web service
In spring boot and spring mvc we have some default exception handling
Standard exception for all restful services
Validation – created user with no id something like that
Customizing the exception handling
Standardization all organization
ResponseEntityExcpetionHandler – base class for standard exception
// applicable to all another controllers
@ControllerAdvice

If delete id not exist – 404 not found or no content 204.

Validation
<dependency>    
            <groupId>org.springframework.boot</groupId>    
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>


Validation
-	Validation of the content
-	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {

javax validation api @valid
fecha tiene que ser pasado
mínimo name 2 characteres
400 bad request 
-	expcetion handling add the custom message of the response error
hadleMethodArgumentNotValid
in beans
  @Size(min = 2, message ="name should have at least 2 characters")
    private String name;
    
    @Past()
    private Date birtDate;

validation for the body like post and update only

HATEOAS – HYPER MEDIA AS THE ENGINE OF APPLICATION STATE  - Offer some link to do more things
