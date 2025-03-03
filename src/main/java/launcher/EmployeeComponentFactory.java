package launcher;

import controller.VideoGameController;
import database.DatabaseConnectionFactory;
import javafx.stage.Stage;
import mapper.VideoGameMapper;
import model.User;
import repository.order.OrderRepository;
import repository.order.OrderRepositoryMySQL;
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

public class EmployeeComponentFactory {

    private final VideoGameView videoGameView;
    private final VideoGameController videoGameController;
    private final VideoGameRepository videoGameRepository;
    private final VideoGameService videoGameService;
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private static volatile EmployeeComponentFactory instance;
    private static User user;

    public static EmployeeComponentFactory getInstance(Boolean componentsForTest, Stage stage, User user) {
        if (instance == null) {
            synchronized (EmployeeComponentFactory.class) {
                if (instance == null) {
                    instance = new EmployeeComponentFactory(componentsForTest, stage, user);
                }
            }
        }
        return instance;
    }

    private EmployeeComponentFactory(Boolean componentsForTest, Stage stage, User user) {
        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(componentsForTest).getConnection();
        this.orderRepository = new OrderRepositoryMySQL(connection);
        this.orderService = new OrderServiceImpl(orderRepository);
        this.videoGameRepository = new VideoGameRepositoryCacheDecorator(new VideoGameRepositoryMySQL(connection), new Cache<>());
        this.videoGameService = new VideoGameServiceImpl(videoGameRepository);
        List<VideoGameDTO> videoGameDTOs = VideoGameMapper.convertVideoGameListToVideoGameDTOList(this.videoGameService.findAll());
        this.videoGameView = new VideoGameView(stage, videoGameDTOs);
        this.videoGameController = new VideoGameController( videoGameService, videoGameView, orderService, user);
    }

    public VideoGameView getVideoGameView() {
        return videoGameView;
    }

    public VideoGameController getVideoGameController() {
        return videoGameController;
    }

    public VideoGameRepository getVideoGameRepository() {
        return videoGameRepository;
    }

    public VideoGameService getVideoGameService() {
        return videoGameService;
    }

    public static EmployeeComponentFactory getInstance() {
        return instance;
    }
}