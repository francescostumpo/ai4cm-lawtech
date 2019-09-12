snamApp.controller("analyzeController", ['$scope', '$http', '$location', '$anchorScroll', '$rootScope', function($scope, $http, $location,$rootScope) {
    console.log("[INFO] Hello World from analyzeController"); 

  
    $scope.currentContract = sessionStorage.getItem('contractId');
    $scope.tabToBeViewed = 'document';
    
    if(sessionStorage.getItem('currentLanguage') == "ENG"){
        $scope.currentLanguage = "ENG"; 
        $scope.currentDictionary = JSON.parse(sessionStorage.getItem('englishDictionary')); 
    } 
    else { 
        $scope.currentLanguage = "ITA"; 
        $scope.currentDictionary = JSON.parse(sessionStorage.getItem('italianDictionary'));  
    } 

    $scope.responseFromBackend; 
    $scope.documentId; 
    $scope.documentText; 
    $scope.categories; 
    $scope.natures; 
    $scope.parties; 
    $scope.attributes; 
    $scope.definitions; 
    $scope.contacts; 
    $scope.dates; 
    $scope.involvedParties;
    
    $scope.sidenavRightCategories = []; 

    $scope.sidenavRightDefinitions = []; 

    $scope.sidenavRightContacts = []; 

    $scope.sidenavRightDates = []; 

    var host = mainController.getHost();

    $scope.getContract = function(){
    	mainController.startProgressIndicator('#loading_1');
    	$http({ 
            method: 'GET', 
            url: host + '/contract/retrieve?documentId=' + $scope.currentContract
        }).then((response) => { 

            // Valorizza la risposta con i valori che arrivano dal back-end 
        	sessionStorage.setItem('contractInSession', JSON.stringify(response));
            $scope.responseFromBackend = response.data;
            $scope.documentId = response.data.documentId; 
            $scope.documentText = response.data.documentText; 
            $scope.elements = response.data.elements; 
            $scope.categories = response.data.categories; 
            $scope.natures = response.data.natures; 
            $scope.attributes = response.data.attributes; 
            $scope.definitions = response.data.definitions; 
            $scope.parties = response.data.parties; 
            $scope.contacts = response.data.contacts; 
            $scope.dates = response.data.dates; 
            $scope.involvedParties = response.data.involvedParties
            mainController.stopProgressIndicator('#loading_1');
            
            console.log('elements: ', $scope.elements); 
            console.log('categories: ', $scope.categories); 
            console.log('natures: ', $scope.natures); 
            console.log('attributes: ', $scope.attributes); 
            console.log('definitions: ', $scope.definitions); 
            console.log('parties: ', $scope.parties); 
            console.log('contacts: ', $scope.contacts); 
            console.log('dates: ', $scope.dates); 
            console.log('involvedParties: ', $scope.involvedParties);

            console.log('response from HTTP request is: ', response); 

        }, (err) => {
        	mainController.stopProgressIndicator('#loading_1');
            console.log('Error in GET request: ', err); 
        }); 
    }
    
    $scope.contractInSession = JSON.parse(sessionStorage.getItem("contractInSession"));
    
    
    if( $scope.contractInSession === undefined ||  $scope.contractInSession === null ||  $scope.contractInSession.data.documentId != $scope.currentContract){
    	$scope.getContract();
    }else{
    	mainController.startProgressIndicator('#loading_1');
    	var response = JSON.parse(sessionStorage.getItem('contractInSession'));
    	$scope.responseFromBackend = response.data;
        $scope.documentId = response.data.documentId; 
        $scope.documentText = response.data.documentText; 
        $scope.elements = response.data.elements; 
        $scope.categories = response.data.categories; 
        $scope.natures = response.data.natures; 
        $scope.attributes = response.data.attributes; 
        $scope.definitions = response.data.definitions; 
        $scope.parties = response.data.parties; 
        $scope.contacts = response.data.contacts; 
        $scope.dates = response.data.dates; 
        $scope.involvedParties = response.data.involvedParties
        mainController.stopProgressIndicator('#loading_1');
    }
    

    //console.log('In analyzeController, $scope.baseResponse is: ', $scope.baseResponse); 


   /*  $scope.responseFromBackend = {
        "document" : "<h2>Lorem ipsum dolor sit amet</h2><p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p><p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p><h2>Lorem Ipsum Dolor</h2><p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>",
        "elements" : [
            {"id": 0, "text" : "<h2>Lorem ipsum dolor sit amet</h2>", "entity" : ["Amendments, Nature1"]},
            {"id": 1,"text" : "<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>", "entity" : ["Assignments"]},
            {"id": 2, "text" : "<p>12345 ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>", "entity" : ["Communications"]},
            {"id": 3, "text" : "<h2>Lorem Ipsum Dolor for Selection</h2>", "entity": []},
            {"id": 4, "text" : "<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>", "entity" : ["Confidentiality"]},
            {"id": 5, "text" : "<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>", "entity" : ["Confidentiality"]},
            {"id": 6, "text" : "<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>", "entity" : ["Confidentiality"]},
            {"id": 7, "text" : "<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>", "entity" : [""]},
            {"id": 8, "text" : "<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>", "entity" : [""]},
            {"id": 9, "text" : "<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>", "entity" : ["Confidentiality"]},
            {"id": 10, "text" : "<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>", "entity" : [""]},
            {"id": 11, "text" : "<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>", "entity" : [""]},
            {"id": 12, "text" : "<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>", "entity" : ["Confidentiality"]},
            {"id": 13, "text" : "<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>", "entity" : [""]},
            {"id": 14, "text" : "<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>", "entity" : [""]},

        ],
        "categories": [
            {"id": 0, name: "24/10/2025", type: "Termination"}, 
            {"id": 1, name: "NameTest", type: "Test"}
        
        ], 
        "attributes": [
            {"id": 0, name:"20/05/2021", type: "Deadline"}, 
            {"id": 1, name: "attTest", type: "AttributeTest"},
            {"id": 2, name: "5/7/2024", type: "Effective Date"}
        ], 
        "entities": [ 
            { "id": 0, "name": "Snam S.p.A.", type: "Contact Name"}, 
            { "id" : 1, "name": "Confidentiality", type: "Confidentiality"}, 
            { "id": 2, "name": "Oracle", type: "Contact Name"}
        ]
    } */ 
    
    // $scope.elements = $scope.responseFromBackend.elements; 
    if($scope.currentLanguage == "ENG"){
        $scope.actionFilterVerb = 'Hide'; 
    } 
    else { 
        $scope.actionFilterVerb = 'Nascondi'; 
    }
    
    $scope.actionFilter = function(){ 
        if($scope.actionFilterVerb === 'Show' || $scope.actionFilterVerb === 'Mostra'){ 
            if($scope.currentLanguage === "ENG") {
                $scope.actionFilterVerb = 'Hide';
            } 
            else {
                $scope.actionFilterVerb = 'Nascondi'; 
            }
            
        }else{ 
            if($scope.currentLanguage === 'ENG') {
                $scope.actionFilterVerb = 'Show'
            } 
            else {
                $scope.actionFilterVerb = 'Mostra';
            }
        }
    }

    $scope.bindHTML = function(id){ 
        for(i=0; i< $scope.elements.length; i++){
            if($scope.elements[i].sequence == id){ 
                return $scope.elements[i].text; 
            }
        }
    }


    /* Nuovo highlight (non funziona, da correggere) 

    $scope.highlight = function(filters, id){
        
        console.log('in highlight, filters is: ', filters); 
        
        // L'array entity contiene le entità dell'elemento 
        var entity = [];
        if(filters.toString().includes(',')){
            entity = filters.toString().split(',');
        }else{
            entity = filters;
        } 

        let isElementInEntityIntersection = true; 
        
        for(i=0; i < $scope.checkedValues.length; i++) {
            if(entity.indexOf($scope.checkedValues[i]) === -1){ 
                isElementInEntityIntersection = false; 
            }
        } 

        if(isElementInEntityIntersection){
            return {'color': '#004D96'}; 
        }
    }  */ 


    $scope.highlight = function(filters, id){
    	for(i=0;i<$scope.elementsArray.length;i++){
            if(id === $scope.elementsArray[i]){
            	return{'background-color' : 'rgba(217, 225, 233, 0.47)'};
            }
        }
//        console.log('in highlight, filters is: ', filters); 
//        
//        // L'array entity contiene le entità dell'elemento 
//        var entity = [];
//        if(filters.toString().includes(',')){
//            entity = filters.toString().split(',');
//        }else{
//            entity = filters;
//        } 
//        
//        for(i=0; i < entity.length; i++){
//            for(j=0; j< $scope.checkedValues.length; j++){
//                if(entity[i] === $scope.checkedValues[j]){
//                    //return {'color' : '#004D96'};
//                	return{'background-color' : 'rgba(217, 225, 233, 0.47)'}
//                }
//            }
//        }
        
    } 

    
    $scope.checkedValues = []; 
    $scope.checkedDates = []; 
    $scope.checkedDateId; 
    $scope.checkedContactId; 
    $scope.currentNature = null;
    $scope.checkedDefinitionId; 
    $scope.deselectedAll = true;

    $scope.change = function(input, item){

        console.log('change called'); 
        console.log('checkedValues before change called: ', $scope.checkedValues); 
        var found = false;
        var index = 0;
        var object; 
        object = input.name; 
        console.log('object is: ', object); 
        let elemsToDeleteCss = document.querySelectorAll('.highlightTextTemp'); 
        for(let i= 0; i < elemsToDeleteCss.length; i++){
            elemsToDeleteCss[i].classList.remove("highlightTextTemp"); 
        }

        if($scope.checkedValues.length === 0){
            $scope.checkedValues.push(object);
            $scope.putElementsInArray();
            if($scope.elementsArray.length !== 0){
            	$scope.actualIndex = 0;
            }
            $scope.scrollToElement('Down')
            return;
        }else{
            for(i=0; i< $scope.checkedValues.length; i++){
                if(object === $scope.checkedValues[i]){
                    found = true;
                    index = i;
                    break;
                }else{
                    found = false;
                }
            }
            if(found === false){
                $scope.checkedValues.push(object);
            }else{
                $scope.checkedValues.splice(index, 1);
            }
        }

        $scope.putElementsInArray();
        if($scope.elementsArray.length !== 0){
        	$scope.actualIndex = 0;
        }
        $scope.scrollToElement('Down')
    }
    
    $scope.natureClicked = function(nature, item){
        console.log('in nature clicked : nature = ', nature.name)
        $scope.currentNature = nature.name
        console.log($scope.checked)
        console.log($scope.checked)
    	$scope.populateFilteredElements();
    }
    $scope.natureSelected = 2;
    console.log('natureSelected : ', $scope.natureSelected)
    $scope.deselectNature = function(){
    	console.log('deselect nature');
    	$scope.natureSelected = 2;
    	$scope.currentNature = null;
    	$scope.populateFilteredElements();
    }
    
    $scope.populateFilteredElements = function(){
        console.log(' ## populateFilteredElements ##')
        console.log('checked Value : ' + $scope.checkedValues)
        console.log($scope.checkedValues)
        console.log($scope.currentNature)
        let elemsToDeleteCss = document.querySelectorAll('.highlightTextTemp'); 
        for(let i= 0; i < elemsToDeleteCss.length; i++){
        	elemsToDeleteCss[i].classList.remove("highlightTextTemp"); 
        }
        $scope.elementsArray = [];
        if($scope.currentNature == null){
            $scope.putElementsInArray()
        }
        else if($scope.checkedValues.length === 0){
            $scope.putElementsWithNatureInArray()
        }
        else{
        	console.log('entrato qui')
            $scope.putElementsInArray()
            console.log($scope.elementsArray)
            if($scope.currentNature !== null){
            //Metto in and con la natura selezionata
            	$scope.removeElementsWithoutNatureFromArray();
            }
        }
        if($scope.elementsArray.length !== 0){
        	$scope.actualIndex = 0;
        }
        $scope.scrollToElement('Down')
    }
    
    $scope.removeElementsWithoutNatureFromArray = function(){
        console.log('removeElementsWithoutNatureFromArray')
        var elementsArray2 = []
        for(i = 0; i < $scope.elementsArray.length; i++){
            var entityArray = [];
            var j = $scope.elementsArray[i]
            if($scope.elements[j].filters.toString().includes(',')){
                entityArray = $scope.elements[j].filters.toString().split(',');
            }else{
                entityArray = $scope.elements[j].filters;
            }
            for(k=0;k<entityArray.length;k++){
                if(entityArray[k] === $scope.currentNature){
                    elementsArray2.push(j)
                    break;
                }
            }
        }
        $scope.elementsArray = elementsArray2;
        if($scope.elementsArray.length !== 0){
            $scope.maxIndex = $scope.elementsArray.length;
            $scope.actualIndex = 0;
        }else{
            $scope.maxIndex = 0;
            $scope.actualIndex = 0;
        } 
        console.log('filter & nature : ' + $scope.elementsArray)

    }
    
    $scope.putElementsWithNatureInArray = function(){
    	console.log('putElementsWithNatureInArray')
        for(i=0;i < $scope.elements.length;i++){
            var entityArray = [];
            if($scope.elements[i].filters.toString().includes(',')){
                entityArray = $scope.elements[i].filters.toString().split(',');
            }else{
                entityArray = $scope.elements[i].filters;
            }
            for(k=0;k<entityArray.length;k++){
                if(entityArray[k] === $scope.currentNature){
                    $scope.elementsArray.push(i)
                }
            }
        }
        var elementsArrayUnique = new Set($scope.elementsArray);
        $scope.elementsArray = Array.from(elementsArrayUnique);
        console.log($scope.elementsArray.length)
        if($scope.elementsArray.length !== 0){
            $scope.maxIndex = $scope.elementsArray.length;
            $scope.actualIndex = 0;
        }else{
            $scope.maxIndex = 0;
            $scope.actualIndex = 0;
        } 

        console.log('elements with nature ' + $scope.currentNature + ' : ' + $scope.elementsArray);
    }


    $scope.elementsArray = []; 
    $scope.datesArray = []; 
    $scope.maxIndex = 0;
    $scope.actualIndex = 0;
    
    $scope.putElementsInArray = function(){
        $scope.elementsArray = [];
        for(i=0; i< $scope.checkedValues.length; i++){
            for(j=0; j < $scope.elements.length; j++){
                
                var entityArray = [];
                if($scope.elements[j].filters.toString().includes(',')){
                    entityArray = $scope.elements[j].filters.toString().split(',');
                }else{
                    entityArray = $scope.elements[j].filters;
                }
                
                for(k=0; k < entityArray.length; k++){
                    if($scope.checkedValues[i] === entityArray[k]){
                        $scope.elementsArray.push($scope.elements[j].sequence);
                        //break;
                    }
                }
            } 

            console.log('in putElementsInArray, $scope.elementsArray is: ', $scope.elementsArray);
            
        }
        
        var elementsArrayUnique = new Set($scope.elementsArray);
        
        $scope.elementsArray = Array.from(elementsArrayUnique);
        $scope.elementsArray.sort();
        
        if($scope.elementsArray.length !== 0){
            $scope.maxIndex = $scope.elementsArray.length;
            if($scope.maxIndex < $scope.actualIndex){
                $scope.actualIndex = $scope.maxIndex;
            }
        }else{
            $scope.maxIndex = 0;
            $scope.actualIndex = 0;
            
        } 

        console.log('checkedValues after change called: ', $scope.checkedValues); 

    } 
    
    $scope.deHighlightAll = function(){
    	let elemsToDeleteCss = document.querySelectorAll('.highlightTextTemp'); 
        console.log('in deselectAll, elemToDeleteCss is: ', elemsToDeleteCss); 

        for(let i= 0; i < elemsToDeleteCss.length; i++){
            elemsToDeleteCss[i].classList.remove("highlightTextTemp"); 
        }
    }

    $scope.highlightDate = function(dateId, dateSequence) {

        if($scope.checkedDateId){
            let elemToDeselect = document.querySelector('.date_' + $scope.checkedDateId); 
            elemToDeselect.classList.remove("highlightDate"); 
        }
        let elemToHighlight = document.querySelector('.date_' + dateId); 
        $scope.checkedDateId = dateId; 
        elemToHighlight.classList.toggle('highlightDate'); 
        location.href = '#element_' + dateSequence; 
        return; 

    } 


    $scope.highlightContact = function(contactId, contact_sequence){ 

        if($scope.checkedContactId){
            let elemToDeselect = document.querySelector('.contact_' + $scope.checkedContactId); 
            elemToDeselect.classList.remove("highlightContact"); 
        }
        let elemToHighlight = document.querySelector('.contact_' + contactId); 
        console.log('elemToHighlight is: ', elemToHighlight); 
        $scope.checkedContactId = contactId; 
        console.log('$scope.checkedContactId is: ', $scope.checkedContactId); 
        elemToHighlight.classList.toggle('highlightContact'); 
        location.href = '#element_' + contact_sequence; 
        return; 

    } 

    $scope.highlightDefinition = function(definitionId, definitionSequence){

        if($scope.checkedDefinitionId){
            let elemToDeselect = document.querySelector('.definition_' + $scope.checkedDefinitionId); 
            elemToDeselect.classList.remove("highlightDefinition"); 
        } 
        let elemToHighlight = document.querySelector('.definition_' + definitionId); 
        $scope.checkedDefinitionId = definitionId; 
        elemToHighlight.classList.toggle('highlightDefinition'); 
        location.href = '#element_' + definitionSequence; 
        return; 

    }

    
    $scope.populateSidenavRight = function(){

        // Popola le dates/durations  nella sidenav di destra 
        let elementsFromBackEnd = $scope.responseFromBackend.elements; 
        let dates = []; 

    }

    $scope.populateSidenavRightWithCategories = function(elementId) { 

        $scope.sidenavRightCategories = []; 
        $scope.sidenavRightDefinitions = []; 
        $scope.sidenavRightContacts = []; 
        $scope.sidenavRightDates = []; 

        // Popola le categories nella sidenav di destra 
        let elementsFromBackEnd = $scope.elements; 
        let element = elementsFromBackEnd.find((elem) => { return elem.sequence == elementId; }); 
        let entitiesInElement = element.filters[0].split(','); 
        console.log("entitiesInElement is: ", entitiesInElement); 

        for(let i=0; i < entitiesInElement.length; i++){ 
            let jsonToAdd = { 
                categoryName: entitiesInElement[i],
                categoryCount: 1
            }; 
            $scope.sidenavRightCategories.push(jsonToAdd); 
        } 
    } 

    $scope.scrollToElement = function(direction){
    	var p = null;
    	if($scope.tabToBeViewed === 'document'){
    		p = 'p';
    	}else{
    		p = 'ps';
    	}
    	
    	let elemsToDeleteCss = document.querySelectorAll('.boxed'); 
        for(let i= 0; i < elemsToDeleteCss.length; i++){
            elemsToDeleteCss[i].classList.remove("boxed"); 
        }
    	
        console.log('in $scope.scrollToElement, $scope.elementsArray is: ', $scope.elementsArray); 
        if($scope.elementsArray.length === 0){
            $scope.actualIndex = 0;
        }else if(direction === 'Down' && $scope.elementsArray.length !== 0){
        	console.log('scrollToElement : ' + $scope.actualIndex + ' ' +  $scope.elementsArray.length);
            if($scope.actualIndex < $scope.elementsArray.length ){
                $scope.actualIndex++;
                //location.href = '#element_'+$scope.elementsArray[$scope.actualIndex -1];
                $location.hash(p+'_' + $scope.elementsArray[$scope.actualIndex -1]);
                //$anchorScroll();
                //$('#element_'+$scope.elementsArray[$scope.actualIndex-1]).addClass('highlightTextTemp');
                $('#p_'+$scope.elementsArray[$scope.actualIndex-2]).removeClass('highlightTextTemp')
                $('#p_'+$scope.elementsArray[$scope.actualIndex-1]).addClass('highlightTextTemp')
                $('#ps_'+$scope.elementsArray[$scope.actualIndex-2]).removeClass('highlightTextTemp')
                $('#ps_'+$scope.elementsArray[$scope.actualIndex-1]).addClass('highlightTextTemp')
                $scope.populateSidenavRightWithCategories($scope.elementsArray[$scope.actualIndex-1])
                
                 //setTimeout(function(){$('#element_'+$scope.elementsArray[$scope.actualIndex-1]).removeClass('highlightTextTemp')}, 300);
            }
            else if($scope.actualIndex === $scope.elementsArray.length){
            	$scope.actualIndex = 0;
            	$scope.actualIndex++;
                console.log("actualIndex element: " + $scope.actualIndex+". SequenceElement: "+ $scope.elementsArray[$scope.actualIndex-1])
            	$location.hash(p+'_' + $scope.elementsArray[$scope.actualIndex -1]);
            	$('#p_'+$scope.elementsArray[$scope.elementsArray.length -1]).removeClass('highlightTextTemp')
            	$('#p_'+$scope.elementsArray[$scope.actualIndex-1]).addClass('highlightTextTemp')
            	$('#ps_'+$scope.elementsArray[$scope.elementsArray.length -1]).removeClass('highlightTextTemp')
            	$('#ps_'+$scope.elementsArray[$scope.actualIndex-1]).addClass('highlightTextTemp')
            	$scope.populateSidenavRightWithCategories($scope.elementsArray[$scope.actualIndex-1])
            }
        }else{
        	console.log('scrollToElement : ' + $scope.actualIndex + ' ' +  $scope.elementsArray.length);
            if($scope.actualIndex !== 0){
                if($scope.actualIndex-1 <=0){
                    $scope.actualIndex = $scope.elementsArray.length;
                    console.log("actualIndex element: " + $scope.actualIndex+". SequenceElement: "+ $scope.elementsArray[$scope.actualIndex-1])
                    $location.hash(p+'_' + $scope.elementsArray[$scope.actualIndex -1]);
                    $('#p_'+$scope.elementsArray[0]).removeClass('highlightTextTemp');
                    $('#p_'+$scope.elementsArray[$scope.actualIndex-1]).addClass('highlightTextTemp');
                    $('#ps_'+$scope.elementsArray[0]).removeClass('highlightTextTemp');
                    $('#ps_'+$scope.elementsArray[$scope.actualIndex-1]).addClass('highlightTextTemp');
                    $scope.populateSidenavRightWithCategories($scope.elementsArray[$scope.actualIndex-1])
                }else{
                    $scope.actualIndex--;
                    console.log("actualIndex element: " + $scope.actualIndex+". SequenceElement: "+ $scope.elementsArray[$scope.actualIndex-1])
                    $location.hash(p+'_' + $scope.elementsArray[$scope.actualIndex -1]);
                    //$anchorScroll();
                    //location.href = '#element_'+$scope.elementsArray[$scope.actualIndex -1];
                //$('#element_'+$scope.elementsArray[$scope.actualIndex-1]).addClass('highlightTextTemp');
                    $('#p_'+$scope.elementsArray[$scope.actualIndex]).removeClass('highlightTextTemp');
                    $('#p_'+$scope.elementsArray[$scope.actualIndex-1]).addClass('highlightTextTemp');
                    $('#ps_'+$scope.elementsArray[$scope.actualIndex]).removeClass('highlightTextTemp');
                    $('#ps_'+$scope.elementsArray[$scope.actualIndex-1]).addClass('highlightTextTemp');
                    $scope.populateSidenavRightWithCategories($scope.elementsArray[$scope.actualIndex-1])
                //setTimeout(function(){$('#element_'+$scope.elementsArray[$scope.actualIndex-1]).removeClass('highlightTextTemp')}, 300);
                }
            }
        }
    }
    $scope.selected = false;
    $scope.deselectAll = function(item){
    	console.log("deselectAll : " + $scope.radioButtonNatures)
    	$scope.natureSelected = 2;
    	$scope.checkedValues = [];
        $scope.deselectedAll = true;
        let elemsToDeleteCss = document.querySelectorAll('.highlightText'); 
        for(let i= 0; i < elemsToDeleteCss.length; i++){
            elemsToDeleteCss[i].classList.remove("highlightText"); 
        }
        elemsToDeleteCss = document.querySelectorAll('.highlightTextTemp'); 
        for(let i= 0; i < elemsToDeleteCss.length; i++){
            elemsToDeleteCss[i].classList.remove("highlightTextTemp"); 
        }
        elemsToDeleteCss = document.querySelectorAll('.highlightDate'); 
        for(let i= 0; i < elemsToDeleteCss.length; i++){
            elemsToDeleteCss[i].classList.remove("highlightDate"); 
        }
        elemsToDeleteCss = document.querySelectorAll('.highlightContact'); 
        for(let i= 0; i < elemsToDeleteCss.length; i++){
            elemsToDeleteCss[i].classList.remove("highlightContact"); 
        }
        elemsToDeleteCss = document.querySelectorAll('.highlightDefinition'); 
        for(let i= 0; i < elemsToDeleteCss.length; i++){
            elemsToDeleteCss[i].classList.remove("highlightDefinition"); 
        }
        $scope.selected = false;
        $scope.maxIndex =0
        $scope.elementsArray = []
        $scope.actualIndex = 0;
        
    	//location.href = "/analyze";
    }

    
    $scope.deselectCheckBox = function(input, item){
    	console.log('input ',input,' item ',item,' length:', $scope.checkedValues.length)
        if($scope.checkedValues.length === 0){
        	return false;
        }else{
            for(i=0; i< $scope.checkedValues.length; i++){
                if($scope.checkedValues[i] === input){
                	
                	//Necessario inserire logica secondo cui se deseleziono un filter nel mentre che un elemento che 
                	//contiene quel filter è selezionato in azzurro, devo deselezionarlo
                	/*var elementToDeselect = document.querySelector('.highlightTextTemp');
                	var elementToDeselectId = document.querySelector('.highlightTextTemp').id;
                	var sequenceArr = elementToDeselectId.split('_');
                	var sequence = sequenceArr[1];
                	
                	if($scope.elements[sequence].filters[0].includes(input)){
                		elementToDeselect.classList.removeClass('highlightTextTemp');
                	}*/
                    return true;
                }
            }
            return false;
        }
    } 

    $scope.isVoidArray = function(array){
        if(array.length == 0){ 
            return true; 
        } 
        return false; 
    }
    
    $scope.makeVisibleTab = function(itemToView, itemToHide){
        $('#'+itemToHide+'Button').removeClass('buttonActive');
        $('#'+itemToHide).hide();
        $('#'+itemToView+'Button').addClass('buttonActive');
        $('#'+itemToView).fadeIn();
        $scope.tabToBeViewed = itemToView;
    }

  
    $scope.filterElement = function(element){
    	for(i=0;i<$scope.elementsArray.length;i++){
    		if($scope.elementsArray[i] === element.sequence){
    			return element;
    		}
    	}
    }
    

  
    $scope.filterEntities = function(element){ 
        var entityArray = [];
        if(element.filters.toString().includes(',')){
            entityArray = element.filters.toString().split(',');
        }else{
            entityArray = element.filters;
        }

        if($scope.checkedValues.length === 0){
            return element;
        }
        for(i=0; i < entityArray.length; i++){
            for(j=0; j< $scope.checkedValues.length; j++){
                if(entityArray[i] === $scope.checkedValues[j]){
                    return element;
                }
            }
        }

    } 
    
    $scope.selectedElement = [];
    $scope.createBorderAndHighlightsSidenvaRight = function(id, tab){
    	if($scope.selectedElement.length === 0){
    		$('#p_'+id).addClass('boxed');
    		$('#ps_'+id).addClass('boxed');
    		$scope.selectedElement.push(id);
    	}else{
    		if($scope.selectedElement[0] === id){
    			$('#p_'+$scope.selectedElement[0]).removeClass('boxed');
    			$('#ps_'+$scope.selectedElement[0]).removeClass('boxed');
        		$scope.selectedElement = [];
        		$scope.sidenavRightCategories = []; 
    		}else{
    			$('#p_'+$scope.selectedElement[0]).removeClass('boxed');
    			$('#ps_'+$scope.selectedElement[0]).removeClass('boxed');
        		$scope.selectedElement = [];
        		$('#p_'+id).addClass('boxed');
        		$('#ps_'+id).addClass('boxed');
        		$scope.selectedElement.push(id);
    		}
    		
    	}
    }


}]);