# COMP3005A F21 - Final Project
Main project for course COMP3005. Full web application representing a virtual bookstore.

# Instructions for Use
1. Install Apache Maven  
  a. Download and install at https://maven.apache.org/download.cgi  
  b. If running on Windows: update MAVEN HOME System variable and the path variable to point to installation location of Maven  
2. Ensure Java JDK 17 is installed
3. Clone this repo by running:  
```git clone https://github.com/COMP3005A-Project/bookstore.git```     
4. Run this command in the project directory in a commandline:   
```mvn spring-boot:run```
5. Go to http://localhost:8080 on a web browser

Alternatively and faster (if VS code is installed)
1. Install the Spring Boot Dashboard plugin for VS code
2. Find the Spring application on the left under the dashboard and press play button
3. Go to http://localhost:8080 on a web browser

Notes for usage:
* The credentials for the admin account are as follows:   
    username: admin@gmail.com  
    password: gaben12
   

# Project Info
## Group Members
- Nabeel Warsalee - 101103167
- Hadi Cheaito    - 101110188
- Aaron Buitenwerf - 101106637

## Instructor
Ahmed El-Roby

## Due Date
December 10th, 2021

## Frameworks/Packages Used
- [PostgreSQL](https://www.postgresql.org)
- [Java Springboot](https://spring.io/projects/spring-boot)
- [Thymeleaf](https://www.thymeleaf.org/doc/articles/springmvcaccessdata.html)
- [Lombok](https://projectlombok.org/features/all)
- [WET](https://wet-boew.github.io/wet-boew-styleguide/index-en.html)
- [JQuery](https://jquery.com)
- [DataTable](https://www.datatables.net)

# Description
This project involves creating a website that acts as a online book store called "Lookinabook". Our project implementation runs on a Spring Boot Web application. Thymeleaf provides the HTML templating, Lombok replaces boilerplate Java code, WET provides the CSS syling for the look of the pages, Jquery simplifies our Javascript code, Datatables provides an API for creating easy tables on our pages. As this is a project for COMP3005 "Database Management Systems", our backend interacts with a PostgreSQL server to store information about Books, Customers, Publishers, and Orders.
