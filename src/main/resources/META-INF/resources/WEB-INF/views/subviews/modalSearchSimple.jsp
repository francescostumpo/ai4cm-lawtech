<div class="modal fade" id="modalSearchSimple" tabindex="-1" ng-controller="navbarController" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="uploadModalLabel">{{ currentDictionary.modalAnalyzeContractTitle }}</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">X</span>
              </button>
            </div>
            <div class="modal-body">
                <img src="./webapp/img/uploadIcon.png" class="mt-2 mx-auto d-block" style="color:blue" />
                <div class="text-center">
                    <h5 class="text-lato-snam-paragraph mt-3">{{ currentDictionary.modalSearchWriteId }}</h5>
                    <p class="text-lato-snam-paragraph"></p>
                    <input type="text" id="searchContractTextField" ng-model="contractId" placeholder="{{ currentDictionary.idContract }}"></input>
                </div>
                <hr/>
                <div class="text-center">
                    <button type="button" class="btn button-neutral btn-block" data-dismiss="modal" ng-click="redirectToPage('search')"> {{ currentDictionary.modalAnalyzeContractSearchButton }} </button>
                </div>
            </div>
            <div class="modal-footer" ng-controller="navbarController">
              <button type="button" class="btn button-dark" data-dismiss="modal">{{ currentDictionary.closeButton }}</button>
              <button type="button" class="btn button-neutral" data-dismiss="modal" ng-click="analyzeContract(contractId)">{{ currentDictionary.continueButton }}</button>
            </div>
          </div>
        </div>
      </div>