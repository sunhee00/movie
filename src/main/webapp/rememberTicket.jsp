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
	<!-- HEADER -->
  <header>
    <div class="inner">
      <!-- logo & 이모티콘영역 -->
      <div class="top_area">
        <div class="logo_con">
          <div class="logo_image">
            <a href="${contextPath}/">
              <img src="${contextPath}/img/logoRed.png" alt="CGV" class="logo logo_red">
              <img src="${contextPath}/img/logoWhite.png" alt="CGV" class="logo logo_white">
            </a>
          </div>
          <div class="logo_text">
            CURTULPLEX
          </div>
        </div>
        <div class="mem_info">
          <c:choose>
            <c:when test="${sessionScope.id==null ||  sessionScope.id==''}">
              <div class="mem_join">
                <a href="${contextPath}/member/join.do">
                  <i class='bx bx-user-plus'></i>
                  <p>회원가입</p> <!--  -->
                </a>
              </div>
            </c:when>
            <c:otherwise>
              <div class="mem_join">
                <%
                  Object ID_get=session.getAttribute("id");
                %>
                <a href="#">
                  <p><%=ID_get %>님<br> 환영합니다</p>
                </a>
              </div>

            </c:otherwise>
          </c:choose>

          <div class="login_info">
            <!-- 로그인/비로그인 상태에 따라 다르게 보이게 -->
            <c:choose>
              <c:when test="${sessionScope.id==null ||  sessionScope.id==''}">
                <div class="login">
                  <a href="${contextPath}/member/login.do">
                    <i class='bx bx-log-in-circle'></i>
                    <p>로그인</p>
                  </a>
                </div>
              </c:when>
              <c:otherwise>
                <div class="logout">
                  <a href="${contextPath}/member/logout.do">
                    <i class='bx bx-log-out-circle'></i>
                    <p>로그아웃</p>
                  </a>
                </div>
              </c:otherwise>
            </c:choose>
          </div> <!--  //div class="login_info" -->




          <div class="myCGV">
              <c:choose>
                <c:when test="${sessionScope.id==null ||  sessionScope.id==''}">
                  <a href="${contextPath}/member/login.do">
                </c:when>
                <c:otherwise>
                  <a href="${contextPath}/member/memberPage.do">
                </c:otherwise>
              </c:choose>
              <i class='bx bx-user'></i>
              <p>MY CGV</p>
						</a>
					</div>


          <div class="QNA">
            <a href="${contextPath}/csCon.do">
              <i class='bx bx-support'></i>
              <p>고객센터</p>
            </a>
          </div>
        </div>
      </div>
    </div>

    <!-- MENU & SEARCH 영역 -->
    <div class="menu_area">
      <div class="inner">
        <ul class="main_menu">
          <li>
            <h2 class="menu_name">영화</h2>
            <ul class="menu_contents">
              <li>
                <h3>영화</h3>
              </li>
              <li><a href="${contextPath}/movie/movieChart.do">무비차트</a></li>
              <li><a href="#">아트하우스</a></li>
              <li><a href="#">ICECON</a></li>
            </ul>
          </li>

          <li>
            <h2 class="menu_name">극장</h2>
            <ul class="menu_contents">
              <li>
                <h3>극장</h3>
              </li>
              <li><a href="#">CGV 극장</a></li>
              <li><a href="#">특별관</a></li>
            </ul>
          </li>

          <li>
            <h2 class="menu_name">예매</h2>
            <ul class="menu_contents">
              <li>
                <h3>예매</h3>
              </li>
              <li><a href="${contextPath}/movie/ticket.do">빠른예매</a></li>
              <li><a href="#">상영스케줄</a></li>
              <li><a href="#">English Ticketing</a></li>
              <li><a href="#">English Scedule</a></li>
            </ul>
          </li>

          <li>
            <h2 class="menu_name">스토어</h2>
            <ul class="menu_contents">
              <li>
                <h3>스토어</h3>
              </li>
              <li><a href="#">영화관람권</a></li>
              <li><a href="#">기프트카드</a></li>
              <li><a href="#">콤보</a></li>
              <li><a href="#">팝콘</a></li>
              <li><a href="#">음료</a></li>
              <li><a href="#">스낵</a></li>
              <li><a href="#">플레이존</a></li>
              <li><a href="#">씨네샵></a></li>
            </ul>
          </li>

          <li>
            <h2 class="menu_name">이벤트</h2>
            <ul class="menu_contents">
              <li>
                <h3>이벤트</h3>
              </li>
              <li><a href="#">SPECIAL</a></li>
              <li><a href="#">영화/예매</a></li>
              <li><a href="#">멤버십/CLUB</a></li>
              <li><a href="#">CGV 극장별</a></li>
              <li><a href="#">제휴할인</a></li>
              <li><a href="#">당첨자 발표</a></li>
              <li><a href="#">종료된 이벤트</a></li>
            </ul>
          </li>

          <li>
            <h2 class="menu_name">혜택</h2>
            <ul class="menu_contents">
              <li>
                <h3>혜택</h3>
              </li>
              <li><a href="#">CGV 할인정보</a></li>
              <li><a href="#">CLUB 서비스</a></li>
              <li><a href="#">VIP 라운지</a></li>
            </ul>
          </li>
        </ul>

        <div class="search_menu">
          <input type="text" name="search_bar" id="search_bar">
          <div class="search_btn"><i class='bx bx-search-alt-2'></i></div>
        </div>
      </div>
      <div class="menu_bg"></div>
    </div>
  </header>
  <div class="inner">
    

    <!-- 콘테이너 -->
   
	 	<div class="container">
	   		<div class="inner">
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
	    </div>
	  
	  <div class="goNext">
	    <div class="inner">
	      <div class="info">
	        <div class="info movie">
	          <span class="movie_poster">
	            <!-- <img src="./image/001.jpg" alt="리멤버"> -->
	          </span>
	          <div class="row movie_title">
	            <span></span>
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
	      <div class="btn_con">
	      <a class="movieListBtn" href="${contextPath}/movieChart.jsp">영화리스트</a>
	      <a class="movieSeatBtn"><input type="button" value=">좌석선택" onclick="fn_ticket()"></a>
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