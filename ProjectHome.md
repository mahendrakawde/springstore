# Goal #
Tutorial project on how to use the Spring 2 technology on an existing J2EE project. This project contains the documentation on how to setup the initial project and a step-by-step tutorial on how and what to convert.

The code migration path is available in several step-by-step projects. Starting with the initial setup, upto the fully migrated version to Spring and Hibernate.

The project used is the 1.0.5 version of the [J2EE Adventure Builder](http://adventurebuilder.dev.java.net/), which we will convert to Spring using the [Spring Framework](http://www.springsource.org). For all the other components we will use as much of Spring (and related projects) as possible.

![https://adventurebuilder.dev.java.net/images/architecture1.gif](https://adventurebuilder.dev.java.net/images/architecture1.gif)
The architecture is described [here](https://adventurebuilder.dev.java.net/architecture.html). It describes the highlevel architecture of the Adventure Builder project. For this migration we are going to focus on the web application and Order Processing System, those are the 2 systems we have control over. The other 4 systems are external systems which are  exposed as a web service.

# Situation #
For the migration we are going to pretend that we are the development team responsible for building the Adventure Builder web application and Order Processing system, those 2 systems are own in house build applications. For this migration we have a few things that we have to bare in mind:
  1. lightweight architecture preferably no EJB's
  1. use of standard frameworks (deprecate/remove WAF)
  1. runtime restricted to JDK 1.4, tests can be written with JDK 1.5

# Versions #
The actual project version is 1.0.5. For this endavour the version is increased to 1.0.6. In different steps we will try to reach our final 1.0.6 version.

|**Version**|**Description**|**Changes**|
|:----------|:--------------|:----------|
|[1](Step.md)|  # Same as the 1.0.5 build but [maven](http://maven.apache.org)ized  # Added tests so whe can test and verify the behavior in the next steps.|[Changelog](http://code.google.com/p/springstore/issues/list?can=1&q=label%3AStep-1)|
|[2](Step.md)|  # Introduce the spring framework  # Simplify code by using JdbcTemplate/JmsTemplate  # Add best practices to Session Beans and simplify code|[Changelog](http://code.google.com/p/springstore/issues/list?can=1&q=label%3AStep-2)|
|[3](Step.md)|  # Instead of relying on JNDI and do lookups do dependency injection|[Changelog](http://code.google.com/p/springstore/issues/list?can=1&q=label%3AStep-3)|
|[4](Step.md)| TBD           | TBD       |