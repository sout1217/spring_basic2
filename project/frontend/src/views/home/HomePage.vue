<template>
  <div>
    <PageHeader />

    <div class="board-container">
      <PersonalBoards />
      <TeamBoards />
      <div class="create-team-wrapper">
        <button class="btn btn-link" @click="createTeam()">
          + 팀 생성하기
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import PageHeader from '@/components/public/PageHeader'
import PersonalBoards from '@/views/home/PersonalBoards'
import TeamBoards from '@/views/home/TeamBoards'

export default {
  name: 'HomePage',
  components: { TeamBoards, PersonalBoards, PageHeader },
}
</script>
<style>
.board-container {
  padding: 0 35px;
  outline: 1px solid red;
}
.board-container h2 {
  font-size: 18px;
  margin-bottom: 15px;
  font-weight: 400;
}
.board-container .boards-section {
  outline: 1px solid orange;
}

.board-container .boards-section .boards {
  outline: 1px solid green;
}

.board-container .boards-section .boards .board {
  outline: 1px solid deepskyblue;
}

.board-container .boards-section .boards h3 {
}
.board-container .boards-section .boards p {
}

.board-container .boards-section .boards .add {
}

.board-container .create-team-wrapper .btn-link {
}
</style>
