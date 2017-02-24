(function(){
	'use strict';
	console.log("inside blog controller");
	angular
	    .module('app')
	    .controller('BlogController',BlogController);
	BlogController.$inject=['$scope','BlogService','$location','$rootScope'];
	function BlogController($scope,BlogService,$location,$rootScope){
		
		var vm=this;
		vm.blog={};
		vm.allBlogs=[];
		vm.getBlog=getBlog;
		vm.fetchAllBlogs=fetchAllBlogs;
		vm.createBlog=createBlog;
		vm.updateBlog=updateBlog;
		vm.submit=submit;
		vm.edit=edit;
		vm.remove=remove;
		vm.reset=reset;
		
		//vm.selectedBlog={};
		fetchAllBlogs();
		
		function getBlog(id){
			console.log("-> getting blog"+id);
			/*BlogService.getBlog(id)
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
			});*/
			
			for(var i=0;i<=vm.allBlogs.length;i++)
			{
				if(vm.allBlogs[i].blogId==id)
				{
					$rootScope.selectedBlog=vm.allBlogs[i];
					console.log("assigned");
					console.log($rootScope.selectedBlog);
					break;
				}
			}
			if($rootScope.selectedBlog!=null)
			{
				try{
					$location.path('/viewblog');
				}
				catch(err){
					console.log(err);
				}
			}
		};
		
		function fetchAllBlogs(){
			console.log("inside fetch all blogs");
			vm.user=$rootScope.currentUser;
			BlogService.fetchAllBlogs()
			.then(
					function(d){
				console.log("inside fetch function")
				vm.allBlogs=d;
				console.log(vm.allBlogs)
				console.log(vm.selectedBlog);
			},
			function(errResponse){
				console.log('error while fetching blogs');
			});
			
			
			
		};
		function createBlog(blog){
			console.log("inside create blog");
			console.log(blog.title);
			console.log(blog.description);
			
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
		function submit(b){
			console.log('Saving new blog');
			console.log(b.description);
			b.user=$rootScope.currentUser;
			vm.createBlog(b);
			//console.log(vm.blog);
			//b.title="";
			//b.desc="";
			
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
		function reset(b){
			//vm.blog={};
			b.title="";
			b.description="";
			};
		}
		
	
})();