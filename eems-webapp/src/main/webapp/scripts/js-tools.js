/*
  JS中的一些工具函数
  以下函数大部分收集于互联网	
  部分函数使用Jquery语法，请在本文件之前导入Jquery
  awzhf
  2018-4-15 15:47
*/

var context_path = "http://localhost:8080/eems-webapp";
var pages_path = context_path;
/* 
  获取URL中的查询参数(URL中?后的参数值) 
  传入参数名称获取对应的值	
*/
function GetQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}

/*
  动态加载资源文件
  加载js文件可以在加载完毕后执行回调
  调用方法：
  DynamicLoading.css('cssurl');

  DynamicLoading.js('jsurl');
  DynamicLoading.js('jsurl', function(){
	...
  });
*/
var DynamicLoading = {
  css: function(path){
    if(!path || path.length === 0){
      throw new Error('argument "path" is required !');
    }
    var head = document.getElementsByTagName('head')[0];
    var link = document.createElement('link');
    link.href = path;
    link.rel = 'stylesheet';
    link.type = 'text/css';
    head.appendChild(link);
  },
  js: function(path, callback){
    if(!path || path.length === 0){
      throw new Error('argument "path" is required !');
    }
    var head = document.getElementsByTagName('head')[0];
    var script = document.createElement('script');
    script.src = path;
    script.type = 'text/javascript';
    if(typeof(callback)=='function'){
      script.onload = script.onreadystatechange = function () {
        if (!this.readyState || this.readyState === "loaded" || this.readyState === "complete"){
          callback();
          script.onload = script.onreadystatechange = null;
        }
      };
    }
    head.appendChild(script);
  }
}

/*
  通过表单Id获取表单数据并转换为Json字符串返回
*/
function GetFormDataToJson(formId){
  var data = {};
  var temp = $('#'+formId).serializeArray();
  $.each(t, function() {
    if(this.value != ''){
      d[this.name] = this.value;
    }
  });
  return JSON.stringify(data);
}

function add0(m) {
	return m < 10 ? '0' + m : m
}

function format(shijianchuo) {
	var time = new Date(shijianchuo);
	var y = time.getFullYear();
	var m = time.getMonth() + 1;
	var d = time.getDate();
	var h = time.getHours();
	var mm = time.getMinutes();
	var s = time.getSeconds();
	return y + '-' + add0(m) + '-' + add0(d) + ' ' + add0(h) + ':' + add0(mm)
			+ ':' + add0(s);
}

function sexChangeFor1Or2(number){
	return number=='1'?'男':'女';
}

function jcChange(jc){
	return jc=='j'?'奖励':'惩罚';
}






