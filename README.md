# HotelWithAuth
Hotel project with spring security basic authentication

This project allows a logged in user to book a room, from a list of displayed rooms. 

This project uses Spring, Spring Security, H2 and Lombok. The back end is written in Java and the front end is in HTML/CSS.

## How to run

The project can be run by right clicking run as > spring boot app or java application (depending on your IDE) within the main class:

![](https://github.com/RavinderSian/HotelWithAuth/blob/readme_branch/hotel-project-auth/screenshots/How%20to%20Run.JPG)

## Home page

![](https://github.com/RavinderSian/HotelWithAuth/blob/main/hotel-project-auth/screenshots/home_page_full.JPG)

The home page is responsive as shown below:

![](https://github.com/RavinderSian/HotelWithAuth/blob/main/hotel-project-auth/screenshots/home_page_minimized.JPG)

## Registering a User

There is a registration page which can be accessed from the hyperlink shown in the home page:

![](https://github.com/RavinderSian/HotelWithAuth/blob/main/hotel-project-auth/screenshots/Register%20Page.JPG)

The registration is successful as the user is now in the database:

![](https://github.com/RavinderSian/HotelWithAuth/blob/main/hotel-project-auth/screenshots/H2%20User.JPG)

The password has been hashed using the BCrypt hashing algorithm

This registration page also has field validation: 

![](https://github.com/RavinderSian/HotelWithAuth/blob/main/hotel-project-auth/screenshots/Register%20Validation.JPG)

## Login 

The default spring security login page is used. This is configured in SecurityConfig.java, along with the logout functionality:

![](https://github.com/RavinderSian/HotelWithAuth/blob/main/hotel-project-auth/screenshots/DefaultLoginLogoutConfig.JPG)

A successful login redirects to the home page (the login hyperlink will still be present even when logged in)

The login page has field validation and displays "Bad credentials" for incorrect User information:

![](https://github.com/RavinderSian/HotelWithAuth/blob/main/hotel-project-auth/screenshots/No%20Fields%20Login.JPG)

![](https://github.com/RavinderSian/HotelWithAuth/blob/main/hotel-project-auth/screenshots/No%20Input%20Login%20Password.JPG)

![](https://github.com/RavinderSian/HotelWithAuth/blob/main/hotel-project-auth/screenshots/Bad%20Credentials.JPG)

## Booking a room

A list of unoccupied rooms can be viewed from the rooms hyperlink at the home page:

![](https://github.com/RavinderSian/HotelWithAuth/blob/main/hotel-project-auth/screenshots/Rooms.JPG)

If you have not logged in you will be redirected to a login page. 
If you are already logged in you will be redirected to a page showing the booking and username:



![](https://github.com/RavinderSian/HotelWithAuth/blob/main/hotel-project-auth/screenshots/BookedRoom.JPG)

Once a room has been booked, its occupied field is set to true and it is no longer on the rooms list: 


![](https://github.com/RavinderSian/HotelWithAuth/blob/main/hotel-project-auth/screenshots/ShorterRoomList.JPG)
