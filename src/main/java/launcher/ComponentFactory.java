package launcher;



import controller.VideoGameController;
import database.DatabaseConnectionFactory;
import javafx.stage.Stage;

import mapper.VideoGameMapper;
import repository.sale.SaleRepository;
import repository.sale.SaleRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import repository.videoGame.VideoGameRepository;
import repository.videoGame.VideoGameRepositoryMySQL;
import service.videoGame.VideoGameService;
import service.videoGame.VideoGameServiceImpl;
import view.VideoGameView;
import view.model.VideoGameDTO;


import java.sql.Connection;
import java.util.List;

public class ComponentFactory {

//    private final VideoGameView videoGameView;
//    private final VideoGameController videoGameController;
//
//    private final VideoGameRepository videoGameRepository;
//
//    private final VideoGameService videoGameService;
//
//    private SaleRepository saleRepository;
//    private RightsRolesRepository rightsRolesRepository;
//    private UserRepository userRepository;
//
//    private static ComponentFactory instance;
//
//    public static ComponentFactory getInstance(Boolean componentsForTest, Stage primarystage){
//        if(instance==null)
//        {
//            synchronized (ComponentFactory.class)
//            {
//                instance = new ComponentFactory(componentsForTest,primarystage);
//            }
//        }
//        return instance;
//    }
//
//    public ComponentFactory(Boolean componentsForTest, Stage primarystage){
//        Connection connection= DatabaseConnectionFactory.getConnectionWrapper(componentsForTest).getConnection();
//        this.rightsRolesRepository=new RightsRolesRepositoryMySQL(connection);
//        this.userRepository=new UserRepositoryMySQL(connection,rightsRolesRepository);
//        this.videoGameRepository=new VideoGameRepositoryMySQL(connection);
//        this.saleRepository=new SaleRepositoryMySQL(videoGameRepository,userRepository,connection);
//        this.videoGameService=new VideoGameServiceImpl(videoGameRepository,saleRepository);
//        List<VideoGameDTO> videoGameDTOS= VideoGameMapper.convertVideoGameListToVideoGameDTOList(videoGameService.findAll());
//        displayVideoGameList(videoGameDTOS);
//        this.videoGameView=new VideoGameView(primarystage,videoGameDTOS);
//        this.videoGameController = new VideoGameController(videoGameService,videoGameView);
//
//
//    }
//
//
//    public VideoGameView getVideoGameview() {
//        return videoGameView;
//    }
//
//    public VideoGameController getVideoGameController() {
//        return videoGameController;
//    }
//
//    public VideoGameRepository getVideoGameRepository() {
//        return videoGameRepository;
//    }
//
//    public VideoGameService getVideoGameService() {
//        return videoGameService;
//    }
//
//    public static ComponentFactory getInstance() {
//        return instance;
//    }
//    public void displayVideoGameList(List<VideoGameDTO> videoGameList) {
//        for (VideoGameDTO videoGame : videoGameList) {
//            System.out.println("Author: " + videoGame.getPublisher());
//            System.out.println("Title: " + videoGame.getTitle());
//            System.out.println("Number: " + videoGame.getNumber());
//            System.out.println("-----------------------------");
//        }
//    }
}