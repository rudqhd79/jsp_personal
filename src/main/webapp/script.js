function initCheckbox() {
	const checkedboxes = document.getElementsByName('chk');
	checkedboxes.forEach((checkedbox) => {
		checkedbox.checked = false;
	})
}

function allselect(bool) {
	var chks = document.getElementsByName("chk");
	for (var i = 0; i < chks.length; i++) {
		chks[i].checked = bool;
	}
}


function checkBoxConf() {
	var fn = document.frm;

	var isSeasonChk = false;
	var arrSeason = document.getElementsByName("chk");
	for (var i = 0; i < arrSeason.length; i++) {
		if (arrSeason[i].checked == true) {
			isSeasonChk = true;
			break;
		}
	}

	if (!isSeasonChk) { //체크가 한개도 안되어 있을때
		alert("체크박스를 선택하세요!");
		return false;
	}
	fn.submit();
}

