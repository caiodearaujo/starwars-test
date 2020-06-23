# Star Wars - Reactive API
[![Build Status](https://travis-ci.org/caiodearaujo/starwars-test.svg?branch=master)](https://travis-ci.org/caiodearaujo/starwars-test)

Project builded with Java 8, Spring Boot WebFlux, Apache Cassandra and Docker

## Run with Docker

For run with docker, execute:

```sh
    # first execute maven goals
    $ mvn clean install
    # after maven goals executed, run:
    $ docker-compose build
    # after docker build the two projects, run:
    $ docker-compose up
```
API wan running on port 8080:

> http://localhost:8080/

## API Specs

### Save a Planet

```javascript
fetch("http://localhost:8080/planets", {
  "method": "POST",
  "headers": {
    "content-type": "application/json"
  },
  "body": {
    "name": "Tatooine",
    "climate": "arid",
    "terrain": "desert"
  }
})
.then(response => {
  console.log(response);
})
.catch(err => {
  console.error(err);
});
```
Response: Status 201

```json
{
  "id": "477d28dd-d475-4668-aeb7-8d38fddb4b8b",
  "name": "Tatooine",
  "climate": "arid",
  "terrain": "desert",
  "films": 5
}
```

### List All Planets Added

```javascript
fetch("http://localhost:8080/planets/", {
  "method": "GET",
  "headers": {}
})
.then(response => {
  console.log(response);
})
.catch(err => {
  console.error(err);
});
```
Response: Status 200

```json
[
    {
      "id": "477d28dd-d475-4668-aeb7-8d38fddb4b8b",
      "name": "Tatooine",
      "climate": "arid",
      "terrain": "desert",
      "films": 5
    }
]
```

### Delete a Planet

```javascript
fetch("http://localhost:8080/planets/15d47a40-b064-4329-aa41-2dd86e62c1f8", {
  "method": "DELETE",
  "headers": {}
})
.then(response => {
  console.log(response);
})
.catch(err => {
  console.error(err);
});
```

Response: Status 200

### Get planet by ID

```javascript
fetch("http://localhost:8080/planets/b6618f9c-6525-4408-b031-718afdec514f", {
  "method": "GET",
  "headers": {}
})
.then(response => {
  console.log(response);
})
.catch(err => {
  console.error(err);
});
```

Response: 200

```json
{
  "id": "b6618f9c-6525-4408-b031-718afdec514f",
  "name": "Tatooine",
  "climate": "arid",
  "terrain": "desert",
  "films": 5
}
```

### Get planet by Name

```javascript
fetch("http://localhost:8080/planets/name/Tatooine", {
  "method": "GET",
  "headers": {}
})
.then(response => {
  console.log(response);
})
.catch(err => {
  console.error(err);
});
```

Response: 200

```json
[
  {
    "id": "066625f1-fd38-4b5f-b5f7-fc28edfedb34",
    "name": "Tatooine",
    "climate": "Arid",
    "terrain": "Desert",
    "films": null
  }
]
```

### Get planet from Star Wars API using API reactive

```javascript
/* Attributes name and page are optional */
fetch("http://localhost:8080/swapi/?name=Tatooine&page=1", {
  "method": "GET",
  "headers": {}
})
.then(response => {
  console.log(response);
})
.catch(err => {
  console.error(err);
});
```

Response: 200

```json
{
  "count": 1,
  "next": null,
  "previous": null,
  "results": [
    {
      "name": "Tatooine",
      "diameter": "10465",
      "climate": "arid",
      "gravity": "1 standard",
      "terrain": "desert",
      "population": "200000",
      "residents": [
        "http://swapi.dev/api/people/1/",
        "http://swapi.dev/api/people/2/",
        "http://swapi.dev/api/people/4/",
        "http://swapi.dev/api/people/6/",
        "http://swapi.dev/api/people/7/",
        "http://swapi.dev/api/people/8/",
        "http://swapi.dev/api/people/9/",
        "http://swapi.dev/api/people/11/",
        "http://swapi.dev/api/people/43/",
        "http://swapi.dev/api/people/62/"
      ],
      "films": [
        "http://swapi.dev/api/films/1/",
        "http://swapi.dev/api/films/3/",
        "http://swapi.dev/api/films/4/",
        "http://swapi.dev/api/films/5/",
        "http://swapi.dev/api/films/6/"
      ],
      "created": "2014-12-09T13:50:49.641000Z",
      "edited": "2014-12-20T20:58:18.411000Z",
      "url": "http://swapi.dev/api/planets/1/",
      "rotation_period": "23",
      "orbital_period": "304",
      "surface_water": "1"
    }
  ]
}
```


