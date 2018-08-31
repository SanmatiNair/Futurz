app.factory('bservice',function($http)
    {
	var REST_SERVICE_URL='http://localhost:8080/JobMiddle/Blog';
	var REST_SERVICE_URL1='http://localhost:8080/JobMiddle/BlogComment';

	var factory = 
	{
			createblog: createblog,
			viewallblogs: viewallblogs,
			viewoneblog: viewoneblog,
			createblogcomment: createblogcomment,
			viewallblogComments: viewallblogComments,
	};
	return factory;
    function createblog(blog) 
    {
        return $http.post(REST_SERVICE_URL, blog);
    }
    
    function viewallblogs() 
    {
        return $http.get(REST_SERVICE_URL);
    }
    
    function viewoneblog(blogid) 
    {
        return $http.get(REST_SERVICE_URL+"/"+blogid);
    }
    
    function createblogcomment(blogComment) 
    {
        return $http.post(REST_SERVICE_URL1, blogComment);
    }
    
    function viewallblogComments(blogid) 
    {
        return $http.get(REST_SERVICE_URL1+"/"+blogid);
    }
    
 });