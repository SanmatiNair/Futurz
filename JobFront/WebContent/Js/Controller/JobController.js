angular.module('myApp').controller('jcontroller', function($scope, jservice, $location, jid,$rootScope) {
	var self = this;
	self.job = {
			id: null,
			jobTitle : '',
			jobDescription : '',
			companyName : '',
			status : 'false',
			skillsRequired : '',
			location: '',
			salary: '',
			experience: '',
			vacancies: null,
			email:''
	};
	
	self.submit = submit;

	function createjob(job) {
		jservice.createjob(job).then(function(response) {
			alert('Job added');
			self.job=null;
		}, function(errResponse) {
			alert('job not added');
		})
	}
	
	function submit()
	{
		self.job.email=$rootScope.currentuser.emailId;
		createjob(self.job);
	}
	
	function viewalljobs() {
		jservice.viewalljobs().then(function(response) {
			self.jobs = response.data;
		}, function(response) {
			alert('No job available');
		})
	}
	
	function viewonejob() {
		jservice.viewonejob(jid.id).then(function(response) {
			self.ojob = response.data;
			self.job=null;
		}, function(response) {
			alert('No job available');
		})
	}
	
	self.selectonejob =function(id) {
		jid.id=id;
		$location.path("/viewonejob")
	}
	viewalljobs()
	viewonejob()
})