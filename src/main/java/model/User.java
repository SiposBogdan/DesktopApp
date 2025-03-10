package model;

import java.util.List;

public class User {
    private Long id;
    private String username;
    private String password;
    private List<Role> roles;
    private String stringRoles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    public String getStringRoles() {
        return stringRoles;
    }
    public void setStringRoles(String role)
    {
        this.stringRoles= role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", stringRoles='" + stringRoles + '\'' +
                '}';
    }



}
