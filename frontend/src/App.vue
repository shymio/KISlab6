<template>
  <div id="app">
    <nav class="navbar">
      <div class="nav-container">
        <router-link to="/" class="nav-title">Управление Деканатами</router-link>
      </div>
    </nav>

    <main class="main-content">
      <router-view></router-view>
    </main>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

#app {
  min-height: 100vh;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  background: linear-gradient(145deg, #f0f4ff, #e0f7fa);
}

.navbar {
  background: linear-gradient(135deg, #6a11cb, #2575fc);
  color: white;
  padding: 1rem 0;
  box-shadow: 0 6px 20px rgba(0,0,0,0.15);
}

.nav-container {
  max-width: 1400px;
  margin: 0 auto;
  padding: 0 20px;
  display: flex;
  justify-content: flex-end;
  align-items: center;
}

.nav-title {
  font-size: 1.8rem;
  font-weight: 700;
  color: #ffffff;
  text-decoration: none;
  cursor: pointer;
  transition: transform 0.2s, text-shadow 0.2s;
}

.nav-title:hover {
  transform: translateY(-2px);
  text-shadow: 0 4px 10px rgba(0,0,0,0.3);
}

.main-content {
  min-height: calc(100vh - 70px);
  padding: 20px;
}
</style>
