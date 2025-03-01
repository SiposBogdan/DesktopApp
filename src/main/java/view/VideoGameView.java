package view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.GameGenres;
import model.VideoGame;
import view.model.VideoGameDTO;


import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class VideoGameView {
    private TableView videoGameTableView;
    private final ObservableList<VideoGameDTO> videoGameObservableList;
    private TextField publisherTextField;
    private TextField titleTextField;
    private TextField genreTextField;
    private Label publisherLabel;
    private Label titleLabel;
    private Label genreLabel;
    private ComboBox<String> genreComboBox;

    private Button saveButton;
    private Button deleteButton;

    public VideoGameView(Stage primaryStage, List<VideoGameDTO> videoGames) {
        primaryStage.setTitle("Store");

        GridPane gridPane = new GridPane();
        initializeGridPage(gridPane);

        Scene scene = new Scene(gridPane, 800, 500);
        primaryStage.setScene(scene);

        videoGameObservableList = FXCollections.observableArrayList(videoGames);
        initTableView(gridPane);
        initSaveOptions(gridPane);

        primaryStage.show();

    }

    private void initializeGridPage(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initTableView(GridPane gridPane) {
        videoGameTableView = new TableView<VideoGameDTO>();
        videoGameTableView.setPlaceholder(new javafx.scene.control.Label("No video games to show"));

        TableColumn<VideoGameDTO, String> titleColumn = new TableColumn<VideoGameDTO, String>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<VideoGameDTO, String> publisherColumn = new TableColumn<VideoGameDTO, String>("Publisher");
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        TableColumn<VideoGameDTO, String> genreColumn = new TableColumn<VideoGameDTO, String>("Genre");
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        videoGameTableView.getColumns().addAll(titleColumn, publisherColumn, genreColumn);
        videoGameTableView.setItems(videoGameObservableList);

        gridPane.add(videoGameTableView, 0, 0, 7, 1);

    }

    private void initSaveOptions(GridPane gridPane) {
        titleLabel = new Label("Title");
        gridPane.add(titleLabel, 1, 1);

        titleTextField = new TextField();
        gridPane.add(titleTextField, 2, 1);

        publisherLabel = new Label("Publisher");
        gridPane.add(publisherLabel, 3, 1);

        publisherTextField = new TextField();
        gridPane.add(publisherTextField, 4, 1);

        genreLabel = new Label("Genre");
        gridPane.add(genreLabel, 5, 1);
        genreComboBox = new ComboBox<>();

        genreComboBox.setItems(FXCollections.observableArrayList(Arrays.stream(GameGenres.values())
                .map(Enum::name)
                .toList()));
        gridPane.add(genreComboBox, 6, 1);

//        genreTextField = new TextField();
//        gridPane.add(genreTextField, 6, 1);

        saveButton = new Button("Save");
        gridPane.add(saveButton, 7, 1);

        deleteButton = new Button("Delete");
        gridPane.add(deleteButton, 8, 1);
    }
    public void addSaveButtonListener(EventHandler<ActionEvent> saveButtonListener){
        saveButton.setOnAction(saveButtonListener);
    }

    public void addDeleteButtonListener(EventHandler<ActionEvent> deleteButtonListener){
        deleteButton.setOnAction(deleteButtonListener);
    }

    public void addDisplayAlertMessage(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
    public String getTitle() {
        return titleTextField.getText();
    }
    public String getPublisher() {
        return publisherTextField.getText();
    }
    public String getGenre () {
//        return genreTextField.getText();
        return genreComboBox.getValue();
    }

    public void addVideoGameToObservableList(VideoGameDTO videoGameDTO){
        this.videoGameObservableList.add(videoGameDTO);
    }
    public void removeVideoGameToObservableList(VideoGameDTO videoGameDTO){
        this.videoGameObservableList.remove(videoGameDTO);
    }
    public TableView getVideoGameTableView(){
        return videoGameTableView;
    }

}
