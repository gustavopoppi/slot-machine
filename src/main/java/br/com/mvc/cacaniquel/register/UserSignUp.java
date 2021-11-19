package br.com.mvc.cacaniquel.register;

import br.com.mvc.cacaniquel.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

public class UserSignUp {

    public void newOne(UserDto userDto, JdbcUserDetailsManager jdbcUserDetailsManager) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        User user = new User(userDto.getName(), encoder.encode(userDto.getPassword()), authorities);

        jdbcUserDetailsManager.createUser(user);
    }
}
