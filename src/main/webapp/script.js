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

function fn_submit() {
   var fn = document.frm;

   //유효성 체크
   if (fn.p_id.value == "") {   //입력창 custname부분이 null이면
      alert("상품코드명이 입력되지 않았습니다.");
      fn.cstname.focus();   //포커싱
      return false;   //함수 끝
   }
   if (fn.p_name.value == "") {
      alert("상품명이 입력되지 않았습니다.");
      fn.phone.focus();   //포커싱
      return false;   //함수 끝
   }
   if (fn.p_price.value == "") {
      alert("상품가격이 입력되지 않았습니다.");
      fn.address.focus();   //포커싱
      return false;   //함수 끝
   }
   if (fn.img_name.value == "") {
      alert("상품이미지가 입력되지 않았습니다.");
      fn.joindate.focus();   //포커싱
      return false;   //함수 끝
   }
   fn.submit();
}
