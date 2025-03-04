package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import launcher.AdminComponentFactory;
import model.User;
import mapper.UserMapper;
import model.builder.UserBuilder;
import model.validation.Notification;
import service.PDFGenerator.PDFService;
import service.admin.AdminService;

import service.user.AuthenticationService;
import view.AdminView;
import view.LoginView;
import view.model.UserDTO;


public class AdminController {
    private final AdminView adminView;
    private final AdminService adminService;
    private final AuthenticationService authenticationService;
    private final PDFService pdfService;

    public AdminController(AdminView adminView, AdminService adminService, AuthenticationService authenticationService,PDFService pdfService) {
        this.adminView = adminView;
        this.adminService = adminService;
        this.authenticationService = authenticationService;
        this.pdfService = pdfService;

        this.adminView.addSaveButtonListener(new AdminController.SaveButtonListener());
        this.adminView.addDeleteButtonListener(new AdminController.DeleteButtonListener());
        this.adminView.addGenerateButtonListener(new AdminController.GenerateReportButtonListener());
        this.adminView.addLogoutButtonListener(new LogoutButtonListener());
    }
    public class SaveButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String username = adminView.getUsername();
            String password = adminView.getPassword();
            String stringRole = adminView.getRole();


            if (username.isEmpty() || password.isEmpty() || stringRole.isEmpty()) {
                adminView.addDisplayAlertMessage("Save Error", "Problem at Username or Password or Role fields", "Can not have an empty fields.");
            } else {
                Notification<Boolean> register = authenticationService.register(username, password, stringRole);
                User user = new UserBuilder()
                        .setUsername(username)
                        .setStringRoles(stringRole)
                        .build();
                System.out.println(user);
                if (register.hasErrors()) {
                    adminView.addDisplayAlertMessage("Save Error","Problem at adding user",register.getFormattedErrors());
                } else {
                    if (register.getResult()) {
                        adminView.addDisplayAlertMessage("Successful", "User added", "User was successfully added to the database");
                        //adminView.addUserToObservableList(user);
                        adminView.addUserToObservableList(UserMapper.convertUsertoUserDTO(user));
                    } else {
                        adminView.addDisplayAlertMessage("Save Error", "Problem at adding user", "There was a problem at adding the user to the database. Please try again.");

                    }
                }


            }
        }
    }

    public class DeleteButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            UserDTO userDTO=(UserDTO) adminView.getUsersTableView().getSelectionModel().getSelectedItem();
            System.out.println(userDTO.getUsername());
            if(userDTO!=null)
            {
                boolean deleteSuccess=adminService.delete(UserMapper.convertUserDTOtoUser(userDTO));
                if(deleteSuccess)
                {
                    adminView.addDisplayAlertMessage("Success","Delete","Delete");
                    adminView.removeUserFromObservableList(userDTO);
                }
                else
                {
                    adminView.addDisplayAlertMessage("Fail","Delete","Delete");
                }
            }
            else
            {
                adminView.addDisplayAlertMessage("EmployeeNULL","Delete","Delete");
            }
        }
    }

    public class GenerateReportButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {

            if (pdfService.generatePDF()) {
                adminView.addDisplayAlertMessage("Successful", "Generated pdf","");
            } else {
                adminView.addDisplayAlertMessage("Error", "Error at generating pdf","");
            }

        }
    }
    public class LogoutButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout Confirmation");
            alert.setHeaderText("Are you sure you want to log out?");
            alert.setContentText("Your session will be closed.");

            ButtonType confirmButton = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(confirmButton, cancelButton);

            alert.showAndWait().ifPresent(response -> {
                if (response == confirmButton) {
                    Stage currentStage = (Stage) adminView.getUsersTableView().getScene().getWindow();
                    currentStage.close();
                    AdminComponentFactory.resetInstance();

                    Stage loginStage = new Stage();
                    LoginView loginView = new LoginView(loginStage);

                    new LoginController(loginView, authenticationService, false);
                }
            });
        }
    }

}
