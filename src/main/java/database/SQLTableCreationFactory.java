package database;

import static database.Constants.Tables.*;

public class SQLTableCreationFactory {
    public String getCreateSQLForTable(String table) {
        return switch (table) {
            case VIDEO_GAME -> "CREATE TABLE IF NOT EXISTS video_game (" +
                    "  id int(11) NOT NULL AUTO_INCREMENT," +
                    "  publisher varchar(500) NOT NULL," +
                    "  title varchar(500) NOT NULL," +
                    "  publishedDate datetime DEFAULT NULL," +
                    "  genre VARCHAR(100) NOT NULL," +
                    "  stock int not null," +
                    "  price float not null," +
                    "  PRIMARY KEY (id)," +
                    "  UNIQUE KEY id_UNIQUE (id)" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
            case USER -> "CREATE TABLE IF NOT EXISTS user (" +
                    "  id INT NOT NULL AUTO_INCREMENT," +
                    "  username VARCHAR(200) NOT NULL," +
                    "  password VARCHAR(64) NOT NULL," +
                    "  role VARCHAR(64) NOT NULL," +
                    "  PRIMARY KEY (id)," +
                    "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                    "  UNIQUE INDEX username_UNIQUE (username ASC));";
            case ROLE -> "  CREATE TABLE IF NOT EXISTS role (" +
                    "  id INT NOT NULL AUTO_INCREMENT," +
                    "  role VARCHAR(100) NOT NULL," +
                    "  PRIMARY KEY (id)," +
                    "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                    "  UNIQUE INDEX role_UNIQUE (role ASC));";
            case RIGHT -> "  CREATE TABLE IF NOT EXISTS `right` (" +
                    "  `id` INT NOT NULL AUTO_INCREMENT," +
                    "  `right` VARCHAR(100) NOT NULL," +
                    "  PRIMARY KEY (`id`)," +
                    "  UNIQUE INDEX `id_UNIQUE` (`id` ASC)," +
                    "  UNIQUE INDEX `right_UNIQUE` (`right` ASC));";
            case ROLE_RIGHT -> "  CREATE TABLE IF NOT EXISTS role_right (" +
                    "  id INT NOT NULL AUTO_INCREMENT," +
                    "  role_id INT NOT NULL," +
                    "  right_id INT NOT NULL," +
                    "  PRIMARY KEY (id)," +
                    "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                    "  INDEX role_id_idx (role_id ASC)," +
                    "  INDEX right_id_idx (right_id ASC)," +
                    "  CONSTRAINT role_id" +
                    "    FOREIGN KEY (role_id)" +
                    "    REFERENCES role (id)" +
                    "    ON DELETE CASCADE" +
                    "    ON UPDATE CASCADE," +
                    "  CONSTRAINT right_id" +
                    "    FOREIGN KEY (right_id)" +
                    "    REFERENCES `right` (id)" +
                    "    ON DELETE CASCADE" +
                    "    ON UPDATE CASCADE);";
            case USER_ROLE -> "\tCREATE TABLE IF NOT EXISTS user_role (" +
                    "  id INT NOT NULL AUTO_INCREMENT," +
                    "  user_id INT NOT NULL," +
                    "  role_id INT NOT NULL," +
                    "  PRIMARY KEY (id)," +
                    "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                    "  INDEX user_id_idx (user_id ASC)," +
                    "  INDEX role_id_idx (role_id ASC)," +
                    "  CONSTRAINT user_fkid" +
                    "    FOREIGN KEY (user_id)" +
                    "    REFERENCES user (id)" +
                    "    ON DELETE CASCADE" +
                    "    ON UPDATE CASCADE," +
                    "  CONSTRAINT role_fkid" +
                    "    FOREIGN KEY (role_id)" +
                    "    REFERENCES role (id)" +
                    "    ON DELETE CASCADE" +
                    "    ON UPDATE CASCADE);";
            case ORDERS -> "CREATE TABLE if not exists`orders` (" +
                    "`id` INT NOT NULL AUTO_INCREMENT," +
                    "`time_stamp` TIMESTAMP NOT NULL," +
                    "`user_id` INT NOT NULL," +
                    "`video_game_title` VARCHAR(255) NOT NULL," +
                    "`video_game_publisher` VARCHAR(255) NOT NULL," +
                    " quantity int not null," +
                    " PRIMARY KEY (`id`)," +
                    "FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)" +
                    " ON DELETE CASCADE " +
                    "ON UPDATE CASCADE"+
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
            default -> "";
        };
    }
}
