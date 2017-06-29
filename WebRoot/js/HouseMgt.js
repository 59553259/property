function exportExcelTemplet(){
	window.location.href="houseMgtAction"; 
}

function reoprtExcel(){	
	$('#fjfile').click();
}


function exprotExcel(){
	doSaveExcel('houseReportMgtAction?f='+Math.random());
}

/**
 * 提交导入的excel
 */
function doSaveExcel(url){
	var xhr = new XMLHttpRequest();
	var formdata = new FormData();
	var fjfile = document.getElementById('fjfile').files[0];//文件
	if(fjfile){
		formdata.append("fileName", fjfile.name);
		formdata.append("excelFile", fjfile);
		xhr.open('POST', url, true);
		xhr.send(formdata);
		 xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					var data=xhr.responseText;
					alert(data);
					window.location.reload();
				}else if(xhr.readyState == 4 && xhr.status != 200){
					alert('导入excel失败');
		         }
			};
	}else{
		alert('文件不能为空');
	}
}