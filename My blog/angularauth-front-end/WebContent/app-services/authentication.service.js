(function () {
    'use strict';
    console.log('inside authentication service')
    angular
        .module('app')
        .factory('AuthenticationService', AuthenticationService);

    AuthenticationService.$inject = ['$http', '$cookies', '$rootScope', '$timeout', 'UserService'];
    function AuthenticationService($http, $cookies, $rootScope, $timeout, UserService) {
        var service = {};

        service.Login = Login;
        service.SetCredentials = SetCredentials;
        service.ClearCredentials = ClearCredentials;

        return service;

        function Login(username, password, callback) {

           console.log('inside Login method inside service')
           
            var data=$http.post('http://localhost:11027/angularauth-rest/user/authenticate', { username: username, password: password });
            console.log("Data:::"+data);
            data.then(function(res){
            	console.log(res)
            	$rootScope.currentUser={
            		firstName:res.data.firstName,           		
            		lastName:res.data.lastName,
            		password:res.data.password,
            		role:res.data.role,
            		userId:res.data.userId,
            		username:res.data.username
            	};
            	console.log("rootscopedate from authenticate"+$rootScope.currentUser.userId);
            	//var response={success:true};
            	callback({success:true});
            });
            
        }

        function SetCredentials(username, password) {
            

            /*$rootScope.currentUser = {
                    username: username,
                    password: password
                
            };*/
        	console.log("rootscopedata of current user is");
        	console.log($rootScope.currentUser);

            // set default auth header for http requests
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.currentUser;

            // store user details in globals cookie that keeps user logged in for 1 week (or until they logout)
            var cookieExp = new Date();
            cookieExp.setDate(cookieExp.getDate() + 7);
            $cookies.putObject('currentUser', $rootScope.currentUser, { expires: cookieExp });
        }

        function ClearCredentials() {
            $rootScope.globals = {};
            $cookies.remove('currentUser');
            $http.defaults.headers.common.Authorization = 'Basic';
        }
    }


})();
