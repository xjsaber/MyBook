package readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by xjsaber on 2017/4/28.
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ReaderRepository readerRepository;

    @Autowired
    public SecurityConfig(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/")
                .access("hasRole('READER')") //Require READER access
                .antMatchers("/**").permitAll()
                .and()
                .formLogin().loginPage("/login") //set login form path
                .failureUrl("/login?error=true");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // Define custom UserDetailsService
        auth.userDetailsService(readerRepository::findOne);
    }
}
