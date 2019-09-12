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
    </head>

    <body class="background-snam text-lato-snam" ng-app="snamApp" ng-controller="navbarController">
    <!-- <body class="background-snam text-lato-snam" ng-app="snamApp" ng-controller="navbarController" style="background-image: url(./webapp/img/Background.png) !important;">-->
            
            <div id="loading">
                <img id="loading-image" src="./webapp/img/spinner.gif"  height="25%" />
            </div>
            <div id="loading_1">
                <img id="loading-image" src="./webapp/img/spinner.gif"  height="25%"/>
                <div class="message"><br><h6>{{currentDictionary.uploadLoading}}</h6></div>
            </div>
        <nav class="navbar navbar-expand-lg navbar-background-snam sticky-top"><jsp:include page="subviews/navbar.jsp"></jsp:include></nav>
        <div class="container-fluid mt-3">
            <div ng-controller="navbarController">
                    <div class="row mt-5 ml-5">
                        <div class="col-sm-12 col-md-8 col-lg-8">
                            <h1>{{ currentDictionary.homepageWelcome}}, <snamApp:out value="${name }"></snamApp:out></h1>
                            <!--<h2>Welcome, {{user.name}}</h2>-->
                        </div>
                    </div>
                    <br>
                    <div class="row mt-5 ml-5">
                    <div class="col-sm-12 col-md-3 col-lg-3">
                            <div class="card">
                                <div class="card-header card-header-background-snam">
                                    {{ currentDictionary.searchForContract }}
                                </div>
                                <div class="card-body">
                                        <img src="./webapp/img/searchIcon.png " class="mx-auto d-block mt-3">
                                        <div class="col-sm-12 mt-4 justify-content-center">
                                            <button type="submit" class="btn button-neutral btn-block" ng-click="goToView('/searchView')">{{ currentDictionary.searchForContractButton }}</button>
                                        </div>
                                </div>
                            </div>
                            <!--<h2>Welcome, {{user.name}}</h2>-->
                        </div>
                        <div class="col-sm-12 col-md-3 col-lg-3">
                            <div class="card">
                                <div class="card-header card-header-background-snam">
                                    {{ currentDictionary.homepageAnalyzeContract }}
                                </div>
                                <div class="card-body">
                                        <img src="./webapp/img/fileIcon.png" class="mx-auto d-block mt-3">
                                        <div class="col-sm-12 mt-4 justify-content-center">
                                            <button type="submit"class="btn button-neutral btn-block" data-toggle="modal" data-target="#uploadModal" ng-click="submit(Mitigations, availableQuestions, 'submit')">{{ currentDictionary.homepageAnalyzeContractButton }}</button>
                                        </div>
                                </div>
                            </div>
                            <!--<h2>Welcome, {{user.name}}</h2>-->
                        </div>
                        <div class="col-sm-12 col-md-3 col-lg-3">
                            <div class="card">
                                <div class="card-header card-header-background-snam">
                                        {{ currentDictionary.homepageCompareContract }}
                                </div>
                                <div class="card-body">
                                        <img src="./webapp/img/compareIconDef.png" class="mx-auto d-block mt-3">
                                        <div class="col-sm-12 mt-4 justify-content-center">
                                            <button type="submit"class="btn button-neutral btn-block" data-toggle="modal" data-target="#uploadModalComplex" ng-click="submit(Mitigations, availableQuestions, 'submit')">{{ currentDictionary.homepageCompareContractButton }}</button>
                                        </div>
                                </div>
                            </div>
                            <!--<h2>Welcome, {{user.name}}</h2>-->
                        </div>
                    </div>
                
                <jsp:include page="subviews/modalSearchSimple.jsp"></jsp:include>
                <jsp:include page="subviews/modalUploadSimple.jsp"></jsp:include>
                <jsp:include page="subviews/modalUploadComplex.jsp"></jsp:include>
            </div>
        </div>
    </body>

    <script type="text/javascript"src="./webapp/js/jquery.min.js"></script>
    <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <!--<script src="./webapp/js/bootstrap.bundle.min.js"></script>-->
    <script type="text/javascript" src="./webapp/js/sorttable.js"></script>
	<script type="text/javascript"src="./webapp/js/bootstrap-notify.js"></script>
    <script type="text/javascript" src="./webapp/AngularJS/scripts/angular.min.js"></script>
    <script type="text/javascript" src="./webapp/AngularJS/scripts/angularjs-gauge.min.js"></script>
    <script type="text/javascript" src="./webapp/AngularJS/scripts/checklist-model.js"></script>
    <script type="text/javascript" src="./webapp/AngularJS/scripts/angularjs-sanitize.js"></script>
    <script type="text/javascript" src="./webapp/AngularJS/controllers/mainController.js"></script>
    <script type="text/javascript" src="./webapp/AngularJS/controllers/navbarController.js"></script>
    
    <script language="javascript" type="text/javascript">
    	$('#loading_1').hide();
        $(window).ready(function() {
        $('#loading').hide();
        
     });
   </script>
    
</html>