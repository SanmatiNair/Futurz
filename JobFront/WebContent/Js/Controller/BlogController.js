	angular.module('myApp').controller('bcontroller', function($scope, bservice, $location,bid) {
	var self = this;
	self.blog = {
			blogTitle : '',
			blogDescription : '',
			author : '',
			status : 'false',
			createdOn : null
	};
	self.submit = submit;
	self.reset =reset;
	self.selectoneblog=selectoneblog;
	
	viewallblogs()
	viewoneblog()



	function createblog(blog) {
		bservice.createblog(blog).then(function(response) {
			alert('Blog Added Successfully ');
			reset();			
		}, function(errResponse) {
			alert('Blog Not Added');
		})
	}
	
	function submit()
	{
		createblog(self.blog);
	}
	
	function reset()
	{
		self.blog=null;
	}
	
	function viewallblogs() {
		bservice.viewallblogs().then(function(response) {
			self.blogs=response.data;
		}, function(errResponse) {
			alert('NO Blog Found');
		})
	}
	

	function viewoneblog() {
		bservice.viewoneblog(bid.id).then(function(response) {
			self.blog=response.data;
		}, function(errResponse) {
			alert('NO Blog Found');
		})
	}
	
	
	function selectoneblog(blogid)
	{
		bid.id=blogid;
		$location.path('/viewoneblog')
	}
})