/**
 *  隐藏/显示错误信息
 * @param onOff true验证成功,隐藏错误  false 验证失败,显示错误
 * @param input 表单域选择器
 * @param errSelector 错误提示选择器
 * @param message 错误信息
 */
function switchValid(onOff,input,errSelector,message){
    if(onOff==false){
        $(errSelector).text(message);
        $(input).addClass("error_input");
        $(errSelector).addClass("error_message");
    }else{
        $(errSelector).text("");
        $(input).removeClass("error_input");
        $(errSelector).removeClass("error_message");
    }
}

/**
 * 检查是否为空
 * @param input 表单域选择器
 * @param errSelector 错误提示选择器
 * return true-校验成功 false-校验失败
 */
function checkEmpty(input,errSelector){
    var val = $(input).val();//获取input的内容也是值
    if($.trim(val)==""){//$.trim()去掉前后空格
        switchValid(false,input,errSelector,"请输入内容");
        return false;
    }else {
        switchValid(true,input,errSelector);
        return true;
    }
}


/**
 * 类别必选
 * @param input 表单域选择器
 * @param errSelector 错误提示选择器
 * @returns true-校验成功  false-校验失败
 */
function checkCategory(input,errSelector){
    var val = $(input).val();
    if(val==-1){
        switchValid(false,input,errSelector,"请输入内容");
        return false;
    }else {
        switchValid(true,input,errSelector);
        return true;
    }
}

/**
 * 价格必须是正整数
 * @param input 表单域选择器
 * @param errSelector 错误提示选择器
 * @returns true-校验成功  false-校验失败
 */
function checkPrice(input,errSelector){
    var val = $(input).val();
    var regex=/^[1-9][0-9]*$/;
    if(!regex.test(val)){
        switchValid(false,input,errSelector,"无效的价格");
        return false;
    }else{
        switchValid(true,input,errSelector);
        return true;
    }
}

function checkFile(input,errSelector){
    if(checkEmpty(input,errSelector)==null){
        return false;
    }
    var val = $(input).val().tolowerCase();
    if(val.length<4){
        switchValid(false,input,errSelector,"请选择有效的图片");
    }
    var suffix = $(val).substring(val.length-3);
    if(suffix=="jpg"||suffix=="png"||suffix=="gif"){
        switchValid(true,input,errSelector);
        return true;
    }else {
        switchValid(false,input,errSelector,"请选择有效的图片");
        return false;
    }

}