var smartCommunity = angular.module('smartCommunity',[
	'ngRoute',
	'smartcommunityControllers',
]);

smartCommunity.config(['$routeProvider',
	function($routeProvider) {
		$routeProvider.
                when('/notification/new',{
                            templateUrl: 'partials/notification.html',
                            controller: 'NotificationController'
                }).
                when('/suggestion/show',{
                            templateUrl: 'partials/suggestion.html',
                            controller: 'SuggesionController'
                }).
                when('/repair/show', {
                    templateUrl: 'partials/repair.html',
                    controller: 'RepairController'
                }).
                when('/family/new', {
                    templateUrl: 'partials/family.html',
                    controller: 'FamilyController'
                }).
                when('/parcel/new', {
                    templateUrl: 'partials/parcel.html',
                    controller: 'ParcelController'
                }).
                when('/telphone/new', {
                    templateUrl: 'partials/telphone.html',
                    controller: 'TelphoneController'
                }).
                when('/order/edit', {
                    templateUrl: 'partials/orderDetail.html',
                    controller: 'OrderDetailsController'
                }).
                when('/order/products/edit', {
                    templateUrl: 'partials/productEdit.html',
                    controller: 'ProductEditController'
                }).
                when('/order/products/show/:productId', {
                    templateUrl: 'partials/productDetail.html',
                    controller: 'ProductDetailController'
                }).
                when('/order/products/new', {
                    templateUrl: 'partials/productAdd.html',
                    controller: 'ProductAddController'
                }).
                when('/life/edit', {
                    templateUrl: 'partials/lifeEdit.html',
                    controller: 'LifeEditController'
                }).
                when('/repair/history/:repairId', {
                    templateUrl: 'partials/repairHistory.html',
                    controller: 'RepairHistoryController'
                }).
                when('/repair/workers', {
                    templateUrl: 'partials/repairWorkers.html',
                    controller: 'repairWorkerController'
                }).
                when('/repair/workerEdit', {
                    templateUrl: 'partials/repairWorkerEdit.html',
                    controller: 'repairWorkerEditController'
                }).
                when('/repair/worker/new', {
                    templateUrl: 'partials/repairWorkerAdd.html',
                    controller: 'repairWorkerAddController'
                }).
                when('/suggestion/history/:suggestionId', {
                    templateUrl: 'partials/suggestionHistory.html',
                    controller: 'suggestionHistoryController'
                }).
                when('/advertise/show', {
                    templateUrl: 'partials/advertises.html',
                    controller: 'AdvertiseController'
                }).
                when('/advertise/new', {
                    templateUrl: 'partials/advertiseAdd.html',
                    controller: 'AdvertiseAddController'
                }).
                when('/advertise/detail/:advertiseId', {
                    templateUrl: 'partials/advertiseDetail.html',
                    controller: 'AdvertiseDetailController'
                }).
                when('/advertise/click/show/:adId', {
                    templateUrl: 'partials/advertiseClick.html',
                    controller: 'AdvertiseClickController'
                })
                ;
	}
]);

