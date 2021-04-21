<template>
  <div class="boards-section">
    <h2 class="section-title">
      개인 보드
    </h2>

    <div class="boards d-flex flex-wrap align-content-center">
      <div class="board list-inline-item">
        <h3>board name1</h3>
        <p>board description</p>
      </div>
      <div class="board list-inline-item">
        <h3>board name2</h3>
        <p>board description</p>
      </div>
      <div class="board list-inline-item">
        <h3>board name3</h3>
        <p>board description</p>
      </div>

      <div class="board add list-inline-item" @click="createBoard">
        <!-- <font awesome /> -->
        <div>보드 생성하기</div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'BoardsSection',
}
</script>
