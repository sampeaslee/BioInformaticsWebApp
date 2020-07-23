package CS_564.Metabolites;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.Assert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
	@Autowired
	UserDetailsService userDetailsService;
	
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
 
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
         .authorizeRequests()
          .antMatchers("/login").permitAll()
          .antMatchers("/home").permitAll()
          .antMatchers("/genes").permitAll()
          .antMatchers("/metabolites").permitAll()
          .antMatchers("/reactions").permitAll()
          .antMatchers("/functionality/find_pathway").permitAll()
          .antMatchers("/functionality/find_product").permitAll()
          .antMatchers("/images/*.png").permitAll()
          .antMatchers("/images/*.jpg").permitAll()
          .antMatchers("/info/metabolites/*").permitAll()
          .antMatchers("/info/reactions/*").permitAll()

         // .antMatchers("/update").hasAnyRole("ADMIN")
          	//.antMatchers("/").permitAll()
           .anyRequest().authenticated()
          	.and()
         .formLogin()
          	.loginPage("/login")
          	.usernameParameter("username")
          	.passwordParameter("password")
          	 //.loginProcessingUrl("/update");
          	.defaultSuccessUrl("/update",true)
          	//.failureUrl("/accessdenied")
          	//.permitAll();
        .and()
        .csrf().disable()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
       //.logoutSuccessUrl("/accessdenied");
        



    }
    
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}