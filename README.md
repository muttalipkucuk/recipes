### Author: Muttalip Küçük

# Architectural choices
In order to complete the assignment I needed to choose the following:
1. Spring Boot as the Java-framework to build REST APIs, because of its ease to create a standalone Java applications.
2. Spring Boot H2 Database as the database, because I found that a lightweight in-memory database seemed sufficient for the assignment and because of the choice for Spring Boot.
3. I also used Jacoco to reach an acceptable level of unit test coverage.

## How to start-up the application?
### 1) Via command line
```
./gradlew clean bootRun
```
### 2) Via IDE
In your IDE, click on `Gradle` > `Tasks` > `application` > `bootRun`.

## Overview of all REST APIs
When the application is running, you can find documentation about the APIs by using the following link:
http://localhost:8080/api/swagger-ui/index.html

## How to call a REST API?
Open Postman or another similar tool to call one of the APIs. The base request URL is:
```
http://localhost:8080/api
```

### Example: retrieving all dishes
#### 1) Via command line
```
curl --location --request POST 'http://localhost:8080/api/dish/search' \
--header 'Content-Type: application/json' \
--data-raw '{
  "filters": [
  ]
}'
```
#### 2) Via Postman:
HTTP method: `POST`
Request URL: `http://localhost:8080/api/dish/search`
Request body: `{"filters": []}`

#### Examples mentioned in the assignment description
#### Example 1: All vegetarian recipes
```json
{
  "filters": [
    {
      "key": "vegetarian",
      "operator": "EQUAL",
      "field_type": "BOOLEAN",
      "value": "true"
    }
  ]
}
```
####Example 2: Recipes that can serve 4 persons and have “potatoes” as an ingredient
```json
{
  "filters": [
    {
      "key": "servings",
      "operator": "EQUAL",
      "field_type": "INTEGER",
      "value": "4"
    },
    {
      "key": "ingredients",
      "operator": "LIKE",
      "field_type": "STRING",
      "value": "potatoes"
    }
  ]
}
```

####Example 3: Recipes without “salmon” as an ingredient that has “oven” in the instructions
```json
{
  "filters": [
    {
      "key": "ingredients",
      "operator": "NOT_LIKE",
      "field_type": "STRING",
      "value": "salmon"
    },
    {
      "key": "instructions",
      "operator": "LIKE",
      "field_type": "STRING",
      "value": "oven"
    }
  ]
}
```
