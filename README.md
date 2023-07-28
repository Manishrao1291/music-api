<h1 align="center"> Music_streaming_service_API </h1>

* Tech stack used: Spring Boot, Hibernate, MySQL, OOPs, Postman, and Java
>### Prerequisites
* ![MySql](https://img.shields.io/badge/DBMS-MYSQL%205.7%20or%20Higher-red)
 * ![SpringBoot](https://img.shields.io/badge/Framework-SpringBoot-green)


* ![Java](https://img.shields.io/badge/Language-Java%208%20or%20higher-yellow)

## Dependencies
The following dependencies are required to run the project:

* springdoc-openapi-starter-webmvc-ui (swagger)
* Spring Web
* Spring Data JPA
* MySQL Driver
* Lombok
* Validation

* Implemented CRUD operations with data validation, MySQL database configuration, 
 authentication and established table relationships.
This project is a portal that handles different types of relationships ( many-to-one, and many-to-many) between various entities. It provides CRUD (Create, Read, Update, Delete) operations for each model, allowing you to manage the data effectively.

>## Data flow
In this project, we have four layers-
* **Controller** - The controller layer handles the  requests, translates the JSON parameter to object, and authenticates the request and transfer it to the business (service) layer. In short, it consists of views i.e., frontend part.
* **Service** -The business layer handles all the business logic. It consists of service classes and uses services provided by data access layers.
* **Repository** - This layer mainatains the CRUD operations are performed
* **Model** - This layer consists basically the class level things- the various classes required for the project and these classes consists the attributes to be stored.
* **dto** -this layer SignupInput,SignUpOutput which store the data of user to Authentication.

## Models
The portal includes the following models:

>### Admin-
- adminId -Auto generate Id.
- adminName:  name of Admin.
- password : password for signIn
- email : Email id of admin

>### User
-  Integer userId.
-  String username.
-  String password.
-  String email.


>### Song
- Integer songId.
- String songName.
- String songArtist.
- LocalDate releaseDate.
- Admin  admin(mapping @ManyToOne)

>### Playlist
- Integer id.
- String name.
- User user(@OneToOne mapping use).
- List<Song>songs(ManyToMany mapping with playlist).

## AuthenticationToken
Represents an authentication token for a Admin and User.
>### AuthenticationToken-
- tokenId (Long): The ID of the token.
- tokenValue (String): The token string for authentication.
- tokenCreationDate (LocalDate): The date when the token was created.


## Controller API 
  >## AdminController
      -POST /Admin/signup: Create a new admin account.
      -POST /Admin/signin: Sign in to an existing admin account.
      -POST /add/song: Admin can add song.
      -DELETE /delete/song: Admin can delete song.
      -PUT / update/song: Admin can update songs.
      -DELETE /Admin/signout: Sign out from the admin account.


 >## Song Endpoints( In Song controller)
      -GET /Admin/songs: Get a list of all songs.
  
  >## User EndPoints
      
      - POST /User/signup: Create a new user account.
      - POST /User/signin: Sign in to an existing user account.
      - POST /playlist/create: Create a new playlist.
      - GET /playlist/: Get playlist  by user.
      - PUT /update/playlist: Update playlist by user.
      - DELETE /User/signout: Sign out from the user account.
      - DELETE /playlist: user can delete it's playlist.
 
  
  
 ## Project Summary

The project is a basic web application built using Java and the Spring framework. It allows users to sign up, sign in, and manage their profile information. Admin can also create and songs. The application uses authentication tokens to secure user data and ensure that only authenticated users can access certain features.
The API endpoints include user signup, signin, and update details, post creation and retrieval, and authentication token creation. 
