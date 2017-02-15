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
						
			};
	}
})();