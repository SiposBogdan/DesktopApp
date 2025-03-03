package launcher;


import controller.AdminController;
import database.DatabaseConnectionFactory;
import javafx.stage.Stage;
import model.User;
import repository.admin.AdminRepository;
import repository.admin.AdminRepositoryMySQL;
import repository.pdf.PDFGenerateMySQLRepository;
import repository.pdf.PDFGenerateRepository;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.PDFGenerator.PDFService;
import service.PDFGenerator.PDFServiceImpl;
import service.admin.AdminService;
import service.admin.AdminServiceImpl;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceImpl;
import view.AdminView;
import view.model.AdminDTO;

import java.sql.Connection;
import java.util.List;

public class AdminComponentFactory {
    private final AdminView adminView;
    private final AdminService adminService;
    private final AdminRepository adminRepository;
    private static AdminComponentFactory instance;
    private final RightsRolesRepository rightsRolesRepository;
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final PDFService pdfGenerateService;
    private final PDFGenerateRepository pdfGenerateRepo;
    private final AdminController adminController;



    public static AdminComponentFactory getInstance(Boolean componentsForTest, Stage stage){
        if (instance == null) {

            synchronized (AdminComponentFactory.class) {
                if (instance == null) {
                    instance = new AdminComponentFactory(componentsForTest, stage);
                }
            }
        }

        return instance;
    }
    private AdminComponentFactory(Boolean componentsForTest, Stage stage){

        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(componentsForTest).getConnection();
        this.pdfGenerateRepo = new PDFGenerateMySQLRepository(connection);
        this.pdfGenerateService = new PDFServiceImpl(pdfGenerateRepo,connection);
        rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        userRepository = new UserRepositoryMySQL(connection,rightsRolesRepository);
        this.authenticationService = new AuthenticationServiceImpl(userRepository,rightsRolesRepository);
        this.adminRepository = new AdminRepositoryMySQL(connection,authenticationService);
        this.adminService = new AdminServiceImpl(adminRepository);
        List<User> users = this.adminService.findAll();
        this.adminView = new AdminView(stage, users);
        this.adminController = new AdminController(adminView, adminService,authenticationService,pdfGenerateService);
    }
}
