//import database.DatabaseConnectionFactory;
//import model.GameGenres;
//import model.VideoGame;
//import model.builder.VideoGameBuilder;
//import repository.VideoGameReposistory;
//import repository.videoGame.VideoGameRepositoryMySQL;
//import service.videoGame.VideoGameService;
//import service.videoGame.VideoGameServiceImpl;
//
//import java.sql.Connection;
//import java.time.LocalDate;
//
//// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
//// then press Enter. You can now see whitespace characters in your code.
//public class Main {
//    public static void main(String[] args) {
////        System.out.printf("Hello and welcome!");
////
////        System.out.println();
//        VideoGame basicGame = new VideoGameBuilder().setTitle("Title")
//                .setGenre(GameGenres.ADVENTURE)
//                .setPublisher("Publisher")
//                .setPublishedDate(LocalDate.of(2000,12,4))
//                .build();
//        //System.out.println(basicGame);
//
//        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(false).getConnection();
//        VideoGameReposistory videoGameReposistory = new VideoGameRepositoryMySQL(connection);
//        VideoGameService videoGameService = new VideoGameServiceImpl(videoGameReposistory, saleRepository);
//
//
//        videoGameService.save(basicGame);
//        //System.out.println(videoGameService.findAll());
//        VideoGame gameEldenRing = new VideoGameBuilder().setTitle("EldenRing")
//                .setGenre(GameGenres.ADVENTURE)
//                .setPublisher("2K")
//                .setPublishedDate(LocalDate.of(2005,10,10))
//                .build();
//        videoGameService.removeAll();
//        videoGameService.save(gameEldenRing);
//        System.out.println(videoGameService.findAll());
//
//
//    }
//}