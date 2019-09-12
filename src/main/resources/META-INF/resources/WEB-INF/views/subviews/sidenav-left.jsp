<nav id="sidebar" class="scrollable mb-3" ng-controller="navbarController">
    <div class="">
    <ul class="components">
        <li class="mycontent-bottom">
            <p data-target="#categories" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle" aria-controls="categories" style="cursor: pointer"> {{ currentDictionary.analyzeLeftNavbarCategories }} </p>
            <div class="panel-collapse collapse show" id="categories">
                <div class="card" style="border-radius: .0rem;">
                    <!--<div class="card-header"><input type="button" ng-model="categoryLeader" ng-click="deselectAll()" style="margin-right: 0.5em;" />Deselect all Categories</div>-->
                    <div class="card-body  "> 
                        <div ng-if="categories.length == 0"> {{ currentDictionary.analyzeLeftNavbarNoCategoriesFound }} </div>
                        <div ng-repeat="availableCategory in categories" > 
                            <input id="checkFollower_{{availableCategory.name}}" type="checkbox" ng-model="selected" ng-checked="deselectCheckBox(availableCategory.name, 'categories')" ng-click="change(availableCategory, 'categories')" style="list-style: none; margin-right: 0.5em;" />{{availableCategory.name}} ({{availableCategory.count}})
                        </div>
                </div>
            </div>
            </div>
        </li>
        <li class="mycontent-bottom">
            <p data-target="#natures" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle" aria-controls="natures" style="cursor: pointer">{{ currentDictionary.analyzeLeftNavbarNatures }}</p>
            <div class="panel-collapse collapse show" id="natures">
                <div class="card" style="border-radius: .0rem;">
                    <!--<div  class="card-header"><input type="checkbox" ng-model="natureLeader" ng-change="changeLeader('natures')" style="margin-right: 0.5em;" />Select all Natures</div>-->
                    <div class="card-body  "> 
                        <div ng-if="natures.length === 0"> {{ currentDictionary.analyzeLeftNavbarNoNaturesFound }}</div>
                        <div ng-repeat="availableNature in natures">
                        	<input id="checkFollower_{{availableNature.name}}" type="checkbox" ng-checked="deselectCheckBox(availableNature.name, 'natures')" ng-click="change(availableNature,'natures')" ng-model="selected" aria-label="Follower input" style="list-style: none; margin-right: 0.5em;" />{{availableNature.name}} ({{availableNature.count}})
                        </div>
                       <!--  <div> 
                            <input id="noNatureSelected" type="radio" name="radioButtonNatures"  ng-click="deselectNature()" ng-model="natureSelected" ng-value="2" aria-label="Follower input" style="list-style: none; margin-right: 0.5em;" />None of the above(s)
                        </div>  --> 
                </div>
            </div>
            </div>
        </li>
        <li class="mycontent-bottom">
            <p data-target="#parties" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle" aria-controls="parties" style="cursor: pointer">{{ currentDictionary.analyzeLeftNavbarParties }}</p>
            <div class="panel-collapse collapse show" id="parties">
                <div class="card" style="border-radius: .0rem;">
                    <!--<div  class="card-header"><input type="checkbox" ng-model="partyLeader" ng-change="changeLeader('parties')" style="margin-right: 0.5em;" />Select all Parties</div>-->
                    <div class="card-body  "> 
                        <div ng-if="parties.length === 0"> {{ currentDictionary.analyzeLeftNavbarNoPartiesFound }}</div>
                        <div ng-repeat="availableParty in parties" > 
                        <input id="checkFollower_{{availableParty.name}}" type="checkbox" ng-checked="deselectCheckBox(availableParty.name, 'parties')" ng-click="change(availableParty, 'parties')" ng-model="selected" aria-label="Follower input" style="list-style: none; margin-right: 0.5em;" />{{availableParty.name}} ({{availableParty.count}})
                        </div>
                </div>
            </div>
            </div>
        </li>
        <li class="mycontent-bottom">
            <p data-target="#attributes" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle" aria-controls="attributes" style="cursor: pointer">{{ currentDictionary.analyzeLeftNavbarAttributes }}</p>
            <div class="panel-collapse collapse show" id="attributes">
                <div class="card" style="border-radius: .0rem;">
                    <!--<div  class="card-header"><input type="checkbox" ng-model="attributeLeader" ng-change="changeLeader('attributes')" style="margin-right: 0.5em;" />Select all Attributes</div>-->
                    <div class="card-body  "> 
                        <div ng-if="attributes.length === 0" > {{ currentDictionary.analyzeLeftNavbarNoAttributesFound }} </div>
                        <div ng-repeat="availableAttribute in attributes">
                        <input id="checkFollower_{{availableAttribute.name}}" type="checkbox" ng-checked="deselectCheckBox(availableAttribute.name, 'attributes')" ng-click="change(availableAttribute, 'attributes')" ng-model="selected" aria-label="Follower input" style="list-style: none; margin-right: 0.5em;" />{{availableAttribute.name}} ({{availableAttribute.count}})
                        </div>
                </div>
            </div>
            </div>
        </li>
    </ul>
    </div>
</nav>