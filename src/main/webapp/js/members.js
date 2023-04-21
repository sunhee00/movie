var contextPath = getContextPath();
function getContextPath() {
	return sessionStorage.getItem("contextpath");
}

//join
//id 중복체크
function fn_idChk() {
  var chkId=$("#id").val(); //입력된 id 값을 chkId로 정의
  if(chkId=="") { //id 입력이 없을 경우
    alert("ID를 입력해주세요");
    return;
  }
  $.ajax({
    type:"post",
    async:true, //false=>동기식, true=>비동기식
    url: contextPath + "/member/idChk.do",
    dataType:"text",
    data: {id: chkId}, //chkId(=유저가 입력한 id)를 id에 담아 서버로 보냄
    success:function(data, textStatus) { //서버가 성공적으로 실행되었을 경우
      if(data=="usable") { //데이터가 사용가능할 경우
        alert("사용할 수 있는 아이디입니다.");
        $("#btnIdChk").prop("disabled", true); //버튼을 비활성화시킴(더 이상 중복체크할 필요가 없으니)
      }else {
        alert("사용할 수 없는 아이디입니다.");
      }
    },
    error:function(data, textStatus, error) { //서버 연결에 실패하였을 경우
      alert("id 중복확인 오류 발생");
      console.log("data : " + data);
      console.log("textStatus : " + data.textStatus);
    }
  });
}

//연락처에 자동 하이픈 삽입
function autoHyphen(target) {
  target.value = target.value
    .replace(/[^0-9]/g, "").replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/\-{1,2}$/g, "");
  // 숫자를 제외한 모든 문자는 삭제
  // 3자리 - 4자리 - 4자리
  // 하이픈만 있을 경우 삭제(숫자가 입력되어야만 하이픈이 나오게함)
}

//메인으로 가기
function backToMain(obj) {
  obj.action = contextPath + "/member/"; //main.do
  obj.submit();
}

//회원가입 페이지로 이동
function moveToJoin(obj) {
  obj.action = contextPath + "/member/join.do";
  obj.submit();
}

//아이디 비밀번호 찾기 페이지로 이동
function moveTofindIdPw(obj) {
	obj.action = contextPath + "/memberView/findIdPw.jsp"
	obj.submit();
}

//로그인 페이지로 이동
function moveToLogin(obj) {
	obj.action = contextPath + "/memberView/login.jsp"
	obj.submit();
}

//회원탈퇴(비밀번호체크) 페이지로 이동
function moveToWithdrawal(obj) {
   console.log('hello')
   obj.action = contextPath + "/memberView/cfmPwdDel.jsp"
   obj.submit();
}

