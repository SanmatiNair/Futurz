angular.module('myApp').controller(
		'lcontroller',
		function($scope, lservice, $location, $rootScope, $cookieStore) {
			var self = this;
			self.customer = {
				name : '',
				emailId : '',
				password : '',
				phoneNo : '',
				onlinestatus : 'false',
				role : '',
				imageurl : ''
			};

			self.notify = {
				notificationId : 0,
				blogTitle : '',
				status : '',
				viewed : null,
				emailId : ''
			}

			self.friend = {
				friendid : '',
				fromid : '',
				toid : '',
				status : ''
			}

			self.submit = submit;
			self.logout = logout;
			self.editnotify = editnotify;

			function getnotify(emailId) {
				lservice.getnotify(emailId).then(function(response) {
					$rootScope.mnotify = response.data;

				}, function(errResponse) {
					$rootScope.mnotify = null;
				})

			}
			function editnotify(notificationId) {
				lservice.editnotify(notificationId).then(function(response) {
					getnotify($rootScope.currentuser.emailId);
					alert("you viewed notification")
				}, function(errResponse) {
				})
			}

			function loginuser(customer) {
				lservice.LoginUser(customer).then(
						function(response) {
							self.customer = response.data;
							email.id = self.customer.emailId;
							$rootScope.currentuser = self.customer;
							$cookieStore.put('currentuser',
									$rootScope.currentuser);
							$rootScope.usersingnedin = true;
							$cookieStore.put('usersingnedin',
									$rootScope.usersingnedin);

							if (self.customer.role == "Student") {
								getnotify(self.customer.emailId);
								$location.path("/viewallblogs")
							}

							else if (self.customer.role == "Employee") {
								getnotify(self.customer.emailId);

								$location.path("/viewalljobs")
							}

							else if (self.customer.role == "Employer") {
								getnotify(self.customer.emailId);
								$location.path("/job")
							} else
								$location.path("/blogapproval")
							alert('Login Successsful');
						}, function(errResponse) {
							alert('login UnSuccesssful');
						})
			}

			function submit() {
				loginuser(self.customer);
			}

			function logout() {
				lservice.LogoutUser($rootScope.currentuser.emailId).then(
						function(response) {
							$rootScope.currentuser = null;
							$cookieStore.remove('currentuser');
							$cookieStore.remove('notifycurrentuser');
							$location.path('/');
							alert('logout Successsful');

						}, function(errResponse) {
							alert('logout UnSuccesssful');
						})
			}
		})