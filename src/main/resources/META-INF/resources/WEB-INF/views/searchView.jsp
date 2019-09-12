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
        </head>


        <body class="background-snam text-lato-snam" ng-app="snamApp" ng-controller="navbarController">
        <!-- <body class="background-snam text-lato-snam" ng-app="snamApp" ng-controller="navbarController" style="background-image: url(./webapp/img/Background.png) !important;">-->
            <div id="loading">
                <img id="loading-image" src="./webapp/img/spinner.gif"  height="25%"/>
            </div>
            <div id="loading_1">
                <img id="loading-image" src="./webapp/img/spinner.gif"  height="25%"/>
                <div class="message"><br><h6>{{currentDictionary.searchLoading}}</h6></div>
            </div>
            <nav class="navbar navbar-expand-lg navbar-background-snam sticky-top" ng-controller="navbarController"><jsp:include page="subviews/navbar.jsp"></jsp:include></nav>
        

            <h2 style="color: #004D96; letter-spacing: 0.2px; font-weight: bold; font-size: 16px; padding: 2% 2% 2% 2%"> {{ currentDictionary.searchTitle }}</h2> 


            <!-- Tabella Ricerca Documenti --> 
            <div id="popupContainer" class="container-fluid" ng-controller="searchViewController"> 
                
                    <div class="row mb-3">
                        <label class="ml-5 my-2">{{ currentDictionary.searchParties }} </label>
                        <select ng-init="searchForm.party = involvedParties[0].name" class="form-control ml-2" style="width: 12%;" ng-model="searchForm.party">
                            <option ng-repeat="involvedParty in involvedParties" value="{{involvedParty.name}}"> {{ involvedParty.name }} </option>
                        </select>
                        
                        <label class="ml-3 my-2" >{{ currentDictionary.searchContractType }}</label>
                        <select ng-init="searchForm.contractType = contractTypes[0].name" class="form-control ml-2" style="width: 12%;" ng-model="searchForm.contractType">
                            <option ng-repeat="contractType in contractTypes" value="{{contractType.name}}">{{ contractType.name }} </option>
                        </select>

                        <label class="ml-3 my-2">{{ currentDictionary.searchGoverningLaw }}</label>
                        <select ng-init="searchForm.governingLaw = governingLaws[0].name" class="form-control ml-2" style="width: 12%;" ng-model="searchForm.governingLaw">
                            <option ng-repeat="governingLaw in governingLaws" value="{{governingLaw.name}}">{{ governingLaw.name }} </option>
                        </select>

                        <label class="ml-3 my-2">{{ currentDictionary.searchContractInForce }}</label>
                        <select ng-init="searchForm.inForce = inForces[0].name" class="form-control ml-2" style="width: 12%;" ng-model="searchForm.inForce">
                            <option ng-repeat="inForce in inForces" value="{{inForce.name}}">{{ inForce.name }} </option>
                        </select>
                   		<div class="ml-3">
                   			<button ng-click="searchContract(searchForm)" class="btn btn-light" style="border: solid;  border-color: #004D96;"> <span style="color: #004D96; "> {{ currentDictionary.searchTitle }} </span> </button> 
                  		</div>
                	</div>
                <div class="card"> 
                
                <table ng-show="tableRows.length!=0" class="table table-striped table-hover sortable">
                    <thead style="border: 0;">
                      <tr style="cursor: pointer">
                        <th width="20%"> DOCUMENT NAME </th> 
                        <th width="15%"> {{ currentDictionary.searchTableParties }}</th>
                        <th width="12.5%"> {{ currentDictionary.searchTableContractType }} </th>
                        <th width="12.5%"> {{ currentDictionary.searchTableGoverningLaw }} </th>
                        <th width="10%"> {{ currentDictionary.searchTableEffectiveDate }} </th>
                        <th width="10%"> {{ currentDictionary.searchTableCreationDate }} </th>
                        <th width="10%"> {{ currentDictionary.searchTableCreationUser }} </th>
                        <!-- <th scope="col"> IN FORCE </th> -->
                        <th width="5%"> </th>
                        <th width="5%"> </th>
                      </tr>
                    </thead>
                    <tbody>
                        <tr ng-repeat="element in tableRows">  
                            <td class="align-middle"> {{ element.name }} </td> 
                            <td class="align-middle"> {{ element.parties }} </td>
                            <td class="align-middle"> {{ element.contractType }} </td>
                            <td class="align-middle"> {{ element.governingLaw }} </td>
                            <td class="align-middle"> {{ element.effectiveDate }} </td>
                            <td class="align-middle"> {{ element.creationDate }} </td>
                            <td class="align-middle"> {{ element.creationUser }} </td>
                            <!--<td> {{ element.inForce }} </td> --> 
                            <td> <button class="btn btn-light button-block align-middle" ng-click="goToAnalyze(element.idContract)" style="border: solid;  border-color: #004D96;"> <span style="color: #004D96; "> {{ currentDictionary.searchTableGo }}  </span> </button> </td>
                            <td> <button class="btn btn-light button-block align-middle" ng-click="javascript(void)" style="border: solid;  border-color: #004D96;"> <span style="color: #004D96; "> {{ currentDictionary.downloadContract }}  </span> </button> </td> 
                        </tr>
                    </tbody>
                </table>  
              </div>
            </div>
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
            $('#loading_1').hide();
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