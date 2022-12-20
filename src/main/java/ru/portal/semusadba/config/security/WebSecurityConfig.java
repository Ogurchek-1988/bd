//package ru.portal.semusadba.config.security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@ComponentScan(basePackages = {"ru.portal.semusadba"})
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
////    private final JwtTokenService jwtProvider;
////    private final CustomUserDetailsService customUserDetailsService;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http.httpBasic().disable();
//        http.csrf().disable();
////        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.authorizeRequests()
//                .antMatchers("/*/**").permitAll()
//                .antMatchers("/*").permitAll()
//                .antMatchers("/public/*").permitAll()
//                .antMatchers("/built/**").permitAll()
//                .antMatchers("/login/dashboard.xhtml").hasAnyAuthority(
//                        UserRole.USER.getAuthority())
//                .antMatchers("/api/points/check/*").hasAnyAuthority(
//                        UserRole.USER.getAuthority())
//                .antMatchers("/api/points/get/*").hasAnyAuthority(
//                        UserRole.USER.getAuthority())
//                .antMatchers("/api/points/clear/*").hasAnyAuthority(
//                        UserRole.USER.getAuthority())
//                .anyRequest().authenticated();
//
//        http.formLogin().loginPage("/public/login.xhtml").permitAll()
//                .defaultSuccessUrl("/public/promo.xhtml")
//                .failureUrl("/public/login.xhtml?error=true");
//        http.logout().logoutSuccessUrl("/public/login.xhtml");
////        http.apply(new JwtFilterConfigurer(jwtProvider));
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
////    @Override
////    @Bean
////    public AuthenticationManager authenticationManagerBean() throws Exception {
////        return super.authenticationManagerBean();
////    }
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
////    }
//
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("test")
//                .password("{noop}test").roles(UserRole.USER.getAuthority());
//    }
//}
//

package ru.portal.semusadba.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // require all requests to be authenticated except for the resources
        http.authorizeRequests().antMatchers("/**")
                .permitAll()
                .antMatchers("/public/about.xhtml")
                .hasAnyAuthority(UserRole.USER.getAuthority());
//        http.authorizeRequests().antMatchers("/admin/admin_panel.xhtml")
//                .authenticated();
//        // login
        http.formLogin().loginPage("/public/login.xhtml").permitAll()
                .failureUrl("/public/login.xhtml?error=true");
//        // logout
        http.logout().logoutSuccessUrl("/admin/admin_panel.xhtml");
//        // not needed as JSF 2.2 is implicitly protected against CSRF
        http.csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().withUser("test")
                .password(new BCryptPasswordEncoder().encode("1234")).roles("USER").and()
                .withUser("admin").password(new BCryptPasswordEncoder().encode("1234")).roles("ADMIN");
    }
}
