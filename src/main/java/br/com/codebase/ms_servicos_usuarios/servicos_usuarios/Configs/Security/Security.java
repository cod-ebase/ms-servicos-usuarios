package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Configs.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Security {

    /*
     * algorítimo vencedor em competição de hash
     * Eficiente contra ataques de força bruta
     * https://www.password-hashing.net/
     * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(Security::CreateStateLessCreationPolicy)
            .authorizeHttpRequests(Security::CustomRequestMatchers);
        return http.build();
    }
    private static void CustomRequestMatchers(
            AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry matcherRegistry) {
        matcherRegistry.requestMatchers(HttpMethod.POST, "/api/users/admin").hasRole("ADMIN") // apenas user logado pode criar outros
                       .anyRequest().permitAll();                                                      // outros endpoints abertos
    }
    private static void CreateStateLessCreationPolicy(SessionManagementConfigurer<HttpSecurity> session) {
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
