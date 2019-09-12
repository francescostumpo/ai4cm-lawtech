<div class="modal fade" id="uploadModal" tabindex="-1" ng-controller="navbarController" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
                <h5 class="text-lato-snam-paragraph mt-3">{{ currentDictionary.modalAnalyzeContractUpload }}</h5>
                <p class="text-lato-snam-paragraph mt-2">{{ currentDictionary.modalAnalyzeContractSupportedFileType }}<br />{{ currentDictionary.modalAnalyzeContractMaximumFileSize }}</p>
                <p class="text-lato-snam-paragraph"></p>
                <input type="file" id="uploadFileInput"></input> <br> <br> 
                <p class="text-lato-snam-paragraph"> {{ currentDictionary.modalAnalyzeContractSelectLanguage }}   </p>
                <div class="form-check form-check-inline">
                  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineCheckbox1" value="ENG" ng-checked="currentDocumentUploadLanguage === 'ENG'" ng-model="docLanguage">
                  <label class="form-check-label" for="inlineCheckbox1">ENG</label>
                </div>
                <div class="form-check form-check-inline">
                  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineCheckbox2" value="ITA" ng-checked="currentDocumentUploadLanguage === 'ITA'" ng-model="docLanguage">
                  <label class="form-check-label" for="inlineCheckbox2">ITA</label>
                </div>
      
                <!-- <div ng-if="currentDocumentUploadLanguage === 'ENG'" value="ENG"> English </div> <br> 
                <div ng-if="currentDocumentUploadLanguage === 'ITA'" value="ITA"> Italiano </div>--> 
            </div>
            <!-- <div class="text-center">
                <button type="button" class="btn button-neutral btn-block" data-dismiss="modal" ng-click="goToView('/searchView')"> {{ currentDictionary.modalAnalyzeContractSearchButton }} </button>
            </div>-->
        </div>
        <div class="modal-footer">
          <!-- <button type="button" class="btn button-dark" data-dismiss="modal">{{ currentDictionary.closeButton }}</button>-->
          <button type="button" class="btn button-neutral" data-dismiss="modal" ng-click="uploadContract()">{{ currentDictionary.continueButton }}</button>
        </div>
      </div>
    </div>
  </div>