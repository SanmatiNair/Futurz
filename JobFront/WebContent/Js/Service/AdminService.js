app.factory('aservice',function($http)
    {
	var factory = 
	{
			displayblogs: displayblogs,
			deleteblogs: deleteblogs,
			approveblog: approveblog,
	};
	return factory;
    function displayblogs() 
    {
    	var REST_SERVICE_URL='http://localhost:8080/JobMiddle/Admin/Blog';
        return $http.get(REST_SERVICE_URL);
    }
    
    function deleteblogs(blogid) 
    {
    	var REST_SERVICE_URL='http://localhost:8080/JobMiddle/Admin/Blog/'+blogid;
        return $http.delete(REST_SERVICE_URL);
    }
    
    function approveblog(blogid) 
    {
    	var REST_SERVICE_URL='http://localhost:8080/JobMiddle/Admin/BlogApprove/'+blogid;
        return $http.put(REST_SERVICE_URL);
    }
 });