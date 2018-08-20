app.factory('rservice',function($http)
    {
	var REST_SERVICE_URL='http://localhost:8080/JobMiddle/Customer';
	var factory = 
	{
			createUser: createUser,
	};
	return factory;
    function createUser(customer) 
    {
        return $http.post(REST_SERVICE_URL, customer);
    }
 });