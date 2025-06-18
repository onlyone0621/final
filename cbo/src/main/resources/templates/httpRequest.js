var XHR=null;
function getXHR(){
	if(window.ActioveXObject){
		return new ActiveXObject("Msxml2.XMLHTTP");
	}else if(window.XMLHttpRequest){
		return new XMLHttpRequest();
	}else{
		return null;
	}
}

function sendRequest(url,params,callback,method){
	XHR = getXHR();
	var newMethod = method ? method : 'GET';//1차 유효성 검사
	//2차 유효성 검사
	if(newMethod!='GET' && newMethod!='POST'){
		newMethod = 'GET';
	}
	//3차 유효성 검사
	var newParams = (params==null||params=='') ? null : params;
	if(newMethod=='GET' && newParams!=null){
		url = url + '?' + newParams;
	}
	XHR.onreadystatechange=callback;
	XHR.open(newMethod, url, true);
	XHR.setRequestHeader('Context-Type','application/x-www-form-urlencoded');
	XHR.send(newParams);
}