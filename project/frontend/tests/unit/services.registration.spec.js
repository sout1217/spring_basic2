import moxios from 'moxios'
import registerationService from '../../src/services/registration'

describe('sevices/registration', () => {
  beforeEach(() => {
    moxios.install()
  })

  afterEach(() => {
    moxios.uninstall()
  })

  test('성공 테스트', () => {
    expect.assertions(2)

    moxios.wait(() => {
      let request = moxios.requests.mostRecent()
      expect(request).toBeTruthy()
      request.respondWith({
        status: 200,
        response: { result: 'success' },
      })
    })

    return registerationService.register().then(data => {
      expect(data.result).toEqual('success')
    })
  })

  test('실패 테스트', () => {
    expect.assertions(2)

    moxios.wait(() => {
      let request = moxios.requests.mostRecent()
      expect(request).toBeTruthy()
      request.reject({
        status: 400,
        response: { message: 'Bad request' },
      })
    })

    return registerationService.register().catch(error => {
      expect(error.response.message).toEqual('Bad request')
    })
  })
})
