package apap.ta.sipayroll.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()).and()
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/user/addUser").permitAll()
                .antMatchers("/api/v1/**").permitAll()
                .antMatchers("/gaji/add").hasAnyAuthority("Kepala Departemen HR", "Staff Payroll")
                .antMatchers("/gaji/viewall").hasAnyAuthority("Kepala Departemen HR", "Staff Payroll", "Karyawan")
                .antMatchers("/gaji/ubah/**").hasAnyAuthority("Kepala Departemen HR", "Staff Payroll")
                .antMatchers("/gaji/delete/**").hasAnyAuthority("Kepala Departemen HR", "Staff Payroll")
                .antMatchers("/gaji/change/status/**").hasAnyAuthority("Kepala Departemen HR")
                .antMatchers("/lembur/add").hasAnyAuthority("Karyawan")
                .antMatchers("/lembur/change/**").hasAnyAuthority("Kepala Departemen HR", "Staff Payroll", "Karyawan")
                .antMatchers("/lembur/change").hasAnyAuthority("Kepala Departemen HR", "Staff Payroll", "Karyawan")
                .antMatchers("/lembur/viewall").hasAnyAuthority("Kepala Departemen HR", "Staff Payroll", "Karyawan")
                .antMatchers("/addLowongan").hasAnyAuthority("Staff Payroll")
                .antMatchers("/bonus/add").hasAnyAuthority("Kepala Departemen HR","Kepala Bagian")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll()
                .and()
                .cors()
                .and()
                .csrf()
                .disable();
    }
    @Bean
    public BCryptPasswordEncoder encoder() { return new BCryptPasswordEncoder(); }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication()
//                .passwordEncoder(encoder())
//                .withUser("admin").password(encoder().encode("admin123"))
//                .roles("ADMIN");
//    }


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }
}