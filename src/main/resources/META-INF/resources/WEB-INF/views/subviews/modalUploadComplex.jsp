<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('#uploadFileFirstContract')
								.change(
										function() {
											console
													.log('change called in modalUploadComplex');
											let
											elem = document
													.querySelector('#uploadFileFirstContract');
											elem.files[0].fileName = elem.files[0].name
													.substring(1, 15)
													+ '...';
											console
													.log(
															'After name change, elem.files[0].name is: ',
															elem.files[0].fileName);

											var container = document
													.getElementById("uploadFileFirstContract");
											var content = container.innerHTML;
											console.log('container is: ',
													container);
											console
													.log('content is: ',
															content);
											container.innerHTML = content;
											console.log('Refreshed content');
											console.log('Now file is: ',
													container.innerHTML);

										});
					});
</script>
<div class="modal fade bd-example-modal-lg" id="uploadModalComplex"
	ng-controller="navbarController" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">

	<!-- <div id="loading">
        <img id="loading-image" src="./webapp/img/spinner.gif"  height="25%" alt="Loading..." />
    </div>  -->

	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="uploadModalLabel"
					style="font-weight: bold">{{
					currentDictionary.modalCompareContractTitle }}</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">X</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-sx-12 col-md-6 col-lg-6 mycontent-left">
						<h6 style="font-weight: bold" class="mb-4">{{
							currentDictionary.modalCompareContractBaseDocument }}</h6>
						<img src="./webapp/img/uploadIcon.png"
							class="mt-3 mx-auto d-block" style="color: blue" />
						<div class="text-center">
							<h5 class="text-lato-snam-paragraph mt-3">{{ currentDictionary.modalCompareContractUpload
								}}</h5>
							<p class="text-lato-snam-paragraph mt-2">
								{{ currentDictionary.modalAnalyzeContractSupportedFileType }}<br />{{
								currentDictionary.modalAnalyzeContractMaximumFileSize }}
							</p>
							<p class="text-lato-snam-paragraph"></p>
							<div class="inputFile">
								<input type="file" value="file" id="uploadFileFirstContract"
									placeholder="Upload File" style="font-size: 12;" />
							</div>
						</div>
						<hr />
						<div class="text-center">
							<button type="button" class="btn button-neutral btn-block"
								data-dismiss="modal" ng-click="redirectToPage('search')">{{
								currentDictionary.modalAnalyzeContractSearchButton }}</button>
						</div>
					</div>
					
					<div class="col-sx-12 col-md-6 col-lg-6">
						<h6 style="font-weight: bold" class="mb-4">{{
							currentDictionary.modalCompareContractComparisonDocument }}</h6>
						<img src="./webapp/img/uploadIcon.png"
							class="mt-3 mx-auto d-block" />
						<div class="text-center">
							<h5 class="text-lato-snam-paragraph mt-3">{{
								currentDictionary.modalAnalyzeContractUpload }}</h5>
							<p class="text-lato-snam-paragraph mt-2">
								{{ currentDictionary.modalAnalyzeContractSupportedFileType }}<br />{{
								currentDictionary.modalAnalyzeContractMaximumFileSize }}
							</p>
							<p class="text-lato-snam-paragraph"></p>
							<input type="file" id="uploadFileSecondContract"
								style="font-size: 12;" ng-click="resetValue(id)" />
						</div>
						<hr />
						<div class="text-center">
							<button type="button" class="btn button-neutral btn-block"
								data-dismiss="modal" ng-click="redirectToPage('search')">{{
								currentDictionary.modalAnalyzeContractSearchButton }}</button>
							<button type="button" class="btn button-neutral btn-block"
								data-dismiss="modal" ng-click="uploadFile(objectInModal)">{{
								currentDictionary.modalCompareContractSnamTemplate }}</button>
						</div>
					</div>
				</div>
				<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio"
							name="inlineRadioOptions" id="inlineCheckbox1" value="ENG"
							ng-checked="currentDocumentUploadLanguage === 'ENG'"
							ng-model="docLanguage"> <label class="form-check-label"
							for="inlineCheckbox1">ENG</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio"
							name="inlineRadioOptions" id="inlineCheckbox2" value="ITA"
							ng-checked="currentDocumentUploadLanguage === 'ITA'"
							ng-model="docLanguage"> <label class="form-check-label"
							for="inlineCheckbox2">ITA</label>
					</div>
			</div>
			<div class="modal-footer">
				<!--  <button type="button" class="btn button-dark" data-dismiss="modal">{{ currentDictionary.closeButton }}</button> -->
				<button type="button" class="btn button-neutral"
					data-dismiss="modal" ng-click="uploadFileCompare()">{{
					currentDictionary.compareButton }}</button>
			</div>
		</div>
	</div>
</div>
<script>
	/* $(window).ready(function() {
	    $('#loading').hide();
	 }); */
</script>