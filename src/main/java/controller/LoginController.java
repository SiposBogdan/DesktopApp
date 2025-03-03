package controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import launcher.AdminComponentFactory;
import launcher.EmployeeComponentFactory;
import model.User;

import model.validation.Notification;
import service.user.AuthenticationService;
import view.LoginView;

import javax.naming.AuthenticationException;
import java.util.List;

import static database.Constants.Roles.*;

public class LoginController {

    private final LoginView loginView;
    private final AuthenticationService authenticationService;
    private final Boolean componentsForTest;


    public LoginController(LoginView loginView, AuthenticationService authenticationService,Boolean componentsForTest) {
        this.componentsForTest = componentsForTest;
        this.loginView = loginView;
        this.authenticationService = authenticationService;

        this.loginView.addLoginButtonListener(new LoginButtonListener());
        this.loginView.addRegisterButtonListener(new RegisterButtonListener());
    }

    private class LoginButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();


            Notification<User> loginNotification = null;
            try {
                loginNotification = authenticationService.login(username, password);
            } catch (AuthenticationException e) {
                throw new RuntimeException(e);
            }
            if (loginNotification.hasErrors())
            {
                loginView.setActionTargetText(loginNotification.getFormattedErrors());

            }else
            {
                loginView.setActionTargetText("LogIn successful");
                User user = null;
                try {
                    user = authenticationService.login(username,password).getResult();
                } catch (AuthenticationException e) {
                    throw new RuntimeException(e);
                }
                switch (user.getStringRoles())
                {

                    case ADMINISTRATOR -> AdminComponentFactory.getInstance(componentsForTest,loginView.getStage());
                    case CUSTOMER -> EmployeeComponentFactory.getInstance(componentsForTest,loginView.getStage(),user);
                    case EMPLOYEE -> EmployeeComponentFactory.getInstance(componentsForTest,loginView.getStage(),user) ;

                }

            }

        }
    }

    private class RegisterButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();


            Notification<Boolean> registerNotification = authenticationService.register(username,password,CUSTOMER);
            if(registerNotification.hasErrors())
            {
                loginView.setActionTargetText(registerNotification.getFormattedErrors());
            }
            else {
                loginView.setActionTargetText("Register successful!");

            }


        }
    }
}
