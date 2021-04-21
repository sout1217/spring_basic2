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
}
.board-container h2 {
  font-size: 18px;
  margin-bottom: 15px;
  font-weight: 400;
}
.board-container .boards-section {
}

.board-container .boards-section .boards {
  margin: 30px 10px;
}

.board-container .boards-section .boards .board {
  width: 270px;
  height: 110px;
  border-right: 5px;
  background-color: #377ef6;
  color: #fff;
  padding: 15px;
  margin-right: 10px;
  margin-bottom: 10px;
  cursor: pointer;
}

.board-container .boards-section .boards h3 {
  font-size: 16px;
}
.board-container .boards-section .boards p {
  line-height: 1.2;
  font-size: 90%;
  font-weight: 100;
  color: rgba(255, 255, 255, 0.7);
}

.board-container .boards-section .boards .add {
  background-color: #f4f4f4;
  color: #666;
  text-align: center;
  padding-top: 30px;
  font-weight: 400;
}

.board-container .create-team-wrapper .btn-link {
  color: #666;
  text-decoration: underline;
}
</style>
