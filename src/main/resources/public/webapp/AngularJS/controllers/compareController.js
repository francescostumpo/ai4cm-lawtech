snamApp.controller("compareController", ['$scope', '$http', '$location', function($scope, $http, $location) {

    //console.log("[INFO] Hello World from analyzeController"); 

  $scope.analyzeEndpoint = 'http://127.0.0.1:3000/analyze?documentId='; 

  $scope.firstContractId; 
  $scope.secondCOntractId; 

 
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


    $scope.elementsArray = []; 
    $scope.datesArray = []; 

    $scope.responseFromBackendSecondContract; 
    $scope.documentIdSecondContract; 
    $scope.documentTextSecondContract; 
    $scope.categoriesSecondContract; 
    $scope.naturesSecondContract; 
    $scope.partiesSecondContract; 
    $scope.attributesSecondContract; 
    $scope.definitionsSecondContract; 
    $scope.contactsSecondContract; 
    $scope.datesSecondContract; 


    $scope.elementsAll = []; 
    $scope.categoriesAll = []; 
    $scope.naturesAll = []; 
    $scope.partiesAll = []; 
    $scope.attributesAll = []; 
    $scope.definitionsAll = []; 
    $scope.contactsAll = []; 
    $scope.datesAll = []; 


    
    $scope.checkedValues = []; 
    $scope.checkedDates = []; 
    $scope.checkedDateId; 
    $scope.checkedContactId; 
    $scope.checkedDefinitionId; 
    $scope.deselectedAll = true;

    $scope.maxIndex = 0;
    $scope.actualIndex = 0; 

    $scope.numberOfElementsInFirstContract; 



    $http({
        method: 'GET', 
        url: mainController.getHost() + "/contract/retrieve?documentId=" + sessionStorage.getItem('firstContractId') 
    }).then((response) => {

        // Valorizza la risposta con i valori che arrivano dal back-end 
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


        $scope.numberOfElementsInFirstContract = $scope.elements.length; 
        console.log('in first HTTP call, $scope.numberOfElementsInFirstContract is: ', $scope.numberOfElementsInFirstContract); 


        $scope.elementsAll.push(...response.data.elements); 
        $scope.categoriesAll.push(...response.data.categories)
        $scope.naturesAll.push(...response.data.natures); 
        $scope.attributesAll.push(...response.data.attributes); 
        $scope.definitionsAll.push(...response.data.definitions); 
        $scope.partiesAll.push(...response.data.parties); 
        $scope.contactsAll.push(...response.data.contacts); 
        $scope.datesAll.push(...response.data.dates); 

        console.log('elements: ', $scope.elements); 
        console.log('categories: ', $scope.categories); 
        console.log('natures: ', $scope.natures); 
        console.log('attributes: ', $scope.attributes); 
        console.log('definitions: ', $scope.definitions); 
        console.log('parties: ', $scope.parties); 
        console.log('contacts: ', $scope.contacts); 
        console.log('dates: ', $scope.dates); 


        console.log('response from HTTP request is: ', response); 

        $scope.makeSecondHttpCall(); 

    }, (err) => {
        console.log('Error in GET request: ', err); 
    });  





    
    // $scope.elements = $scope.responseFromBackend.elements; 
    if($scope.currentLanguage == "ENG"){
        $scope.actionFilterVerb = 'Show'; 
    } 
    else {
        $scope.actionFilterVerb = 'Mostra'; 
    }
  
    
    $scope.makeSecondHttpCall = function() {



    $http({
        method: "GET",
        url: mainController.getHost() + "/contract/retrieve?documentId=" + sessionStorage.getItem('secondContractId')
    }).then(async (response) => {

        // Valorizza la risposta con i valori che arrivano da back-end 
        $scope.responseFromBackendSecondContract = response.data; 
        $scope.documentIdSecondContract = response.data.documentId; 
        $scope.documentTextSecondContract = response.data.documentText; 
        $scope.elementsSecondContract = response.data.elements; 
        $scope.categoriesSecondContract = response.data.categories; 
        $scope.naturesSecondContract = response.data.natures; 
        $scope.attributesSecondContract = response.data.attributes; 
        $scope.partiesSecondContract = response.data.parties; 
        $scope.contactsSecondContract = response.data.contacts; 
        $scope.datesSecondContract = response.data.dates; 


        // Shifta la sequence degli elementi del secondo contratto 
        // in modo da poter distinguere gli elementi dei due contratti
        await $scope.elementsSecondContract.forEach((item) => {
            item.sequence = item.sequence + $scope.numberOfElementsInFirstContract; 
        }); 

        // Aggiunge le categorie, nature, ecc. alla lista dello scope
        $scope.elementsAll.push(...$scope.elementsSecondContract); 
        $scope.categoriesAll.push(...$scope.categoriesSecondContract); 
        $scope.naturesAll.push(...$scope.naturesSecondContract); 
        $scope.attributesAll.push(...$scope.attributesSecondContract); 
        $scope.partiesAll.push(...$scope.partiesSecondContract); 
        $scope.contactsAll.push(...$scope.partiesSecondContract); 
        $scope.datesAll.push(...$scope.datesSecondContract); 

        // Filtra i duplicati nelle liste di categorie, nature, ecc. (Da fare) 
    
        console.log('in second HTTP call, $scope.elementsSecondContract is: ', $scope.elementsSecondContract); 

        $scope.categoriesAll = $scope.filterLeftNavbarFromDuplicates($scope.categoriesAll); 
        $scope.naturesAll = $scope.filterLeftNavbarFromDuplicates($scope.naturesAll); 
        $scope.partiesAll = $scope.filterLeftNavbarFromDuplicates($scope.partiesAll); 
        $scope.attributesAll = $scope.filterLeftNavbarFromDuplicates($scope.attributesAll); 



        console.log('in second HTTP call, $scope.categoriesAll after filtering is: ', $scope.categoriesAll);



    }, (err) => {
        console.log('Error in second GET request: ', err); 
    }); 



    } 



    $scope.removeDuplicates = function (originalArray, objKey) {

        var trimmedArray = [];
        var values = [];
        var value;
      
        for(var i = 0; i < originalArray.length; i++) {
          value = originalArray[i][objKey];
      
          if(values.indexOf(value) === -1) {
            trimmedArray.push(originalArray[i]);
            values.push(value);
          }
        }
      
        return trimmedArray;
      
      } 



     /*
      * Rimuove i duplicati da un array di entità. 
      * Poiché vi sono soltanto due contratti, 
      * una stessa entità comparirà al massimo due volte 
      */ 
    $scope.filterLeftNavbarFromDuplicates = function(entity){


        // Ordina l'array di entità in ordine alfabetico 
        entity.sort((a, b) => {
            return a.name > b.name; 
        }); 


        // Elimina la hashKey dall'elemento
        entity.forEach((elem) => {
            if(elem.$$hashKey){
                delete elem.$$hashKey; 
            }
        }); 
        

        // Modifica il count dell'entità che sarà conservata, 
        // in modo che comprenda il conto di entrambi gli elementi
        entity.forEach((item) => {

            let indexOfItem = entity.indexOf(item); 
            if(entity[indexOfItem + 1] && entity[indexOfItem + 1].name === item.name) {
                item.count += entity[indexOfItem + 1].count; 
            } 
        }); 



        // Crea l'array di entità filtrate e lo ritorna 
        let filteredEntities = []; 

        for(let item of entity){
            
            let indexOfItem = entity.indexOf(item); 
            if(indexOfItem === 0){
                filteredEntities.push(item); 
            } 
            else {
                if(entity[indexOfItem - 1] && entity[indexOfItem - 1].name === item.name) {
                    continue; 
                } 
                else {
                    filteredEntities.push(item); 
                } 
            }
        }
        

       return filteredEntities; 


        
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


    $scope.bindHTMLSecondContract = function(id){
        for(i=0; i< $scope.elementsSecondContract.length; i++){
            if($scope.elementsSecondContract[i].sequence == id){
                return $scope.elementsSecondContract[i].text; 
            }
        }
    }





    $scope.highlight = function(filters, id){
        
        //console.log('in highlight, filters is: ', filters); 
        
        // L'array entity contiene le entità dell'elemento 
        var entity = [];
        if(filters.toString().includes(',')){
            entity = filters.toString().split(',');
        }else{
            entity = filters;
        } 
        
        for(i=0; i < entity.length; i++){
            for(j=0; j< $scope.checkedValues.length; j++){
                if(entity[i] === $scope.checkedValues[j]){
                    return {'color' : '#004D96'};
                }
            }
        }
        
    } 


    $scope.change = function(input, item){

        //console.log('change called'); 
        //console.log('checkedValues before change called: ', $scope.checkedValues); 
        var found = false;
        var index = 0;
        var object; 
        object = input.name; 
        console.log('object is: ', object); 

        if($scope.checkedValues.length === 0){
            $scope.checkedValues.push(object);
            $scope.putElementsInArray(); 
            console.log('in $scope.putElementsInArray after change, $scope.actualIndex is: ', $scope.actualIndex); 
            console.log('in $scope.putElementsInArray after change, $scope.maxIndex is: ', $scope.maxIndex); 
            //console.log('in $scope.putElementsInArray, $scope.checkedValues is: ', $scope.checkedValues); 
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
        console.log('in $scope.putElementsInArray, $scope.checkedValues is: ', $scope.checkedValues); 
        console.log('in $scope.putElementsInArray after change, $scope.actualIndex is: ', $scope.actualIndex); 
        console.log('in $scope.putElementsInArray after change, $scope.maxIndex is: ', $scope.maxIndex); 

    }



    
    $scope.putElementsInArray = function(){
        $scope.elementsArray = []; 
       // console.log('in $scope.putElementsInArray, $scope.checkedValues is: ', $scope.checkedValues); 
        for(i=0; i< $scope.checkedValues.length; i++){
            for(j=0; j < $scope.elementsAll.length; j++){
                
                var entityArray = []; 
                if($scope.elementsAll[j].filters.toString().includes(',')){
                    entityArray = $scope.elementsAll[j].filters.toString().split(',');
                }else{
                    entityArray = $scope.elementsAll[j].filters;
                }
                
                for(k=0; k < entityArray.length; k++){
                    if($scope.checkedValues[i] === entityArray[k]){
                        $scope.elementsArray.push($scope.elementsAll[j].sequence);
                        //break;
                    }
                }
            } 

            
        } 

        //console.log('in $scope.putElementsInArray, $scope.maxIndex is: ', $scope.maxIndex); 

        
        var elementsArrayUnique = new Set($scope.elementsArray);
        
        $scope.elementsArray = Array.from(elementsArrayUnique);
        $scope.elementsArray.sort((a, b) =>  ( parseInt(a) > parseInt(b) )); 

        //console.log('in $scope.putElementsInArray, $scope.elementsArray is: ', $scope.elementsArray); 
        
        if($scope.elementsArray.length !== 0){
            console.log('$scope.elementsArray !== 0, executing...'); 
            $scope.maxIndex = $scope.elementsArray.length;
            if($scope.maxIndex < $scope.actualIndex){
                $scope.actualIndex = $scope.maxIndex;
            } 

        }else{
            $scope.maxIndex = 0;
            $scope.actualIndex = 0;
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
        let element = elementsFromBackEnd.find((elem) => ( elem.sequence == elementId )); 
        let entitiesInElement = element.filters[0].split(','); 
        console.log("entitiesInElement is: ", entitiesInElement); 

        for(let i=0; i < entitiesInElement.length; i++){

            let jsonToAdd = {
                categoryName: entitiesInElement[i],
                categoryCount: 1
            }; 
            $scope.sidenavRightCategories.push(jsonToAdd); 

        } 
        
        
        // Popola le dates/durations nella sidenav di destra 
        //let dates = elementsFromBackEnd.find((elem) => { })
    } 
 
    $scope.testFunction = function(direction){
    	console.log('direction in test function ', direction);
    } 
    
    


    $scope.scrollToElement = function(direction){

            console.log('in $scope.scrollToElement, $scope.elementsArray is: ', $scope.elementsArray); 

            if($scope.elementsArray.length === 0){

                $scope.actualIndex = 0;

            }else if(direction === 'Down' && $scope.elementsArray.length !== 0){

                console.log('scrollToElement : ' + $scope.actualIndex + ' ' +  $scope.elementsArray.length);

                if($scope.actualIndex < $scope.elementsArray.length ){

                    $scope.actualIndex++;

                    //location.href = '#element_'+$scope.elementsArray[$scope.actualIndex -1];

                    //$location.hash('p_' + $scope.elementsArray[$scope.actualIndex -1]); 
                    
                    location.href = mainController.getHost() + '/compare#p_' + $scope.elementsArray[$scope.actualIndex -1]; 

                    //$anchorScroll();

                    //$('#element_'+$scope.elementsArray[$scope.actualIndex-1]).addClass('highlightTextTemp');

                    $('#p_'+$scope.elementsArray[$scope.actualIndex-2]).removeClass('highlightTextTemp')

                    $('#p_'+$scope.elementsArray[$scope.actualIndex-1]).addClass('highlightTextTemp')

                     //setTimeout(function(){$('#element_'+$scope.elementsArray[$scope.actualIndex-1]).removeClass('highlightTextTemp')}, 300);

                }

                else if($scope.actualIndex === $scope.elementsArray.length){

                    $scope.actualIndex = 0;

                    $scope.actualIndex++; 
                    
                    location.href = mainController.getHost() + '/compare#p_' + $scope.elementsArray[$scope.actualIndex -1]; 

                    //$location.hash('p_' + $scope.elementsArray[$scope.actualIndex -1]);

                    $('#p_'+$scope.elementsArray[$scope.elementsArray.length -1]).removeClass('highlightTextTemp')

                    $('#p_'+$scope.elementsArray[$scope.actualIndex-1]).addClass('highlightTextTemp')

                }

            }else{

                console.log('scrollToElement : ' + $scope.actualIndex + ' ' +  $scope.elementsArray.length);

                if($scope.actualIndex !== 0){

                    if($scope.actualIndex-1 <=0){

                        $scope.actualIndex = $scope.elementsArray.length;

                        location.href = mainController.getHost() + '/compare#p_' + $scope.elementsArray[$scope.actualIndex -1]; 

                       
                       // $location.hash('p_' + $scope.elementsArray[$scope.actualIndex -1]);

                        $('#p_'+$scope.elementsArray[0]).removeClass('highlightTextTemp');

                        $('#p_'+$scope.elementsArray[$scope.actualIndex-1]).addClass('highlightTextTemp');

                    }else{

                        $scope.actualIndex--; 
                        
                        location.href = mainController.getHost() + '/compare#p_' + $scope.elementsArray[$scope.actualIndex -1]; 

                        //$location.hash('p_' + $scope.elementsArray[$scope.actualIndex -1]);

                        //$anchorScroll();

                        //location.href = '#element_'+$scope.elementsArray[$scope.actualIndex -1];

                    //$('#element_'+$scope.elementsArray[$scope.actualIndex-1]).addClass('highlightTextTemp');

                        $('#p_'+$scope.elementsArray[$scope.actualIndex]).removeClass('highlightTextTemp');

                        $('#p_'+$scope.elementsArray[$scope.actualIndex-1]).addClass('highlightTextTemp');

                    //setTimeout(function(){$('#element_'+$scope.elementsArray[$scope.actualIndex-1]).removeClass('highlightTextTemp')}, 300);

                    }

                }

            }

        }


    

     /*$scope.scrollToElement = function(direction){
        console.log('in $scope.scrollToElement, $scope.elementsArray is: ', $scope.elementsArray); 
        if($scope.elementsArray.length === 0){
            $scope.actualIndex = 0;
        }else if(direction === 'Down' && $scope.elementsArray.length !== 0){
            if($scope.actualIndex < $scope.elementsArray.length ){
                $scope.actualIndex++;
                console.log('in else if'); 
                console.log('actualIndex: ', $scope.actualIndex); 
                //location.href = '#element_'+$scope.elementsArray[$scope.actualIndex -1];
                $location.hash('p_' + $scope.elementsArray[$scope.actualIndex -1]);
                //$anchorScroll();
                console.log('after location.hash') 
                let elemFirst = document.getElementById('p_'+$scope.elementsArray[$scope.actualIndex-1]);  
                elemFirst.classList.add('highlightTextTemp'); 
                let elemSec = document.getElementById('p_'+$scope.elementsArray[$scope.actualIndex-2]); 
                elemSec.classList.remove('highlightTextTemp'); 
                //$('#element_'+$scope.elementsArray[$scope.actualIndex-1]).addClass('highlightTextTemp');
                $('#p_'+$scope.elementsArray[$scope.actualIndex-1]).addClass('highlightTextTemp')
                $('#p_'+$scope.elementsArray[$scope.actualIndex-2]).removeClass('highlightTextTemp')
                console.log('after jQuery')
                //setTimeout(function(){$('#element_'+$scope.elementsArray[$scope.actualIndex-1]).removeClass('highlightTextTemp')}, 300);
            }
        }else{
            if($scope.actualIndex !== 0){
                if($scope.actualIndex-1 <=0){
                    $scope.actualIndex--;
                }else{
                	console.log('in else'); 
                	console.log('$scope.actualIndex: ', $scope.actualIndex); 
                    $scope.actualIndex--;
                    $location.hash('p_' + $scope.elementsArray[$scope.actualIndex -1]);
                    //$anchorScroll();
                    //location.href = '#element_'+$scope.elementsArray[$scope.actualIndex -1];
                //$('#element_'+$scope.elementsArray[$scope.actualIndex-1]).addClass('highlightTextTemp');
                $('#p_'+$scope.elementsArray[$scope.actualIndex-1]).addClass('highlightTextTemp');
                $('#p_'+$scope.elementsArray[$scope.actualIndex]).removeClass('highlightTextTemp');
                //setTimeout(function(){$('#element_'+$scope.elementsArray[$scope.actualIndex-1]).removeClass('highlightTextTemp')}, 300);
                }
            }
        }
    } */ 
    $scope.selected = false;
    
    $scope.deselectAll = function(item){

        console.log('in "scope.deselectAll, $scope.checkedValues is: ', $scope.selectedValues); 
        $scope.selected = false;
        $scope.checkedValues = [];
        $scope.deselectedAll = true; 

        let elemsToDeleteCss = document.querySelectorAll('.highlightTextTemp'); 
        console.log('in deselectAll, elemToDeleteCss is: ', elemsToDeleteCss); 

        for(let i= 0; i < elemsToDeleteCss.length; i++){
            elemsToDeleteCss[i].classList.remove("highlightTextTemp"); 
        }

        console.log('elemsToDeleteCss is: ', elemsToDeleteCss); 

        $scope.putElementsInArray();

        console.log('after putElementsInArray in deselectAll, $scope.maxIndex is: ', $scope.maxIndex); 

    }

    
    $scope.deselectCheckBox = function(input, item){
        if($scope.checkedValues.length === 0){
            return false;
        }else{
            for(i=0; i< $scope.checkedValues.length; i++){
                if($scope.checkedValues[i] === input){
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
    } 

    $scope.makeVisibleTabSecondContract = function(itemToView, itemToHide){
        $('#'+itemToHide+'ButtonSecondContract').removeClass('buttonActive');
        $('#'+itemToHide+'Second').hide();
        $('#'+itemToView+'ButtonSecondContract').addClass('buttonActive');
        $('#'+itemToView+'Second').fadeIn();
    } 
  
  
    $scope.filterEntities = function(element){
        //console.log('in filterEntities, element is: ', element); 
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

    /* 
    
    
        if(element.filters.toString().includes(',') || elementsSecondContract.filters.toString().includes(',')){ 

            if(element.filters[0] == ""){
                entityArray = elementsSecondContract.filters.toString().split(','); 
            } 
            else if(elementsSecondContract.filters[0] == ""){
                entityArray = elements.filters.toString().split(','); 
            } 
            else {
                tempArray = element.filters.toString().split(','); 
                tempArray.push(elementsSecondContract.toString().split(',')); 
                let joinString = tempArray.join(','); 
                entityArray = [joinString]; 
            } 
        }  
        else{
            entityArray = element.filters;
        }

        */

}]);