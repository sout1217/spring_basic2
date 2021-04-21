<template>
  <footer class="footer">
    <span>ⓒ 스프링 부트 Vue 2</span>
    <ul class="footer-links list-inline float-right">
      <li class="list-inline-item"><a href="#">about</a></li>
      <li class="list-inline-item"><a href="#">termOfService</a></li>
      <li class="list-inline-item"><a href="#">privatePolicy</a></li>
      <li class="list-inline-item">
        <a
          href="https://github.com/taskagile/vuejs.spring-boot.mysql"
          target="_blank"
          >GitHub</a
        >
      </li>
    </ul>
  </footer>
</template>

<script>
export default {
  name: 'PageFooter',
}
</script>

<style scoped>
.footer {
  width: 100%;
  font-size: 13px;
  color: #666;
  line-height: 40px;
  border-top: 1px solid #ddd;
  margin-top: 50px;
}
.footer .list-inline-item {
  margin-right: 10px;
}
.footer a {
  color: #666;
}
</style>
