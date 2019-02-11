# JEE basics tutorial

This tutorial will take you through core concepts of Java EE and also touch on subjects like swagger, docker, and openshift. Just enought to spark your interest :)
The purpose of this exercise it to build a Java EE web application and in doing so, explore concepts like JPA, CDI, RESTful WebServices, external interfaces. The example application is following the CA 4.1 (Component Architecture 4.1) architecture from Adam Bien. 

## The web app

The app will publish some web services for populating and querying a move database. We will also be calling an external system (omdbapi.com) to retrieve some additional information for the movies (i.e. the plot, the movie poster, the genre, etc.).

## Prerequisites

In order to go through this tutorial you will need:
* [payara](https://www.payara.fish/software/downloads/) - Application Server
* [maven](https://maven.apache.org/download.cgi) - Dependency Management
* [JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - JDK

Install everything and let's get to work.

## Steps

### Setting up the project

The first thing we need to do is to create a maven project for our application, and then start implementing it. The outcome of this exercise is an empty maven project. We need to add some dependecies in order the access the JEE classes we will need further.

```
        <dependency>
            <groupId>javax</groupId>
            <artifactId>javaee-api</artifactId>
            <version>7.0</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>fish.payara.extras</groupId>
            <artifactId>payara-embedded-all</artifactId>
            <version>4.1.152.1</version>
            <scope>provided</scope>
        </dependency>
```
Note: as you can se the scope is set to provided; that is because the jars needed at runtime will alredy be provided in glassfish.

### Creating the endpoint

The next exercise is to create one resource called "/movies". We want three actions on this resource: create a movie, get a list of all movies, get a movie by id.
The domain objects that we will work on are:

* movie -> title, cast (list of actors)
* actor -> fist name, last name, birth date

While implementing these we will keep a static list of movies. We will be introducing a database later. In the case of get by id, to keep things simple, we will think of the index in the list as the id.  

Hints:

* create classes for modelling the domain objects (Movie, Actor) 
* the correspondence between http verbs and CRUD operation is as follows:
 	- POST -> create
 	- GET -> read
 	- PUT -> update
	- DELETE -> delete
* @Consumes, @Produces

### JPA

Now we will be adding a databse to our little project. Glassfish comes with an embedded derby database, so out of convenience we will be using that, but our code should run on any other DB as long as we use JPA. Add a persistence.xml file to be able to connect to the derby DB, and annotate the two entities (movie and actor) so that we could access the DB using them. The id of the entities will be autogenerated. 

Hints:

* @Id, @GeneratedValue, @OneToMany 

### Validation

Let's add some validation to our entities. The last name and the birth date of the actor cannot be null. The same goes for the movie title.

### Persisting the data

Now that we introduced the DB, let's use it. Modify the creating and reading of the movies to use the DB to persist them.

Hints:

* EntityManager, @NamedQuery

### Adding some more features

Add some more functionality. We want to be able to also edit and delete a movie based on its id. Another find method would be nice to add as well: find by title. It would be nice if you could make the title accept wildcards (*, ?). If not an exact match will sufice.

Hints:

* @DELETE, @PUT, @PathParam

### External service

At this step we will be adding an external system. We will use omdbapi.com. It's fairly easy to get a apikey. You just need to have an email address. We will be retrieving from the omdbapi service:
 
* the plot
* the genre
* the movie poster
* the imdb rating
* the imdb votes

We will use tht title as query param for the external service. As a first step we will create a new endpoint that gets this additional information. We do this intermediary step to make sure we are able to call the web service and that everything works fine, before integrating it with the rest of the app.

### Integrating the external service

Now you must add a field to store this external data in the movie object. And in order to control the external service being called we will add a query param to the find by title method and use as a flag to control wether the external data will be included in the response or not (and whether the external service gets called or not).

### Transient data
At the previous step we created a new field in the movie entity. You probably annotated the external data so that it's an entity too, and that it gets saved to the DB. This step is about preventing that the external data gets persisted to our DB.

Hints: 

* @Transient

## Next chapter

In the next tutorial we will cover:
* swagger
* docker
* openshift
