function initCheckbox()  {
  const checkedboxes 
       = document.getElementsByName('chk');
  checkedboxes.forEach((checkedbox) => {
    checkedbox.checked = false;
  })
}

function allselect(bool){
	var chks = document.getElementsByName("chk");
	for(var i = 0; i < chks.length; i++){
		chks[i].checked = bool;
	}
}

function goToDelete() {
	var rc = document.myform.chk;
	flag = false;
	
	for(i=0; i<rc.length; i++) {
		if(rc[i].checked) {
			flag = true;
		}
	}
	
	if(flag == false) {
		alert("하나 이상 선택해주세요");
		return;
	}
	document.myform.submit();
}