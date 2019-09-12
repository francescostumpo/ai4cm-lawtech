snamApp.controller("navbarController", ['$scope', '$http', '$location', '$rootScope', function($scope, $http, $location,$rootScope) {
    console.log("[INFO] Hello World from navbarController");
    

    $scope.contractEndpoint = '/uploadContractOnDB'; 

    $scope.baseEndpoint = 'https://snam-ai-frontend.eu-de.mybluemix.net/'; 
    $scope.analyzeEndpoint = 'https://snam-lawtech.eu-gb.mybluemix.net/analyze?documentId='; 
    $scope.contractUploadEndpoint = '/uploadContractOnDB'; 
    
    $scope.currentLanguage; 
    $scope.currentDocumentUploadLanguage; 
    $scope.currentFont; 
    $scope.currentFontDimension; 

    $scope.fileToUpload; 

    // Impostazioni permesse nei settings 
    $scope.allowedLanguages = [ "ENG", "ITA"]; 
    $scope.allowedDocumentUploadLanguage = ["ENG", "ITA"]; 
    $scope.allowedFonts = ["Standard", "Verdana", "Arial"]; 
    $scope.allowedFontDimension = [ '12', '14', '16', '18', '20', '22', '24']; 

    $scope.englishDictionary = {

        "closeButton": "Close",
        "continueButton": "Analyze",
        "compareButton": "Compare",
        "analyzeButton": "Analyze",
        "goButton": "GO", 
        "headerAnalyze": "ANALYZE",
        "headerCompare": "COMPARE", 
        "headerSearch" : "SEARCH",
        "idContract": "ID Contract", 
        "homepageWelcome": "Hello", 
        "homepageDescriptionFirstRow": "Accelerate decision making by cutting time spent on document analysis. ", 
        "homepageDescriptionSecondRow": "Improve the core model and get more value out of documents by proving labelling ",
        "homepageDescriptionThirdRow": "suggestions that incorporate your domain knowledge.", 
        "homepageAnalyzeContract": "Analyze Contract", 
        "homepageAnalyzeContractButton": "Select Document", 
        "homepageCompareContract": "Compare Contracts", 
        "homepageCompareContractButton": "Select Documents", 
        "modalAnalyzeContractTitle": "Analyze Document", 
        "modalAnalyzeContractUpload": "Upload your document here", 
        "modalAnalyzeContractSupportedFileType": "Supported file types: PDF, Word, Images. ", 
        "modalAnalyzeContractMaximumFileSize": "Maximum file size: 50MB", 
        "modalAnalyzeContractSearchButton": "Search for Document", 
        "modalAnalyzeContractSelectLanguage": "Language of the contract to be loaded: ", 
        "modalSearchWriteId": "Insert the id of the contract to analyze: ",
        "modalCompareContractTitle": "Select new documents to compare",
        "modalCompareContractBaseDocument": "Base Document", 
        "modalCompareContractUpload": "Upload your document here", 
        "modalCompareContractComparisonDocument": "Comparison Document", 
        "modalCompareContractSnamTemplate": "SNAM Template",
        "analyzeLoading": "Loading contract...",
        "analyzeShowFilters": "Show Filters", 
        "analyzeResetFilters": "Reset Filters",
        "analyzeElementScrollbar": "Element", 
        "analyzeDocumentButton": "Document",
        "analyzeSelectionButton": "Synthesis", 
        "analyzeLoadButton": "Load", 
        "analyzeShow": "Show", 
        "analyzeHide": "Hide", 
        "analyzeFilters": "Filters", 
        "analyzeLeftNavbarCategories": "Categories", 
        "analyzeLeftNavbarNatures": "Natures", 
        "analyzeLeftNavbarParties": "Parties",
        "analyzeLeftNavbarAttributes": "Attributes", 
        "analyzeLeftNavbarNoCategoriesFound": "No categories found", 
        "analyzeLeftNavbarNoNaturesFound": "No natures found", 
        "analyzeLeftNavbarNoPartiesFound": "No parties found", 
        "analyzeLeftNavbarNoAttributesFound": "No attributes found", 
        "analyzeRightNavbarElementDetails": "Element Details", 
        "analyzeRightNavbarEntitiesFound": "Filters found in element: ",
        "analyzeRightNavbarNoEntities": "No filters found in element", 
        "analyzeRightNavbarDefinitions": "Definitions", 
        "analyzeRightNavbarContacts": "Contacts",
        "analyzeRightNavbarDates": "Dates/Durations", 
        "analyzeRightNavbarType": "Type: ", 
        "analyzeRightNavbarNoCategories": "No categories found in element", 
        "analyzeRightNavbarNoDefinitions": "No definitions found in contract", 
        "analyzeRightNavbarNoContacts": "No contacts found in contract", 
        "analyzeRightNavbarNoDates": "No dates found in contract", 
        "searchForContract": "Search Contract",
        "searchForContractButton": "Search Document",
        "searchLoading": "Searching for contracts...",
        "searchTitle": "DOCUMENT SEARCH", 
        "searchParties": "Party: ",
        "searchContractType": "Contract Type: ", 
        "searchGoverningLaw": "Governing Law: ", 
        "searchContractInForce": "Language: ", 
        "searchTableDocName": "DOCUMENT NAME", 
        "searchTableParties": "PARTIES",
        "searchTableCreationDate":"CREATION DATE",
        "searchTableCreationUser":"CREATION USER",
        "searchTableContractType": "CONTRACT TYPE", 
        "searchTableGoverningLaw": "GOVERNING LAW", 
        "searchTableEffectiveDate": "EFFECTIVE DATE", 
        "searchTableContractInForce": "IN FORCE", 
        "searchTableGo": "ANALYZE",
        "settingsTitle": "SETTINGS", 
        "settingsMenuLang": "Language",
        "settingsMenuApp": "Appearance",
        "settingsLanguagePersonalization": "Language personalization", 
        "settingsGlobalLanguage": "GLOBAL LANGUAGE",
        "settingsLanguage": "Language: ",
        "settingsDocumentsLanguage": "DOCUMENTS LANGUAGE", 
        "settingsTextVisualization": "Text Visualization", 
        "settingsFont": "Font", 
        "settingsDimension": "Dimension",
        "settingsDocumentSelection": "Document selection",
        "settingsColorPalette": "Color Palette",
        "uploadLoading": "Uploading and analyzing contract...",
        "downloadContract" : "DOWNLOAD"
        
    
    } 

    $scope.italianDictionary = {

        "closeButton": "Chiudi",
        "continueButton": "Analizza", 
        "analyzeButton": "Analizza", 
        "compareButton": "Confronta",
        "goButton": "VAI AL DOCUMENTO", 
        "headerAnalyze": "ANALIZZA",
        "headerCompare": "CONFRONTA", 
        "headerSearch" : "CERCA",
        "idContract": "ID Contratto", 
        "homepageWelcome": "Ciao", 
        "homepageDescriptionFirstRow": "Accelera i processi decisionali tagliando il tempo speso nell'analisi documentale. ", 
        "homepageDescriptionSecondRow": "Aumenta il core model e ottieni più valore dai tuoi documenti ",
        "homepageDescriptionThirdRow": "utilizzando un raffinato sistema di analisi cognitiva.", 
        "homepageAnalyzeContract": "Analizza Contratto", 
        "homepageAnalyzeContractButton": "Seleziona Contratto", 
        "homepageCompareContract": "Confronta Contratti", 
        "homepageCompareContractButton": "Seleziona Documento", 
        "modalAnalyzeContractTitle": "Analizza Documento", 
        "modalAnalyzeContractUpload": "Sottometti qui il tuo contratto: ", 
        "modalAnalyzeContractSupportedFileType": "Tipologia di file supportati: PDF, Word, File immagine. ", 
        "modalAnalyzeContractMaximumFileSize": "Dimensione massima file: 50MB", 
        "modalAnalyzeContractSearchButton": "Ricerca Documenti", 
        "modalAnalyzeContractSelectLanguage": "Lingua del contratto da caricare: ", 
        "modalSearchWriteId": "Inserisci l'id del contratto da analizzare: ",
        "modalCompareContractTitle": "Seleziona documenti da confrontare",
        "modalCompareContractBaseDocument": "Documento Base", 
        "modalCompareContractUpload": "Sottometti qui il tuo documento", 
        "modalCompareContractComparisonDocument": "Confronto Documenti", 
        "modalCompareContractSnamTemplate": "Template SNAM", 
        "analyzeLoading": "Caricamento contratto in corso...",
        "analyzeShowFilters": "Mostra Filtri", 
        "analyzeResetFilters": "Resetta Filtri",
        "analyzeElementScrollbar": "Elemento", 
        "analyzeDocumentButton": "Documento", 
        "analyzeSelectionButton": "Sintesi", 
        "analyzeLoadButton": "Leggi", 
        "analyzeShow": "Mostra", 
        "analyzeHide": "Nascondi", 
        "analyzeFilters": "Filtri", 
        "analyzeLeftNavbarCategories": "Categorie", 
        "analyzeLeftNavbarNatures": "Nature", 
        "analyzeLeftNavbarParties": "Parte Coinvolta",
        "analyzeLeftNavbarAttributes": "Attributi", 
        "analyzeLeftNavbarNoCategoriesFound": "Nessuna categoria trovata", 
        "analyzeLeftNavbarNoNaturesFound": "Nessuna natura trovata", 
        "analyzeLeftNavbarNoPartiesFound": "Nessuna parte coinvolta trovata", 
        "analyzeLeftNavbarNoAttributesFound": "Nessun attributo trovato", 
        "analyzeRightNavbarElementDetails": "Dettaglio Elementi", 
        "analyzeRightNavbarEntitiesFound": "Filtri trovati nell'elemento: ",
        "analyzeRightNavbarNoEntities": "Nessun filtro trovato nell'elemento. ", 
        "analyzeRightNavbarDefinitions": "Definizioni", 
        "analyzeRightNavbarContacts": "Contatti",
        "analyzeRightNavbarDates": "Date/Durate", 
        "analyzeRightNavbarType": "Tipo: ", 
        "analyzeRightNavbarNoCategories": "Nessuna categoria trovata nell'elemento", 
        "analyzeRightNavbarNoDefinitions": "Nessuna definizione trovata nel contratto", 
        "analyzeRightNavbarNoContacts": "Nessun contatto trovato nel contratto", 
        "analyzeRightNavbarNoDates": "Nessuna data o durata trovata nel contratto", 
        "searchTitle": "RICERCA DOCUMENTI", 
        "searchLoading": "Ricerca contratti in corso...",
        "searchParties": "Parti: ",
        "searchContractType": "Tipologia Contratto: ", 
        "searchGoverningLaw": "Legge in vigore: ", 
        "searchContractInForce": "Lingua: ", 
        "searchTableCreationDate":"DATA CREAZIONE",
        "searchTableCreationUser":"UTENTE CREAZIONE",
        "searchTableDocName": "NOME DOCUMENTO", 
        "searchTableParties": "PARTI COINVOLTE",
        "searchTableContractType": "TIPOLOGIA CONTRATTO", 
        "searchTableGoverningLaw": "LEGGE IN VIGORE", 
        "searchTableEffectiveDate": "DATA EFFETTIVA", 
        "searchTableContractInForce": "IN VIGORE", 
        "searchTableGo": "ANALIZZA",
        "searchForContract": "Cerca Contratto",
        "searchForContractButton": "Cerca Documento",
        "settingsTitle": "IMPOSTAZIONI", 
        "settingsMenuLang": "Lingua",
        "settingsMenuApp": "Visualizzazione",
        "settingsLanguagePersonalization": "Personalizza lingua", 
        "settingsGlobalLanguage": "LINGUA APPLICAZIONE",
        "settingsLanguage": "Lingua: ",
        "settingsDocumentsLanguage": "LINGUA DOCUMENTI", 
        "settingsTextVisualization": "VIsualizzazione Testo", 
        "settingsFont": "Font", 
        "settingsDimension": "Dimensione",
        "settingsDocumentSelection": "Selezione Documento",
        "settingsColorPalette": "Palette Colori",
        "uploadLoading": "Upload e analisi del contratto in corso...",
        "downloadContract" : "SCARICA"

    }; 
  
    
this.$onInit = function() {

    sessionStorage.setItem('englishDictionary', JSON.stringify($scope.englishDictionary)); 
    sessionStorage.setItem('italianDictionary', JSON.stringify($scope.italianDictionary)); 



    // Setta il linguaggio corrente se non presente in sessione
    if(!sessionStorage.getItem('currentLanguage')){
        sessionStorage.setItem('currentLanguage', "ENG");  
        $scope.currentLanguage = sessionStorage.currentLanguage; // Questo dato sarà recuperato dal DB 
    } 
    else {
        
         if(sessionStorage.getItem('currentLanguage') == "ENG"){
             $scope.currentDictionary = JSON.parse(sessionStorage.getItem('englishDictionary')); 
             $scope.currentLanguage = "ENG"; 
         } 
         else {
             $scope.currentDictionary = JSON.parse(sessionStorage.getItem('italianDictionary')); 
             $scope.currentLanguage = "ITA"; 
         } 
    } 

    
    // Setta il linguaggio di upload dei documenti se non presente in sessione
    if(!sessionStorage.getItem('currentDocumentUploadLanguage')){
        sessionStorage.setItem('currentDocumentUploadLanguage', "ENG"); 
        $scope.currentDocumentUploadLanguage = sessionStorage.currentDocumentUploadLanguage; 
    } 
    else {
        $scope.currentDocumentUploadLanguage = sessionStorage.getItem('currentDocumentUploadLanguage'); 
    }


    // Setta il font dell'applicazione se non presente in sessione
    if(!sessionStorage.getItem('currentFont') || sessionStorage.getItem('currentFont') == undefined){ 
        sessionStorage.setItem('currentFont', "Lato"); 
        $scope.currentFont = sessionStorage.getItem('currentFont'); 
    } 
    else {
        $scope.currentFont = sessionStorage.getItem('currentFont'); 
        $("body").css('font-family', sessionStorage.getItem('currentFont')); 
        $(".text-lato-snam").css('font-family', sessionStorage.getItem('currentFont')); 
        $(".text-lato-snam-paragraph").css('font-family', sessionStorage.getItem('currentFont'));     
    }


    // Setta la dimensione del font dell'applicazione 
    if(!sessionStorage.getItem('currentFontDimension')){
        sessionStorage.setItem('currentFontDimension', 16); 
        $scope.currentFontDimension = sessionStorage.getItem('currentFontDimension'); 
    } 
    else { 
        $scope.currentFontDimension = sessionStorage.getItem('currentFontDimension'); 
        $("body").css('font-size', sessionStorage.getItem('currentFontDimension')); 
        $(".text-lato-snam").css('font-size', sessionStorage.getItem('currentFontDimension')); 
        $("text-lato-snam-paragraph").css('font-size', sessionStorage.getItem('currentFontDimension'));     
    }

  } // Fine della funzione onInit() 

    $scope.changeCurrentLanguage = function(languageId){

        if($scope.allowedLanguages.indexOf(languageId) != -1){ 
            sessionStorage.currentLanguage = languageId; 
            $scope.currentLanguage = sessionStorage.currentLanguage; 
            window.location.reload(); 
        } 
        else {
            console.error('Language not allowed'); 
            return; 
        } 

    } 

    $scope.changeCurrentDocumentUploadLanguage = function(docLangId){ 

        console.log('In $scope.currentDocUploadLanguage, docLangId is: ', docLangId); 
        if($scope.allowedDocumentUploadLanguage.indexOf(docLangId) != -1){ 
            console.log('sessionStorage: ', sessionStorage.currentDocumentUploadLanguage); 
            sessionStorage.currentDocumentUploadLanguage = docLangId; 
            $scope.currentDocumentUploadLanguage = sessionStorage.currentDocumentUploadLanguage; 
            window.location.reload(); 
         } 
         else{
            console.error('Document Upload Language not allowed'); 
            return; 
         }
    } 

    $scope.changeCurrentFontType = function(font){ 
        if($scope.allowedFonts.indexOf(font) != -1){ 
            if(font == "Standard"){
                sessionStorage.currentFont = "Lato"; 
            } 
            else{
                sessionStorage.currentFont = font; 
            }
            window.location.reload(); 
         } 
         else{
            console.error('Font Size not allowed'); 
            return; 
         }
    } 

    $scope.changeCurrentFontDimension = function(dimension){ 
        if($scope.allowedFontDimension.indexOf(dimension.toString()) != -1){ 
                sessionStorage.currentFontDimension = dimension; 
                window.location.reload(); 
         } 
         else{
            console.error('Font Dimension not allowed'); 
            return; 
         }
    } 


    $scope.uploadContract = function(){

    	if($scope.docLanguage === undefined){
            $scope.docLanguage = $scope.currentDocumentUploadLanguage;
            console.log("docLanguage was undefined. Now it's: " + $scope.docLanguage);
        }
        var input = document.getElementById("uploadFileInput");
    
        var file = input.files[0]; 
    
        var data =  {
    
    
                "contract": file, 
    
                "language":  $scope.docLanguage === "ITA" ? 1 : 2
    
        }; 

        console.log('in $scope.uploadContract, $scope.currentDocumentUploadLanguage is: ', $scope.currentDocumentUploadLanguage); 
        console.log('in $scope.uploadContract, data is: ', data); 
    
        var formData = new FormData();
    
        Object.keys(data).forEach(function(key) {
    
            formData.append(key, data[key]);
    
        });
    
        console.log('in uploadContract, formData is: ', formData);
    
        var url = mainController.getHost() + $scope.contractUploadEndpoint; 
    
        var config = {
                headers : {
    
                    'Content-Type': undefined,
    
                }, 
                transformResponse: [ 
                    function(data){
                        return data; 
                    }
                ]
        } 

        console.log('in $scope.uploadContract'); 
        console.log('url is: ', url); 
        console.log('config is: ', config); 
        
        try {
            mainController.uploadFile($http, url, formData, config); 
        } 
        catch(e){
            console.error('Error while uploading file: ', e.value); 
        }
    
        // Empty the input file node
    
       setTimeout(function(){var input = document.getElementById("uploadFileInput").value = ""}, 1000);
    
    }
    


    /* $scope.uploadContract = function(){

        let contractToUpload = document.querySelector('#uploadFileForm'); 
        console.log('contractToUpload is: ', contractToUpload); 

        console.log('in uploadContract, formData is: ', $scope.fileToUpload); 

        let jsonToPass = {
            contract: $scope.fileToUpload, 
            language: 1
        }; 

        console.log('in uploadContract, jsonToPass is: ', jsonToPass); 

        $http.post($scope.contractUploadEndpoint, $scope.fileToUpload), {
            "Content-Type": "undefined", 
            "data": $scope.fileToUpload
        }.then(function(response){
            console.log('in uploadContract, response.data is: ', response.data);
            if(response.data.Status === 'OK'){ 
                console.log('OK Response - Contract Uploaded'); 
                mainController.showNotification('bottom', 'right', response.data.Esito, 2, "icon-check-2");
            }else{ 
                console.log('FAIL response - Contract not uploaded'); 
                mainController.showNotification('bottom', 'right', response.data.Esito, 3, "icon-alert-circle-exc");
            }
        });
        
    },*/ 
    
    
    
    $scope.uploadFileCompare = function() {


        mainController.startProgressIndicator('#loading'); 

        let firstContractElem = document.getElementById('uploadFileFirstContract'); 
        let secondContractElem = document.getElementById('uploadFileSecondContract'); 

        let firstContract = firstContractElem.files[0]; 
        let secondContract = secondContractElem.files[0]; 

        var dataFirstContract =  {
            "contract": firstContract, 
            "language":  $scope.currentDocumentUploadLanguage === "ITA" ? 1 : 2 
        }; 

        var dataSecondContract = {
            "contract": secondContract,
            "language": $scope.currentDocumentUploadLanguage === "ITA" ? 1 : 2 
        }; 

        console.log('in navbarController, dataFirstContract is: ', dataFirstContract); 
        console.log('in navbarController, dataSecondContract is: ', dataSecondContract); 

        let formDataFirst = new FormData(); 
        let formDataSecond = new FormData(); 

        console.log('in navbarController, ')

        Object.keys(dataFirstContract).forEach(function(key) { 
            console.log('key: ', key); 
            formDataFirst.append(key, dataFirstContract[key]); 
            console.log('formDataFirst[key]: ', dataFirstContract[key]); 
        }); 

        Object.keys(dataSecondContract).forEach(function(key) {
            console.log('key: ', key); 
            formDataSecond.append(key, dataSecondContract[key]); 
            console.log('formDataSecond[key]: ', dataSecondContract[key]); 
        }); 

        let url = mainController.getHost() + $scope.contractUploadEndpoint;  

        console.log('in navbarController, formDataFirst is: ', formDataFirst); 
        console.log('in navbarController, formDataSecond is: ', formDataSecond); 

        var config = {
            headers : {
                'Content-Type': undefined,
            }, 
            transformResponse: [
                function(data){
                    return data; 
                }]
            }; 


        try{
            mainController.uploadFileCompare($http, url, formDataFirst, formDataSecond, config); 
        } 
        catch(e){
            console.error('Error while uploading file: ', e); 
        } 

        setTimeout(function(){var input = document.getElementById("uploadFileFirstContract").value = ""}, 1000); 
        setTimeout(function(){var input = document.getElementById("uploadFileSecondContract").value = ""}, 1000); 


    }
    


    
    
    
    $scope.setUploadContractLanguage= function(language) {
        console.log('in setUploadContractLanguage, language is: ', language); 
        $scope.currentDocumentUploadLanguage = language; 
        console.log('in setUploadContractLanguage, $scope.currentDocumentUploadLanguage is: ', $scope.currentDocumentUploadLanguage); 
    } 

    $scope.goToView = function(pathname){
        console.log("Redirecting to: " +pathname)
        // De-commentare in fase di porting su server
        location.href = pathname;
    } 

    $scope.goToPage = function(url){
        //location.href = url;  
    }

    $scope.isActive = function(viewLocation){
        //console.log("ViewLocation " + viewLocation);
        // De-commentare in fase di porting su server
        // return window.location.pathname === viewLocation;
        if(window.location.pathname === viewLocation){
        //if("/eniICR/dashboard" === viewLocation){
            return "nav-item-active";
        }else{
            return "nav-item-inactive";
        }
    }

}]);