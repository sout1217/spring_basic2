const data = require('../data/user')

module.exports = {
  // 로그인 페이지 렌더링
  'login page renders elements': function(browser) {
    const loginPage = browser.page.LoginPage() // 페이지 오브젝트 인스턴스
    loginPage
      .navigate() // 해당 url 로 이동
      .waitForElementVisible('@app', 2000) // 2000 mils 동안 app 로드 기다리기
      .assert.visible('@usernameInput') // usernameInput 로드 되어 보이는 지 확인하기
      .assert.visible('@passwordInput')
      .assert.visible('@submitButton')
      .assert.hidden('@formError')

    browser.end() // 브라우저 종료
  },

  // email 로 로그인
  'login with email address': function(browser) {
    const loginPage = browser.page.LoginPage() // 로그인 페이지 인스턴스
    const homePage = browser.page.HomePage() // 홈 페이지 인스턴스

    loginPage
      .navigate() // 로그인 페이지로 url 이동
      .waitForElementVisible('@app', 2000)
      .login(data.emailAddress, data.password) // page-objects/LoginPage.js 에 정의 된 login() 메소드 실행

    browser.pause(500) // 0.5초 대기

    homePage
      .navigate() // 홈 페이지로 이동
      .expect.element('@pageTitle')
      .text.to.contains('Home Page !')
    // pageTile 을 element 를 가져온 후, 'Home Page' 내용이 있는 지 확인한다

    browser.end() // 브라우저 종료
  },

  // username 으로 로그인
  'login with username': function(browser) {
    const loginPage = browser.page.LoginPage() // 로그인 페이지 인스턴스
    const homePage = browser.page.HomePage() // 홈 페이지 인스턴스

    loginPage
      .navigate() // 로그인 페이지로 이동
      .login(data.username, data.password) // data/user.js 에 있는 data 를 인자로 넣어 로그인 메소드 실행

    browser.pause(2000) // 2초 대기

    homePage
      .navigate() // 홈 페이지 이동
      .expect.element('@pageTitle')
      .text.to.contains('Home Page')
    // pageTile 을 element 를 가져온 후, 'Home Page' 내용이 있는 지 확인한다

    browser.end() // 브라우저 종료
  },

  // 유효하지 않은 data 는 로그인에 실패해야 한다
  'login with invalid credentials': function(browser) {
    const loginPage = browser.page.LoginPage() // 로그인 페이지 인스턴스

    loginPage.navigate().login('not-exist', 'incorrect') // 존재하지 않고 유효하지않는 username 과 password 로 login 메소드 실행

    browser.pause(500) // 0.5초 대기

    loginPage.assert
      .visible('@formError') // 유효하지 않았을 때 나오는 formError 가 화면에 보이는 지
      .assert.containsText('@formError', 'Invalid credentials') // fromError 요소의 텍스트 내용에 Invalid credentials 이 포함되어있는지

    browser.assert
      .urlEquals(browser.launchUrl + 'login') // 현재 url 이 /login 페이지 인지 검사
      .end() // 브라우저 종료
  },
}
