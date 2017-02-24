(function () {
    'use strict';
    console.log('inside homecontroller.js')
    angular
        .module('app')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['UserService', '$rootScope'];
    function HomeController(UserService, $rootScope) {
        var vm = this;

      //  vm.user = null;
        vm.allUsers = [];
        vm.deleteUser = deleteUser;

        initController();

        function initController() {
        	
            loadCurrentUser();
            loadAllUsers();
        }

        function loadCurrentUser() {
            /*UserService.GetByUsername($rootScope.currentUser.username)
                .then(function (user) {
                    vm.user = user;
                });*/
        	vm.user=$rootScope.currentUser;
        }

        function loadAllUsers() {
            UserService.GetAll()
                .then(function (users) {
                    vm.allUsers = users;
                });
        }

        function deleteUser(id) {
            UserService.Delete(id)
            .then(function () {
                loadAllUsers();
            });
        }
        
        function logout(id){
        	UserService.logout(id)
        	.then(function() {
        		$location.path("/login");
        	});
        }
        
        function makeAdmin(id){
        	UserService.MakeAdmin(id)
        	.then(function (){
        		loadAllUsers();
        		
        	});
        }
    }

})();
