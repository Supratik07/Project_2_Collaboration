( function(){
	'use strict'
	angular
	.model('app')
	.factory('JobService',JobService)
	JobService.$inject=['$http','$q','$rootscope'];
	function JobService($http,$q,$rootscope){
		var BASE_URL='http://localhost:11026/angularauth-rest';
		var service={};
		service.getAllJobs=getAllJobs;
		return service;
		function getAllJobs(){
			return $http.get(BASE_URL+'/job')
			.then(
					function (response){
						return response.data;
					},
					function (errResponse){
						console.error('Error while getting all Jobs');
						return $q.reject(errResponse);
					});
			}
		function getJobDetails(jobID){
			console.log("Getting job details of "+jobID)
			return $http.get(BASE_URL+"/job"+jobID)
			.then(
					function(response){
						console.log('Job Details :'+response.data.title);
						try{
							$rootscope.selectedJob=response.data;
							console.log($rootscope.selectedJob.jobId+' '+$rootscope.selectedJob);
						}catch(err){
							console.error(err.message);
						}
						return response.data;
					},
					function(errResponse){
						console.error('Error while getting job details');
						return $q.reject(errResponse);
					}
					
			);
		}
	
	
	}
})