angular.module('myApp').controller('lcontroller', function($scope, lservice, $location) {
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

	function loginuser(customer) {
		lservice.LoginUser(customer).then(function(response) {
			alert('login Successsfuly ');
			$location.path('/blog');
		}, function(errResponse) {
			alert('login UnSuccesssfuly');
		})
	}
	
	function submit()
	{
		loginuser(self.customer);
	}
})