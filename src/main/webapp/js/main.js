//이벤트 Swiper
var swiper = new Swiper(".eventSwiper", {
  slidesPerView: 3,
  spaceBetween: 30,
  autoplay:{
    delay:2500,
    disableOnlnteraction:false
  },
  loop:true,
  freeMode: true,
  pagination: {
    el: ".swiper-pagination",
    clickable: true,
  },
  navigation: {   // 버튼
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev",
  },
  resistance:false,
});

//공지사항 Swiper
var swiper = new Swiper(".noticeSwiper", {
  effect: "cube",
  grabCursor: true,
  cubeEffect: {
    shadow: true,
    slideShadows: true,
    shadowOffset: 20,
    shadowScale: 0.94,
  },
  autoplay:{
    delay:2500,
    disableOnlnteraction:false
  },
});


let toTicket=document.querySelector('#to-ticket')


window.addEventListener('scroll',_.throttle(()=>{
  if(window.scrollY>110) {
    //gsap.to(요소, 지속시간, 옵션)
    //버튼 보이기
    gsap.to(toTicket, .2, {
      x : 0
    })
  } else {
    //버튼 숨기기
    gsap.to(toTicket, .2, {
      x : 400
    })
  }
},300))