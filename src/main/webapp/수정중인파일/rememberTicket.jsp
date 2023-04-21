<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link rel="stylesheet" href="${contextPath}/css/common2.css">
  <link rel="stylesheet" href="${contextPath}/css/ticket.css">
<title>예매하기</title>
<script type="text/javascript">


function fn_ticket() {
		let frmMovie=document.frmMovie;
		let mvTitle = frmMovie.mvTitle.value;
		let theater = frmMovie.theater.value;
		let showingDate = frmMovie.showingDate.value;
		let showingTime = frmMovie.showingTime.value;
		
		if(mvTitle.length == 0 || mvTitle == ""){
			alert("영화를 선택해주세요")
		}else if(showingDate.length == 0 || showingDate == ""){
			alert("날짜를 선택해주세요")
		}else if(theater.length == 0 || theater == ""){
			alert("상영관을 선택해주세요")
		}else if(showingTime.length == 0 || showingTime == ""){
			alert("시간을 선택해주세요")
		}else {
			frmMovie.method="post";
			frmMovie.action="movieSeat.do"; //임시
			frmMovie.submit(); //서블릿으로 전송
		}
	}

	
</script>

  <!-- BOXICONE -->
  <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
  
  <!-- JSCODE -->
  <script src="${contextPath}/js/ticket.js" defer></script>
</head>
<body>
  <div class="inner">
    <div class="memInfo">
      <input type="button" value="로그아웃" class="memInfobtn">
      <input type="button" value="마이페이지" class="memPagebtn">
    </div>
    
    <div class="title">
      <span class="title_left">예매</span>
      <span class="title_img"><i class='bx bx-camera-movie'></i></span>
      <span class="title_right">영화정보</span>
    </div>

    <!-- 검색창 -->
    <div class="search">
      <form action="#">
        <span class="movie_serch">영화찾기</span>
        <input type="text" name="movieSearch" id="movieSearch">
        <button><i class='bx bx-search-alt-2'></i></button>
      </form>
    </div>

    <!-- 콘테이너 -->
   
	 	<div class="container">
	      <div class="movie_tbl tbl_con">
	        <span class="">영화선택</span>
	        <ul>
	          <li><a href="#">${mvInfoList[0].mvTitle}</a></li>
	        </ul>
	      </div>
	      
	      <div class="day_tbl tbl_con">
	        <span class="">날짜선택</span>
	        <ul class="movie1 day1">
	          <li class="day1"><a href="#">${mvInfoList[0].showingDate}</a></li>
	          <li class="day2"><a href="#">${mvInfoList[2].showingDate}</a></li>
	          <li class="day3"><a href="#">${mvInfoList[4].showingDate}</a></li>
	        </ul>
	      </div>
	
	      <div class="screen_tbl tbl_con">
	        <span class="">상영관선택</span>
	        <ul class="movie1 day1 screen">
	          <li class="screen1"><a href="#">${mvInfoList[0].theater}</a></li>
	          <li class="screen2"><a href="#">${mvInfoList[2].theater}</a></li>
	        </ul>
	      </div>
	
	      <div class="time_tbl tbl_con">
	        <span class="">시간선택</span>
	        <ul class="movie1 day1 time1">
	          <li><a href="#">${mvInfoList[0].showingTime}</a></li>
	          <li><a href="#">${mvInfoList[3].showingTime}</a></li>
	          <li><a href="#">${mvInfoList[5].showingTime}</a></li>
	        </ul>
	      </div>
    	</div>
	
	    <div class="btn_con">
	      <a class="movieListBtn" href="${contextPath}/movieChart.jsp">영화리스트</a>
	      <a class="movieSeatBtn"><input type="button" value=">좌석선택" onclick="fn_ticket()"></a>
	    </div>
	  </div>
	  
	  <div class="goNext">
	    <div class="inner">
	      <div class="info">
	        <div class="info movie">
	          <span class="movie_poster">
	            <!-- <img src="./image/001.jpg" alt="리멤버"> -->
	          </span>
	          <div class="row movie_title">
	            <span>${mvInfoList[0].mvTitle}</span>
	          </div>
	        </div>
	        <div class="target info">
	          <div class="row date">
	            <span>일시</span>
	            <span class="date"></span>
	            <span class="time"></span>
	          </div>
	          <div class="row screen">
	            <span>상영관</span>
	            <span class="screen"></span>
	          </div>
	        </div>
	        <div class="row colspan">
	          <span class="path_step1">>좌석선택</span>
	          <span class="path_step2">>결제</span>
	        </div>
	      </div>
   	 </div>
 	 </div>
 	 
 	   <form name="frmMovie">
        <input type="hidden" name="mvTitle" id="movie_title">
        <input type="hidden" name="theater" id="screen">
        <input type="hidden" name="showingDate" id="date">
        <input type="hidden" name="showingTime" id="time">
        <input type="button" value="좌석선택" onclick="fn_ticket()">
      </form>
</body>
</html>