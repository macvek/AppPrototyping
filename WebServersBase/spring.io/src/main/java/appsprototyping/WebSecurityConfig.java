package appsprototyping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = false)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain formLoginSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .requestMatchers().antMatchers("/form-protected/*","/form-protected-login").and()
                .authorizeHttpRequests((requests) ->
                        requests.antMatchers("/form-protected/*").authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/form-protected-login")
                )
                .logout( l -> l.logoutUrl("/form-protected/logout")
                        .logoutSuccessUrl("/")
                )
                .build();
    }

    @Bean
    public SecurityFilterChain otherFormLoginSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .requestMatchers().antMatchers("/other-form-protected/*","/other-form-protected-login").and()
                .authorizeHttpRequests((requests) ->
                        requests.antMatchers("/other-form-protected/*").authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/other-form-protected-login")
                )
                .logout((logout) -> logout
                        .logoutUrl("/other-form-protected/logout")
                        .logoutSuccessUrl("/"))
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}
