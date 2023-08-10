package com.developmentproject.bts.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;




@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
	private UserPrincipalDetailsService userPrincipalDetailsService;
    private final BCryptPasswordEncoder encoder;
    
    @Autowired
    public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService) {
        this.encoder = new BCryptPasswordEncoder();
		this.userPrincipalDetailsService = userPrincipalDetailsService;
           
        }

	
	   protected void configure(AuthenticationManagerBuilder auth) {
	  	auth.authenticationProvider(authenticationProvider()); }
	   
	 
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/home" , "/").permitAll()
                .requestMatchers("/#").permitAll()
                .requestMatchers("/success").permitAll()
                .requestMatchers("/success").permitAll()
                .requestMatchers("/register" , "/").permitAll()
                .requestMatchers("/adduser" , "/").permitAll()
                .requestMatchers("/user").hasAuthority("USER")           
                .requestMatchers("/reservation/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/bus/").hasAuthority("ADMIN")
                .requestMatchers("/station/**").hasAuthority("ADMIN")
                .requestMatchers("Users/update_user").hasAuthority("ADMIN")
                .requestMatchers("/busstation/**").hasAuthority("ADMIN")
                .requestMatchers("/session/**").hasAuthority("ADMIN")
                .requestMatchers("/row/**").hasAuthority("ADMIN")
                .requestMatchers("/row/busStationRows").hasAuthority("ADMIN")
                .requestMatchers("/seat/**").hasAuthority("ADMIN")
                .requestMatchers("/bussession/**").hasAuthority("ADMIN")
                .requestMatchers("/busstation/showbusstations").hasAuthority("ADMIN")
                .requestMatchers("/busstation/busstations").hasAuthority("ADMIN")
                .requestMatchers("/bus/new_bus").hasAuthority("ADMIN")
                .requestMatchers("/bus/buses").hasAuthority("ADMIN")
                .requestMatchers("/bus/editBus/1").hasAuthority("ADMIN")        
                .requestMatchers("/ticket/**").hasAuthority("ADMIN")
                .requestMatchers("/ticket/showTickets").hasAuthority("ADMIN")
                .requestMatchers("/user/**").hasAuthority("ADMIN") 
                .requestMatchers("/css/ad.css").hasAuthority("ADMIN")
                .requestMatchers("/js/**", "/img/**").permitAll()
                .requestMatchers("/css/idstyle.css","/css/main.css","/css/media_query.css","/css/reservationstyle.css","/css/reservebusstyle.css","/css/reset.css","/css/style.css","/css/variable.css").permitAll()
                .and()
                .formLogin()
                .loginProcessingUrl("/signin")
                .loginPage("/login").permitAll()
                .usernameParameter("txtUsername")
                .passwordParameter("txtPassword")
                .defaultSuccessUrl("/success", true)
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
		return http.build();
                
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
	public BCryptPasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
	   

}
	





