const user = require('../data/user')

module.exports = {
  before: function() {
    console.log(
      'user.js 파일을 지우고 랜덤 username 과 랜덤 emailAddress 로 새로 생성 로직',
    )
  },
  'register page renders elements': function(browser) {
    const registerPage = browser.page.RegisterPage()

    registerPage
      .navigate()
      .waitForElementVisible('@app', 500)
      .assert.visible('@usernameInput')
      .assert.visible('@emailAddressInput')
      .assert.visible('@passwordInput')
      .assert.visible('@submitButton')
      .assert.hidden('@formError')

    browser.end()
  },

  // 유효하지 않은 데이터로는 회원가입 할 수 없습니다
  'register with invalid data': function(browser) {
    const registerPage = browser.page.RegisterPage()

    registerPage.navigate().register('', '', '')

    // This assertion is just to make sure the page doesn't
    // redirect to login page. It would be better to assertion
    // the validation error for each field individually.
    browser.assert.urlEquals(browser.launchUrl + 'register').end()
  },

  // 유효한 데이터로는 회원가입할 수 있습니다
  'register with valid data': function(browser) {
    const registerPage = browser.page.RegisterPage()

    registerPage
      .navigate()
      .waitForElementVisible('@app', 500)
      .register(user.username, user.emailAddress, user.password)

    browser.pause(2000)
    browser.assert.urlEquals(browser.launchUrl + 'login').end()
  },
}
