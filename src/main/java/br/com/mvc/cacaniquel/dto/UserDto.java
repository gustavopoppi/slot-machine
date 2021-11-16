package br.com.mvc.cacaniquel.dto;

import javax.validation.constraints.NotBlank;

public class UserDto {

    @NotBlank
    private String name;

    @NotBlank
    private String password;
    private boolean enabled;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
