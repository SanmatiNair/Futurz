angular.module('myApp').controller('rcontroller', function($scope, rservice, $location) {
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

	function createuser(customer) {
		rservice.createUser(customer).then(function(response) {
			alert('Registered Successsfuly LogIn To Continue');
			$location.path('/login');
		}, function(errResponse) {
			alert('Registered UnSuccesssfuly');
		})
	}
	
	function submit()
	{
		createuser(self.customer);
	}
})