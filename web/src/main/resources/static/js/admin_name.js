window.onload=function(){
	var xmlHttp = new XMLHttpRequest();//创建XMLHttpRequest对象
	xmlHttp.onreadystatechange=function(){//监听事件
		if(xmlHttp.readyState==4&& xmlHttp.status == 200)
			document.getElementById("admin_name").innerHTML=xmlHttp.responseText;
		
	}
	url="/try/ajax/getcustomer.php?q=";
	xmlHttp.open("GET",url,true);
	xmlHttp.send();
}