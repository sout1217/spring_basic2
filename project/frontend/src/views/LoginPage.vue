<template>
  <div>
    <h1>LoginPage</h1>
    <form @submit.prevent="submitForm">
      <div v-show="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>
      <div>
        <label for="username"></label>
        <input id="username" type="text" v-model="form.username">
        <div class="field-error" v-if="$v.form.username.$dirty">
          <div class="error" v-if="!$v.form.username.required">require</div>
        </div>
      </div>
      <div>
        <label for="password"></label>
        <input id="password" type="text" v-model="form.password">
        <div class="field-error" v-if="$v.form.password.$dirty">
          <div class="error" v-if="!$v.form.password.required">require</div>
        </div>
      </div>
      <button type="submit">로그인</button>
    </form>
  </div>
</template>

<script>
import { required } from 'vuelidate/lib/validators'
import authenticationService from '../services/authenticate'

export default {
  name: 'LoginPage',
  validations: {
    form: {
      username: {
        required
      },
      password: {
        required
      }
    }
  },
  data () {
    return {
      form: {
        username: '',
        password: ''
      },
      errorMessage: ''
    }
  },
  methods: {
    submitForm () {
      this.$v.$touch()
      if (this.$v.$invalid) {
        return
      }
      authenticationService.authenticate(this.form).then(() => {
        this.$router.push({ name: 'HomePage' })
      }).catch((error) => {
        this.errorMessage = 'Invalid credentials'
        console.log(error.response.data)
        // todo 에러 메시지 반환하기
        // this.errorMessage = error.message
      })
    }
  }

}
</script>

<style scoped>

</style>
