function CreateCheck() {
	if(!document.check.teamname.value){
		alert("구단명을 입력해 주세요.")
		check.teamname.focus();
		return false;
	}
	if(document.check.country.value.length == 0){
		alert("연고지를 입력해 주세요.")
		check.country.focus();
		return false;
	}
	if(document.check.homeground.value.length == 0){
		alert("홈구장을 입력해 주세요.")
		check.homeground.focus();
		return false;
	}
	if(document.check.coach.value.length == 0){
		alert("감독명을 입력해 주세요.")
		check.coach.focus();
		return false;
	}
	if(document.check.players.value.length == 0){
		alert("선수단정보를 입력해 주세요.")
		check.players.focus();
		return false;
	}
	return true;
}