package launcher;

import controller.VideoGameController;
import database.DatabaseConnectionFactory;
import javafx.stage.Stage;
import mapper.VideoGameMapper;
import model.VideoGame;
import repository.VideoGameReposistory;
import repository.VideoGameRepositoryMySQL;
import service.VideoGameService;
import service.VideoGameServiceImpl;
import view.VideoGameView;
import view.model.VideoGameDTO;

import java.sql.Connection;
import java.util.List;

public class ComponentFactory {
    private final VideoGameView videoGameView;
    private final VideoGameController videoGameController;
    private final VideoGameReposistory videoGameReposistory;
    private final VideoGameService videoGameService;
    private static ComponentFactory instance;
    public static ComponentFactory getInstance(Boolean componentsForTest, Stage primaryStage){
        if (instance == null) {
            instance = new ComponentFactory(componentsForTest, primaryStage);
        }
        return instance;
    }
    public ComponentFactory(Boolean componentsForTest, Stage primaryStage){
        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(componentsForTest).getConnection();
        this.videoGameReposistory = new VideoGameRepositoryMySQL(connection);
        this.videoGameService = new VideoGameServiceImpl(videoGameReposistory);

        List<VideoGameDTO> videoGames = VideoGameMapper.convertVideoGameListToVideoGameDTOList(videoGameService.findAll());
        this.videoGameView = new VideoGameView(primaryStage, videoGames);
        this.videoGameController = new VideoGameController(videoGameService, videoGameView);
    }

    public VideoGameView getVideoGameView() {

        return videoGameView;
    }

    public VideoGameController getVideoGameController() {

        return videoGameController;
    }

    public VideoGameReposistory getVideoGameReposistory() {

        return videoGameReposistory;
    }

    public VideoGameService getVideoGameService() {

        return videoGameService;
    }
}
