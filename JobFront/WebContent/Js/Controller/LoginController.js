angular.module('myApp').controller('lcontroller',
		function($scope, lservice, $location, $rootScope, $cookieStore) {
			var self = this;
			self.customer = {
				name : '',
				emailId : '',
				password : '',
				phoneNo : '',
				onlinestatus : 'false',
				role : ''
			};

			self.notify = {
				notificationId : 0,
				blogTitle : '',
				status : '',
				viewed : null,
				emailId : ''
			}

			self.submit = submit;
			self.logout = logout;
			self.editnotify = editnotify;

			$rootScope.currentuser = self.customer;
			
			
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
				lservice.LoginUser(customer).then(function(response) {
					self.customer = response.data;
					$rootScope.currentuser = self.customer;
					$cookieStore.put('currentuser', $rootScope.currentuser);
					$rootScope.usersingnedin = true;
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
				self.customer = null;
				$rootScope.currentuser = null;
				$cookieStore.remove('currentuser');
				$cookieStore.remove('notifycurrentuser');
				$location.path('/');

			}
		})