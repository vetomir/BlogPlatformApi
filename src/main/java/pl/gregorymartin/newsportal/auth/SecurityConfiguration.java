package pl.gregorymartin.newsportal.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.gregorymartin.newsportal.appUser.AppUserService;

import java.util.List;
import java.util.Set;

@EnableWebSecurity
class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final TokenService tokenService;
    private UserDetailsService userDetailsService;

    SecurityConfiguration(TokenService tokenService, final UserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/login")
                .permitAll()
                .antMatchers("/api/authenticate")
                .permitAll()
                .antMatchers("/api/logout")
                .permitAll()
                .antMatchers("/h-2console/**").hasAnyRole("ADMIN")
                .antMatchers("/api/status").permitAll()

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/api/logout"))
                .logoutSuccessUrl("/")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")

                .and()
                .authorizeRequests()

                .antMatchers(HttpMethod.POST,"/api").permitAll()
                .antMatchers(HttpMethod.GET,"/api/**").permitAll()
                .anyRequest()
                .authenticated()
                //.permitAll()


                .and()
                .addFilterBefore(new AuthenticationFilter(userDetailsService, tokenService), AnonymousAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

   /* @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3001")
                        .allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
            }
        };
    }*/
/*   @Bean
   public CorsFilter corsFilter() {
       final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       final CorsConfiguration config = new CorsConfiguration();
       config.setAllowCredentials(true);
       config.setAllowedOrigins(List.of("http://localhost:3001"));
       config.setAllowedHeaders(List.of("Origin", "Content-Type", "Accept"));
       config.setAllowedMethods(List.of("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
       source.registerCorsConfiguration("/**", config);
       return new CorsFilter(source);
   }*/
    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
                                "https://portfolio-blog-react.vercel.app",
                                "http://localhost:3000",
                                "http://localhost:3001");
            }
        };
    }

}
