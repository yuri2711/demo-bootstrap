package ru.yakov.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final LoginSuccessHandler loginSuccessHandler;

    public SecurityConfig(@Qualifier("userDetailsServiceImp") UserDetailsService userDetailsService, LoginSuccessHandler loginSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); // конфигурация для прохождения аутентификации
    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").authorities("ROLE_USER")
//                .and()
//                .withUser("admin").password("password").authorities("ROLE_ADMIN");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/webjars/**")
//                .permitAll();


        http.formLogin()
                // указываем страницу с формой логина
     //           .loginPage("/login")
                //указываем логику обработки при логине
                .successHandler(loginSuccessHandler)
                // указываем action с формы логина
        //        .loginProcessingUrl("/login")
                // Указываем параметры логина и пароля с формы логина
          //      .usernameParameter("j_username")
            //    .passwordParameter("j_password")
                // даем доступ к форме логина всем
                .permitAll();

        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                // указываем URL логаута
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login")
                //выклчаем кроссдоменную секьюрность (на этапе обучения неважна)
                .and().csrf().disable();

        http
                // делаем страницу регистрации недоступной для авторизированных пользователей
                .authorizeRequests()
                //страницы аутентификаци доступна всем
                .antMatchers("/login").anonymous()
                .antMatchers("/addUser").access("hasAnyRole('ADMIN')")
                .antMatchers("/updateUser").access("hasAnyRole('ADMIN')")
                .antMatchers("/admin").access("hasAnyRole('ADMIN')")
                .antMatchers("/user").access("hasAnyRole('USER', 'ADMIN')")
                // защищенные URL
                .antMatchers("/home").access("hasAnyAuthority()")
                .antMatchers("/header").access("hasAnyAuthority()")
                .antMatchers("/resources/**", "/css/**", "/js/**", "/webjars/**").permitAll().anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
