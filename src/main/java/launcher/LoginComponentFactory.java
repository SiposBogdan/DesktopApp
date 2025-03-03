package launcher;



import controller.LoginController;
import database.DatabaseConnectionFactory;
import javafx.stage.Stage;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import repository.videoGame.VideoGameRepositoryMySQL;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceImpl;

import view.LoginView;

import java.sql.Connection;

public class LoginComponentFactory {
    private final LoginView loginView;
    private final LoginController loginController;
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    private final VideoGameRepositoryMySQL videoGameRepository;
    private static LoginComponentFactory instance;
    private static Boolean componentsForTests;
    private static Stage stage;

    public static LoginComponentFactory getInstance(Boolean aComponentsForTests, Stage aStage) {
        if (instance == null) {
            componentsForTests = aComponentsForTests;
            stage = aStage;
            instance = new LoginComponentFactory(componentsForTests, stage);
        }

        return instance;
    }

    public LoginComponentFactory(Boolean componentsForTests, Stage stage){
        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(componentsForTests).getConnection();
        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
        this.authenticationService = new AuthenticationServiceImpl(userRepository, rightsRolesRepository);
        this.loginView = new LoginView(stage);
        this.loginController = new LoginController(loginView, authenticationService, componentsForTests);
        this.videoGameRepository = new VideoGameRepositoryMySQL(connection);
    }

    public static Stage getStage(){
        return stage;
    }

    public static Boolean getComponentsForTests(){
        return componentsForTests;
    }

    public AuthenticationService getAuthenticationService(){
        return authenticationService;
    }

    public UserRepository getUserRepository(){
        return userRepository;
    }

    public RightsRolesRepository getRightsRolesRepository(){
        return rightsRolesRepository;
    }

    public LoginView getLoginView(){
        return loginView;
    }

    public VideoGameRepositoryMySQL getVideoGameRepository(){
        return videoGameRepository;
    }

    public LoginController getLoginController(){
        return loginController;
    }

}