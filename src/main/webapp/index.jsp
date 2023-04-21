<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>메인페이지</title>
  <!-- CSS LINK -->
  <link rel="stylesheet" href="${contextPath}/css/icommon.css">
  <link rel="stylesheet" href="${contextPath}/css/mainpage.css">
  <!--공통영역 CSS-->

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
  <script src="${contextPath}/js/main.js" defer></script>
  <!-- 유튜브 링크 -->
  <script defer src="./js/youtube.js"></script>

  <!-- SWIPER CDN (슬라이더)-->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.css" />
  <script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>

  <title>영화의 시작 CGV</title>
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

   <!-- CONTAINER -->
  <div class="container">
    <div class="youtubebox">
      <div class="inner">
        <div id="player" class="youtubeplayer"></div>
        <div class="textBox">
          <h2 class="title">리멤버</h2>
          <div class="movie_info">
            "이 일은 아주 오래전부터 계획되었습니다"<br>
            기억이 사라지기 전, 복수를 끝내야 한다!
          </div>
          <div class="btn_box">
            <div class="more_btn">
              <a href="#">상세보기 ></a>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="moviechart">
      <div class="inner">
        <div class="movieChartBeScreen_btn_wrap">
          <div class="movieChartBeScreen">
            <div class="movieChart"><a href="${contextPath}/movie/movieChart.do">
                무비차트
              </a></div>
            <div class="beScreen"><a href="#">
                상영예정작
              </a></div>
          </div>

          <div class="btn_wrap">
            <a href="${contextPath}/movie/movieChart.do">전체보기 ></a>
          </div>
        </div>

        <div class="movie_slide">
          <div class="movie_01 movie_con">
            <div class="movie_img">
              <div class="btn_con">
                <a href="${contextPath}/movie/remember.do">상세보기</a>
                <a href="${contextPath}/movie/ticket.do">예매하기</a>
              </div>
              <div class="movie_num">1</div>
            </div>
            <div class="movie_title">리멤버</div>
          </div>
          <div class="movie_01 movie_con">
            <div class="movie_img">
              <div class="btn_con">
                <a href="${contextPath}/movie/confession.do">상세보기</a>
                <a href="${contextPath}/movie/ticket.do">예매하기</a>
              </div>
              <div class="movie_num">2</div>
            </div>
            <div class="movie_title">자백</div>
          </div>
          <div class="movie_01 movie_con">
            <div class="movie_img">
              <div class="btn_con">
                <a href="${contextPath}/img/blackadam.jpg">상세보기</a>
                <a href="${contextPath}/movie/ticket.do">예매하기</a>
              </div>
              <div class="movie_num">3</div>
            </div>
            <div class="movie_title">블랙 마담</div>
          </div>
          <div class="movie_01 movie_con">
            <div class="movie_img">
              <div class="btn_con">
                <a href="${contextPath}/img/evry.jpg">상세보기</a>
                <a href="${contextPath}/movie/ticket.do">예매하기</a>
              </div>
              <div class="movie_num">4</div>
            </div>
            <div class="movie_title">에르비씽 에브리웨어 올 앳 원스</div>
          </div>
          <div class="movie_01 movie_con">
            <div class="movie_img">
              <div class="btn_con">
                <a href="${contextPath}/img/highpass.jpg">상세보기</a>
                <a href="${contextPath}/movie/ticket.do">예매하기</a>
              </div>
              <div class="movie_num">5</div>
            </div>
            <div class="movie_title">고속도로 가족</div>
          </div>
        </div>
      </div>
    </div>

    <div class="event">
      <div class="inner">
        <div class="iventScrint_btn_wrap">
          <div class="eventTitle">EVENT</div>
          <div class="btn_con">
            <a href="#">전체보기 ></a>
          </div>
        </div>
        <!-- Swiper -->
        <div class="swiper eventSwiper">
          <div class="swiper-wrapper">
            <div class="swiper-slide">
              <div class="img_box img1"></div>
              <div class="event_text">
                <div class="event_title">쉽고 많은 혜택, [CGV VIP 가보자고!]</div>
                <div class="event_day">2022.11.17~2022.12.31</div>
              </div>
            </div>
            <div class="swiper-slide">
              <div class="img_box img2"></div>
              <div class="event_text">
                <div class="event_title">카타르월드컵 스페셜이벤트</div>
                <div class="event_day">2022.11.15~2022.12.31</div>
              </div>
            </div>
            <div class="swiper-slide">
              <div class="img_box img3"></div>
              <div class="event_text">
                <div class="event_title">[올빼미]골든에그지수 맞히기</div>
                <div class="event_day">2022.11.23~2022.12.06</div>
              </div>
            </div>
            <div class="swiper-slide">
              <div class="img_box img4"></div>
              <div class="event_text">
                <div class="event_title">[원피스 레드 필름] 필름마크</div>
                <div class="event_day">2022.11.23~2022.12.06</div>
              </div>
            </div>
            <div class="swiper-slide">
              <div class="img_box img5"></div>
              <div class="event_text">
                <div class="event_title">축구 굿즈 사고 뜨겁게 응원하자★</div>
                <div class="event_day">2022.11.24~2022.12.03</div>
              </div>
            </div>
          </div>
          <div class="swiper-pagination"></div>
        </div>
      </div>
    </div>
    <div class="gift_card">
      <div class="inner">
        <div class="movie_voucher">
          <div class="container">
            <div class="title_btn_con">
              <div class="title">영화관람권</div>
              <div class="btn"><a href="#">더보기</a></div>
            </div>
            <div class="item item1">
              <div class="img_box"></div>
              <div class="text_box">
                <div class="title">CGV 영화관람권</div>
                <div class="price">12,000원</div>
              </div>
            </div>
            <div class="item item2">
              <div class="img_box"></div>
              <div class="text_box">
                <div class="title">CGV 골드클래스</div>
                <div class="price">40,000원</div>
              </div>

            </div>
            <div class="item item3">
              <div class="img_box"></div>
              <div class="text_box">
                <div class="title">4DX관람권</div>
                <div class="price">19,000원</div>
              </div>

            </div>
          </div>
        </div>
        <div class="gift_card">
          <div class="container">
            <div class="title_btn_con">
              <div class="title">기프트카드</div>
              <div class="btn"><a href="#">더보기</a></div>
            </div>
            <div class="item item1">
              <div class="img_box"></div>
              <div class="text_box">
                <div class="title">PACONNIE A형</div>
                <div class="price">금액충전형</div>
              </div>
            </div>
            <div class="item item2">
              <div class="img_box"></div>
              <div class="text_box">
                <div class="title">PACONNIE B형</div>
                <div class="price">금액충전형</div>
              </div>

            </div>
            <div class="item item3">
              <div class="img_box"></div>
              <div class="text_box">
                <div class="title">PACONNIE C형</div>
                <div class="price">금액충전형</div>
              </div>

            </div>
          </div>
        </div>
        <div class="drink">
          <div class="container">
            <div class="title_btn_con">
              <div class="title">음료</div>
              <div class="btn"><a href="#">더보기</a></div>
            </div>
            <div class="item item1">
              <div class="img_box"></div>
              <div class="text_box">
                <div class="title">탄산음료(M)</div>
                <div class="price">2,500원</div>
              </div>
            </div>
            <div class="item item2">
              <div class="img_box"></div>
              <div class="text_box">
                <div class="title">아메리카토(HOT)</div>
                <div class="price">3,500원</div>
              </div>

            </div>
            <div class="item item3">
              <div class="img_box"></div>
              <div class="text_box">
                <div class="title">아메리카토(ICE)</div>
                <div class="price">4,000원</div>
              </div>

            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 공지사항 및 QNA + 이벤트 -->
    <div class="notice_QNA">
      <div class="inner">
        <div class="notice_QNA_APP_wrap">
          <div class="notice_QNA">
            <div class="notice">
              <div class="title">공지사항</div>
              <div class="dec">[기타]22년 VIP 선정 기준 변경</div>
              <div class="btn">
                <a href="${contextPath}/NoticeListCon.do">더보기</a>
              </div>
            </div>
            <div class="QNA">
              <div class="title">고객센터</div>
              <div class="dec">
                <p>1544-1122</p>
                고객센터 운영시간 (평일 09:00~18:00)<br>
                업무시간 외 자동응답 안내 가능합니다.
              </div>
            </div>
            <div class="btn">
              <a href="#">FAQ</a>
              <a href="${contextPath}/QNAListController.do">1:1문의</a>
              <a href="#">대관/단체 문의</a>
            </div>
          </div>
          <div class="APP">
            <div class="title">앱 다운로드</div>
            <div class="dec">cgv앱에서 더 편리하게 이용하세요</div>
            <div class="QR">
              <img src="${contextPath}/img/main_QR.gif" alt="QR코드">
            </div>
            <div class="dec">QR코드를 스캔하고<br> 앱설치 페이지로 바로 이동하세요</div>
          </div>
        </div>
        <div class="notice_slide">
          <!-- Swiper -->
    <div class="swiper noticeSwiper">
      <div class="swiper-wrapper">
        <div class="swiper-slide">
          <img src="${contextPath}/img/noticeimg01.png" />
        </div>
        <div class="swiper-slide">
          <img src="${contextPath}/img/noticeimg02.png" />
        </div>
      </div>
      <div class="swiper-pagination"></div>
    </div>
        </div>
      </div>
    </div>
  </div>

  <div class="uricard">
    <div class="inner">
      <img src="${contextPath}/img/uricard.png" alt="우리카드">
    </div>
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
  <div id="to-ticket">
    <a href="${contextPath}/movie/ticket.do">예매하기</a>
  </div>
</body>

</html>