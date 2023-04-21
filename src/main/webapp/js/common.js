let head=document.querySelector('header');
let con=document.querySelector('.container');
const toTopEl = document.querySelector('#to-top'); 


window.addEventListener('scroll',_.throttle(()=>{
  if(window.scrollY>110) {
    head.classList.add('red');
    con.classList.add('red');
    //gsap.to(요소, 지속시간, 옵션)
    //버튼 보이기
    gsap.to(toTopEl, .2, {
      x : 0
    })
  } else {
    head.classList.remove('red');
    con.classList.remove('red');
    //버튼 숨기기
    gsap.to(toTopEl, .2, {
      x : 400
    })
  }
},300))

toTopEl.addEventListener('click', ()=>{
  gsap.to(window, .7, {
    scrollTo : 0
  })
})
