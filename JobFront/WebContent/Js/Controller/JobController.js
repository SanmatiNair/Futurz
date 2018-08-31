angular.module('myApp').controller('jcontroller', function($scope, jservice, $location, jid) {
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
			vacancies: null
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