import database.DatabaseConnectionFactory;
import model.GameGenres;
import model.VideoGame;
import model.builder.VideoGameBuilder;

import java.time.LocalDate;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
//        System.out.printf("Hello and welcome!");
//
//        System.out.println();
//        VideoGame game = new VideoGameBuilder().setTitle("Title")
//                .setGenre(GameGenres.ADVENTURE)
//                .setPublisher("Publisher")
//                .setPublishedDate(LocalDate.of(2000,12,4))
//                .build();
//        System.out.println(game);
        DatabaseConnectionFactory.getConnectionWrapper(false);

    }
}