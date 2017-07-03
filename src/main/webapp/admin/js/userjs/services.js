var dataAccessServices = angular.module('dataAccessServices',[]);

dataAccessServices.config(function($httpProvider) {
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
 
    // Override $http service's default transformRequest
    $httpProvider.defaults.transformRequest = [function(data) {
        /**
         * The workhorse; converts an object to x-www-form-urlencoded serialization.
         * @param {Object} obj
         * @return {String}
         * @author http://my.oschina.net/buwei/blog/191640
         */
        var param = function(obj) {
            var query = '';
            var name, value, fullSubName, subName, subValue, innerObj, i;
 
            for (name in obj) {
                value = obj[name];
 
                if (value instanceof Array) {
                    for (i = 0; i < value.length; ++i) {
                        subValue = value[i];
                        fullSubName = name + '[' + i + ']';
                        innerObj = {};
                        innerObj[fullSubName] = subValue;
                        query += param(innerObj) + '&';
                    }
                } else if (value instanceof Object) {
                    for (subName in value) {
                        subValue = value[subName];
                        fullSubName = name + '[' + subName + ']';
                        innerObj = {};
                        innerObj[fullSubName] = subValue;
                        query += param(innerObj) + '&';
                    }
                } else if (value !== undefined && value !== null) {
                    query += encodeURIComponent(name) + '='
                            + encodeURIComponent(value) + '&';
                }
            }
 
            return query.length ? query.substr(0, query.length - 1) : query;
        };
 
        return angular.isObject(data) && String(data) !== '[object File]'
                ? param(data)
                : data;
    }];
});

dataAccessServices.factory('dataService',['$http', function($http) {
    var notificationUrlBase = '../notification';
    var suggestionUrlBase = "../suggestion";
    var userUrlBase = "../user";
    var UsersUrlBase = "../users";
    var repairUrlBase = "../repair";
    var familyUrlBase = "../family";
    var parcelUrlBase = "../parcel";
    var phoneUrlBase = "../telephone";
    var orderUrlBase = "../order";
    var lifeUrlBase = "../life";
    var advertiseUrlBase = "../advertise";
    var services = {};
    
    services.addNewNotification = function (title, text, type) {
        return $http.post(notificationUrlBase + "/new", 
        {'title': title, 'text': text, 'type': type}
        );
    };
    
    services.getSuggestions = function (page, size, startTime, endTime) {
        return $http.get(suggestionUrlBase + "/show",
        {params: {'page': page, 'size': size, 'startTime': startTime, 'endTime': endTime}}
        );
    };

    services.updateSuggestion = function (suggestionId, comment, status) {
        return $http.post(suggestionUrlBase + "/update",
        {'id': suggestionId, 'comment': comment, 'status': status}
        );
    };
    
    services.getSuggestionById = function (suggessionId) {
        return $http.get(suggestionUrlBase + "/" + suggessionId);
    };
    
    services.getUserInfo = function () {
        return $http.get(userUrlBase + "/me");
    };
    
    services.login = function (user, pwd, place, role) {
        return $http.post(userUrlBase + "/login",
        {'user': user, 'pwd': pwd, 'place': place, 'role': role}
        );
    };
    
    services.logout = function () {
        return $http.post(userUrlBase + "/logout");
    };
    
    services.getAllCommunities = function () {
        return $http.get(userUrlBase + "/community");
    };
    
    services.getRepairs = function (startTime, endTime, page, size) {
        return $http.get(repairUrlBase + "/show",
        {params: {'startTime': startTime, 'endTime': endTime, 'page': page, 'size': size}}
        );
    };
 
    services.getRepairHistoryList = function(repairId) {
        return $http.get(repairUrlBase + "/historyList",
        {params: {'repairId': repairId}}
        );
    };
    
    services.addNewCommentOnRepairHistory = function(repairId, status, comment) {
        return $http.post(repairUrlBase + "/history/new",
        {'repairId': repairId, 'status': status, 'comment': comment}
        );
    };
        
    services.addNewTopicWithoutAttachments = function (type, title, text) {
        return $http.post(familyUrlBase + "/new",
        {'type': type, 'title': title, 'text': text}
        );
    };
    
    services.searchUsers = function (name, role) {
        return $http.get(userUrlBase + "/search",
        {params: {'name': name, 'role': role}}
        );
    };
    
    services.addNewParcel = function (title, name) {
        return $http.post(parcelUrlBase + "/new",
        {'title': title, 'name': name}
        );
    };
    
    services.addNewTelphone = function (title, number) {
        return $http.post(phoneUrlBase + "/new",
        {'title': title, 'number': number}
        );
    };
    
    services.getGroceryInfo = function () {
        return $http.get(orderUrlBase + "/me");
    };
    
    services.getProductForGrocery = function () {
        return $http.get(orderUrlBase + "/products/me");
    };
    
    services.getProductById = function (productId) {
        return $http.get(orderUrlBase + "/products/" + productId);
    };
    
    services.deleteProduct = function (productId) {
        return $http.post(orderUrlBase + "/products/destroy/" + productId);
    };
    
    services.getMyLifeInfo = function () {
        return $http.get(lifeUrlBase + "/me");
    };
    
    services.signup = function (user, room, role, businessType, pwd, place) {
        return $http.post(userUrlBase + "/new",
        {'user': user, 'role': role, 'room': room, 'businessType': businessType, 'pwd': pwd, 'place':place}
        );
    };
    
    services.getUsersByRole = function (role) {
        return $http.get(UsersUrlBase,
        {params: {'role': role}}
        );
    };
    
    services.updateUser = function (userId, name, sex, phone, age) {
        return $http.post(userUrlBase + "/edit", 
            {'userId': userId, 'name':name, 'sex': sex, 'phone': phone, 'age': age}
        );
    };
    
    services.deleteUser = function (userId) {
        return $http.post(userUrlBase + "/destroy/" + userId);
    };
    
    services.addWorker = function (name, sex, phone) {
        return $http.post(userUrlBase + "/worker/add",
        {'name': name, 'sex': sex, 'phone': phone}
        );
    };

    services.getAdvertises = function (page, size) {
        return $http.get(advertiseUrlBase + "/show",
        {params: {'page': page, 'size': size}}
        );
    };

    services.getAdvertise = function (id) {
        return $http.get(advertiseUrlBase + "/detail/" + id);
    };

    services.getAdvertiseClicks = function (page, size, adId) {
        return $http.get(advertiseUrlBase + "/click/show",
        {params: {'page': page, 'size': size, 'adId': adId}}
        );
    };

    return services;
}]);