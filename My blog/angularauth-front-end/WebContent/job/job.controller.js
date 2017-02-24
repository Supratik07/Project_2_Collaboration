(function(){
	'use strict';
	console.log('inside job controller')
	angular
	.module('app')
	.controller('JobController', JobController);
	
	JobController.$inject=['JobService','$rootScope','$scope','$location'];
	function JobController(JobService,$rootScope,$scope,$location){
		var vm=this;
		vm.job=null;
		vm.jobs=[];
		
		
		
		vm.submit=submit;
		vm.reset=reset;
		vm.getAllJobs=getAllJobs;
		vm.getJobDetails=getJobDetails;
		vm.applyForJob=applyForJob;
		
		console.log('username in Jobcontroller:'+$rootScope.currentUser);
		getAllJobs();
		
		function getAllJobs(){
			console.log('calling the method getAllJobs');
			JobService.getAllJobs()
			.then(
					function(d){
						vm.jobs=d;
					},
					function(errResponse){
						console.error('Error while fetching All jobs');					
					});
			};
			
			function getJobDetails(jobID){
				console.log('get Job details of the id' , jobID);
				JobService.getJobDetaiils(jobID)
				.then(
						function(d){
							$location.path('/jobdetails');
						},
						function(errResponse){
							console.error('Error while fetching job details');
							
						});
						
			}
			
			function postAJob(){
				
				console.log('submit a new job',vm.job);
				JobService.postAJob(job).then(function(d){
					alert("You successfully posted the job");
				},
				function(errResponse){
					console.error("Error while posting job.");
				});
			}
			
			function applyForJob(jobID){
				console.log("apply for job");
				var currentUser=$rootScope.currentUser;
				console.log("currentUser.userId"+currentUser.userId)
				if(typeof currentUser.userId=='undefined'){
					alert("Please login to apply for the job");
					console.log("user is not logged in. Can't apply for the job")
					$location.path('/login');
				}
				console.log("->userID: "+currentUser.userId+" apply for job :"+jobID)
				
				JobService.applyForJob(jobID)
				.then(
						function(d){
							alert:"you have successfully applied for job.HR will get back to you";
						},
						function(errResponse){
							console.error('Error while applying for job request');
						});
				
			}
			function submit(){
				console.log('submit a new job',vm.job);
				postAJob(vm.job);
				vm.reset();
			}
			function reset(){
				console.log('reseting the form');
				vm.user={};
				
				$scope.myForm.$setPristine();
			}
	}
})();