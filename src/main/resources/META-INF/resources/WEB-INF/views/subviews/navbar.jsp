<div class="container-fluid"> 
    <img class="navbar-brand" src="./webapp/img/snamLogo.png" height="2%" width="2%" style="cursor: pointer;" ng-click="goToView('/homepage')"></img> 
    <a style="cursor: pointer; color: #004D96; letter-spacing: 0.2px; font-weight: bold; font-size: 16px" href="homepage">AI 4 CONTRACT MANAGEMENT</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
        <li class="nav-item" ng-class="isActive('/searchView')" ng-click="goToView('/searchView')" style="padding-right: 1.5rem; cursor: pointer; ">
                <a class="nav-link" style="cursor: pointer;">{{  currentDictionary.headerSearch }}</a>
            </li>
            <li class="nav-item mr-4" ng-class="isActive('/analyze')">
                <a class="nav-link" style="cursor: pointer;" data-toggle="modal" data-target="#uploadModal"> {{ currentDictionary.headerAnalyze }} </a>
            </li>
            <li class="nav-item" ng-class="isActive('/compare')">
                <a class="nav-link" style="cursor: pointer;" data-toggle="modal" data-target="#uploadModalComplex">{{  currentDictionary.headerCompare }}</a>
            </li>
            
            <!-- <li class="nav-item" ng-class="isActive('/neverActive')" style="padding-top: 3%;"> 
                <a href="searchView" style="cursor: pointer;"> <i class="fas fa-search"> </i> </a> 
                <a class="nav-link" style="cursor:pointer;"  data-toggle="modal" data-target="#searchModal"> SEARCH </a>
            </li>-->
        </ul>
        <ul class="navbar-nav ml-4">
            <li>
                <a href="settings"> <i style="cursor: pointer;" class="fas fa-cog"></i> </a> 
            </li>
        </ul>
    </div>
</div> 


