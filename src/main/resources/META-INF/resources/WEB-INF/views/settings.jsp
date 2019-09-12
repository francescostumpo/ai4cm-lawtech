<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="snamApp"%>
<html> 
        <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
            <meta name="description" content="">
            <meta name="author" content="">
        
            <title>SNAM - AI4CM</title>
        
            <!-- Bootstrap core CSS -->
            <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
            <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
            <link href="./webapp/css/customCSS.css" rel="stylesheet"> 
            <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script> 
            <script src="./webapp/AngularJS/controllers/navbarController.js"> </script> 
            <script src="./webapp/AngularJS/controllers/analyzeController.js"> </script> 
        </head> 

        <body class="background-snam text-lato-snam" ng-app="snamApp" ng-controller="navbarController">
        <!-- <body class="background-snam text-lato-snam" ng-app="snamApp" ng-controller="navbarController" style="background-image: url(./webapp/img/Background.png) !important;">-->
                <div id="loading">
                <img id="loading-image" src="./webapp/img/spinner.gif"  height="25%" alt="Loading..." />
            </div> 

            <nav class="navbar navbar-expand-lg navbar-background-snam sticky-top"  ng-controller="navbarController"><jsp:include page="subviews/navbar.jsp"></jsp:include></nav>
        

            <h2 style="color: #004D96; letter-spacing: 0.2px; font-weight: bold; font-size: 16px; padding: 2% 2% 2% 2%"> {{ currentDictionary.settingsTitle }} </h2> 

            
            <ul class="nav nav-tabs auto">
                    <li><a class="nav-link active" href="#language" data-toggle="tab"><span style="color:#004D96; font-size: 18px; font-weight: bold;"> {{ currentDictionary.settingsMenuLang }}  </span> </a></li>
                    <li><a class="nav-link" href="#appearance" data-toggle="tab"> <span style="color:#004D96; font-size: 18px; font-weight: bold;"> {{ currentDictionary.settingsMenuApp }}  </span> </a></li>
            </ul>


            <div class="tab-content"> 

              <div class="tab-pane active" id="language"> 
 
                <div class="languagePersonalizationDiv" style="width:30%; padding: 2% 2% 2% 1%; float: left;" ng-controller="navbarController"> 
                    <span style="color:#004D96; font-size: 18px; font-weight: 600;"> {{ currentDictionary.settingsLanguagePersonalization }} </span>
                </div> 
                <div class="languageChoicesDiv" style="width: 70%; float: left;" ng-controller="navbarController"> 

                        <div style="color:#004D96; font-size: 18px; font-weight: 600; padding: 10% 2% 2% 2%;" ng-controller="navbarController"> {{ currentDictionary.settingsGlobalLanguage }} </div> 
                        <button class="btn btn-secondary btn-lg dropdown-toggle button-neutral ml-3" style="width: 40%; background-color: white; color: black; border-color: grey;" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
                            <span style="color: grey; float: left; font-size: 18px; width: 25%; text-align: left;" > {{ currentDictionary.settingsLanguage }}</span> <span style="color:#004D96; float: left; font-size: 18px; width: 60%; text-align:left;"> {{ currentLanguage == 'ITA' ? 'Italiano' : 'English' }} </span> 
                            <i class="fas fa-angle-down" style="margin-left: 5%; margin-top: 2%; float: right;"> </i>
                        </button>
                        <div class="dropdown-menu" style="width: 28%; ">
                           <a class="dropdown-item" id="ENG" ng-click="changeCurrentLanguage('ENG')"> English </a> 
                           <a class="dropdown-item" id="ITA" ng-click="changeCurrentLanguage('ITA')"> Italiano </a> 
                        </div> 
                    </div> 
                   <div style="width: 30%; height: 20%; float: left;"> </div>
                    <div class="languageDocumentUploadDiv" style="width: 70%; float: left;" ng-controller="navbarController">
                        <br> 
                        <div style="color:#004D96; font-size: 18px; font-weight: 600; padding: 12% 2% 2% 2%;"> {{ currentDictionary.settingsDocumentsLanguage }} </div> 
                        <button class="btn btn-secondary btn-lg dropdown-toggle button-neutral ml-3" style="width: 40%; background-color: white; color: black; border-color: grey;" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
                            <span style="color: grey; float: left; font-size: 18px; width: 25%; text-align: left;" > {{ currentDictionary.settingsLanguage }} </span> <span style="color:#004D96; float: left; font-size: 18px; width: 60%; text-align:left;"> {{ currentDocumentUploadLanguage == 'ITA' ? 'Italiano': 'English' }} </span> 
                            <i class="fas fa-angle-down" style="margin-left: 5%; margin-top: 2%; float: right;"> </i>
                        </button>
                        <div class="dropdown-menu" style="width: 28%; ">
                            <a class="dropdown-item" ng-click="changeCurrentDocumentUploadLanguage('ENG')"> English </a> 
                            <a class="dropdown-item" ng-click="changeCurrentDocumentUploadLanguage('ITA')"> Italiano </a> 
                        </div>
                </div>

              </div> 

            

            <div class="tab-pane" id="appearance"> 

            <div class="section"> 
                <div class="appearanceDiv" style="width:30%; height: 30%; padding: 2% 2% 2% 2%; float: left;"> 
                    <span style="color:#004D96; font-size: 18px; font-weight: 600;"> {{ currentDictionary.settingsTextVisualization }} </span>
                </div> 


             <div class="columnContainer"> 
                <content> 
	                <div style="color:#004D96; font-size: 18px; font-weight: 600; padding: 2.5% 2% 2% 2%; width: 70%; float: left;"> {{ currentDictionary.settingsFont }} </div> 
	                <button class="btn btn-secondary btn-lg dropdown-toggle button-neutral ml-3" style="width: 20%; background-color: white; color: black; border-color: grey;" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
	                    <span style="color: grey; float: left; font-size: 18px; width: 25%; text-align: left;" > {{ currentFont }} </span> 
	                    <i class="fas fa-angle-down" style="margin-left: 5%; margin-top: 2%; float: right;"> </i>
	                </button>
	                <div class="dropdown-menu" style="width: 20%; ">
	                   <a class="dropdown-item" ng-click="changeCurrentFontType('Standard')"> Lato </a> 
	                   <a class="dropdown-item" ng-click="changeCurrentFontType('Verdana')"> Verdana </a> 
	                   <a class="dropdown-item" ng-click="changeCurrentFontType('Arial')"> Arial </a> 
	                </div>
                </content> 
            </div>
            <div class="columnContainer">
				<content>
	                <div style="color:#004D96; font-size: 18px; font-weight: 600; padding: 2.5% 2% 2% 2%; width: 70%; float: left;"> {{ currentDictionary.settingsDimension }} </div> 
	                <button class="btn btn-secondary btn-lg dropdown-toggle button-neutral ml-3" style="width: 20%; background-color: white; color: black; border-color: grey;" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
	                    <span style="color: grey; float: left; font-size: 18px; width: 25%; text-align: left;" > {{ currentFontDimension }} </span> 
	                    <i class="fas fa-angle-down" style="margin-left: 5%; margin-top: 2%; float: right;"> </i>
	                </button>
	                <div class="dropdown-menu" style="width: 20%; ">
	                    <a class="dropdown-item" ng-click="changeCurrentFontDimension(14)"> 14 </a> 
	                    <a class="dropdown-item" ng-click="changeCurrentFontDimension(16)"> 16 </a> 
	                    <a class="dropdown-item" ng-click="changeCurrentFontDimension(18)"> 18 </a> 
	                    <a class="dropdown-item" ng-click="changeCurrentFontDimension(20)"> 20 </a> 
	                </div>
                </content>
             </div> 
            
            

                <div class="g" ng-controller="navbarController" style="float: left; width: 100%;"> 
                    <content> 

                     <!--    <div class="documentSelectionDiv" ng-controller="navbarController" style="width:30%; height: 30%; padding: 2% 2% 2% 2%; float: left;"> 
                            <span style="color:#004D96; font-size: 18px; font-weight: 600;"> {{ currentDictionary.settingsFont }}</span>
                        </div> 
            
                            <div ng-controller="navbarController" style="color:#004D96; font-size: 18px; font-weight: 600; padding: 5% 2% 2% 2%; width: 70%; float: left;"> {{ currentDictionary.settingsColorPalette }} </div> 
                            <button class="btn btn-secondary btn-lg dropdown-toggle button-neutral ml-3" style="width: 20%; background-color: white; color: black; border-color: grey; float: left;" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
                                <span style="color: grey; float: left; font-size: 18px; width: 25%; text-align: left;" > Light </span> 
                                <i class="fas fa-angle-down" style="margin-left: 5%; margin-top: 2%; float: right;"> </i>
                            </button>
                            <div class="dropdown-menu" style="width: 20%; ">
                                <a class="dropdown-item"> Light </a> 
                                <a class="dropdown-item"> Dark </a> 
                            </div> --> 


                    </content>

                </div>
            </div> 
            

        </div>
        
            </div> 

        </div>


            </div>
    


    	<jsp:include page="subviews/modalSearchSimple.jsp"></jsp:include>
		<jsp:include page="subviews/modalUploadSimple.jsp"></jsp:include>
        
        </body> 


        <script src="./webapp/js/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script> 
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
        <!--<script src="./webapp/js/bootstrap.bundle.min.js"></script>-->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

        <script src="./webapp/js/sorttable.js"></script>
        <script src="./webapp/js/bootstrap-notify.js"></script>
        <script src="./webapp/AngularJS/scripts/angular.min.js"></script>
        <script src="./webapp/AngularJS/scripts/angularjs-gauge.min.js"></script>
        <script src="./webapp/AngularJS/scripts/angularjs-sanitize.js"></script>
        <script src="./webapp/AngularJS/scripts/checklist-model.js"></script>
        <script src="./webapp/AngularJS/controllers/mainController.js"></script>
        <script src="./webapp/AngularJS/controllers/navbarController.js"></script>
        <script src="./webapp/AngularJS/controllers/searchViewController.js"></script>
    
        <script language="javascript" type="text/javascript">
            $(window).ready(function() {
            $('#loading').hide();
         });
       </script>
       <script type="text/javascript">
            $(document).ready(function () {
                $('#sidebarCollapse').on('click', function () {
                    $('#sidebar').toggleClass('active');
                });
            });
        </script> 


</html>