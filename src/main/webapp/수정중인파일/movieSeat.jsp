<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
	request.setCharacterEncoding("utf-8");
	String mvTitle = request.getParameter("mvTitle");
	String theater = request.getParameter("theater");
	String showingDate = request.getParameter("showingDate");
	String showingTime = request.getParameter("showingTime");
	String seatNum = request.getParameter("seatNum");
    Object ID_get=session.getAttribute("id");
%>


<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" defer>
window.onload = function(){
	let list = new Array();

	<c:forEach var="seat" items="${seatInfo}" > //배열에 seatNum 배열값 추가하기
		list.push("${seat.seatNum}"); 
	</c:forEach>
	let str = list.toString(); // 추가된 seatNum 배열을 합쳐 문자값으로 변환
	console.log(str);

	let checkSeat = str.split(','); // seatNum 배열을 ,로 구분하여 셋팅
	let tbs = document.querySelectorAll('td'); 

	console.log(checkSeat.length);
  for(i=0; i<checkSeat.length; i++){
    console.log(checkSeat[i])
  }

	for(let i=0; i<checkSeat.length; i++){ //예매된 좌석번호
		for(let j=0; j<tbs.length; j++){ //전체 좌석번호
			if(tbs[j].innerText == checkSeat[i]){ //전체 좌석번호와 예매된 좌석번호 가 맞을시 해당 좌석번호의 클래스 select로 변경
				tbs[j].className='select';
				console.log("예매된 좌석"+checkSeat[i]+"전체좌석"+tbs[j]+"맞음");
			}else {
				console.log("예매된 좌석"+checkSeat[i]+"전체좌석"+tbs[j]+"틀림");
			}
		}
	}	
}	
</script>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>예매하기</title>
<script type="text/javascript" src="${contextPath}/js/jquery-3.6.0.min.js"></script>
  <link rel="stylesheet" href="${contextPath}/css/movieSeat.css">
  <link rel="stylesheet" href="${contextPath}/css/icommon.css">
  <link rel="stylesheet" href="${contextPath}/css/ticket.css">

  <!-- BOXICONE https://boxicons.com/ 사이트에서 이모티콘 가져올 수 있음-->
  <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>

  <!-- LODASH CDN -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.21/lodash.min.js"
    integrity="sha512-WFN04846sdKMIP5LKNphMaWzU7YpMyCU245etK3g/2ARYbPK9Ub18eG+ljU96qKRCWh+quCY7yefSmlkQw1ANQ=="
    crossorigin="anonymous"></script>
  <!-- GSAP CDN (javascript로 애니매이션 효과)-->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.9.1/gsap.min.js"
    integrity="sha512-H6cPm97FAsgIKmlBA4s774vqoN24V5gSQL4yBTDOY2su2DeXZVhQPxFK4P6GPdnZqM9fg1G3cMv5wD7e6cFLZQ=="
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.9.1/ScrollToPlugin.min.js"
    integrity="sha512-agNfXmEo6F+qcj3WGryaRvl9X9wLMQORbTt5ACS9YVqzKDMzhRxY+xjgO45HCLm61OwHWR1Oblp4QSw/SGh9SA=="
    crossorigin="anonymous"></script>

  <!-- SCROLL MAGIC CDN -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/ScrollMagic/2.0.8/ScrollMagic.min.js"
    integrity="sha512-8E3KZoPoZCD+1dgfqhPbejQBnQfBXe8FuwL4z/c8sTrgeDMFEnoyTlH3obB4/fV+6Sg0a0XF+L/6xS4Xx1fUEg=="
    crossorigin="anonymous"></script>

  <!-- JAVA SCRIPT 연결 -->
  <script src="${contextPath}/js/common.js" defer></script>
  <script src="${contextPath}/js/members.js" defer></script>
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script src="${contextPath}/js/movieSeat.js" defer></script>

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
            <a href="${contextPath}/NoticeListCon.do">
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

  <!-- CONTAINER -->
  <div class="container">
    <div class="book_info">
      <div class="movie_info">
        <span class="movie_title"><%=mvTitle%></span>
      </div>
      <div class="time_info">
        <span>일시 : </span>
        <span class="day"><%=showingDate%></span><br>
        <span>시간 : </span>
        <span class="time"><%=showingTime%></span>
      </div>
    </div>
    <div class="Count">
      <span>인원수 선택</span>
      <div class="Count_con">
        <div class="adult">
          <label for="AdultCount">성인</label>
          <input type="number" name="AdultCount" id="AdultCount" max="3" min="0" value="0">
        </div>
        <div class="children">
          <label for="childrenCount">어린이</label>
          <input type="number" name="childrenCount" id="childrenCount" max="2" min="0" value="0">
        </div>
      </div>
      
    </div>

    <div class="seatTitle">
      좌석선택  
    </div>
    
    <table class="seat">
      <th colspan="5"><span>스크린</span></th>
      <tr>
        <td class="null">A1</td>
        <td class="null">A2</td>
        <td class="null">A3</td>
        <td class="null">A4</td>
        <td class="null">A5</td>
      </tr>
      <tr>
        <td class="null">B1</td>
        <td class="null">B2</td>
        <td class="null">B3</td>
        <td class="null">B4</td>
        <td class="null">B5</td>
      </tr>
      <tr>
        <td class="null">C1</td>
        <td class="null">C2</td>
        <td class="null">C3</td>
        <td class="null">C4</td>
        <td class="null">C5</td>
      </tr>
    </table>
  </div>

  <div class="btn_con">
    <div class="pageMoveBtn"><a href="${contextPath}/movie/movieChart.do">영화리스트</a></div>
    <div class="pageMoveBtn payment">결제하기</div>
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
          <span><%=mvTitle%></span>
        </div>
      </div>
      <div class="target info">
        <div class="row date">
          <span>일시</span>
          <span class="date"><%=showingDate%></span>
          <span class="time"><%=showingTime%></span>
        </div>
        <div class="row screen">
          <span><%=theater%></span>
          <span class="screen"></span>
        </div>
      </div>
      <div class="row colspan">
        <span class="path_step1">>좌석선택</span>
        <span class="path_step2">>결제</span>
      </div>
    </div>


    <form action="ticketComp.do">
	  <input type="hidden" name="id" id="id" value="<%=ID_get %>">
      <input type="hidden" name="mvTitle" id="movie_title" value="<%=mvTitle%>">
      <input type="hidden" name="theater" id="screen" value="<%=theater%>">
      <input type="hidden" name="showingDate" id="date" value="<%=showingDate%>">
      <input type="hidden" name="showingTime" id="time" value="<%=showingTime%>">
      <input type="hidden" name="seatNum" id="seatNum">
      <input type="hidden" name="bookNum" id="bookNum">
      <input type="button" id="seat_btn" value="좌석선택">
    </form>
  </div>

  <!-- FOOTER -->
  <footer>
    <div class="policy_list">
      <div class="inner">
        <ul>
          <li><a href="#">회사소개</a></li>
          <li><a href="#">IR</a></li>
          <li><a href="#">채용정보</a></li>
          <li><a href="#">광고/제휴/출점문의</a></li>
          <li><a href="#">이용약관</a></li>
          <li><a href="#">편성기준</a></li>
          <li><a href="#">개인정보처리방침</a></li>
          <li><a href="#">법적고지</a></li>
          <li><a href="#">이메일주소무단수집거부</a></li>
          <li><a href="#">윤리경영</a></li>
          <li><a href="#">사이버감사실</a></li>
        </ul>
      </div>
    </div>
    <div class="company_info_wrap">
      <div class="inner">
        <div class="company_info">
          <p>(04377)서울특별시 용산구 한강대로 23길 55, 아피카므로 6층(한강로동)</p>
          <p>대표이사 : 오남석 · 사업자등록번호 : 123-45-678910 · 통신판매업신고번호 : 2017-서울용산-0662 <a href="#">사업자정보확인</a></p>
          <p>호스팅사업자 : CJ올리브네트웍스 · 개인정보보호 책임자 : 심준범 · 대표이메일 : abcdefg@cj.net</p>
          <p>ⓒ CJ CGV. All Rights Reserved</p>
        </div>
      </div>
    </div>
  </footer>

  <div id="to-top">
    <i class='bx bx-up-arrow-alt'></i>
  </div>
  
</body>
</html>
</head>
<body>

</body>
</html>