<template>
  <div id="app">
    <nav>
      <div>
        <img alt="SSAFY logo" src="@/assets/ssafy.png" />
      </div>
      <router-link to="/">홈</router-link> |
      <router-link to="/board/write">글 등록</router-link> |
      <router-link to="/board">글 목록</router-link>
    </nav>
    <router-view/>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
