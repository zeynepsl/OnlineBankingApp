package patika.bootcamp.onlinebanking.config;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.security.JWTAuthenticationEntryPoint;
import patika.bootcamp.onlinebanking.security.UserDetailService;
import patika.bootcamp.onlinebanking.security.filter.JWTAuthenticationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final UserDetailService userDetailService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(bcryptPasswordEncoder());
    }

    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/*").permitAll()
                .and()
                .csrf().disable();

    }


   /*hoca: @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/swagger-ui.html#/**")
                .permitAll()
                .antMatchers("/oauth/token").permitAll()
                .anyRequest()
                .authenticated();
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }*/
    
    /*
     * .antMatchers("/oauth/token").permitAll()
     * http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().antMatchers("/oauth/token").permitAll().
    anyRequest().authenticated();*/
    
    
    
    
    
   /* //orbidden alıyıruz: 
    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(AUTH_WHITELIST);
    }
    
  @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
             .csrf().disable().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
             .sessionManagement()
             .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
             .and()
             .authorizeRequests()
             .antMatchers("/swagger-ui/**")
             .permitAll()
             .antMatchers("/v3/api-docs/**")
             .permitAll()
             .anyRequest().authenticated();
        //http.apply(new JwtTokenConfigurer(tokenProvider));
    }*/
    
    
    
    
    

 
    
    //stacoverflow:
   /* @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                                   "/configuration/ui",
                                   "/swagger-resources/**",
                                   "/configuration/security",
                                   "/swagger-ui.html",
                                   "/webjars/**");
    }*/
    
   /* @Override
    antMatchers("/api/sign-in")
    
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/*").permitAll()
                .and()
                .csrf().disable();

    }*/

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }*/

}

