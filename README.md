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


Internalization
I18 – customize for different people of the world – return in their languages – dictionary -
Customize localresolver – default local- locale.use
-	Resourcebundlemessagesource -  customize – spring mvc – handling your properties
-	Use autowire messagesource – value =”accept-Language”

Create properties for each language

Pass local based the databases for specific language databases



Content negotiation

Rest – is resources – json or xml

Put in header of the postman – 
Accept   Application/json or Application/xml

You response is json but you accept only xml

406 Not Acceptable

Pom.xml
Add Jackson dataformat xml
Jackson transform object to json or xml
<dependency>
            <groupId>com.fasterxml.jackson.dataformat</groupId>
            <artifactId>jackson-dataformat-xml</artifactId>
        </dependency>



Documentation

Consumer ask what is the contract of your service
Response or request format



1.	<dependency>
2.		<groupId>io.springfox</groupId>
3.		<artifactId>springfox-boot-starter</artifactId>
4.		<version>3.0.0</version>
5.	</dependency>
Create configuration file of swagger
http://localhost:8080/swagger-ui/
Json - http://localhost:8080/v2/api-docs

Tags can be used to group resource multiple categories
Path – expose all the resources
Definitions – show the users model what type of data is

contact info – and consumes and produces set swagger config file
Validation like minimum 2 character, y contraints in User.java in the bean or model
With annotation you can enhace you documentation
@ApiModel(description = "All details about the user.")
public class User {

    @Size(min = 2, message ="name should have at least 2 characters")
    @ApiModelProperty(notes = "name should have at least 2 characters")
    private String name;
documentation - the consumer of your service should not have any questions about how to consume your service. Where service exposed, what details have, input format, output format
you can add authorization details, scopes, responseHeaders, etc in swagger annotations.
Monitor web services

Add hal browser in pom.xml

1.	<dependency>
2.	<groupId>org.springframework.data</groupId>
3.	<artifactId>spring-data-rest-hal-explorer</artifactId>
4.	</dependency>
Hal – hypertext application language – how to hyperlink between resources in your Api
Actuator is hal format
Hal explorer – browser to consume actuator services
http://localhost:8080/actuator
http://localhost:8080/explorer/index.html

enable property for actuator
## habilitar todo los actuators
management.endpoints.web.exposure.include=*


Filter json content

Static filtering
Filter your response in your beans
@JsonIgnoreProperties(value = {"field1"})
public class SomeBean {

@JsonIgnore
private String field3;
use jsonignore because jsonignoreproperties is hardcoded,

dynamic filtering
by request filter, example(specific) first request send me 1 or 2 or second request send me 2 and 3. 
Use Jackson for dynamic filtering
  
public MappingJacksonValue retrieveSomeBean() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        
        mapping.setFilters(filters);

        return mapping;
    }

@JsonFilter("SomeBeanFilter")
public class SomeBean {




versioning

media type versioning aka content negotiation or accept header

different version of 2 same services

1 - uri versioning
controller of versioning 
@GetMapping("v1/person")
    public PersonV1 personV1() {
        return new PersonV1("bob dillan");
    }

    @GetMapping("v2/person")
    public PersonV2 personV2() {
        return new PersonV2(new Name("MIN KU","nam"));
    }


2 - Use request parameter versioning – second option

localhost:8080/person/param?version=2
@GetMapping(value="/person/param", params = "version=1")
    public PersonV1 paramV1() {
        return new PersonV1("bob dillan");
    }




3 - Header versioning 

// header versioning
    @GetMapping(value="/person/header", headers = "X-API-VERSION=1")
    public PersonV1 headerV1() {
        return new PersonV1("bob dillan");
    }


localhost:8080/person/header

in header add
X-API-VERSION   1



4 – content negotiation or accept header or mime(media type) type versioning

What is the output of this service – application json etc

@GetMapping(value="/person/produces", produces = "application/vnd.company.app-v1+json")
     public PersonV1 producesV1() {
         return new PersonV1("bob dillan");
     }


In header add
Accept        application/vnd.company.app-v1+json



Github using media type versioning
Microsoft using header versioning
Twitter using uri versioning
Amazon using request parameter versioning


Uri and request parameter versioning the problem is URI pollution
mime type versioning and headers versioning – misuse of http headers, because http headers never intended to use for versioning
Mime type and headers versioning cannot be cached.
For uri and request parameter can be cached.

Media type and headers cannot be executed in browser, need to have technical knowledge.
Uri and request versioning, any user can execute.

Uri and request parameter is easy documentation, media type and header versioning is more difficult to generate documentation.

Trade off

Have strategy for versioning in first place



Security – authentication – basic authentication

Basic – send user id and password as part of your request
Digest – digest created and sent
Oauth and aouth2 – authentication



If add spring starter security, you will have default security password
401 – unauthorized – le falta usar password o mal el password
403 – forbidden – esta mal el request algo

In the postman authorization in basic auth
User: default user is user
Password is will be the default security password


201 – created

In application.properties you can configure username and password

## username and password for basic auth
spring.security.user.name=minku
spring.security.user.password=1234



JPA in rest app

userDaoService is static arraylist – change to jpa


h2 database
application properties

spring.datasource.url=jdbc:h2:mem:testdb
spring.data.jpa.repositories.bootstrap-mode=default
h2 database create random name for sql file so, in application propertie you need add 

spring.jpa.defer-datasource-initialization=true

<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>


In memory database h2
<dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>


For jpa(java persistent api) we need to convert java beans to entity

@Entity
public class User {
    
    @Id
    @GeneratedValue
    private Integer id;

log :
Hibernate: create table user (id integer not null, birt_date timestamp, name varchar(255), primary key (id))


with jpa is automate creation of the table in in memory database h1


create data.sql – for insert data in the tables in the resource directory
INSERT INTO USER VALUES(1,SYSDATE(), 'min ku nam');
INSERT INTO USER VALUES(2,SYSDATE(), 'jack jill');
INSERT INTO USER VALUES(3,SYSDATE(), 'human baby');


Open to brawser for h2 console
http://localhost:8080/h2-console

jdbc url: jdbc:h2:mem:testdb


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    
}



Use repository

@Autowired
    private UserRepository userRepository;

    //retrieveAllUsers
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    // Get /users
    //retrieveUser(int id)
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) throw new UserNotFoundExcetion("id :" +id);

        //HATEOAS
        // "all-users", SERVER_PATH + "/users"
        // retrieveAllUsers - we can use method, to not use hardcoded path using Resource
        EntityModel<User> resource = EntityModel.of(user.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }




400 – bad request – request format is wrong
404 – not found – cannot found the resource – id not exist
201 – created- post success


Create Post.java entity

Relationship manytoone,  lazy fetch, unless call user, it not going to fetch
@ManyToOne(fetch = FetchType.LAZY)
    private User user;


relationship onetomany in user.java entity
@OneToMany(mappedBy = "user")
    private List<Post> posts;


in the user controller or resource

    // retrieve all the post of specific user
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllPostsByUser(@PathVariable int id) {
        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()) throw new UserNotFoundExcetion("id :" +id);

        return userOptional.get().getPosts();
    }




Best practices


- Richardson maturity model

Level 0 – expose soap web services in rest style

http://server/getPosts

http://server/doThis

Level 1 – expose resources with proper URI
Improper use of http metods

http://server/accounts

http://server/ accounts /10

Level 2 – level1 + http methods

http://server/accounts - get

http://server/ accounts /10.  - post

level 3 – level 2 + HATEOAS

data+next possible actions


- consumer first – who is the consumer(web app, mobile app)
Name of resources – first think perspective of your customers. Understandable

- have great popular documentation like swagger

-	Make best use of http provided(get, post, put , delete)

-	Use correct Response status

o	200 success	
o	404 – resource not found
o	400 – bad request (validation error and request format is wrong)
o	201 – created
o	401 – unauthorized
o	500 – sever error
-	Ensure no secure information in URI

-	Use plurals 

o	Prefer. /users to /user
o	Prefer /users/1 to /user/1

-	Use nouns for resources – account, no delete account, nouns
o	 Cannot be all nouns, there are exceptions
	/search
	PUT / gists/{id}/star
	DELETE. /gists/{id}/star


