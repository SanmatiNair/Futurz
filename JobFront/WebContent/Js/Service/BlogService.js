app.factory('bservice',function($http)
    {
	var REST_SERVICE_URL='http://localhost:8080/JobMiddle/Blog';
	var REST_SERVICE_URL1='http://localhost:8080/JobMiddle/BlogComment';
	var REST_SERVICE_URL2='http://localhost:8080/JobMiddle/mydata';
	var factory = 
	{
			createblog: createblog,
			viewallblogs: viewallblogs,
			viewoneblog: viewoneblog,
			viewmyblogs: viewmyblogs,
			createblogcomment: createblogcomment,
			viewallblogComments: viewallblogComments,
			deleteblog: deleteblog,
			deleteblogcomment: deleteblogcomment,
			viewLikedislike:viewLikedislike,
			createbloglikedislike:createbloglikedislike,
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
    
    function viewmyblogs(emailid) 
    {
        return $http.get(REST_SERVICE_URL2+"/blog?email="+emailid);
    }
    
    function deleteblog(id) 
    {
        return $http.delete(REST_SERVICE_URL2+"/blog/"+id);
    }
    
    function deleteblogcomment(id) 
    {
        return $http.delete(REST_SERVICE_URL1+"/"+id);
    }
    
    function viewLikedislike(blogid) 
    {
        return $http.get(REST_SERVICE_URL+"/ld/"+blogid);
    }
    
    function createbloglikedislike(likedislike) 
    {
        return $http.post(REST_SERVICE_URL+"/ld", likedislike);
    }
    
 });