app.factory('lservice',function($http)
    {
	var REST_SERVICE_URL='http://localhost:8080/JobMiddle/Login';
	var REST_SERVICE_URL1='http://localhost:8080/JobMiddle/Friend';

	var factory = 
	{
			LoginUser: loginUser,
			getnotify:getnotify,
			editnotify:editnotify,
			getsugg:getsugg,
			getpend:getpend,
			getfred:getfred,
			LogoutUser: LogoutUser,
			createrequest:createrequest,
			update:update,
			rdelete:rdelete
	};
	return factory;
    function loginUser(customer) 
    {
        return $http.post(REST_SERVICE_URL, customer);
    }
    function LogoutUser(emailId) 
    {
        return $http.post(REST_SERVICE_URL+"/logout?email="+emailId);
    }
    function getnotify(emailId) 
    {
        return $http.get(REST_SERVICE_URL+"/notify?email="+emailId);
    }
    function editnotify(notificationId) 
    {
        return $http.get(REST_SERVICE_URL+"/notify/"+notificationId);
    }
    function getsugg(emailId) 
    {
        return $http.get(REST_SERVICE_URL1+"/suggest?email="+emailId);
    }
    function getpend(emailId) 
    {
        return $http.get(REST_SERVICE_URL1+"/pending?email="+emailId);
    }
    function getfred(emailId) 
    {
        return $http.get(REST_SERVICE_URL1+"/?email="+emailId);
    }
    function createrequest(friend) 
    {
        return $http.post(REST_SERVICE_URL1, friend);
    }
    function update(id) 
    {
        return $http.get(REST_SERVICE_URL1+"/"+id);
    }
    function rdelete(id) 
    {
        return $http.delete(REST_SERVICE_URL1+"/rdelete/"+id);
    }
 });