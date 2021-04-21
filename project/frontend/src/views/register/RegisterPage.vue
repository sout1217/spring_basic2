<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="register-form">
        <Logo />
        <form @submit.prevent="submitForm">
          <div v-show="errorMessage" class="alert alert-danger failed">
            {{ errorMessage }}
          </div>

          <div class="from-group">
            <label for="username">USERNAME</label>
            <input
              id="username"
              class="form-control"
              type="text"
              v-model="form.username"
            />
          </div>
          <div class="field-error" v-if="$v.form.username.$dirty">
            <div class="error" v-if="!$v.form.username.required">required</div>
            <div class="error" v-if="!$v.form.username.alphaNum">alphaNum</div>
            <div class="error" v-if="!$v.form.username.minLength">
              minLength
            </div>
            <div class="error" v-if="!$v.form.username.maxLength">
              maxLength
            </div>
          </div>

          <div class="from-group">
            <label for="emailAddress">EMAIL ADDRESS</label>
            <input
              id="emailAddress"
              class="form-control"
              type="text"
              v-model="form.emailAddress"
            />
            <div class="field-error" v-if="$v.form.emailAddress.$dirty">
              <div class="error" v-if="!$v.form.emailAddress.required">
                required
              </div>
              <div class="error" v-if="!$v.form.emailAddress.email">email</div>
              <div class="error" v-if="!$v.form.emailAddress.maxLength">
                maxLength
              </div>
            </div>
          </div>

          <div class="from-group">
            <label for="password">PASSWORD</label>
            <input
              id="password"
              class="form-control"
              type="text"
              v-model="form.password"
            />
            <div class="field-error" v-if="$v.form.password.$dirty">
              <div class="error" v-if="!$v.form.password.required">
                required
              </div>
              <div class="error" v-if="!$v.form.password.minLength">
                minLength
              </div>
              <div class="error" v-if="!$v.form.password.maxLength">
                maxLength
              </div>
            </div>
          </div>

          <button type="submit" class="btn btn-primary btn-block mt-4">
            SignUp
          </button>

          <p class="accept-terms text-muted">
            <!-- <i18n /> -->
            <span class="accept-terms text-muted">
              <a href="#" place="termsOfService">termsOfService </a>
              <a href="#" place="privacyPolicy">privacyPolicy</a>
            </span>
          </p>
          <p class="text-center text-muted">
            이미 계정을 가지고 있습니다
            <router-link to="login">가입하기</router-link>
          </p>
        </form>
      </div>
    </div>
    <PageFooter />
  </div>
</template>

<script>
import registrationService from '../../services/registration'
import {
  required,
  email,
  minLength,
  maxLength,
  alphaNum,
} from 'vuelidate/lib/validators'
import Logo from '@/components/public/Logo'
import PageFooter from '@/components/public/PageFooter'

export default {
  name: 'RegisterPage',
  components: { PageFooter, Logo },
  validations: {
    form: {
      username: {
        required,
        minLength: minLength(2),
        maxLength: maxLength(50),
        alphaNum,
      },
      emailAddress: {
        required,
        email,
        maxLength: maxLength(100),
      },
      password: {
        required,
        minLength: minLength(6),
        maxLength: maxLength(30),
      },
    },
  },
  data() {
    return {
      form: {
        username: '',
        emailAddress: '',
        password: '',
      },
      errorMessage: '',
    }
  },
  methods: {
    submitForm() {
      this.$v.$touch()
      if (this.$v.$invalid) {
        console.log('유효성 검사 실패')
        return
      }

      registrationService
        .register(this.form)
        .then(() => {
          this.$router.push({ name: 'LoginPage' })
        })
        .catch(error => {
          this.errorMessage =
            'Failed to register user ' +
            (error.message ? error.message : 'unknown')
        })
    },
  },
}
</script>

<style scoped>
.container {
  max-width: 900px;
}
.register-form {
  margin-top: 50px;
  max-width: 320px;
}
.accept-terms {
  margin: 20px 0 40px 0;
}
</style>
