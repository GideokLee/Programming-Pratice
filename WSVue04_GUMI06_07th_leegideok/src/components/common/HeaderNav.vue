<template>
   <header>
    <div class="header">
      <nav>
        <ul>
          <li>
            <router-link to="/">Home</router-link>
          </li>
          <li>
            <router-link to="/book">도서 목록</router-link>
          </li>
          <li>
            <router-link to="/about">About</router-link>
          </li>
        </ul>
      </nav>
      <router-link to="/">
        <img src="@/assets/ssafy_logo.png" id="ssafy_logo"/>
      </router-link>
      <p class="logo">도서관리</p>
   </div>
   </header>
</template>

<style>
  nav ul{
    padding-top:30px;
  } 
  nav ul li { 
    display:inline; 
    border-left:1px solid #999; 
    font:bold 20px Dotum;  
    padding:0 10px; 
  } 

  nav ul li:first-child {
    border-left:none;
  }
</style>

