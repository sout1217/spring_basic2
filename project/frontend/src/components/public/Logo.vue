<template>
  <div class="logo-wrapper">
    <img class="logo" src="/images/logo.png" alt="" />
    <div class="tagline">tagline</div>
  </div>
</template>

<script>
export default {
  name: 'Logo',
}
</script>

<style scoped>
.logo-wrapper {
  text-align: center;
  margin-bottom: 40px;
}

.logo-wrapper .tagline {
  line-height: 100%;
  color: #666;
}

.logo-wrapper .logo {
  max-width: 150px;
  margin: 0 auto;
}
</style>
