app.factory('aservice',function($http)
    {
	var factory = 
	{
			displayblogs: displayblogs,
			deleteblogs: deleteblogs,
			approveblog: approveblog,
			getalljobs: getalljobs,
			deletejob: deletejob,
			approvejob: approvejob
			
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
    
    
    function getalljobs() 
    {
    	var url='http://localhost:8080/JobMiddle/Admin/jobs';
        return $http.get(url);
    }
    function deletejob(id)
    {
    	var url="http://localhost:8080/JobMiddle/Admin/job/"+id;
		return $http.delete(url);
    } 
    
    function approvejob(id)
    {
    	var url="http://localhost:8080/JobMiddle/Admin/jobapprove/"+id;
		return $http.put(url);
    }
    
 });