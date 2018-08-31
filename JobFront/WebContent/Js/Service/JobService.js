app.factory('jservice',function($http)
    {
	var url='http://localhost:8080/JobMiddle/job';
	var factory = 
	{
			createjob: createjob,
			viewalljobs: viewalljobs,
			viewonejob: viewonejob
	};
	return factory;
    function createjob(job) 
    {
        return $http.post(url, job);
    }
    
    function viewalljobs() 
    {
        return $http.get(url);
    }
    function viewonejob(id) 
    {
    	var joburl = url +"/"+id;
        return $http.get(joburl);
    }
 });
