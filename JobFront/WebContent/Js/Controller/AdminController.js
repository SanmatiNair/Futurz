angular.module('myApp').controller('acontroller', function($scope, aservice, $location) {
	var self = this;
	self.blog = {
			  	status: null,
		        blogDescription: "",
		        blogName: "",
		        blogTitle: "",
		        author: "",
		        blogId: null,
		        createdOn: null
	};
	
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
	
	self.blogs=[];
	displayblogs();
	self.deleteblog=deleteblog;
	self.approveblog=approveblog;

	function displayblogs() {
		aservice.displayblogs().then(function(response) {
			self.blogs=response.data;
		}, function(errResponse) {
			alert('NO Blog Found');
		})
	}
	
	function deleteblog(blogid) 
	{
		aservice.deleteblogs(blogid).then(function(response) {
			alert("Blog deleted successfully")
			displayblogs();
		}, function(errResponse) {
			alert('Blog not deleted');
		})
	}
	
	function approveblog(blogid) 
	{
		aservice.approveblog(blogid).then(function(response) {
			alert("Blog Approved successfully")
			displayblogs();
		}, function(errResponse) {
			alert('Blog not Approved');
		})
	}
	
	
	function getalljobs() {
		aservice.getalljobs().then(function(response) {
			self.jobs = response.data;
		}, function(response) {
			alert('No job available');
		})
	}
	
	self.deletejob =function(id){
		aservice.deletejob(id).then(function(response) {
			getalljobs();
		}, function(response) {
			alert('Not deleted');
		})
	}
	
	self.approvejob =function(id){
		aservice.approvejob(id).then(function(response) {
			alert('job Approved');
			getalljobs();
		}, function(response) {
			alert('Not approved');
		})
	}
	getalljobs()
	
	
})