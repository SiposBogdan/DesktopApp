package database;

public class DatabaseConnectionFactory {
    private static final String SCHEMA = "video_game_store";
    private static final String TEST_SCHEMA = "test_video_game_store";

    public static JDBConnectionWrapper getConnectionWrapper(boolean test){
        if (test){
            return new JDBConnectionWrapper(TEST_SCHEMA);
        } else {
            return new JDBConnectionWrapper(SCHEMA);
        }
    }
}
