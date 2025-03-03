package service.user;

import model.User;
import model.validation.Notification;

import javax.naming.AuthenticationException;

public interface AuthenticationService {

    Notification<Boolean> register(String username, String password, String role);
    Notification<User> login(String username, String password) throws AuthenticationException, AuthenticationException;
    boolean logout(User user);
    String getRoleFromUser(String username, String password);

}