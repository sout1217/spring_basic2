import { mount, createLocalVue } from '@vue/test-utils'
import Vuelidate from 'vuelidate'
import VueRouter from 'vue-router'
import LoginPage from '@/views/LoginPage'
import authenticationService from '@/services/authenticate'

// Setup local Vue with Vuelidate
const localVue = createLocalVue()
localVue.use(Vuelidate)
localVue.use(VueRouter)
const router = new VueRouter()

// Mock dependency registratioService
jest.mock('@/services/authenticate')

describe('LoginPage.vue', () => {
  let wrapper
  let fieldUsername
  let fieldPassword
  let buttonSubmit
  let authenticateSpy

  beforeEach(() => {
    wrapper = mount(LoginPage, {
      localVue,
      router
    })
    fieldUsername = wrapper.find('#username')
    fieldPassword = wrapper.find('#password')
    buttonSubmit = wrapper.find('form button[type="submit"]')
    // Create spy for registration service
    authenticateSpy = jest.spyOn(authenticationService, 'authenticate')
  })

  afterEach(() => {
    authenticateSpy.mockReset()
    authenticateSpy.mockRestore()
  })

  afterAll(() => {
    jest.restoreAllMocks()
  })

  // 올바른 내용을 렌더링해야합니다
  it('should render correct contents', () => {
    expect(wrapper.find('h1').text()).toEqual('LoginPage')
  })

  // 초기 값이있는 데이터 모델을 포함해야합니다.
  it('should contain data model with initial values', () => {
    expect(fieldUsername.element.value).toEqual('')
    expect(fieldPassword.element.value).toEqual('')
  })

  // 데이터 모델에 바인딩 된 양식 입력이 있어야합니다.
  it('should have form inputs bound with data model', async () => {
    const username = 'sunny'
    const password = 'VueJsRocks!'

    await wrapper.setData({
      form: {
        username: username,
        password: password
      }
    })

    expect(fieldUsername.element.value).toEqual(username)
    expect(fieldPassword.element.value).toEqual(password)
  })

  // 양식 제출 이벤트 핸들러`submitForm`이 있어야합니다.
  it('should have form submit event handler `submitForm`', () => {
    // 방식 1 (나중에 지원안함)
    // const stub = jest.fn()
    // wrapper.setMethods({ submitForm: stub })
    // buttonSubmit.trigger('submit')
    // expect(stub).toBeCalled()

    // 방식 2
    const stub = jest.spyOn(wrapper.vm, 'submitForm')
    buttonSubmit.trigger('submit')
    expect(stub).toBeCalled()
  })

  // 자격 증명이 유효하면 성공해야합니다.
  it('should succeed when credentials are valid', async () => {
    expect.assertions(2)
    const stub = jest.fn()
    wrapper.vm.$router.push = stub
    wrapper.vm.form.username = 'sunny'
    wrapper.vm.form.password = 'JestRocks!'
    wrapper.vm.submitForm()
    expect(authenticateSpy).toBeCalled()
    await wrapper.vm.$nextTick()
    expect(stub).toHaveBeenCalledWith({ name: 'HomePage' })
  })

  // 자격 증명이 유효하지 않으면 실패해야합니다.
  it('should fail when credentials are invalid', async () => {
    expect.assertions(3)
    // In the mock, only password `JestRocks!` combined with
    // username `sunny` or `sunny@taskagile.com` is valid
    wrapper.vm.form.username = 'sunny'
    wrapper.vm.form.password = 'MyPassword!'
    expect(wrapper.find('.failed').isVisible()).toBe(false)
    wrapper.vm.submitForm()
    expect(authenticateSpy).toBeCalled()
    await wrapper.vm.$nextTick()
    expect(wrapper.find('.failed').isVisible()).toBe(true)
  })

  // 유효하지 않은 데이터 제출을 방지하려면 유효성 검사가 있어야합니다.
  it('should have validation to prevent invalid data submit', () => {
    // Empty form
    wrapper.vm.submitForm()
    expect(authenticateSpy).not.toHaveBeenCalled()

    // Only username is valid
    wrapper.vm.form.username = 'sunny'
    wrapper.vm.submitForm()
    expect(authenticateSpy).not.toHaveBeenCalled()

    // Only email is valid
    wrapper.vm.form.username = 'sunny@taskagile.com'
    wrapper.vm.submitForm()
    expect(authenticateSpy).not.toHaveBeenCalled()

    // Only password is valid
    wrapper.vm.form.password = 'MyPassword!'
    wrapper.vm.form.username = ''
    wrapper.vm.submitForm()
    expect(authenticateSpy).not.toHaveBeenCalled()
  })
})
