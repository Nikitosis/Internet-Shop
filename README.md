# Internet Shop

## About
It is my first big java web application. I decided to create something simmilar to internet shop website.
Here user can look through commodities and add them to the basket. 
Later user can pay for commodities in basket.
As for administrator, he can modify commodities(modify/add/delete them), modify user's authorities(give admin roots),modify 
other accounts(modify/delete them).

## Technologies
Current project is build via Java Spring stack.
I decided no to use Spring Boot, but implement every configuration by my own.
For database management I use Hibernate with MySql
For user authentication - Spring Security
For inner logic - Spring MVC
For frontend - Freemarker

This project is annotation-based,except for hibernate configuration(.xml)

![Alt text](/readme_img/Technologies.png?raw=true)

## Functionality

![alt text](/readme_img/Functionality.png?raw=true)

## DB structure

![alt text](/readme_img/Db_structure.png?raw=true)

## Roles

There are user and admin roles in the project.

Here is a page structure for both admin and user

### User pages

![alt text](/readme_img/Pages_USER.png?raw=true)

### Admin pages

![alt text](/readme_img/Pages_ADMIN.png?raw=true)
