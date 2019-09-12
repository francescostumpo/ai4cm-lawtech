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

    <body class="background-snam text-lato-snam" ng-app="snamApp" style="background-image: url(./webapp/img/Background.png) !important;">
        
            <div id="loading">
                <img id="loading-image" src="./webapp/img/spinner.gif"  height="25%" alt="Loading..." />
            </div>
            
        <nav class="navbar navbar-expand-lg navbar-background-snam"><jsp:include page="subviews/navbar.jsp"></jsp:include></nav>
        
        <div class="">
            <div ng-controller="compareController">
                <div class="wrapper">
                    <jsp:include page="subviews/sidenav-left-compare.jsp" ></jsp:include> 
       
                            
                            <div class="container-fluid mt-3" style="padding-top: 3%;"> 



                                    <!-- First Contract -->
                                    <div class="container-fluid mt-1" style="width: 50%; float: left; padding-left: 2.5%;"> 
                                            <p class="text-lato-snam" style="font-weight: bold; width: 50%; text-align: center; float: left;"> New Contract </p> 
                                            <div class="btn-group" style="width: 50%;  margin-top: -0.5%;">
                                                    <button id="documentButton" type="button" ng-click="makeVisibleTab('document', 'selection')" class="btn button-neutral buttonActive" ><i class="fas fa-file"></i><span style="margin-left: 0.5em;">{{ currentDictionary.analyzeDocumentButton }}</span></button>
                                                    <button id="selectionButton" type="button" ng-click="makeVisibleTab('selection', 'document')" class="btn button-neutral" ><i class="far fa-file"></i><span style="margin-left: 0.5em;">{{ currentDictionary.analyzeSelectionButton }}</span></button> 
                                                </div> 
                                        </div> 

                                        

                                        <!-- Comparison Contract -->
                                        <div class="container-fluid mt-1" style="width: 50%; float: left; padding-left: 2.5%;"> 
                                                <p class="text-lato-snam" style="font-weight: bold; width: 50%; text-align: center; float: left;"> Comparison Contract </p> 
                                                <div class="btn-group" style="width: 50%;  margin-top: -0.5%;">
                                                        <button id="documentButtonSecondContract" type="button" ng-click="makeVisibleTabSecondContract('document', 'selection')" class="btn button-neutral buttonActive" ><i class="fas fa-file"></i><span style="margin-left: 0.5em;">{{ currentDictionary.analyzeDocumentButton }}</span></button>
                                                        <button id="selectionButtonSecondContract" type="button" ng-click="makeVisibleTabSecondContract('selection', 'document')" class="btn button-neutral" ><i class="far fa-file"></i><span style="margin-left: 0.5em;">{{ currentDictionary.analyzeSelectionButton }}</span></button> 
                                                    </div> 
                                            </div>


                                        <div class="buttonElementsCompare" style="width: 100%; padding-top: 5%; float: left;"> 
                                            <div class="navFirstContract" style="float: left; width: 50%;"> 
                                                    <ul class="nav navbar-nav ml-auto"> 
                                                        <ul class="btn-group" role="tablist" style="padding: 1% 15% 0% 40%; margin-left: 45%;">
                                                                <span class="badge badge-background-neutral" style="cursor: pointer;" ng-click="scrollToElement('Down')"><i class="fas fa-chevron-down"></i></span><span style="margin-left: 0.5em;">{{ currentDictionary.analyzeElementScrollbar }}</span><span style="margin-left: 0.5em; margin-right: 0.5em;">{{actualIndex}}/{{maxIndex}}</span>
                                                                <span class="badge badge-background-neutral" style="cursor: pointer;" ng-click="scrollToElement('Up')"><i class="fas fa-chevron-up"></i></span>
                                                        </ul>
                                                    </ul>
                                            </div> 
                                       

                                            <div class="deselectButtonCompare" style="width: 20%; float: left; padding-left: 34%;">
                                                <button type="button" id="sidebarCollapse" class="btn button-neutral" ng-click="deselectAll()">
                                                    <i class="fas fa-filter"></i>
                                                    <span style="margin-left: 0.5em;">{{ currentDictionary.analyzeResetFilters }}</span>
                                                </button> 
                                            </div> 
                                        </div>

                                            <!-- <div class="navFirstContract" style="float: left; margin-top: 2%; width: 50%;"> 
                                                    <ul class="nav navbar-nav ml-auto" style="padding-top: 5%;"> 
                                                        <ul class="btn-group" role="tablist" style="padding: 0% 25% 0% 25%;">
                                                                <span class="badge badge-background-neutral" style="cursor: pointer;" ng-click="scrollToElement('Down')"><i class="fas fa-chevron-down"></i></span><span style="margin-left: 0.5em;">{{ currentDictionary.analyzeElementScrollbar }}</span><span style="margin-left: 0.5em; margin-right: 0.5em;">{{actualIndex}}/{{maxIndex}}</span>
                                                                <span class="badge badge-background-neutral" style="cursor: pointer;" ng-click="scrollToElement('Up')"><i class="fas fa-chevron-up"></i></span>
                                                        </ul>
                                                    </ul>
                                            </div> -->
                




                                    <div class="card sticky-top-custom-card scrollable" style="top: 50px; width: 50%; float: left;">
                                    <div class="card-body tab-content card-body-custom " >
    
                                    <div class="tab-pane active show" id="document">
                                        <div ng-repeat="availableElement in elements" id="element_{{availableElement.sequence}}" ng-click="populateSidenavRightWithCategories(availableElement.sequence)">
                                            <!--<div ng-bing-html="myWay"></div>-->
                                            <span id="p_{{availableElement.sequence}}" ng-bind-html="bindHTML(availableElement.sequence)" class="text-lato-snam-paragraph-document element" ng-style="highlight(availableElement.filters, availableElement.sequence)" style="cursor: pointer;"></span>
                                            <!--<div ng-bind-html-unsafe="availableElement.text"></div>--> <br> <br> 
                                        </div>
                                    </div>
                                    <!--<hr>-->
                                    <div class="tab-pane" id="selection" >
                                        <div ng-repeat="availableElement in elements | filter:filterEntities" id="element_{{availableElement.sequence}}" ng-click="populateSidenavRightWithCategories(availableElement.sequence)">
                                            <!--<div ng-bing-html="myWay"></div>-->
                                            <div ng-bind-html="bindHTML(availableElement.sequence)" class="text-lato-snam-paragraph-document element" ng-style="highlight(availableElement.filters, availableElement.sequence)" style="cursor: pointer;"></div>
                                            <!--<div ng-bind-html-unsafe="availableElement.text"></div>--> <br> <br> 
                                        </div>
                                    </div>
                                </div>
                            </div>
                             


                            <div class="card sticky-top-custom-card scrollable" style="top: 50px; width: 50%; float: left;">
                                    <div class="card-body tab-content card-body-custom " >
    
                                    <div class="tab-pane active show" id="documentSecond">
                                        <div ng-repeat="availableElement in elementsSecondContract" id="elementSecond_{{availableElement.sequence}}">
                                            <!--<div ng-bing-html="myWay"></div>-->
                                            <span id="p_{{availableElement.sequence}}" ng-bind-html="bindHTMLSecondContract(availableElement.sequence)" class="text-lato-snam-paragraph-document element" ng-style="highlight(availableElement.filters, availableElement.sequence)" style="cursor: pointer;"></span>
                                            <!--<div ng-bind-html-unsafe="availableElement.text"></div>--> <br> <br> 
                                        </div>
                                    </div>
                                    <!--<hr>-->
                                    <div class="tab-pane" id="selectionSecond" >
                                        <div ng-repeat="availableElement in elementsSecondContract | filter:filterEntities" id="element_{{availableElement.sequence}}">
                                            <!--<div ng-bing-html="myWay"></div>-->
                                            <div ng-bind-html="bindHTMLSecondContract(availableElement.sequence)" class="text-lato-snam-paragraph-document element" ng-style="highlight(availableElement.filters, availableElement.sequence)" style="cursor: pointer;"></div>
                                            <!--<div ng-bind-html-unsafe="availableElement.text"></div>--> <br> <br> 
                                        </div>
                                    </div>
                                </div>
                            </div> 
                        </div> 
                            </div>


                            </div>

                        <!-- <div class="container-fluid mt-3" style="width: 50%; float: left;"> 
                        <div class="card sticky-top-custom-card scrollable">
                        <div class="card-body tab-content card-body-custom ">
        
                        <div class="tab-pane active show" id="document">
                                    <div ng-bind-html="bindHTML(availableElement.sequence)" class="text-lato-snam-paragraph-document element" ng-style="highlight(availableElement.filters, availableElement.sequence)" style="cursor: pointer;"></div>
                                    <br> 
                                </div>
                        </div> 
                        <div class="tab-pane" id="selection" >
                                <div ng-repeat="availableElement in elements | filter:filterEntities" id="element_{{availableElement.sequence}}" ng-click="populateSidenavRightWithCategories(availableElement.sequence)">
                                    <div ng-bind-html="bindHTML(availableElement.sequence)" class="text-lato-snam-paragraph-document element" ng-style="highlight(availableElement.filters, availableElement.sequence)" style="cursor: pointer;"></div>
                                    <br> 
                                </div>
                            </div>
                        </div> 
                        </div> 
                        </div> --> 
                    </div>
                </div>
                
                <jsp:include page="subviews/modalSearchSimple.jsp"></jsp:include>
                <jsp:include page="subviews/modalUploadSimple.jsp"></jsp:include>
                <jsp:include page="subviews/modalUploadComplex.jsp"></jsp:include>
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
    <script src="./webapp/AngularJS/controllers/compareController.js"></script>
    <script language="javascript" type="text/javascript">
        $(window).ready(function() {
        $('#loading').hide();
     });
   </script>
   <script type="text/javascript">

    </script>
    <script>
            // When the user scrolls down 20px from the top of the document, show the button


            </script>
    
</html>
