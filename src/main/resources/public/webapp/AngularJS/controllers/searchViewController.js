snamApp.controller("searchViewController", ['$scope', '$http', '$location', '$rootScope', function($scope, $http, $location,$rootScope) { 
    console.log("[INFO] Hello World from searchViewController"); 
    
    $scope.baseEndpoint = 'file:///Users/guidorocco/Documents/Other_Projects/snamLawtech/snam-frontend/public/'; 

    $scope.tableRows = [];
    var allValue = "All";

    $scope.involvedParties = [
        {"name": allValue},
    ]   

    $scope.governingLaws = [ 
        {"name": allValue}
    ]

    $scope.inForces = [
        {"name": allValue},
        {"name": "Italiana"},
        {"name": "English"}
    ];
    
    $scope.contractTypes = [
        {"name": allValue}
    ]

    var host = mainController.getHost();
    
    $http.get(host + '/getAllInvolvedParties').then(function(response){
       response.data.forEach(type => {
           $scope.involvedParties.push({"name": type.name})
       })
    })
    
     $http.get(host + '/getAllGoverningLaw').then(function(response){
       response.data.forEach(type => {
    	   console.log(response.data);
           $scope.governingLaws.push({"name": type.type})
       })
    })

    $http.get(host + '/getAllContractType').then(function(response){
        response.data.forEach(type => {
            $scope.contractTypes.push({"name": type.type})
        })
    })

    $scope.searchContract = function(searchForm){
        $scope.tableRows = [];
        mainController.startProgressIndicator('#loading_1');
        var url = host + '/searchContract';
        $http.post(url, searchForm).then(function(response){
            console.log(response.data.length == 0)
            if(response.data.length == 0){
            	mainController.stopProgressIndicator('#loading_1');
                mainController.showNotification('bottom', 'right', 'No elements found', 3,'fa fa-exclamation-circle');
            }
            console.log(response.data);
            $scope.tableRows = response.data;
            mainController.stopProgressIndicator('#loading_1');
            //response.data.forEach(contract => {
              //  $scope.tableRows.push(contract)
            //})
        }).catch(function(exception){
            console.log(exception)
            mainController.stopProgressIndicator('#loading_1');
        })
        
        
    }

    $scope.goToAnalyze = function(contractId){
        console.log(contractId); 
        console.log('in $scope.goToAnalyze, redirecting to: ', host + 'analyze?contractId=' + contractId); 
        //window.location.href = host + 'analyze?contractId=' + contractId;  
        sessionStorage.setItem("contractId", contractId);
        location.href = '/analyze';
    } 

    $scope.makeVisibleTab = function(itemToView, itemToHide) {
        $('#'+itemToHide).removeClass('active'); 
        $('#'+itemToHide).hide(); 
        $('#'+itemToView).addClass('active'); 
        $('#'+itemToView).fadeIn(); 
    }


}]); 