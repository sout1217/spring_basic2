<template>
  <div class="boards-section">
    <h2 class="section-title">
      개인 보드
    </h2>

    <div class="boards d-flex flex-wrap align-content-center">
      <div
        v-for="board in personalBoards"
        :key="board.id"
        class="board list-inline-item"
      >
        <h3>{{ board.name }}</h3>
        <p>{{ board.description }}</p>
      </div>

      <div class="board add list-inline-item" @click="createBoard">
        <!-- <font awesome /> -->
        <div>보드 생성하기</div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapGetters } from 'vuex'

export default {
  name: 'BoardsSection',
  computed: {
    ...mapGetters(['personalBoards']),
  },
  methods: {
    createBoard() {
      console.log('PersonalBoard createBoard')
      this.$emit('createBoard')
    },
  },
}
</script>
