(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['$http'];
    function UserService($http) {
        var service = {};

        service.GetAll = GetAll;
        service.GetById = GetById;
        service.GetByUsername = GetByUsername;
        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;

        return service;

        function GetAll() {
            return $http.get('http://localhost:11027/angularauth-rest/user/').then(handleSuccess, handleError('Error getting all users'));
        }

        function GetById(id) {
            return $http.get('http://localhost:11027/angularauth-rest/user/' + id).then(handleSuccess, handleError('Error getting user by id'));
        }

        function GetByUsername(username) {
            return $http.get('http://localhost:11027/angularauth-rest/user/' + username).then(handleSuccess, handleError('Error getting user by username'));
        }

        function Create(user) {
            return $http.post('http://localhost:11027/angularauth-rest/user/', user).then(createSuccess, handleError('Error creating user'));
        }

        function Update(user) {
            return $http.put('http://localhost:11027/angularauth-rest/user/' + user.id, user).then(handleSuccess, handleError('Error updating user'));
        }

        function Delete(id) {
            return $http.delete('http://localhost:11027/angularauth-rest/user/' + id).then(handleSuccess, handleError('Error deleting user'));
        }
        
        function Logout(id) {
            return $http.put('http://localhost:11027/angularauth-rest/user/' + id).then(handleSuccess, handleError('Error deleting user'));
        }
        
        function GetAllExceptCurrent(){
        	    return $http.get('http://localhost:11027/angularauth-rest/user/friend').then(handleSuccess,handleError('Error getting all users'));

        }
        	// private functions

        function handleSuccess(res) {
            return res.data;
        }
        
        function createSuccess(res) {
        	var res={success:true};
            return res;
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }

})();
