package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import mapper.AdminMapper;
import model.User;
import model.builder.UserBuilder;
import model.validation.Notification;
import service.PDFGenerator.PDFService;
import service.admin.AdminService;

import service.user.AuthenticationService;
import view.AdminView;
import view.model.AdminDTO;


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

                if (register.hasErrors()) {
                    adminView.addDisplayAlertMessage("Save Error","Problem at adding user",register.getFormattedErrors());
                } else {
                    if (register.getResult()) {
                        adminView.addDisplayAlertMessage("Successful", "User added", "User was successfully added to the database");
                        adminView.addUserToObservableList(user);
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
}
