/*
 * package com.cinematrics.config;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.crypto.password.NoOpPasswordEncoder; import
 * org.springframework.security.crypto.password.PasswordEncoder;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class AppConfig extends
 * WebSecurityConfigurerAdapter {
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * http.authorizeRequests().antMatchers("/").permitAll().
 * antMatchers("/admin/**").hasAnyRole("ADMIN", "USER").and().formLogin(); }
 * 
 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
 * throws Exception {
 * auth.inMemoryAuthentication().withUser("admin").password("admin123").roles(
 * "ADMIN");
 * auth.inMemoryAuthentication().withUser("user1").password("user1").roles(
 * "USER");
 * auth.inMemoryAuthentication().withUser("user2").password("user2").roles(
 * "USER"); }
 * 
 * @Bean public PasswordEncoder password() { return
 * NoOpPasswordEncoder.getInstance(); } }
 */