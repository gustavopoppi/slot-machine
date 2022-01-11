package br.com.mvc.cacaniquel.support;

import br.com.mvc.cacaniquel.model.User;

public class UserSupport {

    public static User newUser(){
        User user = new User();
        user.setUsername("teste");
        user.setPassword("teste");
        return user;
    }
}
