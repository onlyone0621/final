var XHR = null;

function getXHR() {
    if (window.ActiveXObject) {
        return new ActiveXObject("Msxml2.XMLHTTP");
    } else if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    } else {
        return null;
    }
}

function sendRequest(url, params, callback, method) {
    XHR = getXHR();
    var newMethod = method || 'GET';

    if (newMethod !== 'GET' && newMethod !== 'POST') {
        newMethod = 'GET';
    }

    if (newMethod === 'GET' && params) {
        url += '?' + params;
    }

    XHR.onreadystatechange = callback;
    XHR.open(newMethod, url, true);
    XHR.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    XHR.send(newMethod === 'POST' ? params : null);
}