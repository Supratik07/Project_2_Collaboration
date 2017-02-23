(function(){
	'use strict';
	angular
	     .module('app')
	     .factory('BlogService',BlogService);
	BlogService.$inject=['$http','$q','$rootScope'];
	function BlogService($http,$q,$rootScope){
		console.log('inside blog service');
		var BASE_URL='http://localhost:11027/angularauth-rest'
			var service={}
		service.fetchAllBlogs=fetchAllBlogs;
		service.createBlog=createBlog;
		service.updateBlog=updateBlog;
		service.deleteBlog=deleteBlog;
		service.getBlog=getBlog;
		
		return service;
		
		function fetchAllBlogs(){
			console.log("inside fetch blog");
			
			return $http.get(BASE_URL+'/blog/')
			.then(
					function(response){
						console.log('Blog-->'+response.data);
						return response.data;
					},
					function(errResponse){
						console.error('error while fetching Blogs');
						return $q.reject(errResponse);
					}
					);
			}
		function createBlog(blog){
			console.log("inside create blog");
			return $http.post(BASE_URL+'/blog/',blog)
			.then(
					function(response){
						return response.data;
						$rootScope.currentUser = {
			                    username: username,
						};
					},
					function(errResponse){
						console.error('Error while creating Blog');
						return $q.reject(errResponse);
					}
					);
			}
		function updateBlog(blog,id){
			return $http.put(BASE_URL+'/blog/'+id,blog)
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while updating Blog');
						return $q.reject(errResponse);
					}
					);
		}
		function deleteBlog(id){
			return $http.delete(BASE_URL+'/blog/'+id)
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while deleting Blog');
						return $q.reject(errResponse);
					}
					);
		}
		function getBlog(id){
			return $http.get(BASE_URL+'/blog/'+id)
			.then(
					function(response){
						$rootscope.selectedBlog=response.data;
						return response.data;
					},
					function(errResponse){
						console.error('Error while getting Blog');
						return $q.reject(errResponse);
					}
					);
		}
	
	}

		
		
	
	
	     
	
})();