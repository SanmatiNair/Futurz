angular
		.module('myApp')
		.controller(
				'bcontroller',
				function($scope, $route, bservice, $location, bid, bed,
						$rootScope) {
					var self = this;
					self.blog = {
						blogId : 0,
						blogTitle : '',
						blogDescription : '',
						author : '',
						authoremail : '',
						status : '',
						createdOn : null
					};
					self.oblog = {
						blogId : 0,
						blogTitle : '',
						blogDescription : '',
						author : '',
						authoremail : '',
						status : '',
						createdOn : null
					};
					self.blogComment = {
						blogCommentsId : 0,
						blogid : 0,
						comment : '',
						authorname : '',
						postedOn : null,
					};
					self.likedislike = {
						dummyid : 0,
						blogId : 0,
						likecount : 0,
						dislikecount : 0,
					}
					
					
					self.submit = submit;
					self.reset = reset;
					self.selectoneblog = selectoneblog;
					self.submitComment = submitComment;
					self.resetComment = resetComment;

					viewallblogs()
					viewoneblog()
					viewallblogComments()

					function createblog(oblog) {
						bservice.createblog(oblog).then(function(response) {
							alert('Blog Added Successfully ');
							reset();
						}, function(errResponse) {
							alert('Blog Not Added');
						})
					}

					function submit() {
						self.oblog.author = $rootScope.currentuser.name;
						self.oblog.authoremail = $rootScope.currentuser.emailId;
						self.oblog.status = 'false';
						createblog(self.oblog);
					}

					function reset() {
						self.blog = null;
						self.oblog = null;
					}

					function viewallblogComments() {
						bservice.viewallblogComments(bid.id).then(
								function(response) {
									self.blogComments = response.data;
								}, function(errResponse) {
									alert('NO Blog Found');
								})
					}

					function viewallblogs() {
						bservice.viewallblogs().then(function(response) {
							self.blogs = response.data;
						}, function(errResponse) {
							alert('NO Blog Found');
						})
					}

					function viewoneblog() {
						bservice.viewoneblog(bid.id).then(function(response) {
							if (bed.edit == true) {
								self.oblog = response.data;
								bed.edit = false;
							} else {
								viewmylike();
								self.blog = response.data;
							}
							

						}, function(errResponse) {
							alert('NO Blog Found');
						})
					}

					function selectoneblog(blogid) {
						bid.id = blogid;
						$location.path('/viewoneblog')
					}

					self.editblog = function(id) {
						bid.id = id;
						bed.edit = true;
						$location.path("/blog")
					}

					function createblogcomment(blogComment) {
						bservice.createblogcomment(blogComment).then(
								function(response) {
									alert('BlogComments Posted Successfully ');
									resetComment();
									$route.reload();
								}, function(errResponse) {
									alert('BlogComments Not Posted');
								})
					}

					function submitComment() {
						alert(self.blogComment)
						self.blogComment.authorname = $rootScope.currentuser.name;
						self.blogComment.blogid = bid.id;
						createblogcomment(self.blogComment);
					}

					function resetComment() {
						self.blogComment = null;
					}

					function viewmyblogs() {
						bservice.viewmyblogs($rootScope.currentuser.emailId)
								.then(function(response) {
									self.myblogs = response.data;
									viewmylike();
								}, function(response) {
									self.myblogs = null;
								})

					}

					function viewmylike() {
						bservice.viewLikedislike(bid.id).then(
								function(response) {
									self.likedislike = response.data;
								}, function(response) {
									self.likedislike = response.data;
								})
					}

					self.deleteblog = function(id) {
						bservice.deleteblog(id).then(function(response) {
							alert('blog deleted')
							$route.reload();
						}, function(errResponse) {
							alert('NO Blog Found');
						})
					}

					self.deleteblogcomment = function(id) {
						bservice.deleteblogcomment(id).then(function(response) {
							alert('blogcomment deleted')
							$route.reload();
						}, function(errResponse) {
							alert('NO Blogcomment Found');
						})
					}

					self.updatelike = function(id) {
						self.likedislike.blogId = bid.id;
						self.likedislike.likecount = (self.likedislike.likecount + 1);
						bservice.createbloglikedislike(self.likedislike).then(
								function(response) {
									alert('Blog Added Successfully ');
								}, function(errResponse) {
									alert('Blog Not Added');
								})
					}

					self.updatedislike = function(id) {
						self.likedislike.blogId = bid.id;
						self.likedislike.dislikecount = (self.likedislike.dislikecount + 1);
						bservice.createbloglikedislike(self.likedislike).then(
								function(response) {
									alert('Blog Added Successfully ');
								}, function(errResponse) {
									alert('Blog Not Added');
								})
					}

					viewmyblogs()

				})