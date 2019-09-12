<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="snamApp"%>
<html lang="en">

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
        <script>  </script>
    </head>

    <body class="background-snam text-lato-snam" ng-app="snamApp" style="position: fixed;" ng-controller="navbarController">
    <!-- <body class="background-snam text-lato-snam" ng-app="snamApp" style="background-image: url(./webapp/img/Background.png) !important; position: fixed;">-->
         <div id="loading">
                <img id="loading-image" src="./webapp/img/spinner.gif"  height="25%" />
         </div>   
         <div id="loading_1">
                <img id="loading-image" src="./webapp/img/spinner.gif"  height="25%"/>
                <div class="message"><br><h6>{{currentDictionary.analyzeLoading}}</h6></div>
            </div>
            
        <nav class="navbar navbar-expand-lg navbar-background-snam"><jsp:include page="subviews/navbar.jsp"></jsp:include></nav>

        <div class="">
            <div ng-controller="analyzeController">
                <div class="wrapper">
                    <jsp:include page="subviews/sidenav-left.jsp" ></jsp:include> 
                    
                    <div id="content" class="mt-3">
                            
                            <nav class="navbar navbar-expand-lg">
                                <div class="btn-group">
                                    <button type="button" id="sidebarCollapse" class="btn button-neutral" ng-click="actionFilter()">
                                        <i class="fas fa-align-left"></i>
                                        <span style="margin-left: 0.5em;">{{actionFilterVerb}} {{ currentDictionary.analyzeFilters }}</span>
                                    </button>
                                    <button type="button" id="sidebarCollapse" class="btn button-neutral" ng-click="deselectAll()">
                                        <i class="fas fa-filter"></i>
                                        <span style="margin-left: 0.5em;">{{ currentDictionary.analyzeResetFilters }}</span>
                                    </button>
                                </div>
                                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                                        <ul class="nav navbar-nav ml-auto">
                                            <ul class="btn-group" role="tablist">
                                                    <span class="badge badge-background-neutral" style="cursor: pointer;" ng-click="scrollToElement('Down')"><i class="fas fa-chevron-down"></i></span><span style="margin-left: 0.5em;">{{ currentDictionary.analyzeElementScrollbar }}</span><span style="margin-left: 0.5em; margin-right: 0.5em;">{{actualIndex}}/{{maxIndex}}</span>
                                                    <span class="badge badge-background-neutral" style="cursor: pointer;" ng-click="scrollToElement('Up')"><i class="fas fa-chevron-up"></i></span>
                                            </ul>
                                        </ul>
                                    </div>
                                
                                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                                    <ul class="nav navbar-nav ml-auto">
                                        <ul class="btn-group" role="tablist">
                                                <button id="documentButton" type="button" ng-click="makeVisibleTab('document', 'selection')" class="btn button-neutral buttonActive" ><i class="fas fa-file"></i><span style="margin-left: 0.5em;">{{ currentDictionary.analyzeDocumentButton }}</span></button>
                                                <button id="selectionButton" type="button" ng-click="makeVisibleTab('selection', 'document')" class="btn button-neutral" ><i class="far fa-file"></i><span style="margin-left: 0.5em;">{{ currentDictionary.analyzeSelectionButton }}</span></button> 
                                                <!--  <li class="nav-item">
                                                   <button type="button" class="btn button-neutral ml-3" data-toggle="modal" data-target="#uploadModal"><i class="fas fa-upload"></i><span style="margin-left: 0.5em;">{{ currentDictionary.analyzeLoadButton }}</span></button>
                                                </li>-->
                                        </ul>
                                        <!--<li class="nav-item">
                                            <a class="nav-link" href="#">Page</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#">Page</a>
                                        </li>-->
                                    </ul>
                                </div>

                                
                            </nav>
                
                            <div class="container-fluid mt-3">
                                <div class="card scrollable">
                                <div class="card-body tab-content card-body-custom " >

                                <div class="tab-pane active show" id="document" ng-show="tabToBeViewed === 'document'">
                                    <div ng-repeat="availableElement in elements" id="element_{{availableElement.sequence}}" ng-click="populateSidenavRightWithCategories(availableElement.sequence); createBorderAndHighlightsSidenvaRight(availableElement.sequence, 'document')">
                                        <!--<div ng-bing-html="myWay"></div>-->
                                        <span id="p_{{availableElement.sequence}}" ng-bind-html="bindHTML(availableElement.sequence)" class="text-lato-snam-paragraph-document element" ng-style="highlight(availableElement.filters, availableElement.sequence)" style="cursor: pointer;"></span>
                                        <!--<div ng-bind-html-unsafe="availableElement.text"></div>--> <br> <br> 
                                    </div>
                                </div>
                                
                                <div class="tab-pane active" id="selection" ng-show="tabToBeViewed === 'selection'">
                                    <div ng-repeat="availableElement in elements | filter:filterElement" id="element_{{availableElement.sequence}}" ng-click="populateSidenavRightWithCategories(availableElement.sequence); createBorderAndHighlightsSidenvaRight(availableElement.sequence, 'selection')">
                                        <!--  <div ng-bing-html="myWay"></div> -->
                                        <span id="ps_{{availableElement.sequence}}" ng-bind-html="bindHTML(availableElement.sequence)" class="text-lato-snam-paragraph-document element" ng-style="highlight(availableElement.filters, availableElement.sequence)" style="cursor: pointer;"></span>
                                        <br> <br><!-- <div ng-bind-html-unsafe="availableElement.text"></div> <br> -->
                                    </div>
                                </div>
                            </div>
                        </div>
                        </div>
                        </div>

                        <jsp:include page="subviews/sidenav-right.jsp"></jsp:include> 


                </div>
                
                <jsp:include page="subviews/modalSearchSimple.jsp"></jsp:include>
                <jsp:include page="subviews/modalUploadSimple.jsp"></jsp:include>
                <jsp:include page="subviews/modalUploadComplex.jsp"></jsp:include>
                <button class="btn button-neutral" onclick="topFunction()" id="toTopButton">Scroll to Top</button>
            </div>
        </div>
    </body>

    <script src="./webapp/js/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <!--<script src="./webapp/js/bootstrap.bundle.min.js"></script>-->
    <script src="./webapp/js/sorttable.js"></script>
	<script src="./webapp/js/bootstrap-notify.js"></script>
    <script src="./webapp/AngularJS/scripts/angular.min.js"></script>
    <script src="./webapp/AngularJS/scripts/angularjs-gauge.min.js"></script>
    <script src="./webapp/AngularJS/scripts/angularjs-sanitize.js"></script>
    <script src="./webapp/AngularJS/scripts/checklist-model.js"></script>
    <script src="./webapp/AngularJS/controllers/mainController.js"></script>
    <script src="./webapp/AngularJS/controllers/navbarController.js"></script>
    <script src="./webapp/AngularJS/controllers/analyzeController.js"></script>
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
    <script>
            // When the user scrolls down 20px from the top of the document, show the button
            window.onscroll = function() {scrollFunction()};
            
            function scrollFunction() {
              if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
                document.getElementById("toTopButton").style.display = "block";
              } else {
                document.getElementById("toTopButton").style.display = "none";
              }
            }
            
            // When the user clicks on the button, scroll to the top of the document
            function topFunction() {
              document.body.scrollTop = 0;
              document.documentElement.scrollTop = 0;
            } 

                function setIdsIntoDates(){
    
                    let contractElements = document.querySelectorAll('.date'); 
                    console.log('spanElements is: ', contractElements); 
                } 

            window.onload = setIdsIntoDates; 

            </script>
    
</html>