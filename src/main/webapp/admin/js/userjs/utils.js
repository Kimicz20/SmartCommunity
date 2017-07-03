/**
 * 检测字符串在去掉首尾空格后是否为空
 * @param {String} texts 要检测的字符串
 * @returns {Boolean} 如果字符串为空，返回true，否则false
 */
function isEmpty(texts) {
    var result = false;
    
    if (!texts || !texts.trim()) {
        result = true;
    }
    
    return result;
}

/**
 * 获取静态文件的URL前缀
 * @returns {String} 静态文件的URL前缀
 */
function getStaticFileURLPrefix() {
    return "../static/";
}

/**
 * 根据请求返回的结果，检测会话是否已经失效
 * @param {String} info 请求返回的结果
 * @returns {Boolean} 如果会话已经失效，则返回true，否则false
 */
function isSessionTimeout(info) {
    var timeoutPattern = "form-signin";
    var isTimeout = false;
    var s = new String(info);
    
    if (s.indexOf(timeoutPattern) > 0)
        isTimeout = true;
    
    return isTimeout;
}

/**
 * 跳转到登录界面
 * 
 */
function relogin() {
    alert("您的会话已失效，请重新登录，谢谢！");
    window.location.href = "../admin/login.html";
}

/**
 * 网络资源访问错误处理方法
 * @param {String} error 返回的错误文本信息
 * @param {type} status 返回的HTTP状态码
 */
function errorHandler(error, status) {
    if (404 === status)
        alert("嗯……我暂时和数据中心失去了联系，您待会再试试看……");
    else if (isSessionTimeout(error)) {
        relogin();
    }    
}

/**
 * 根据selector来获取节点对象，该节点对象由JQuery包装过
 * @param {String} selector 节点的selector值，如 #userInput
 * @returns 由JQuery包装过的节点对象
 */
function getElementBySelector(selector) {
    return angular.element(document.querySelector(selector));
}

/**
 * 根据名称来获取节点列表
 * @param {String} name 名称
 * @returns {unresolved} 数组形式的节点列表
 */
function getElementsByName(name) {
    return angular.element(document.getElementsByName(name));
}

/**
 * 获取选中的radio的值
 * @param {type} radioList
 * @returns {String} 选中的radio的值
 */
function getCheckedRadioValue(radioList) {
    var checkedValue = "";
    
    for (var i = 0, length = radioList.length; i < length; i++) {
        if (radioList[i].checked) {
            checkedValue = radioList[i].value;

            break;
        }
    }
    
    return checkedValue;
}

/**
 * Form上传后的回调方法，如果resultCode为1,则说明上传成功，刷新当前页面
 * @param {int} resultCode 上传操作的结果码
 */
function uploadCompleted(resultCode){
    if (1 === resultCode) {
        alert("操作成功！");
        window.location.reload();
    }
    else
        alert("操作失败！");
}

/**
 * Form上传后的回调方法，如果resultCode为1,则说明上传成功
 * @param {int} resultCode 上传操作的结果码
 */
function updateCompletedAndGoBack(resultCode) {
    if (1 === resultCode) {
        alert("更新产品信息操作成功！");
        window.location.href = '../../admin/order.html#/order/products/edit';
    }
    else
        alert("更新产品信息失败！");
}

/**
 * Form上传后的回调方法，如果resultCode为1,则说明上传成功
 * @param {int} resultCode 上传操作的结果码
 */
function addCompletedAndGoBack(resultCode) {
    if (1 === resultCode) {
        alert("添加产品信息操作成功！");
        window.location.href = '../../admin/order.html#/order/products/edit';
    }
    else
        alert("添加产品信息失败！");
}

/**
 * Form上传后的回调方法，如果resultCode为1,则说明上传成功
 * @param {int} resultCode 上传操作的结果码
 */
function lifeEditCompletedAndGoBack(resultCode) {
    if (1 === resultCode) {
        alert("商家信息信息保存成功！");
        window.location.reload();
    }
    else
        alert("商家信息信息保存失败！");
}

/**
 * 新增广告表单上传后的回调方法，如果resultCode为1,则说明上传成功
 * @param {int} resultCode 上传操作的结果码
 */
function addAdvertiseCompletedAndGoBack(resultCode) {
    if (1 == resultCode) {
        alert("新增广告成功！");
        window.location.href = '../admin/admin.html#/advertise/show';
    }
    else
        alert("新增广告失败！");
}

/**
 * 更新广告表单上传后的回调方法，如果resultCode为1,则说明上传成功
 * @param {int} resultCode 上传操作的结果码
 */
function updateAdvertiseCompletedAndGoBack(resultCode) {
    if (1 == resultCode) {
        alert("更新广告成功！");
        window.location.href = '../admin/admin.html#/advertise/show';
    }
    else
        alert("更新广告失败！");
}

/**
 * 回退到历史记录里的上一层
 */
function goBack() {
    history.back();
}

/**
 * 在提交表单前的验证
 * @param {String} askStr 询问语句
 * @param {Form} form 表单对象
 * @returns {Boolean} 是否通过验证, boolean值
 */
function checkBeforeSubmit(askStr, form) {
    //检查表单内容是否填写完整
    for (var i = 0; form !== undefined && i < form.length; i++) {
        if (!(form.elements[i].nodeName.toLowerCase() === 'input' && form.elements[i].type === "text")
            && form.elements[i].nodeName.toLowerCase() !== "textarea") {
            continue;
        }
        if (isEmpty(form.elements[i].value)) {
            alert("请完整填写内容！");
            return false;
        }
    }
    
    //确认并将button的状态改为disabled
    if (confirm(askStr)) {
        for (var i = 0; form !== undefined && i < form.length; i++) {
            var e = form.elements[i];
            if (e.nodeName.toLowerCase() === 'button') {
                e.disabled = true;
                if (e.getAttribute('type') === 'submit')
                    e.innerHTML = "保存中……";
            }
        }
        
        return true;
    }
    
    return false;
}

/**
 * 检测浏览器是否为IE
 * @returns {Boolean} 如果是IE，返回true，否则false
 */
function isIE() {
    var isIe = false;
    
    if(navigator.userAgent.indexOf("MSIE")>0) 
        isIe = true;
    
    return isIe;
}

/**
 * 刷新页面
 */
function refresh() {
    window.location.reload();
}

/**
 * 日期格式化为YYYY-MM-DD
 * @param rawDateStr 字符串形式的日期表示，如1990-04-03 12:13:17
 * @return {String} 格式化后的日期
 */
function formatDateToYYYYMMDD(rawDateStr) {
    var dateStr = "";

    try {
        dateStr = rawDateStr.split(" ")[0];
    } catch (e) {
        console.error(e);
    }

    return dateStr;
}


