package vuejs.springboot.mysql.backend.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import vuejs.springboot.mysql.backend.domain.application.service.impl.UserFindServiceImpl;
import vuejs.springboot.mysql.backend.domain.model.account.AccountRepository;
import vuejs.springboot.mysql.backend.web.security.filter.AuthenticationFilter;
import vuejs.springboot.mysql.backend.web.security.hanlder.SimpleFailureHandler;
import vuejs.springboot.mysql.backend.web.security.hanlder.SimpleLogoutSuccessHandler;
import vuejs.springboot.mysql.backend.web.security.hanlder.SimpleSuccessHandler;

@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AccountRepository accountRepository;

    private static final String[] PUBLIC = {"/error", "/login", "/logout", "/register", "/api/v1/registrations", "/h2-console/**"};

    /* static 접근 설정 무시 */
    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        web.ignoring().antMatchers("/static/**", "/js/**", "/css/**", "/images/**", "/favicon.ico");
    }

    /* template 접근 권한 설정 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                    .antMatchers(PUBLIC).permitAll()
                    .anyRequest().authenticated()
            .and()
                .formLogin()
                    .loginPage("/login")
            .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessHandler(simpleLogoutSuccessHandler())
            .and()
                .addFilterAt(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userFindService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public UserDetailsService userFindService() {
        return new UserFindServiceImpl(accountRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFilter authenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.setAuthenticationSuccessHandler(successHandler());
        authenticationFilter.setAuthenticationFailureHandler(failHandler());
        authenticationFilter.setAuthenticationManager(authenticationManagerBean());

        return authenticationFilter;
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new SimpleSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler failHandler() {
        return new SimpleFailureHandler();
    }

    @Bean
    public LogoutSuccessHandler simpleLogoutSuccessHandler() {
        return new SimpleLogoutSuccessHandler();
    }

}
