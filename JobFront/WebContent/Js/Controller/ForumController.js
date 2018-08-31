angular.module('myApp').controller('fcontroller', function($scope, fservice,  $location, foid,$rootScope) {
	var self = this;
	self.forum = {
			foid: null,
			forumTitle : '',
			forumDescription : '',
			
	};
	
	self.forumComment = {
			forumCommentsId : 0,
			foid : 0,
			content : '',
			postedBy : '',
			postedOn : null,
		};
	
	
	
	self.submit = submit;
	self.reset = reset;
	self.selectoneforum = selectoneforum;
	self.submitComment = submitComment;
	self.resetComment = resetComment;


	function createforum(forum) {
		fservice.createforum(forum).then(function(response) {
			alert('forum added');
		}, function(errResponse) {
			alert('forum not added');
		})
	}
	
	function submit()
	{
		createforum(self.forum);
	}

	function reset() {
		self.forum = null;
	}

	
	function viewallforums() {
		fservice.viewallforums().then(function(response) {
			self.forums = response.data;
		}, function(response) {
			alert('No forum available');
		})
	}
	
	function viewallforumComments() {
		fservice.viewallforumComments(foid.id).then(function(response) {
			self.forumComments = response.data;
		}, function(errResponse) {
			alert('NO Forum Found');
		})
	}
	
	
	function viewoneforum() {
		fservice.viewoneforum(foid.id).then(function(response) {
			self.oforum = response.data;
			viewallforumComments()
		}, function(errResponse) {
			alert('NO Forum Found');
		})
	}

	function selectoneforum(id) {
		fid.id = id;
		$location.path('/viewoneforum')
	}
	
		
	
	function createforumcomment(forumComment) {
		bservice.createforumcomment(forumComment).then(
				function(response) {
					alert('forumComments Posted Successfully ');
					resetComment();
				}, function(errResponse) {
					alert('forumComments Not Posted');
				})
	}

	function submitComment() 
	{
		alert(self.forumComment)
		self.forumComment.content = $rootScope.currentuser.name;
		self.forumComment.foid = foid.id;
		createforumComment(self.forumComment);
	}

	function resetComment() {
		self.forumComment = null;
	}
	
	
	
	
	
	viewallforums()
})
