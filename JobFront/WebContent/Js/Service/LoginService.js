app.factory('lservice',function($http)
    {
	var REST_SERVICE_URL='http://localhost:8080/JobMiddle/Login';
	var factory = 
	{
			LoginUser: loginUser,
	};
	return factory;
    function loginUser(customer) 
    {
        return $http.post(REST_SERVICE_URL, customer);
    }
 });