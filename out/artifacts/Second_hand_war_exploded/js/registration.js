
//提交前判断是否存在空值，判断密码是否一致,邮箱是否被注册
function check(form) {
    ajaxFunction();
    // ajaxFunction1();
    if(form.uname.value=="") {
        alert("请填写姓名！");
        form.uname.focus();
        return false;
    }else if(form.uclass.value=="") {
        alert("请填写年级！");
        form.uclass.focus();
        return false;
    }else if(form.ucontentway.value=="") {
        alert("请输入联系方式！");
        form.ucontentway.focus();
        return false;
    }else if(form.uemail.value=="") {
        alert("邮箱不能为空！");
        form.uemail.focus();
        return false;
    }else if(form.upwd.value=="") {
        alert("密码不能为空！");
        form.upwd.focus();
        return false;
    }else if(form.uvc.value=="") {
        alert("请输入验证码！");
        form.uvc.focus();
        return false;
    }else if (document.getElementById("pwd1").value != document.getElementById("pwd2").value) {
        alert("两次输入密码不一致！");
        form.upwd.focus();
        return false;
    } else {
        if(form.uvc.value!=form.hid1.value){
            alert("验证码错误！");
            form.uvc.focus();
            return false;
        }else{
            if(form.hid2.value=="false"){
                alert("该邮箱已被使用！");
                form.uemail.focus();
                return false;
            }else {
                form.submit();
                return true;
            }
        }
    }
}

/*******    刷新验证码   ********/
function refreshCode() {
    document.getElementById("code").src = "validateCode?t="+Math.random();

    //顺便改了存在隐藏表单中的验证码值
    ajaxFunction();
}


function createXHR() {
    var xmlhttp;
    if (window.XMLHttpRequest) {    //若支持XMLHttpRequest
        xmlhttp = new XMLHttpRequest();
    }else{
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xmlhttp;
}
//获得验证码并存入隐藏表单hid1中
function ajaxFunction() {
    //创建XMLHttpRequest对象
    xhr = createXHR();
    //设定请求地址
    var url = "registrationAjax";
    //建立对服务器的调用
    xhr.open("GET",url,true);
    //指定响应事件处理函数
    xhr.onreadystatechange = function () {
        //当readyState = 4且状态为200时，表示响应已就绪
        if (xhr.readyState == 4 && xhr.status == 200) {
            //对响应结果进行处理
            var resData = xhr.responseText;
            //将响应数据更新到hid1隐藏控件中
            document.getElementById("hid1").value = resData;
        }
    };
    //向服务器发出请求
    xhr.send();
}
//
function ajaxFunction1(email){
    xhr = createXHR();
    var url = "checkEmailAjax?email="+email;
    xhr.open("GET",url,true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var resData = xhr.responseText;
            //将响应数据更新到hid1隐藏控件中
            document.getElementById("hid2").value = resData;
        }
    };
    xhr.send();
}