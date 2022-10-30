package appsprototyping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

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

    //@Bean
    public UserDetailsService naiveUserDetailsService() {

        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER","TESTUSER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public UserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        // TAKEN FROM: https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/jdbc.html#servlet-authentication-jdbc
        // there are also SQL schemas for it provided
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                .roles("USER", "ADMIN")
                .build();
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        if (!users.userExists("user")) {
            users.createUser(user);
            users.createUser(admin);
        }
        return users;
    }
}
