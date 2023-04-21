// 맨처음 ul
let movie_tbl=document.querySelector('.movie_tbl');
let day_tbl=document.querySelector('.day_tbl');
let screen_tbl=document.querySelector('.screen_tbl');
let time_tbl=document.querySelector('.time_tbl');

// 검은배경 내부 text
let row_info=document.querySelector('.info');
let in_movieIMG=row_info.querySelector('.movie_poster');
let in_movieTitle=row_info.querySelector('.movie_title').children[0];
let in_date=row_info.querySelector('span.date');
let in_time=row_info.querySelector('span.time');
let in_screen=row_info.querySelector('span.screen');

// input hidden
let in_mo_hi=document.querySelector('#movie_title');
let in_da_hi=document.querySelector('#date');
let in_ti_hi=document.querySelector('#time');
let in_sc_hi=document.querySelector('#screen');

let movie_list=movie_tbl.children[1].querySelectorAll('li');
let day_list=day_tbl.children[1].querySelectorAll('li');
let time_list=time_tbl.children[1].querySelectorAll('li');
let screen_list=screen_tbl.children[1].querySelectorAll('li');




movie_list.forEach((movie)=>{
  movie.addEventListener('click',()=>{
    movie_list.forEach((v)=>{
      v.classList.remove('target');
    })
    movie.classList.add('target');
    in_mo_hi.value=movie.innerText;
    in_movieTitle.innerHTML=movie.innerText;
    in_movieIMG.innerHTML=`<img src=\"../img/${movie.innerText}.jpg\" alt=\"${movie.innerText}\">`;
  })
})

day_list.forEach((day)=>{
  day.addEventListener('click',()=>{
    day_list.forEach((v)=>{
      v.classList.remove('target')
    })
    day.classList.add('target');
    in_da_hi.value=day.innerText;
    in_date.innerHTML=day.innerText;
  })
})

time_list.forEach((time)=>{
  time.addEventListener('click',()=>{
    time_list.forEach((v)=>{
      v.classList.remove('target')
    })
    time.classList.add('target');
    in_ti_hi.value=time.innerText;
    in_time.innerHTML=time.innerText;
  })
})

screen_list.forEach((screen)=>{
  screen.addEventListener('click',()=>{
    screen_list.forEach((v)=>{
      v.classList.remove('target')
    })
    screen.classList.toggle('target');
    in_sc_hi.value=screen.innerText;
    in_screen.innerHTML=screen.innerText;
  })
})


