<template>
  <div class="container public">
    <div class="row justify-content-center">
      <div class="form">
        <Logo />
        <form @submit.prevent="submitForm">
          <div v-show="errorMessage" class="alert alert-danger failed">
            {{ errorMessage }}
          </div>
          <div class="form-group">
            <label for="username">USERNAME</label>
            <input
              class="form-control"
              id="username"
              type="text"
              v-model="form.username"
            />
            <div class="field-error" v-if="$v.form.username.$dirty">
              <div class="error" v-if="!$v.form.username.required">
                require
              </div>
            </div>
          </div>
          <div class="form-group">
            <label for="password">PASSWORD</label>
            <input
              class="form-control"
              id="password"
              type="text"
              v-model="form.password"
            />
            <div class="field-error" v-if="$v.form.password.$dirty">
              <div class="error" v-if="!$v.form.password.required">require</div>
            </div>
          </div>
          <button class="btn btn-primary btn-block" type="submit">
            로그인
          </button>
          <div class="links">
            <p class="sign-up text-muted">
              아직 계정이 없습니다
              <router-link to="register" class="link-sign-up">
                가입하기
              </router-link>
            </p>
            <p class="sign-up text-muted">
              비밀번호를 잊었습니다
              <router-link to="#" class="link-sign-up">
                비밀번호 찾기
              </router-link>
            </p>
          </div>
        </form>
      </div>
    </div>
    <PageFooter />
  </div>
</template>

<script>
import { required } from 'vuelidate/lib/validators'
import authenticationService from '../../services/authenticate'
import Logo from '@/components/public/Logo'
import PageFooter from '@/components/public/PageFooter'

export default {
  name: 'LoginPage',
  components: { PageFooter, Logo },
  validations: {
    form: {
      username: {
        required,
      },
      password: {
        required,
      },
    },
  },
  data() {
    return {
      form: {
        username: '',
        password: '',
      },
      errorMessage: '',
    }
  },
  methods: {
    submitForm() {
      this.$v.$touch()
      if (this.$v.$invalid) {
        return
      }
      authenticationService
        .authenticate(this.form)
        .then(() => {
          this.$router.push({ name: 'HomePage' })
        })
        .catch(error => {
          this.errorMessage = 'Invalid credentials'
          console.log(error.response.data)
          // todo 에러 메시지 반환하기
          // this.errorMessage = error.message
        })
    },
  },
}
</script>

<style scoped>
.links {
  margin: 30px 0 50px 0;
  text-align: center;
}
</style>
