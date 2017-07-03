var smartcommunityControllers = angular.module('smartcommunityControllers',['ui.bootstrap','dataAccessServices']);

smartcommunityControllers.factory('audio',function ($document) {
  var audioElement = $document[0].createElement('audio'); // <-- Magic trick here
  return {
    audioElement: audioElement,

    play: function(filename) {
        audioElement.src = filename;
        audioElement.play();     
    }
  };
});

smartcommunityControllers.controller('NotificationController',['$scope','dataService','$window','$controller',
	function($scope, dataService, $window, $controller) {
        angular.extend(this, $controller('BaseController', {$scope: $scope}));
        
        $scope.isRequesting = false;
        
        //清空标题和正文输入框内容
        $scope.cleanInput = function() {
            $scope.noti_title = "";
            $scope.noti_text = "";            
        };
        
        //提交新增的通知
        $scope.submitNoti = function(title, text, type){
            if (isEmpty(title) || isEmpty(text)) {
                $scope.addAlert("danger", "请完整填写通知标题和正文！");
                return;
            }
            
            if (!$window.confirm("您确定要发送这则通知给用户吗？")) {
                return;
            }
            
            $scope.isRequesting = true;
            
            dataService.addNewNotification(title.trim(), text.trim(), type)
                    .success(function (data) {
                        if (isSessionTimeout(data)) {
                            relogin();
                        }
                        if (data.resultCode === 1) {
                            $scope.status = "新增物业通知成功！";
                            $scope.addAlert("success", $scope.status);
                            $scope.cleanInput();
                        }
                        else {
                            $scope.status = "新增物业通知失败！";
                            $scope.addAlert("danger", $scope.status);                            
                        }
                        
                        $scope.isRequesting = false;
                    })
                    .error(function (error,status) {
                        errorHandler(error, status);
                        $scope.status = "新增物业通知失败！";
                        $scope.addAlert("danger", $scope.status);
                        $scope.isRequesting = false;
                    });
        };
	}
]);

smartcommunityControllers.controller('SuggesionController',['$scope', '$modal','dataService',
	function($scope, $modal, dataService) {
        var suggestionDescMap = {
            0: "已受理",
            1: "已完成",
        };
        var suggestionTypeMap = {
            1: "物业服务",
            2: "环境卫生",
            3: "小区秩序",
            4: "收费问题",
            5: "公共报修",
            6: "其他",
        };
        $scope.page = 1;
        $scope.size = 20;
        $scope.isTheLastPage = false;
        $scope.fetchSuggestions = function (page, size) {
            $scope.isTheLastPage = false;
            dataService.getSuggestions(page, size)
                    .success(function (data) {
                        if (isSessionTimeout(data)) {
                            relogin();
                        }                    
                        for (var i = 0; i < data.length; i++) {
                            var pictureList = [];
                            var node = data[i];

                            if (!isEmpty(node.pictureOne))
                                pictureList.push(node.pictureOne);
                            if (!isEmpty(node.pictureTwo))
                                pictureList.push(node.pictureTwo);
                            if (!isEmpty(node.pictureThree))
                                pictureList.push(node.pictureThree);

                            data[i].pictures = pictureList;

                            node.statusDesc = suggestionDescMap[node.status];
                            node.typeDesc = suggestionTypeMap[node.type];
                        }
                        $scope.items = data;
                        //判断是否已经是最后一页：数据长度为0或者小于默认的大小
                        if ($scope.items.length === 0 || $scope.items.length < $scope.size)
                            $scope.isTheLastPage = true;                        
                    })
                    .error(function (error,status){
                        errorHandler(error, status);
                    });
        };
        $scope.previous = function () {
            // 如果是第一页就不进行任何操作
            if ($scope.page <= 1) {
                return;
            }
            $scope.page = $scope.page - 1;
            $scope.fetchSuggestions($scope.page, $scope.size);            
        };
        $scope.next = function () {
            if ($scope.isTheLastPage) 
                return;
            $scope.page = $scope.page + 1;
            $scope.fetchSuggestions($scope.page, $scope.size);
        };
        
        //弹出窗口的方法
        $scope.open = function (size, index) {
            var modalInstance = $modal.open({
              templateUrl: 'partials/suggestionPopup.html',
              controller: DetailModalController,
              size: size,
              resolve: {
                item: function () {
                 return $scope.items[index];
                }
              }              
            });
            
            modalInstance.result.then();            
        };
        
        $scope.fetchSuggestions($scope.page, $scope.size);
	}
]);

smartcommunityControllers.controller('suggestionHistoryController',['$scope', 'dataService', '$routeParams', '$window',
	function($scope, dataService, $routeParams, $window) {
        $scope.suggestionId = $routeParams.suggestionId;
        $scope.comment = null;
        $scope.status = null;
        $scope.isProcessing = false;
        $scope.suggestionItem = null;
        $scope.saveComment = function () {
            if (isEmpty($scope.comment)) {
                alert("请填写回复内容");
                return;
            }
            
            if (!confirm("您确定要回复么？"))
                return;
                
            $scope.isProcessing = true;
            dataService.updateSuggestion($scope.suggestionId, $scope.comment)
                    .success(function (data) {
                        if (isSessionTimeout(data)) {
                            relogin();
                        }    
                        refresh();
                    })
                    .error(function (error, status) {
                        errorHandler(error, status);
                    });
        };
        $scope.updateStatus = function (status) {
            if (!confirm("您确定要将状态修改为“已完成”么？"))
                return;            
            
            $scope.isProcessing = true;
            dataService.updateSuggestion($scope.suggestionId, null, status)
                    .success(function (data) {
                        if (isSessionTimeout(data)) {
                            relogin();
                        }                          
                        refresh();
                    })
                    .error(function (error, status) {
                        errorHandler(error, status);
                    });
        };
        
        dataService.getSuggestionById($scope.suggestionId)
                .success(function (data) {
                    if (isSessionTimeout(data)) {
                        relogin();
                    }     
                    $scope.suggestionItem = data;
                })
                .error (function (error, status) {
                    errorHandler(error, status);
                }); 
                    
                
	}
]);

var DetailModalController = function ($scope, $modalInstance, item, audio) {
        $scope.item = item;
        $scope.isIe = isIE();
        
        $scope.ok = function (selected) {
          $modalInstance.close(selected);
        };

        $scope.cancel = function () {
          $modalInstance.dismiss('cancel');
        };
        
        $scope.playme = function(songPath) {
          audio.play(getStaticFileURLPrefix() + songPath);    
        };
};

smartcommunityControllers.controller('WrapperController',['$scope','$location','dataService','$window',
	function($scope,$location,dataService,$window) {
        dataService.getUserInfo()
                .success(function (data) {
                    if (isSessionTimeout(data)) {
                        relogin();
                    }                    
                    $scope.userInfo = data;
                })
                .error(function (error,status) {
                    errorHandler(error, status);                  
                });
        
        $scope.$location = $location;
        
        //页面左边菜单栏的条目
        $scope.navItems = [
            {path: '/notification/new', title: '物业通知'},
            {path: '/suggestion/show', title: '投诉建议'},
            {path: '/repair/show', title: '业主报修'},
            {path: '/repair/workers', title: '维修人员'},
            {path: '/advertise/show', title: '小区广告'},
        ];
        
        //判断条目是否需要激活
        $scope.isActive = function(item) {
          if (item.path === $location.path()) {
              return true;
          }  
          
          return false;  
        };
        
        //隐去或展现左边栏
		$scope.toggleSidebar = function() {
			$scope.boolChangeClass = !$scope.boolChangeClass;
		};
        
        //注销退出
        $scope.logout = function() {
            if (!$window.confirm("您确定要退出么？")) {
                return;
            }
            
            dataService.logout()
                    .success(function(data){
                        $window.location.href="../admin/login.html";
                    })
                    .error(function(error,status){
                        $window.location.href="../admin/login.html";
                    });
        };


	}
]);

smartcommunityControllers.controller('LoginController',['$scope','dataService','$window','$controller',
	function($scope, dataService, $window, $controller) {
        angular.extend(this, $controller('BaseController', {$scope: $scope}));
        
        dataService.getAllCommunities()
                .success(function(data){
                    $scope.communities = data;
                })
                .error(function(error, status) {
                    errorHandler(error, status);
                });
        
        //登录
        $scope.signin = function() {
            var user = getElementBySelector("#userInput").val();
            var pwd = getElementBySelector("#pwdInput").val();
            var place = getElementBySelector("#placeSelect").val();
            var role = getCheckedRadioValue(getElementsByName("roleOption"));
            
            dataService.login(user, pwd, place, role)
                    .success(function (data) {
                        if (data.resultCode === 1 && data.businessType === 0) 
                            $window.location.href="../admin/admin.html#/repair/show";
                        else if (data.resultCode === 1 && data.businessType === 2)
                            $window.location.href="../admin/order.html#/order/edit";
                        else if (data.resultCode === 1 && data.businessType === 4)
                            $window.location.href="../admin/life.html#/life/edit";
                        else {
                            $scope.status = "登录失败，请确认您的用户名、密码以及选择的社区是否正确！";
                            $scope.addAlert("danger", $scope.status);                            
                        }
                    })
                    .error(function (error,status) {
                            $scope.status = "登录失败！";
                            $scope.addAlert("danger", $scope.status);                            
                    });        
        };
	}
]);

smartcommunityControllers.controller('RepairController',['$scope','dataService','$modal',
    function($scope, dataService, $modal) {
        var repairTypeDescMap = {
            1: "给排水维修",
            2: "室内电器",
            3: "门窗",
            4: "保洁",
            5: "其他",
        };
        
        var repairStatusDescMap = {
            0: "已提交",
            2: "处理中",
            1: "已完成",
        };
        
        $scope.size = 20;
        $scope.page = 1;
        $scope.items = [];
        $scope.isTheLastPage = false;
        $scope.previous = function () {
            // 如果是第一页就不进行任何操作
            if ($scope.page <= 1) {
                return;
            }
            $scope.page = $scope.page - 1;
            $scope.getRepairs(null, null, $scope.page, $scope.size);
        }
        $scope.next = function () {
            if ($scope.isTheLastPage) 
                return;
            $scope.page = $scope.page + 1;
            $scope.getRepairs(null, null, $scope.page, $scope.size);
        }
        $scope.getRepairs = function (startTime, endTime, page, size) {
            $scope.items = [];
            $scope.isTheLastPage = false;
            dataService.getRepairs(startTime, endTime, page, size)
                    .success(function (data){
                        if (isSessionTimeout(data)) {
                            relogin();
                        }
                        for (var i = 0; i < data.length; i++) {
                            var pictures = [];
                            var times = [];
                            var node = data[i];

                            if (!isEmpty(node.pictureOne))
                                pictures.push(node.pictureOne);
                            if (!isEmpty(node.pictureTwo))
                                pictures.push(node.pictureTwo);
                            if (!isEmpty(node.pictureThree))
                                pictures.push(node.pictureThree);

                            data[i].pictures = pictures;

                            if (!isEmpty(node.timeOne))
                                times.push(node.timeOne);
                            if (!isEmpty(node.timeTwo))
                                times.push(node.timeTwo);
                            if (!isEmpty(node.timeThree))
                                times.push(node.timeThree);

                            node.statusText = repairStatusDescMap[node.isFixed];
                            node.typeDesc = repairTypeDescMap[node.type];

                            data[i].times = times;
                        }

                        $scope.items = data;
                        //判断是否已经是最后一页：数据长度为0或者小于默认的大小
                        if ($scope.items.length === 0 || $scope.items.length < $scope.size)
                            $scope.isTheLastPage = true;
                    })
                    .error(function (error, status){
                        errorHandler(error, status);
                    });
        };
        
        $scope.getRepairs(null, null, $scope.page, $scope.size);
                
        //弹出窗口的方法
        $scope.open = function (size, index) {
            var modalInstance = $modal.open({
              templateUrl: 'partials/repairPopup.html',
              controller: DetailModalController,
              size: size,
              resolve: {
                item: function () {
                 return $scope.items[index];
                }
              }              
            });
            
            modalInstance.result.then();            
        };
    }
]);

smartcommunityControllers.controller('RepairHistoryController',['$scope', 'dataService', '$routeParams', '$window','$modal',
    function($scope, dataService, $routeParams, $window, $modal) {
        $scope.repairId = $routeParams.repairId;
        $scope.submittedList = [];
        $scope.processingList = [];
        $scope.fixedList = [];
        $scope.comment = undefined;
        $scope.repairStatus = undefined;
        $scope.repairNextStatus = undefined;
        $scope.isProcessing = false;
        $scope.workerRoleId = 6; // 维修工的角色ID
        $scope.workers = undefined;
        $scope.workerCommentTemplate = "维修人员：[workerName]\n联系电话：[workerPhone]";
        
        $scope.updateRepairStatus = function () {
            var id = $scope.repairId;
            var comment = $scope.comment;
            var status = $scope.repairNextStatus;
            
            if (!$window.confirm("您即将改变报修单的状态，一旦操作成功，结果将不可更改。确定继续么？")) {
                return;
            }
            
            if (isEmpty(comment) || 
                (!isEmpty(comment) && !$window.confirm("您在回复栏里填写了信息。需要提交这些回复信息么？"))) {
                if (2 === status) 
                    comment = "您好，正在处理您的报修，我们会尽快派出专业人员来为您服务，谢谢！";
                else if (1 === status)
                    comment = "您好，您的报修已完成，请对我们的服务进行评价，谢谢！";
            }
            
            $scope.isProcessing = true;
            dataService.addNewCommentOnRepairHistory(id, status, comment)
                    .success(function (data) {
                        if (isSessionTimeout(data)) {
                            relogin();
                        }
                        
                        if (data.resultCode === 1) {
                            refresh();
                        }
                    }) 
                    .error(function(error, status) {
                        errorHandler(error, status);
                        $scope.isProcessing = false;
                    });            
        };
        
        $scope.addNewRepairHistory = function () {
            var id = $scope.repairId;
            var comment = $scope.comment;
            var status = $scope.repairStatus;
            
            if (isEmpty(comment)) {
                alert("请填写回复的内容。");
                return;
            }
            
            if (!$window.confirm("您确定要回复吗？")) {
                return;
            } 
            
            $scope.isProcessing = true;
            dataService.addNewCommentOnRepairHistory(id, status, comment)
                    .success(function (data) {
                        if (isSessionTimeout(data)) {
                            relogin();
                        }
                        
                        if (data.resultCode === 1) {
                            refresh();
                        }
                    }) 
                    .error(function(error, status) {
                        errorHandler(error, status);
                        $scope.isProcessing = false;
                    });
        };
        
        dataService.getUsersByRole($scope.workerRoleId)
                .success(function (data) {
                    if (isSessionTimeout(data)) {
                        relogin();
                    }
                    
                    $scope.workers = data;
                })
                .error(function (error, status) {
                    errorHandler(error, status);
                });
        
        dataService.getRepairHistoryList($scope.repairId)
                .success(function (data) {
                    if (isSessionTimeout(data)) {
                        relogin();
                    }
                    
                    data.forEach(function(element, index, array) {
                        switch(element.status) {
                            case 0:
                                $scope.submittedList.push(element);
                                break;
                            case 1:
                                $scope.fixedList.push(element);
                                break;
                            case 2:
                                $scope.processingList.push(element);
                                break;
                        }
                    });
                    
                    // 确定当前的报修状态，0为已提交，2为处理中，1为已完成
                    // 之所以是3个不连续的数字，是因为刚开始压根没想过报修流程化这种东西，没考虑过“处理中”……
                    if ($scope.fixedList.length > 0) {
                        $scope.repairStatus = 1;
                    }
                    else if ($scope.processingList.length > 0) {
                        $scope.repairStatus = 2;
                        $scope.repairNextStatus = 1;
                    }
                    else {
                        $scope.repairStatus = 0;
                        $scope.repairNextStatus = 2;
                    }
                })
                .error(function(error, status) {
                     errorHandler(error, status);
                });
        //弹出窗口的方法
        $scope.open = function (size) {
            var modalInstance = $modal.open({
              templateUrl: 'partials/repairSelectWorkerPopup.html',
              controller: DetailModalController,
              size: size,
              resolve: {
                item: function () {
                    return $scope.workers;
                }
              }              
            });
            
            modalInstance.result.then(
                function (selectedWorker) {
                    if (selectedWorker === undefined)
                        return;
                    
                    $scope.comment = $scope.workerCommentTemplate
                            .replace("[workerName]", selectedWorker.name)
                            .replace("[workerPhone]", selectedWorker.phone);
                }, 
                function () {
                }
            );           
        };                
    }    
]);

smartcommunityControllers.controller('repairWorkerController',['$scope','dataService',
    function($scope, dataService) {
        $scope.workers = undefined;
        $scope.workerRoleId = 6; // 维修工的角色
        
        dataService.getUsersByRole($scope.workerRoleId)
                .success(function (data) {
                    if (isSessionTimeout(data)) {
                        relogin();
                    }
                    
                    $scope.workers = data;
                })
                .error(function (error, status) {
                    errorHandler(error, status);
                });
    }
]);

smartcommunityControllers.controller('repairWorkerEditController',['$scope','dataService','$routeParams','$window',
    function($scope, dataService, $routeParams, $window) {
        $scope.workerId = $routeParams.workerId;
        $scope.workerName = $routeParams.workerName;
        $scope.workerSex = $routeParams.workerSex;
        $scope.workerPhone = $routeParams.workerPhone;
        $scope.isProcessing = false;
        
        $scope.deleteWorker = function() {
            if (!$window.confirm("您确定要删除该维修工的资料么？")) {
                return;
            }              
            dataService.deleteUser($scope.workerId)
                .success(function (data) {
                     if (isSessionTimeout(data)) {
                         relogin();
                     } 

                     if (data.resultCode === 1) {
                         goBack();
                     }
                     else {
                         alert("删除当前维修工资料失败！");
                     }
                     $scope.isProcessing = false;
                })
                .error(function (error, status) {
                         errorHandler(error, status);
                 });
        };
        $scope.updateWorker = function() {
            if (isEmpty($scope.workerName)) {
                alert("请填写维修工姓名");
                return;
            }
            if (isEmpty($scope.workerPhone)) {
                alert("请填写维修工联系电话");
                return;
            }            
            if (!$window.confirm("您确定要更新该维修工的资料么？")) {
                return;
            }            
            $scope.isProcessing = true;
            dataService.updateUser($scope.workerId, 
                                   $scope.workerName,
                                   $scope.workerSex, 
                                   $scope.workerPhone)
                                   .success(function (data) {
                                        if (isSessionTimeout(data)) {
                                            relogin();
                                        } 
                                        
                                        if (data.resultCode === 1) {
                                            goBack();
                                        }
                                        else {
                                            alert("更新维修工资料失败！");
                                        }
                                        $scope.isProcessing = false;
                                   })
                                   .error(function (error, status) {
                                            errorHandler(error, status);
                                    });
        };
    }
]);
smartcommunityControllers.controller('repairWorkerAddController',['$scope','dataService','$window',
    function($scope, dataService, $window) {
        $scope.isProcessing = false;
        $scope.name = '';
        $scope.sex = '';
        $scope.phone = '';
        
        $scope.newWorker = function () {
            if (isEmpty($scope.name)) {
                alert("请填写维修工姓名");
                return;
            }
            if (isEmpty($scope.phone)) {
                alert("请填写维修工联系电话");
                return;
            }
            if (isEmpty($scope.sex)) {
                alert("请选择性别");
                return;
            }
            if (!$window.confirm("您确定要新增该维修工么？")) {
                return;
            }
            $scope.isProcessing = true;
            dataService.addWorker($scope.name, $scope.sex, $scope.phone)
                    .success(function (data) {
                        if (isSessionTimeout(data)) {
                            relogin();
                        } 

                        if (data.resultCode === 1) {
                            goBack();
                        }
                        else {
                            alert("新增维修工失败！");
                        }
                        $scope.isProcessing = false;                        
                    }
                    )
                    .error(function (error, status) {
                             errorHandler(error, status);
                     });
        };
    }
]);

smartcommunityControllers.controller('AdvertiseController',['$scope','dataService',
    function($scope, dataService) {
        $scope.advertises = undefined;
        $scope.page = 1;
        $scope.size = 10;
        $scope.previous = function () {
            // 如果是第一页就不进行任何操作
            if ($scope.page <= 1) {
                return;
            }
            $scope.page = $scope.page - 1;
            $scope.fetchAdvertises($scope.page, $scope.size);
        }
        $scope.next = function () {
            if ($scope.isTheLastPage)
                return;
            $scope.page = $scope.page + 1;
            $scope.fetchAdvertises($scope.page, $scope.size);
        }

        $scope.fetchAdvertises = function (page, size) {
            $scope.isTheLastPage = false;
            dataService.getAdvertises(page, size)
                       .success(function (data) {
                            if (isSessionTimeout(data)) {
                                relogin();
                            }

                            //将广告的类型代码转成相应的文字表述
                            data.forEach(function(advertise) {
                                var typeText = "";
                                switch(advertise.type) {
                                    case 1: typeText = "简单投放广告";
                                            break;
                                    case 2: typeText = "按次数收费广告";
                                            break;
                                    case 3: typeText = "启动屏广告";
                                            break;
                                    default:
                                            typeText = "";
                                }
                                advertise.type = typeText;
                                advertise.startTime = formatDateToYYYYMMDD(advertise.startTime);
                                advertise.dueTime = formatDateToYYYYMMDD(advertise.dueTime);
                            });
                            $scope.advertises = data;
                            //判断是否已经是最后一页：数据长度为0或者小于默认的大小
                            if ($scope.advertises.length === 0 || $scope.advertises.length < $scope.size)
                                $scope.isTheLastPage = true;
                       })
                       .error(function (error, status) {
                            errorHandler(error, status);
                       });
        }
        $scope.fetchAdvertises($scope.page, $scope.size);
    }
]);

smartcommunityControllers.controller('AdvertiseDetailController', ['$scope', 'dataService', '$routeParams',
    function ($scope, dataService, $routeParams) {
        $scope.advertiseId = $routeParams.advertiseId;
        $scope.staffRole = 1;

        dataService.getUsersByRole($scope.staffRole)
                   .success(function (data) {
                        if (isSessionTimeout(data)) {
                            relogin();
                        }

                        $scope.responsibles = data;
                        dataService.getAdvertise($scope.advertiseId)
                                   .success(function (data) {
                                        if (isSessionTimeout(data)) {
                                            relogin();
                                        }

                                        data.picture = getStaticFileURLPrefix() + data.picture;
                                        data.startTime = formatDateToYYYYMMDD(data.startTime);
                                        data.dueTime = formatDateToYYYYMMDD(data.dueTime);
                                        $scope.advertise = data;
                                        $scope.selectedPerson = {id: data.responsiblePerson};
                                   })
                                   .error(function (error, status) {
                                        errorHandler(error, status);
                                   });
                   })
                   .error(function (error, status) {
                        errorHandler(error, status);
                   });

    }
]);

smartcommunityControllers.controller('AdvertiseAddController', ['$scope', 'dataService', '$window',
    function ($scope, dataService, $window) {
        $scope.staffRole = 1;
        dataService.getUsersByRole($scope.staffRole)
                   .success(function (data) {
                        if (isSessionTimeout(data)) {
                            relogin();
                        }

                        $scope.responsibles = data;
                   })
                   .error(function (error, status) {
                        errorHandler(error, status);
                   });
    }
]);

smartcommunityControllers.controller('AdvertiseClickController', ['$scope', 'dataService', '$window', '$routeParams',
    function ($scope, dataService, $window, $routeParams) {
        $scope.adId = $routeParams.adId;
        $scope.page = 1;
        $scope.size = 10;
        $scope.previous = function () {
            // 如果是第一页就不进行任何操作
            if ($scope.page <= 1) {
                return;
            }
            $scope.page = $scope.page - 1;
            $scope.fetchAdvertiseClicks($scope.page, $scope.size, $scope.adId);
        }
        $scope.next = function () {
            if ($scope.isTheLastPage)
                return;
            $scope.page = $scope.page + 1;
            $scope.fetchAdvertiseClicks($scope.page, $scope.size, $scope.adId);
        }
        $scope.fetchAdvertiseClicks = function (page, size, adId) {
            $scope.isTheLastPage = false;
            dataService.getAdvertiseClicks(page, size, adId)
                       .success(function (data) {
                           if (isSessionTimeout(data)) {
                               relogin();
                           }

                           $scope.clicks = data;
                           //判断是否已经是最后一页：数据长度为0或者小于默认的大小
                           if ($scope.clicks.length === 0 || $scope.clicks.length < $scope.size)
                               $scope.isTheLastPage = true;
                       })
                       .error(function (error, status) {
                            errorHandler(error, status);
                       });
        }

        $scope.fetchAdvertiseClicks($scope.page, $scope.size, $scope.adId);
    }
]);

smartcommunityControllers.controller('FamilyController',['$scope','dataService','$window','$controller',
    function($scope, dataService, $window, $controller) {
        angular.extend(this, $controller('BaseController', {$scope: $scope}));
        
        $scope.topicType = 1;
        $scope.isRequesting = false;
        
        $scope.submitNewTopic = function (title, text) {
            if (isEmpty(title) || isEmpty(text)) {
                $scope.addAlert("danger", "请完整填写小区资讯的标题和正文！");
                return;
            }
            
            if (!$window.confirm("您确定要发送这则资讯给用户吗？")) {
                return;
            }
            
            $scope.isRequesting = true;
            
            dataService.addNewTopicWithoutAttachments($scope.topicType, title, text)
                    .success(function (data) {
                        if (isSessionTimeout(data)) {
                            relogin();
                        }
                        if (data.resultCode === 1) {
                            $scope.status = "新增小区资讯成功！";
                            $scope.addAlert("success", $scope.status);
                            $scope.cleanInput();
                        }
                        else {
                            $scope.status = "新增小区资讯失败！";
                            $scope.addAlert("danger", $scope.status);                            
                        }
                        $scope.isRequesting = false;
                    })
                    .error(function (error, status) {
                        errorHandler(error, status);
                        $scope.status = "新增小区资讯失败！";
                        $scope.addAlert("danger", $scope.status);
                        $scope.isRequesting = false;
                    });
        };
        
        //清空标题和正文输入框内容
        $scope.cleanInput = function() {
            $scope.topic_title = "";
            $scope.topic_text = "";            
        };        
    }
]);
   
smartcommunityControllers.controller('ParcelController',['$scope','dataService','$window','$controller',
    function($scope, dataService, $window, $controller) {
        angular.extend(this, $controller('BaseController', {$scope: $scope}));
        
        $scope.userRole = 3;
        $scope.isRequesting = false;        
        
        $scope.submitNewParcel = function (title, name) {
            if (isEmpty(title) || isEmpty(name)) {
                $scope.addAlert("danger", "请完整填写邮包内容和接收人信息！");
                return;
            }
            
            if (!$window.confirm("您确定要发送该邮包信息给此用户吗？")) {
                return;
            }
            
            $scope.isRequesting = true;
            
            dataService.addNewParcel(title, name)
                    .success(function(data) {
                        if (isSessionTimeout(data)) {
                            relogin();
                        }
                        if (data.resultCode === 1) {
                            $scope.status = "邮包消息发送成功！";
                            $scope.addAlert("success", $scope.status);
                            $scope.cleanInput();
                        }
                        else if (data.resultCode === 995) {
                            $scope.status = "邮包消息发送失败！该用户不存在！";
                            $scope.addAlert("danger", $scope.status);
                            $scope.cleanInput();                            
                        }
                        else {
                            $scope.status = "邮包消息发送失败！";
                            $scope.addAlert("danger", $scope.status);                            
                        }
                        $scope.isRequesting = false;                        
                    })
                    .error(function(error, status){
                        errorHandler(error, status);
                        $scope.status = "邮包消息发送失败！";
                        $scope.addAlert("danger", $scope.status);
                        $scope.isRequesting = false;                        
                    });
        };
        
        //清空标题和正文输入框内容
        $scope.cleanInput = function() {
            $scope.parcel_info = "";
            $scope.parcel_user = "";
            $scope.parcel_room = "";
        };
        
        $scope.searchUsers = function(name, role) {
            return dataService.searchUsers(name, role)
                    .then(function (res){
                        var users = [];
                
                        if (isSessionTimeout(res.data)) {
                            relogin();
                        }
                        for (var i = 0; i < res.data.length; i++) {
                            users.push(res.data[i].name);
                        }
                        return users;
                    });
        };
        
    }
]);

smartcommunityControllers.controller('TelphoneController',['$scope','dataService','$window','$controller',
    function($scope, dataService, $window, $controller){
        angular.extend(this, $controller('BaseController', {$scope: $scope}));
        
        $scope.isRequesting = false;
        
        //清空标题和正文输入框内容
        $scope.cleanInput = function() {
            $scope.phone_title = "";
            $scope.phone_number = "";
        };
        
        $scope.addNewTelphone = function(title, number) {
            if (isEmpty(title) || isEmpty(number)) {
                $scope.addAlert("danger", "请完整填写文字描述和常用电话！");
                return;
            }
            
            if (!$window.confirm("您确定要添加该常用电话给吗？")) {
                return;
            }
            
            dataService.addNewTelphone(title, number)
                    .success(function(data) {
                        if (isSessionTimeout(data)) {
                            relogin();
                        }
                        
                        if (data.resultCode === 1) {
                            $scope.status = "常用电话添加成功！";
                            $scope.addAlert("success", $scope.status);
                            $scope.cleanInput();
                        }
                        else {
                            $scope.status = "常用电话添加失败！";
                            $scope.addAlert("danger", $scope.status);                            
                        }
                        $scope.isRequesting = false;                        
                    })
                    .error(function(error,status){
                        errorHandler(error, status);
                        $scope.status = "常用电话添加失败！";
                        $scope.addAlert("danger", $scope.status);
                        $scope.isRequesting = false;                           
                    });
        };
    }
]);

smartcommunityControllers.controller('BaseController',['$scope',
    function ($scope) {
        $scope.alerts = [];
        
        //新增Alert
        $scope.addAlert = function(statusType, status) {
            //为了防止生成多个Alert
            if ($scope.alerts.length > 0) {
                $scope.closeAlert(0,1);
            }
            $scope.alerts.push({'statusType': statusType, 'status': status});  
        };
        
        //关闭Alert
        $scope.closeAlert = function(index) {
            $scope.alerts.splice(index, 1);  
        };        
    }
]);

smartcommunityControllers.controller('OrderController',['$scope','dataService','$location','$window','$controller',
    function ($scope, dataService, $location, $window, $controller) {
        angular.extend(this, $controller('WrapperController', {$scope: $scope,$location: $location,dataService:dataService,$window: $window}));
        
        $scope.navItems = [
            {path: '/order/edit', title: '基本信息'},
            {path: '/order/products/edit', title: '商品信息'}
        ];
    }
]);

smartcommunityControllers.controller('OrderDetailsController',['$scope','dataService',
    function ($scope, dataService) {
        dataService.getGroceryInfo()
                .success(function(data) {
                    if (isSessionTimeout(data)) {
                        relogin();
                    }
                    
                    $scope.grocery_name = data.title;
                    $scope.grocery_phone = data.phone;
                    $scope.grocery_description = data.description;
                    $scope.grocery_address = data.address;
                    $scope.grocery_picture = getStaticFileURLPrefix() + data.icon;
                    $scope.grocery_type = data.type;
                    $scope.grocery_id = data.id;
                })
                .error(function(error,status) {
                    errorHandler(error, status);
                });
    }
]);

smartcommunityControllers.controller('ProductDetailController',['$scope','dataService','$routeParams','$window',
    function ($scope, dataService, $routeParams, $window) {
        $scope.product_id = $routeParams.productId;
        
        dataService.getProductById($scope.product_id)
                .success(function(data){
                    if (isSessionTimeout(data)) {
                        relogin();
                    }
                    
                    $scope.product_name = data.title;
                    $scope.product_price = data.price;
                    $scope.product_unit = data.unit;
                    $scope.product_intro = data.description;
                    $scope.product_icon = getStaticFileURLPrefix() + data.icon;
                    
                })
                .error(function(error,status) {
                    errorHandler(error, status);
                });
        
        $scope.deleteProduct = function () {
            if (!$window.confirm("您确定要删除当前产品么？")) {
                return;
            }
            
            dataService.deleteProduct($scope.product_id)
                    .success(function(data) {
                        if (isSessionTimeout(data)) {
                            relogin();
                        }  
                        
                        if (1 === data.resultCode) {
                            alert("删除成功！");
                            goBack();
                        }
                        else {
                            alert("删除不成功！");
                        }
                    })
                    .error(function(error, status) {
                        errorHandler(error, status);
                    });
        };
    }
]);

smartcommunityControllers.controller('ProductEditController',['$scope','dataService','$modal',
    function ($scope, dataService, $modal) {
        dataService.getProductForGrocery()
                .success(function(data) {
                    if (isSessionTimeout(data)) {
                        relogin();
                    }
                    
                    $scope.items = data;
                })
                .error(function(error,status) {
                    errorHandler(error, status);
                });
                
        //弹出窗口的方法
        $scope.open = function (size, index) {
            var modalInstance = $modal.open({
              templateUrl: 'partials/productPopup.html',
              controller: DetailModalController,
              size: size,
              resolve: {
                item: function () {
                 return $scope.items[index];
                }
              }              
            });
            
            modalInstance.result.then();            
        };         
    }
]);

smartcommunityControllers.controller('ProductAddController',['$scope','dataService',
    function ($scope, dataService) {
        
    }
]);

smartcommunityControllers.controller('LifeController',['$scope','dataService','$location','$window','$controller',
    function ($scope, dataService, $location, $window, $controller) {
        angular.extend(this, $controller('WrapperController', {$scope: $scope,$location: $location,dataService:dataService,$window: $window}));
        
        $scope.navItems = [
            {path: '/life/edit', title: '商家信息'}
        ];        
    }
]);

smartcommunityControllers.controller('LifeEditController',['$scope','dataService',
    function ($scope, dataService) {
        dataService.getMyLifeInfo()
                .success(function(data) {
                    if (isSessionTimeout(data)) {
                        relogin();
                    }
                    
                    $scope.life_id = data.id;
                    $scope.life_name = data.title;
                    $scope.life_type = data.type;
                    $scope.life_phone = data.phone;
                    $scope.life_intro = data.intro;
                    $scope.life_desc = data.description;
                    $scope.life_address = data.address;
                    $scope.life_discount = data.discount;
                    $scope.life_icon = getStaticFileURLPrefix() + data.icon;
                    $scope.life_picture = getStaticFileURLPrefix() + data.picture;
                })
                .error(function(error, status) {
                    errorHandler(error, status);
                });
    }
]);


smartcommunityControllers.controller('SignupController',['$scope','dataService','$window','$controller',
	function($scope, dataService, $window, $controller) {
        angular.extend(this, $controller('BaseController', {$scope: $scope}));
        
        dataService.getAllCommunities()
                .success(function(data){
                    $scope.communities = data;
                })
                .error(function(error, status) {
                    errorHandler(error, status);
                });
        
        //注册
        $scope.signup = function() {
            var user = getElementBySelector("#userInput").val();
            var pwd = getElementBySelector("#pwdInput").val();
            var place = getElementBySelector("#placeSelect").val();
            var businessType = getCheckedRadioValue(getElementsByName("businessTypeOption"));
            var role = 5;
            var room = "business";
            
            dataService.signup(user, room, role, businessType, pwd, place)
                    .success(function (data) {
                        $scope.status = "注册成功！";
                        $scope.addAlert("success", $scope.status); 
                    })
                    .error(function (error,status) {
                        $scope.status = "注册失败！";
                        $scope.addAlert("danger", $scope.status);                            
                    });        
        };
	}
]);