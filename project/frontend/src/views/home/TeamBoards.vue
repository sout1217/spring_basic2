<template>
  <div class="boards-section">
    <h2 class="section-title">팀 보드명</h2>
    <div class="boards d-flex align-content-start flex-wrap">
      <div class="board list-inline-item" @click="openBoard(board)">
        <h3>board name1</h3>
        <p>board description</p>
      </div>
      <div class="board list-inline-item" @click="openBoard(board)">
        <h3>board name2</h3>
        <p>board description</p>
      </div>
      <div class="board list-inline-item" @click="openBoard(board)">
        <h3>board name3</h3>
        <p>board description</p>
      </div>
      <div class="board add list-inline-item" @click="createBoard(team)">
        <!-- <font awesome /> -->
        <div>팀 보드 생성하기</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'TeamBoards',
}
</script>

<style scoped></style>
