angular.module('myApp').controller('lcontroller', function($scope, lservice, $location,$rootScope,$cookieStore) {
	var self = this;
	self.customer = {
			name : '',
		emailId : '',
		password : '',
		phoneNo : '',
		onlinestatus : 'false',
		role : ''
	};
	self.submit = submit;
	self.logout = logout;

	$rootScope.currentuser=self.customer;

	function loginuser(customer) {
		lservice.LoginUser(customer).then(function(response) {
			self.customer=response.data;
			$rootScope.currentuser=self.customer;
			$cookieStore.put('currentuser',	$rootScope.currentuser);
			$rootScope.usersingnedin=true;
			if(self.customer.role=="Student")
				$location.path("/viewallblogs")
			else if(self.customer.role=="Employee")
				$location.path("/viewalljobs")
			else if(self.customer.role=="Employer")
				$location.path("/job")
			else
				$location.path("/blogapproval")
			alert('Login Successsful');
			}, function(errResponse) {
			alert('login UnSuccesssful');
			})
	}
	
	function submit()
	{
		loginuser(self.customer);
	}
	
	function logout()
	{
		self.customer=null;
		$rootScope.currentuser = null;
		$cookieStore.remove('currentuser');
		$location.path('/');

	}
})