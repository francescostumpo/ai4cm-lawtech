var snamApp = angular.module("snamApp", ["angularjs-gauge", "checklist-model", "ngSanitize"]);

type = ['primary', 'info', 'success', 'warning', 'danger'];

var colorPalette = {
    palette : [
        {'entity' : 'Amendments', 'color' : '#c55a11'},
        {'entity' : 'Assignments', 'color' : '#f8cbad'},
        {'entity' : 'Communications', 'color' : '#fff2cc'},
        {'entity' : 'Confidentiality', 'color' : '#a9d18e'}
    ]
}

sessionStorage.setItem("palette", JSON.stringify(colorPalette)); 


 
mainController = {
	showNotification : function(from, align, message, color, icon) {

        $.notify({
            icon: icon,
            message: message

            }, {
            type: type[color],
            timer: 100,
            placement: {
                from: from,
                align: align
            }
            });
    },
    
    /*startProgressIndicator: function (id){
        console.log("[INFO] startProgressIndicator invoked")
        var div = document.getElementById("loading_"+id);
        div.innerHTML="<i class='fas fa-spinner'></i>";
        
    },
    
    stopProgressIndicator: function(id){
        console.log("[INFO] stopProgressIndicator invoked");
        var div = document.getElementById("loading_"+id);
        div.innerHTML="";
        
    },*/
    
    startProgressIndicator: function (id){
    	$(id).show();
        
        
    },
    
    stopProgressIndicator: function(id){
        setTimeout(function(){$(id).hide()}, 150);
        
    },

    sendTest: function(scope){
        console.log(scope.availableTemplates);
    },
    uploadFile: function($http, url, formData,config){
    	mainController.startProgressIndicator('#loading_1')
        $http.post(url, formData, config).then(function(response){
            console.log('callback of uploadFile is executed'); 
            console.log(response);
            if(response.status === 200){
            	mainController.stopProgressIndicator('#loading_1')
                mainController.showNotification('bottom', 'right', 'Contract successfully uploaded', 2, "icon-check-2"); 
            	
                // Reindirizza alla pagina dell'analyze con il contratto
            	var obj = JSON.parse(response.data)
                var contractId = obj.contractId; 
                sessionStorage.setItem('contractId', contractId);
                location.href = '/analyze';
            }else{
            	mainController.stopProgressIndicator('#loading_1')
                mainController.showNotification('bottom', 'right', 'Error while uploading contract', 3, "icon-alert-circle-exc");
            }
        }).catch(function(Exception){
        	mainController.stopProgressIndicator('#loading_1')
            mainController.showNotification('bottom', 'right', 'Error while uploading contract', 3, "icon-alert-circle-exc");
        });
    },

    sendDownloadRequest : function($http, url, data, config){
        //window.location.href = url;
        $http.post(url, data, config).then(function(response){
            console.log(response);
            debugger;
            var file = new Blob([response.data], {type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});

            //return response.data;
            mainController.downloadFile(file);
            
        }).catch(function(Exception){
            console.log(Exception)
        })
    },

    downloadFile: function(file){
        
        var url = window.URL || window.webkitURL;
        var element = document.createElement('a');
        element.setAttribute('href', url.createObjectURL(file));
        element.setAttribute('download', "Template.xlsx");
        element.style.display = 'none';
        document.body.appendChild(element);
        element.click();
        document.body.removeChild(element);
    },

    verifyResidualValue: function(value){
            
        if (value < 25){ return 'negligible';}
            else if (value >= 25 && value < 45){ return 'low';}
            else if (value >= 45 && value < 60){ return 'medium';}
            else if (value >= 60 && value < 70){ return 'high';}
            else{return 'very-high';}
    },

    test : function(){
        console.log("Test function");
    },

    getColorHighlight : function(entity){
        var palette = JSON.parse(sessionStorage.getItem('palette'));
        for(i=0; i < palette.palette.length; i++){
            if(entity === palette.palette[i].entity){
                return palette.palette[i].color;
            }
        }
    },
    uploadFileCompare: function($http, url, firstContractForm, secondContractForm, config){
        let firstContractId; 
        let secondContractId; 

        mainController.startProgressIndicator('#loading'); 

        console.log('in uploadFileCompare, firstContractForm is: ', firstContractForm); 
        console.log('in uploadFileCompare, secondContractForm is: ', secondContractForm); 

        $http.post(url, firstContractForm, config).then((response) => {
            if(response.status === 200){
                mainController.showNotification('bottom', 'right', 'First contract successfully uploaded', 2, "icon-check-2"); 

                console.log('in uploadFileCompare, response from first upload is: ', response); 
                firstContractId = JSON.parse(response.data).contractId; 
                sessionStorage.setItem('firstContractId', firstContractId); 
                console.log('firstContractId in session: ', sessionStorage.getItem('firstContractId')); 
                console.log('firstResponse.data is: ', response.data); 

                // Carica il secondo contratto 
                $http.post(url, secondContractForm, config).then((res) => {
                    if(res.status === 200){
                        console.log('in uploadFileCompare, response from second upload is: ', res); 
                        mainController.showNotification('bottom', 'right', 'Second contract successfully uploaded', 2, "icon-check-2"); 
                        secondContractId = JSON.parse(res.data).contractId; 
                        console.log('secondResponse.data is: ', res.data); 
                        sessionStorage.setItem('secondContractId', secondContractId); 
                        console.log('firstContractId in session: ', sessionStorage.getItem('firstContractId')); 
                        console.log('secondContractId in session: ', sessionStorage.getItem('secondContractId')); 
                        mainController.stopProgressIndicator('#loading'); 
                        window.location.href = mainController.getHost() + '/compare'; 
                        
                    } 
                    else {
                        mainController.showNotification('bottom', 'right', 'Error while uploading contracts'); 
                        mainController.stopProgressIndicator('#loading'); 
                    }
                }); 
            } 
            else {
                mainController.showNotification('bottom', 'right', 'Error while uploading contracts'); 
                mainController.stopProgressIndicator('#loading'); 
            }
        })
    },
    
    getHost: function(){
    	var location = window.location.hostname;
    	console.log('location ' + location)
    	var host = null;
    	if(location.includes("localhost")){
    		host = "http://localhost:3000"
    	}else if(location.includes("test")){
    		host = "//";
    	}else{
    		host = "https://snam-lawtech.eu-gb.mybluemix.net";
    	}
    	return host;
    }
}   