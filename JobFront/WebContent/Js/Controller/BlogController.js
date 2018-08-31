angular.module('myApp').controller(
		'bcontroller',
		function($scope, $route,bservice, $location, bid, $rootScope) {
			var self = this;
			self.blog = {
				blogTitle : '',
				blogDescription : '',
				author : '',
				status : 'false',
				createdOn : null
			};
			self.blogComment = {
				blogCommentsId : 0,
				blogid : 0,
				comment : '',
				authorname : '',
				postedOn : null,
			};
			
				

			self.submit = submit;
			self.reset = reset;
			self.selectoneblog = selectoneblog;
			self.submitComment = submitComment;
			self.resetComment = resetComment;

			viewallblogs()
			viewoneblog()
			viewallblogComments()

			function createblog(blog) {
				bservice.createblog(blog).then(function(response) {
					alert('Blog Added Successfully ');
					reset();
				}, function(errResponse) {
					alert('Blog Not Added');
				})
			}

			function submit() {
				self.blog.author = $rootScope.currentuser.name;
				createblog(self.blog);
			}

			function reset() {
				self.blog = null;
			}

			function viewallblogComments() {
				bservice.viewallblogComments(bid.id).then(function(response) {
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
					self.sblog = response.data;
				}, function(errResponse) {
					alert('NO Blog Found');
				})
			}

			function selectoneblog(blogid) {
				bid.id = blogid;
				$location.path('/viewoneblog')
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

			function submitComment() 
			{
				alert(self.blogComment)
				self.blogComment.authorname = $rootScope.currentuser.name;
				self.blogComment.blogid = bid.id;
				createblogcomment(self.blogComment);
			}

			function resetComment() {
				self.blogComment = null;
			}

		})