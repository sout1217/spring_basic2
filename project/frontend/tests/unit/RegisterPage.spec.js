import { shallowMount, createLocalVue } from '@vue/test-utils'
import RegisterPage from '../../src/views/RegisterPage'
import VueRouter from 'vue-router'
import Vuelidate from 'vuelidate'
import registrationService from '../../src/services/registration'

// vm.$router 에 접근할 수 있도록
// 테스트에 Vue Router 추가
const localVue = createLocalVue()
localVue.use(VueRouter)
localVue.use(Vuelidate)
const router = new VueRouter()

// registrationService 의 목
jest.mock('../../src/services/registration')

describe('RegisterPage.vue', () => {
  let wrapper
  let fieldUsername
  let filedEmailAddress
  let filedPassword
  // let buttonSubmit
  let registerSpy

  // 테스트를 수행하기전에 초기화 작업
  beforeEach(() => {
    wrapper = shallowMount(RegisterPage, {
      localVue,
      router,
    })
    fieldUsername = wrapper.find('#username')
    filedEmailAddress = wrapper.find('#emailAddress')
    filedPassword = wrapper.find('#password')
    // buttonSubmit = wrapper.find('form button[type="submit"]')
    registerSpy = jest.spyOn(registrationService, 'register')
  })

  afterEach(() => {
    registerSpy.mockReset()
    registerSpy.mockRestore()
  })

  // 모든 테스트가 끝난 경우 실행
  afterAll(() => {
    jest.restoreAllMocks()
    jest
  })

  test('렌더링 테스트', () => {
    expect(fieldUsername.element.value).toEqual('')
    expect(filedEmailAddress.element.value).toEqual('')
    expect(filedPassword.element.value).toEqual('')
  })

  test('데이터 모델의 초깃값 테스트', () => {
    expect(wrapper.vm.form.username).toEqual('')
    expect(wrapper.vm.form.emailAddress).toEqual('')
    expect(wrapper.vm.form.password).toEqual('')
  })

  test('데이터 모델과 폼 입력 필드 간의 바인딩 테스트', async () => {
    // given
    const username = 'root'
    const emailAddress = 'root@gmail.com'
    const password = 'root'

    // when
    await wrapper.setData({
      form: {
        username: username,
        emailAddress: emailAddress,
        password: password,
      },
    })

    console.log('form username = ', wrapper.vm.form.username)

    // then
    expect(fieldUsername.element.value).toEqual(username)
    expect(filedEmailAddress.element.value).toEqual(emailAddress)
    expect(filedPassword.element.value).toEqual(password)
  })

  test('회원가입 성공 테스트', async () => {
    expect.assertions(2)

    const stub = jest.fn()
    wrapper.vm.$router.push = stub

    wrapper.vm.form.username = 'root001'
    wrapper.vm.form.emailAddress = 'root@gmail.com'
    wrapper.vm.form.password = 'rootroot'

    wrapper.vm.submitForm()
    expect(registerSpy).toBeCalled()
    await wrapper.vm.$nextTick()

    expect(stub).toHaveBeenCalledWith({ name: 'LoginPage' })
  })

  test('회원가입 실패 테스트', async () => {
    expect.assertions(3)

    wrapper.vm.form.emailAddress = 'something@gmail.com'
    wrapper.vm.form.username = 'something'
    wrapper.vm.form.password = 'Something!'

    expect(wrapper.find('.failed').isVisible()).toBeFalsy()

    wrapper.vm.submitForm()
    expect(registerSpy).toBeCalled()
    // vuelidate 의 유효성검사는 통과하기 때문에 registrationService 의 register 메소드는 실행된다
    // mock 에서 root@gmail.com 이 아니면 모두 reject() 이기 때문에 error 를 반환한다
    // error 의 message 가 있는 경우 .failed 클래스 부분은 v-show 를 통해 화면에 보여지게 된다

    await wrapper.vm.$nextTick()

    expect(wrapper.find('.failed').isVisible()).toBeTruthy()
  })

  test('이메일 주소가 유효하지 않으면 실패한다', () => {
    wrapper.vm.form.emailAddress = 'bad-email-address'
    wrapper.vm.submitForm()
    expect(registerSpy).not.toHaveBeenCalled()
  })
})
