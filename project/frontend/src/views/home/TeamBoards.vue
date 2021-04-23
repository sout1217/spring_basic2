<template>
  <!-- 바로 v-for 문 쓰면 오류 발생하마 -->
  <div>
    <div v-for="team in teamBoards" :key="'t' + team.id" class="boards-section">
      <h2 class="section-title">{{ team.name }}</h2>
      <div class="boards d-flex align-content-start flex-wrap">
        <div
          v-for="board in team.boards"
          :key="board.id"
          class="board list-inline-item"
          @click="openBoard(board)"
        >
          <h3>{{ board.name }}</h3>
          <p>{{ board.description }}</p>
        </div>
        <div class="board add list-inline-item" @click="createBoard(team)">
          <!-- <font awesome /> -->
          <div>팀 보드 생성하기</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'TeamBoards',
  computed: {
    ...mapGetters(['teamBoards']),
  },
  methods: {
    openBoard(board) {
      console.log(board)
    },
    createBoard(team) {
      console.log('TeamBoard createBoard')
      console.log(team)
      this.$emit('createBoard', team)
    },
  },
}
</script>

<style scoped></style>
