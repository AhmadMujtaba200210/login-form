package com.springsecure.form.configuration;

import com.springsecure.form.filter.CustomAuthenticationFilter;
import com.springsecure.form.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable();
       http.sessionManagement().sessionCreationPolicy(STATELESS);
       http.authorizeRequests().antMatchers("/login", "/postEmployee").permitAll();
       //Employee APIs
       http.authorizeRequests().antMatchers(HttpMethod.GET,"/getEmployee/**","/getEmployeeByEmail/**").hasAnyAuthority("Admin");
       //Role APIs
       http.authorizeRequests().antMatchers(HttpMethod.GET,"/getRoleName").hasAnyAuthority("Admin");
       http.authorizeRequests().antMatchers(HttpMethod.POST,"/addRole").hasAnyAuthority("Admin");
       http.authorizeRequests().antMatchers(HttpMethod.PUT,"/updateRole").hasAnyAuthority("Admin");
       http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/deleteRole").hasAnyAuthority("Admin");
       http.authorizeRequests().anyRequest().authenticated();
       http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
       http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Setting my own authentication configuration
        // created my own employeeDetailsServices interface
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
