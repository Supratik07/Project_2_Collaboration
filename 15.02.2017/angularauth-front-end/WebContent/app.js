(function () {
    'use strict';
console.log('inside app.js')
    angular
        .module('app', ['ngRoute', 'ngCookies'])
        .config(config)
        .run(run);

    config.$inject = ['$routeProvider', '$locationProvider'];
    function config($routeProvider, $locationProvider) {
        $routeProvider
            .when('/', {
                controller: 'HomeController',
                templateUrl: 'home/home.view.html',
                controllerAs: 'vm'
            })

            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'login/login.view.html',
                controllerAs: 'vm'
            })

            .when('/register', {
                controller: 'RegisterController',
                templateUrl: 'register/register.view.html',
                controllerAs: 'vm'
            })
            
            .when('/createblog',{
            	controller:'BlogController',
            	templateUrl:'blog/createblog.view.html',
            	controllerAs:'vm'
            	
            })
            
            .when('/listblog',{
            	templateUrl:'blog/listblog.view.html',
            	controller: 'BlogController',
            	controllerAs: 'vm'
            })
            
            .when('/viewblog',{
            	templateUrl:'blog/showblog.view.html',
            	controller: 'BlogController',
            	controllerAs: 'vm'
            })
            .when('/searchjob',{
            	templateUrl:'job/searchjob.view.html',
            	controller:'JobController',
            	controllerAs:'vm'
            })
            .when('/jobdetails',{
            	templateUrl:'job/jobdetails.view.html',
            	controller:'JobController',
            	controllerAs:'vm'
            })
            
            .otherwise({ redirectTo: '/login' });
    }

    run.$inject = ['$rootScope', '$location', '$cookies', '$http'];
    function run($rootScope, $location, $cookies, $http) {
        // keep user logged in after page refresh
        $rootScope.currentUser = $cookies.getObject('currentUser') || {};
        if ($rootScope.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.currentUser;
        }

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in and trying to access a restricted page
        	console.log("location is"+$location.path());
        	var path=+$location.path();
            var restrictedPage = $.inArray($location.path(), ['/login', '/register','/viewblog','/listblog','/viewfriend']) === -1;
            console.log('Restricted Page:'+restrictedPage);
            
            var loggedIn = $rootScope.currentUser.username;
            console.log('logged In:'+loggedIn);
            if (restrictedPage && !loggedIn) {
                $location.path('/login');
            }
            
        });
    }

})();
