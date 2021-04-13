<template>
    <div class="container">
      <div class="row justify-content-center">
        <div class="register-form">
          <div class="logo-wrapper">
          </div>
          <form @submit.prevent="submitForm">
            <div v-show="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>
            <div class="from-group">
              <input id="username" type="text" v-model="form.username">
            </div>
            <div class="from-group">
              <input id="emailAddress" type="text" v-model="form.emailAddress">
            </div>
            <div class="from-group">
              <input id="password" type="text" v-model="form.password">
            </div>
            <button type="submit" class="btn btn-primary btn-block">SignUp</button>
          </form>
        </div>
      </div>
      <footer class="footer">
        <span class="copyright"></span>
        <ul class="footer-links list-inline float-right">
          <li>1</li>
          <li>2</li>
          <li>3</li>
        </ul>
      </footer>
    </div>
</template>

<script>
import registrationService from '../services/registration'
import { required, email, minLength, maxLength, alphaNum } from 'vuelidate/lib/validators'

export default {
  name: 'RegisterPage',
  validations: {
    form: {
      username: {
        required,
        minLength: minLength(2),
        maxLength: maxLength(50),
        alphaNum
      },
      emailAddress: {
        required,
        email,
        maxLength: maxLength(100)
      },
      password: {
        required,
        minLength: minLength(6),
        maxLength: maxLength(30)
      }
    }
  },
  data () {
    return {
      form: {
        username: '',
        emailAddress: '',
        password: ''
      },
      errorMessage: ''
    }
  },
  methods: {
    submitForm () {
      this.$v.$touch()
      if (this.$v.$invalid) {
        console.log('유효성 검사 실패')
        return
      }

      registrationService.register(this.form).then(() => {
        this.$router.push({ name: 'LoginPage' })
      }).catch(error => {
        this.errorMessage = 'Failed to register user ' + (error.message ? error.message : 'unknown')
      })
    }
  }
}
</script>

<style scoped>
  .container {
    max-width: 900px;
    outline: 1px solid red;
  }
  .register-form {
    margin-top: 50px;
    max-width: 320px;
    outline: 1px solid blue;
  }
  .logo-wrapper {
    margin-bottom: 40px;
    outline: 1px solid green
  }
  .footer {
    width: 100%;
    line-height: 40px;
    margin-top: 50px;
  }
</style>
