app.factory('lservice',function($http)
    {
	var REST_SERVICE_URL='http://localhost:8080/JobMiddle/Login';
	var factory = 
	{
			LoginUser: loginUser,
			getnotify:getnotify,
			editnotify:editnotify,
	};
	return factory;
    function loginUser(customer) 
    {
        return $http.post(REST_SERVICE_URL, customer);
    }
    function getnotify(emailId) 
    {
        return $http.get(REST_SERVICE_URL+"/notify?email="+emailId);
    }
    function editnotify(notificationId) 
    {
        return $http.get(REST_SERVICE_URL+"/notify/"+notificationId);
    }
 });