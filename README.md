[![codecov](https://codecov.io/gh/annaazizova/spring-boot-test-task-back/branch/master/graph/badge.svg)](https://codecov.io/gh/annaazizova/spring-boot-test-task-back)

This project is simple inventory system with different available functionality depending on user's role (User or Admin).

## Front

The front part of this app is [spring-boot-test-task-front](https://github.com/annaazizova/spring-boot-test-task-front)

## Tech/framework used

<ul>
  <li>Java 8</li>
  <li>Spring Boot 2</li>
  <li>Basic auth</li>
  <li>JWT (<a href="https://github.com/annaazizova/spring-boot-test-task-back/tree/feat/jwt">feat/jwt</a> branch)</li>
  <li>H2</li>
  <li>Siren4J</li>
  <li>Lombok</li>
  <li>Swagger2</li>
</ul>

**Built with**
[Maven](https://maven.apache.org/)

## Features

User is able to
<ul>
  <li>create, find, update, remove product</li>
  <li>see all leftovers</li>
  <li>export search result an an xlsx file</li>
  <li>healthcheck on <a href="http://localhost:8080/healthcheck">http://localhost:8080/healthcheck</a> (available without authorization)</li>
  <li>api documentation on <a href="http://localhost:8080/swagger-ui.html">http://localhost:8080/swagger-ui.html</a> (available without authorization)</li>
</ul>

## How to use?

Run maven app with `spring-boot:run` command on [http://localhost:8080](http://localhost:8080), main branch is [master](https://github.com/annaazizova/spring_boot_test_task_back). Login page is [http://localhost:8080/login](http://localhost:8080/login), after successful auth redirect to all products info.

## HTTP RESTful API
Available entry points:
<ul>
  <li>
    <code>GET /api/products</code>: returns all existing products
  </li>
  <li>
    <code>GET /api/products/{productId}</code>: returns product details
  </li>
  etc
</ul>
