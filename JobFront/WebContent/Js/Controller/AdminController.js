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
		alert("i am called");
		aservice.deleteblogs(blogid).then(function(response) {
			alert("Blog deleted successfully")
			displayblogs();
		}, function(errResponse) {
			alert('Blog not deleted');
		})
	}
	
	function approveblog(blogid) 
	{
		alert("i am called");
		aservice.approveblog(blogid).then(function(response) {
			alert("Blog Approved successfully")
			displayblogs();
		}, function(errResponse) {
			alert('Blog not Approved');
		})
	}
	
	
})