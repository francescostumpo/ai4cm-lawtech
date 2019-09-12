eniApp.controller("breadcrumbController", ['$scope', '$http', '$location', '$rootScope', function($scope, $http, $location,$rootScope) {
    console.log("[INFO] Hello World from breadcrumbController");

var breadcrumb = document.getElementById("b_1");
var node = document.getElementById("b_1").getElementsByTagName("li");
var lastChild = node.length - 1
var li = document.createElement('li');
li.setAttribute("class", "breadcrumb-item");
li.appendChild(document.createTextNode("Test"));
breadcrumb.appendChild(li);

//$("#breadcrumb ol").append('<li><a href="/user/messages"><span class="tab">Message Center</span></a></li>');
}]);