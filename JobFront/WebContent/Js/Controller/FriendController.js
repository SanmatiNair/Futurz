angular.module('myApp').controller('fcontroller',
		function($scope, lservice, $location, $rootScope, $cookieStore, $route) {
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

			self.friend = {
				friendid : '',
				fromid : '',
				toid : '',
				status : ''
			}

		
			getsugg()
			getpend()
			getfred()
			
			self.submit=submit;
			self.update=update;
			self.rdelete=rdelete;

			function getsugg() {
				lservice.getsugg($rootScope.currentuser.emailId).then(function(response) {
					self.scustomer = response.data;
				}, function(errResponse) {
					self.scustomer = null;
				})

			}

			function getpend() {
				lservice.getpend($rootScope.currentuser.emailId).then(function(response) {
					self.pcustomer = response.data;
				}, function(errResponse) {
					self.pcustomer = null;
				})

			}

			function getfred() {
				lservice.getfred($rootScope.currentuser.emailId).then(function(response) {
					self.fcustomer = response.data;

				}, function(errResponse) {
					self.fcustomer = null;
				})
			}
			
			function submit(email) {
				self.friend.fromid = $rootScope.currentuser.emailId;
				self.friend.toid = email;
				self.friend.status = 'Pending';
				createrequest(self.friend);
			}
			
			function createrequest(friend) {
				lservice.createrequest(friend).then(function(response) {
					alert('Request Sent');
					$route.reload();
				}, function(errResponse) {
					alert('Request Not Sent');
				})
			}
			
			function update(email) {
				lservice.update(email,$rootScope.currentuser.emailId).then(function(response) {
					alert('Request Approved');
					$route.reload();
				}, function(errResponse) {
					alert('Request Not Sent');
				})
			}
			
			function rdelete(email) {
				lservice.rdelete(email,$rootScope.currentuser.emailId).then(function(response) {
					alert('Request rejected');
					$route.reload();
				}, function(errResponse) {
					alert('Request Not Sent');
				})
			}
		
		})