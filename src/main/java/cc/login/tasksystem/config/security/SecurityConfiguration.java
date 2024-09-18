package cc.login.tasksystem.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
    private static final String[] PUBLIC_MATCHERS = {
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-resource/**",
            "https://trilhabackendjr-jun15-render.onrender.com"}; // Achei que ia da certo k

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(PUBLIC_MATCHERS).permitAll() // permissão visual

                        // Login
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                        // ADMIN 777
                        .requestMatchers(HttpMethod.DELETE, "/api/admin777/{userId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/admin777/{userId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/end/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/admin777").hasRole("ADMIN")

                        // Rota Task Admin
                        .requestMatchers(HttpMethod.GET, "/end/tasks").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/end/tasks/{userId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/end/tasks/{userId}").hasRole("ADMIN")

                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
