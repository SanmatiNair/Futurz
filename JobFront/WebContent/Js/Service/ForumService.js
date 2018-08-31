app.factory('fservice',function($http)
    {
	var url='http://localhost:8080/JobMiddle/forum';
	var url1='http://localhost:8080/JobMiddle/ForumComment';
	var factory = 
	{
			createforum: createforum,
			viewallforums: viewallforums,
			viewoneforum: viewoneforum,
			createforumcomment: createforumcomment,
			viewallforumComments: viewallforumComments,
			
	};
	return factory;
    function createforum(forum) 
    {
        return $http.post(url, forum);
    }
    
    function viewallforums() 
    {
        return $http.get(url);
    }
    
    function viewoneforum(foid) 
    {
        return $http.get(url+"/"+foid);
    }
    
    function createforumcomment(forumComment) 
    {
        return $http.post(url1, forumComment);
    }
    
    function viewallforumComments(foid) 
    {
        return $http.get(url1+"/"+foid);
    }
 });