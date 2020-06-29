//显示上传的图片
function changepic() {
    var reads = new FileReader();
    f = document.getElementById("file").files[0];
    reads.readAsDataURL(f);
    reads.onload = function(e) {
        document.getElementById("img3").src = this.result;
        document.getElementById("img3").style.display = "block";
    };
}

//提交前判断是否存在空值，询问是否提交
function check(form) {
    if(form.gname.value=="") {
        alert("物品名称不能为空");
        form.gname.focus();
        return false;
    }else if(form.gprice.value=="") {
        alert("物品价格不能为空");
        form.gprice.focus();
        return false;
    }else if(form.gsite.value=="") {
        alert("交易地点不能为空");
        form.gsite.focus();
        return false;
    }else if(form.gpicture.value=="") {
        alert("请先上传图片！");
        form.gpicture.focus();
        return false;
    }else{
        if(confirm("你确定发布吗？")){
            return true;
        }else{
            return false;
        }
    }
}

/*********************/
function check1(form) {
    if (form.gprice.value == "") {
        alert("请输入转手价！");
        form.gprice.focus();
        return false;
    }else if (form.gsite.value == "") {
        alert("请填写交易地址！");
        form.gsite.focus();
        return false;
    }else {
        if(confirm("你确定修改吗？")){
            form.submit();
            return true;
        }else{
            return false;
        }

    }
}
function fck(){
    document.getElementById("ck").style.display = "block";
    document.getElementById("main").style.display = "none";
}
function fmain(){
    document.getElementById("ck").style.display = "none";
    document.getElementById("main").style.display = "block";
}
