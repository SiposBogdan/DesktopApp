package launcher;



import controller.VideoGameController;
import database.DatabaseConnectionFactory;
import javafx.stage.Stage;

import mapper.VideoGameMapper;
import model.User;
import repository.order.OrderRepository;
import repository.order.OrderRepositoryMySQL;
import repository.sale.SaleRepository;
import repository.sale.SaleRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import repository.videoGame.Cache;
import repository.videoGame.VideoGameRepository;
import repository.videoGame.VideoGameRepositoryCacheDecorator;
import repository.videoGame.VideoGameRepositoryMySQL;
import service.order.OrderService;
import service.order.OrderServiceImpl;
import service.videoGame.VideoGameService;
import service.videoGame.VideoGameServiceImpl;
import view.VideoGameView;
import view.model.VideoGameDTO;


import java.sql.Connection;
import java.util.List;

public class ComponentFactory {
//    private final VideoGameView videoGameView;
//    private final VideoGameController videoGameController;
//    private final VideoGameRepository videoGameRepository;
//    private final VideoGameService videoGameService;
//    private final OrderService orderService;
//    private final OrderRepository orderRepository;
//    private static volatile ComponentFactory instance;
//    private static User user;
//
//    public static ComponentFactory getInstance(Boolean componentsForTest, Stage stage, User user) {
//        if (instance == null) {
//            synchronized (ComponentFactory.class) {
//                if (instance == null) {
//                    instance = new ComponentFactory(componentsForTest, stage, user);
//                }
//            }
//        }
//        return instance;
//    }
//
//    private ComponentFactory(Boolean componentsForTest, Stage stage, User user) {
//        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(componentsForTest).getConnection();
//        this.orderRepository = new OrderRepositoryMySQL(connection);
//        this.orderService = new OrderServiceImpl(orderRepository);
//        this.videoGameRepository = new VideoGameRepositoryCacheDecorator(new VideoGameRepositoryMySQL(connection), new Cache<>());
//        this.videoGameService = new VideoGameServiceImpl(videoGameRepository);
//        List<VideoGameDTO> videoGameDTOs = VideoGameMapper.convertVideoGameListToVideoGameDTOList(this.videoGameService.findAll());
//        this.videoGameView = new VideoGameView(stage, videoGameDTOs);
//        this.videoGameController = new VideoGameController( videoGameService, videoGameView, orderService, user);
//    }
//
//    public VideoGameView getVideoGameView() {
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
}