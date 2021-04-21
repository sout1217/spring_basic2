<template>
  <div class="page-header d-flex align-content-center">
    <div class="logo" @click="goHome">
      <!-- <font awesome /> -->
      <img src="/images/logo.png" alt="" />
    </div>
    <div class="boards-menu-toggle">
      <div class="dropdown">
        <button
          class="btn dropdown-toggle"
          type="button"
          id="boardsMenu"
          data-toggle="dropdown"
          aria-haspopup="true"
          aria-expanded="false"
        >
          Boards
        </button>
        <div class="dropdown-menu" aria-labelledby="boardsMenu">
          <div v-show="!hasBoards" class="dropdown-item">보드 없음</div>
          <div v-show="hasBoards">
            <h6 class="dropdown-header" v-show="personalBoards.length">
              개인 보드
            </h6>
            <button class="dropdown-item" type="button">버튼 명 1</button>
            <button class="dropdown-item" type="button">버튼 명 2</button>
            <button class="dropdown-item" type="button">버튼 명 3</button>
            <div>
              <h6 class="dropdown-header">팀 명</h6>
              <button class="dropdown-item" type="button">버튼 명</button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="search-box flex-fill">
      <div class="search-wrapper">
        <!-- <font awesome /> -->
        <input
          type="text"
          :placeholder="'검색'"
          class="form-control form-control-sm"
        />
      </div>
    </div>

    <div class="profile-menu-toggle">
      <div class="dropdown">
        <button
          class="btn dropdown-toggle"
          type="button"
          id="profileMenu"
          data-toggle="dropdown"
          aria-haspopup="true"
          aria-expanded="false"
        >
          프로필
        </button>
        <div class="dropdown-menu" aria-labelledby="profileMenu">
          <button class="dropdown-item" type="button">프로필</button>
          <button class="dropdown-item" type="button">로그아웃</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// bootstrap 은 config 에서 css 만 잡았으나 js 는 잡지 않았기 때문에 import 를 해주어야 한다
import 'bootstrap/dist/js/bootstrap.min'

export default {
  name: 'PageHeader',
  data() {
    return {
      personalBoards: [{}, {}, {}],
    }
  },
  computed: {
    hasBoards() {
      return true
    },
  },
  methods: {
    goHome() {
      this.$router.push({ name: 'HomePage' })
    },
  },
}
</script>

<style scoped>
.page-header {
}

.page-header .logo {
  color: #444;
  height: 25px;
  width: 115px;
  margin-top: 2px;
  cursor: pointer;
}

.page-header .home-icon {
}

.page-header img {
  margin-left: 5px;
  margin-top: 6px;
  width: 80px;
}
.page-header .boards-menu-toggle {
  padding-left: 20px;
  width: 100px;
}

.page-header .profile-menu-toggle {
  width: 215px;
  text-align: right;
}

.page-header .search-box .search-wrapper {
  width: 300px;
  margin: 0 auto;
  position: relative;
}
.page-header .search-box .search-icon {
  position: absolute;
  top: 8px;
  left: 8px;
  color: #666;
}

.page-header .search-box input {
  padding-left: 30px;
  height: calc(1.8125rem + 5px);
  font-size: 1rem;
  border: 1px solid #eee;
  border-radius: 5px;
}

.dropdown-toggle {
  padding: 2px 5px !important;
}
</style>
