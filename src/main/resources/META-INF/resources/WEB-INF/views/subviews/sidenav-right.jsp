<!-- Sidebar  -->
<nav id="sidebar" class="sidebar-right mycontent-right scrollable mb-3" ng-controller="navbarController">
    <div>
    <ul class="components">
			<li class="mycontent-bottom">
                <p data-target="#elementDetails" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle sidebar-item" aria-controls="categories" style="cursor: pointer">{{ currentDictionary.analyzeRightNavbarElementDetails }}</p>
                <div class="panel-collapse collapse show" id="elementDetails">
                    <div class="card" style="border-radius: .0rem;">
                        <!--<div class="card-header"><input type="button" ng-model="categoryLeader" ng-click="deselectAll()" style="margin-right: 0.5em;" />Deselect all Categories</div>-->
                        <div class="card-body "> 
                            <div ng-if="isVoidArray(sidenavRightCategories)" > {{ currentDictionary.analyzeRightNavbarNoEntities }} </div>
                            <div ng-repeat="availableCategory in sidenavRightCategories" >
                                <span id="checkFollower_{{availableCategory.category}}" ng-if="!isVoidArray(sidenavRightCategories)" style="list-style: none; margin-right: 0.5em;" />  {{availableCategory.categoryName}} 
                            </div>
                    </div>
                </div>
                </div>
            </li>
    	
         <li class="mycontent-bottom" ng-if="tabToBeViewed === 'document'">
            <p data-target="#definitions" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle" aria-controls="definitions" style="cursor: pointer">{{ currentDictionary.analyzeRightNavbarDefinitions }}</p>
            <div class="collapse" id="definitions">
                    <div class="card" style="border-radius: .0rem;"> 
                        <div class="card-body "> 
                        	<div class="break"></div>
                            <div ng-if="definitions.length == 0"> {{ currentDictionary.analyzeRightNavbarNoDefinitions }}</div>
                            <div ng-repeat="definition in definitions | orderBy:entityValue"> 
                                <span ng-click="highlightDefinition(definition.id, definition.elementSequence)" style="cursor: pointer;">  
                                	{{ definition.entityValue }} 
                                </span>
                                <div class="break"></div>
                             </div>
                        </div>
                    </div>
            </div>
        </li>
        <li class="mycontent-bottom" ng-if="tabToBeViewed === 'document'">
            <p data-target="#contacts" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle" aria-controls="contacts" style="cursor: pointer">{{ currentDictionary.analyzeRightNavbarContacts }}</p>
            <div class="collapse" id="contacts"> 
                    <div class="card" style="border-radius: .0rem;"> 
                        <div class="card-body "> 
                        	<div class="break"></div>
                            <div ng-if="contacts.length == 0"> {{ currentDictionary.analyzeRightNavbarNoContacts }}</div>
                            <div ng-repeat="contact in contacts"> 
                                  <span  ng-click="highlightContact(contact.id, contact.elementSequence)" style="cursor: pointer;"> 
                                   	{{ contact.entityValue }} 
                                   </span>
                                   <div class="break"></div>
                            </div>
                        </div>
                </div>
        </li> 

        
        <li class="mycontent-bottom" ng-if="tabToBeViewed === 'document'">
            <p data-target="#dateDuration" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle" aria-controls="dateDuration" style="cursor: pointer">{{ currentDictionary.analyzeRightNavbarDates }} </p>
            <div class="collapse" id="dateDuration"> 
                <div class="card" style="border-radius: .0rem;"> 
                    <div class="card-body "> 
                        <div class="break"></div>
                        <div ng-if="dates.length == 0"> {{currentDictionary.analyzeRightNavbarNoDates }}</div>
                        <div ng-repeat="date in dates">  
                            <span ng-click="highlightDate(date.id, date.elementSequence)" style="cursor: pointer;" > 
                                 {{date.entityValue}} <br> ({{ currentDictionary.analyzeRightNavbarType }} {{ date.entityType }})
                            </span> 
                            <div class="break"></div>
                        </div> 
                    </div>
            </div>
           <!--  <ul class="collapse" id="dateDuration">
                <li>
                    <a href="#">Page 1</a>
                </li>
                <li>
                    <a href="#">Page 2</a>
                </li>
                <li>
                    <a href="#">Page 3</a>
                </li>
            </ul>
        </li>
    </ul> -->
    	
    </div>
    </li>
    <li class="mycontent-bottom">
            <p data-target="#involvedPartiesTab" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle" aria-controls="involvedPartiesTab" style="cursor: pointer"> Involved parties </p>
            <div class="panel-collapse collapse show" id="involvedPartiesTab"> 
                <div class="card" style="border-radius: .0rem;"> 
                    <div class="card-body"> 
                    	<div class="break"></div>
                        <div ng-repeat="party in involvedParties"> 
                            {{party.id.nameInvolvedParty}}
                            <div class="break"></div>
                        </div> 
                    </div>
                </div>
            </div>
        </li>
</nav>