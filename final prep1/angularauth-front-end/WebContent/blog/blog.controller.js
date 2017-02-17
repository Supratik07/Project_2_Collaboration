(function(){
	'use strict';
	console.log("inside blog controller");
	angular
	    .module('app')
	    .controller('BlogController',BlogController);
	BlogController.$inject=['$scope','BlogService','$location','$rootScope'];
	function BlogController($scope,BlogService,$location,$rootScope){
		
		var vm=this;
		vm.blog=null;
		vm.allBlogs=[];
		/*vm.getBlog=getBlog;*/
		vm.fetchAllBlogs=fetchAllBlogs;
		vm.createBlog=createBlog;
		vm.updateBlog=updateBlog;
		vm.submit=submit;
		vm.edit=edit;
		vm.remove=remove;
		vm.reset=reset;
		
		//fetchAllBlogs();
		
		function getBlog(id){
			console.log("-> getting blog"+id);
			BlogService.getBlog(id)
			.then(
			function(){
				try{
					$location.path('/viewblog');
				}
				catch(err){
					console.log(err);
				}
			},
			function(errResponse){
				console.error("error while fetching blogs: ")
			});
		};
		
		function fetchAllBlogs(){
			console.log("inside fetch all blogs");
			BlogService.fetchAllBlogs()
			.then(function(d){
				console.log("inside fetch function")
				vm.allBlogs=d;
				console.log(vm.allBlogs)
			},
			function(errResponse){
				console.log('error while fetching blogs');
			});
			
			
			
		};
		function createBlog(blog){
			console.log("inside create bog");
			BlogService.createBlog(blog)
			.then(
					vm.fetchAllBlogs,
					function(errResponse){
						console.error('Error while creating blog');
					}
					);
		};
		function updateBlog(blog, id){
			BlogService.updateBlog(blog, id)
			.then(
					vm.fetchAllBlogs,
					function(errResponse){
						console.error('Error while updating Blog.');
					}
					);
		};
		function submit(){
			console.log('Saving new blog',vm.blog);
			console.log($rootScope.currentUser.username);
			console.log($rootScope.currentUser);
			vm.blog.user=$rootScope.currentUser;
			vm.createBlog(vm.Blog);
			
			vm.reset();
		};
		function edit(id){
			console.log('id to be edited',id);
			for(var i=0;i<vm.allBlogs.length;i++){
				if(vm.allBlogs[i].id===id){
					vm.blog=angular.copy(vm.allBlogs[i]);
					break;
				}
			}
		};
		function remove(id){
			
			console.log('id to be deleted',id);
			if(vm.blog.id===id){
				vm.reset();
			}
			vm.deleteBlog(id);
		};
		function reset(){
			vm.blog={};
			$scope.myform.$setPristine();
		};
		}
		
	
})();