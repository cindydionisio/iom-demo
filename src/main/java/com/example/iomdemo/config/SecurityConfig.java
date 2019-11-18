package com.example.iomdemo.config;

import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.example.iomdemo.properties.ApplicationProperties;
import com.example.iomdemo.util.ApplicationUtil;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	ApplicationProperties applicationProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
         .csrf().disable()
         .authorizeRequests().anyRequest().authenticated()
         .and()
         .httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception
    {
    	/*initiate admin/ super user*/
        auth.inMemoryAuthentication()
            .withUser(applicationProperties.getAdminUserName())
            .password("{noop}"+ applicationProperties.getAdminPassword())
            .roles("USER");
        auth.userDetailsService(inMemoryUserDetailsManager());
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        final Properties users = new Properties();
        Map<String, String> usernamePasswordPairs = ApplicationUtil.getUsernameAndPasswords(applicationProperties.getUserCredDir());
        if(usernamePasswordPairs.size() > 0) {
        	for (Map.Entry<String, String> entry : usernamePasswordPairs.entrySet()) {
        		System.out.println("key : " + entry.getKey() + " val : " + entry.getValue());
        		users.put(entry.getKey(), "{bcrypt}"+entry.getValue()+",ROLE_USER,enabled");
        	}
        }
        return new InMemoryUserDetailsManager(users);
    }
}
