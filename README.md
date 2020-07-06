Api Documentation:http://localhost:8080/swagger-ui.html

1.First you should create user at http://localhost:8080/api/users/register,
in order to be pass security auth and access all URLs. 
1. a) Json example for user: POST request:
	{
    	"firstName": "Petar",
    	"lastName": "Kuzmanov",
    	"email": "petar@gmial.com",
    	"address": {
    		"city": "Varna",
    		"country": "Bulgaria",
    		"street": "Levski 44356"
    	},
    	"contact": {
    		"mobilePhone": "085664214",
    		"secondEmail":"petar@abv.bg"
    	},
    	"password": "petar123",
    	"username": "petar@gmial.com"
    }
2. Seed your local DB with roles in order not to get NullPointerException when
registering.