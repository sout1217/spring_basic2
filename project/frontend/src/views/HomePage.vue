<template>
    <div id="home-page">
      <button @click="getAccounts">요청하기</button>
    </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'HomePage',
  methods: {
    getAccounts () {
      axios.get('/accounts').then(data => {
        console.log(data)
      }).catch(error => {
        console.log(error)
      })
    }
  }
}
</script>

<style scoped>

</style>
