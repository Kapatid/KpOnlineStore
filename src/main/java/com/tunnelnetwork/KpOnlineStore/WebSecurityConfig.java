package com.tunnelnetwork.KpOnlineStore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@EnableWebSecurity 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
	public InMemoryUserDetailsManager getInMemoryUserDetailsManager() {
			return new InMemoryUserDetailsManager();
	}

  // @Override
  // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  //     auth.inMemoryAuthentication()
  //             .withUser("admin").password(passwordEncoder().encode("adminadmin")).roles("USER", "ADMIN");
  // }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      .antMatchers(
        "/signup",
        "/signup1",
        "/js/**",
        "/css/**",
        "/img/**").permitAll()
      .anyRequest().authenticated()
      .and()
      .formLogin()
        .loginPage("/login")
        .permitAll()
      .and()
      .logout().permitAll();
  }
}
