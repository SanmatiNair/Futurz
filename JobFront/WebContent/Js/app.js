var app = angular.module('myApp', ['ngRoute']);
app.value('bid', {id : 1});

app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "home.html"
    })
    .when("/about", {
        templateUrl : "aboutus.html"
    })
    .when("/contact", {
        templateUrl : "contact.html"
    })
    .when("/login", {
        templateUrl : "login.html"
    })
     .when("/blog", {
        templateUrl : "blog.html"
    })
     .when("/blogapproval", {
        templateUrl : "blogapproval.html"
    })
    .when("/viewallblogs", {
        templateUrl : "viewallblogs.html"
    })
     .when("/viewoneblog", {
        templateUrl : "viewoneblog.html"
    })
    
    
    .when("/register", {
        templateUrl : "register.html"        	
    });
}); 