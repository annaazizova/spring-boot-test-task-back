This project is simple inventory system.

## Front

The front part of this app is [spring_boot_test_task_front](https://github.com/annaazizova/spring_boot_test_task_front)

## Tech/framework used

<ul>
  <li>Java 8</li>
  <li>Spring Boot 2</li>
  <li>Basic auth ([feat/security](https://github.com/annaazizova/spring_boot_test_task_back/tree/feat/security) branch)</li>
  <li>JWT ([feat/jwt](https://github.com/annaazizova/spring_boot_test_task_back/tree/feat/jwt) branch)</li>
  <li>H2</li>
  <li>Siren4J ([feat/siren](https://github.com/annaazizova/spring_boot_test_task_back/tree/feat/siren) branch)</li>
  <li>Lombok</li>
</ul>

**Built with**
[Maven](https://maven.apache.org/)

## Features

User is able to
<ul>
  <li>create, find, update, remove product</li>
  <li>see all leftovers</li>
  <li>export search result an an xlsx file</li>
  <li>healthcheck on [http://localhost:8080/healthcheck](http://localhost:8080/healthcheck) (available without authorization)</li>
</ul>

## How to use?

Run maven app with `spring-boot:run` command on [http://localhost:8080](http://localhost:8080)

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
