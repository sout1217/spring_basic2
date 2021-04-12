import { shallowMount, createLocalVue } from '@vue/test-utils'
import RegisterPage from '../../src/views/RegisterPage'
import VueRouter from 'vue-router'

// vm.$router 에 접근할 수 있도록
// 테스트에 Vue Router 추가
const localVue = createLocalVue()
localVue.use(VueRouter)
const router = new VueRouter()

// registrationService 의 목
jest.mock('../../src/services/registration')

describe('RegisterPage.vue', () => {
  let wrapper
  let fieldUsername
  let filedEmailAddress
  let filedPassword
  let buttonSubmit

  // 테스트를 수행하기전에 초기화 작업
  beforeEach(() => {
    wrapper = shallowMount(RegisterPage, {
      localVue,
      router,
    })
    fieldUsername = wrapper.find('#username')
    filedEmailAddress = wrapper.find('#emailAddress')
    filedPassword = wrapper.find('#password')
    buttonSubmit = wrapper.find('form button[type="submit"]')
  })

  // 모든 테스트가 끝난 경우 실행
  afterAll(() => {
    jest.restoreAllMocks()
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
    const stub = jest.fn()
    wrapper.vm.$router.push = stub

    wrapper.vm.form.username = 'root'
    wrapper.vm.form.emailAddress = 'root@gmail.com'
    wrapper.vm.form.password = 'root'

    await wrapper.vm.submitForm()

    wrapper.vm.$nextTick(() => {
      expect(stub).toHaveBeenCalledWith({ name: 'LoginPage' })
    })
  })

  test('회원가입 실패 테스트', async () => {
    wrapper.vm.form.emailAddress = 'something@gmail.com'
    expect(wrapper.find('.failed').isVisible()).toBeFalsy()

    await wrapper.vm.submitForm()

    wrapper.vm.$nextTick(null, () => {
      expect(wrapper.find('.failed').isVisible()).toBeTruthy()
    })
  })
})
