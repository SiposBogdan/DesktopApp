package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import launcher.AdminComponentFactory;
import launcher.EmployeeComponentFactory;
import mapper.VideoGameMapper;
import model.Order;
import model.User;
import model.builder.OrderBuilder;
import service.order.OrderService;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceImpl;
import service.videoGame.VideoGameService;
import view.LoginView;
import view.VideoGameView;
import view.model.VideoGameDTO;
import view.model.builder.VideoGameDTOBuilder;

import java.sql.Timestamp;
import java.time.Instant;

import static java.lang.Boolean.TRUE;


public class VideoGameController {

   private final VideoGameView videoGameView;
   private final VideoGameService videoGameService;
    private final OrderService orderService;
    private final User user;
    private final AuthenticationService authenticationService;


    public VideoGameController(VideoGameService videoGameService, VideoGameView videoGameView,OrderService orderService, User user, AuthenticationService authenticationService){
       this.user = user;
       this.videoGameView = videoGameView;
       this.videoGameService = videoGameService;
       this.orderService = orderService;
       this.authenticationService = authenticationService;

       this.videoGameView.addSaveButtonListener(new SaveButtonListener());
       this.videoGameView.addDeleteButtonListener(new DeleteButtonListener());
       this.videoGameView.addSellButtonListener(new SellButtonListener());
       this.videoGameView.addLogoutButtonListener(new LogoutButtonListener());
   }
   private class SaveButtonListener implements EventHandler<ActionEvent> {
       @Override
       public void handle(ActionEvent event) {
           String title = videoGameView.getTitle();
           String publisher = videoGameView.getPublisher();
           String genre = videoGameView.getGenre();
           int stock = videoGameView.getStock();
           float price = videoGameView.getPrice();

           if (title.isEmpty() || publisher.isEmpty() || genre.isEmpty()) {
               videoGameView.addDisplayAlertMessage("Save Error", "Problem at Title or Publisher or Genre fields", "Can not have an empty Title or Publisher or Genre field.");
           } else {
               if (stock < 0) {
                   videoGameView.addDisplayAlertMessage("Save Error", "Invalid Stock Value",
                           "Stock must be a non-negative integer.");
               } else {

                   // Validate price (should be a positive float)
                   if (price < 0) {
                       videoGameView.addDisplayAlertMessage("Save Error", "Invalid Price Value",
                               "Price must be a non-negative number.");
                   } else {

                       VideoGameDTO videoGameDTO = new VideoGameDTOBuilder().setTitle(title).setPublisher(publisher).setGenre(genre).setStock(stock).setPrice(price).build();


                       boolean savedVideoGame = videoGameService.save(VideoGameMapper.convertVideoGameDTOToVideoGame(videoGameDTO));

                       if (savedVideoGame) {
                           videoGameView.addDisplayAlertMessage("Save Successful", "Video game Added", "Video Game was successfully added to the database.");
                           videoGameView.addVideoGameToObservableList(videoGameDTO);
                       } else {
                           videoGameView.addDisplayAlertMessage("Save Error", "Problem at adding Video Game", "There was a problem at adding the video game to the database. Please try again!");
                       }
                   }
               }
           }
       }
   }
       private class DeleteButtonListener implements EventHandler<ActionEvent>{

           @Override
           public void handle(ActionEvent event) {
               VideoGameDTO videoGameDTO = (VideoGameDTO) videoGameView.getVideoGameTableView().getSelectionModel().getSelectedItem();
               if (videoGameDTO != null){
                   boolean deletionSuccessful = videoGameService.delete(VideoGameMapper.convertVideoGameDTOToVideoGame(videoGameDTO));

                   if (deletionSuccessful){
                       videoGameView.addDisplayAlertMessage("Delete Successful", "Video game Deleted", "Video game was successfully deleted from the database.");
                       videoGameView.removeVideoGameToObservableList(videoGameDTO);
                   } else {
                       videoGameView.addDisplayAlertMessage("Delete Error", "Problem at deleting video game", "There was a problem with the database. Please try again!");
                   }
               } else {
                   videoGameView.addDisplayAlertMessage("Delete Error", "Problem at deleting video game", "You must select a video game before pressing the delete button.");
               }
           }
       }
    private class SellButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            VideoGameDTO videoGameDTO = (VideoGameDTO) videoGameView.getVideoGameTableView().getSelectionModel().getSelectedItem();
            VideoGameDTO oldVideoGameDTO = videoGameDTO;
            if (videoGameView.getQuantity() < 0) {
                videoGameView.addDisplayAlertMessage("Order Error", "Invalid Quantity Value", "Price must be a non-negative number."
                );

            } else {
                if (videoGameDTO != null) {
                    boolean updateSuccessful = TRUE;

                    if (videoGameDTO.getStock() < videoGameView.getQuantity()) {
                        videoGameView.addDisplayAlertMessage("Order Error", "Problem at ordering", "There was not enough stock.");
                    } else {

                        updateSuccessful = videoGameService.update(
                                VideoGameMapper.convertVideoGameDTOToVideoGame(videoGameDTO),
                                videoGameDTO.getStock() - videoGameView.getQuantity()
                        );


                        if (updateSuccessful) {
                            Order order = new OrderBuilder()
                                    .setId(null)
                                    .setVideoGameTitle(videoGameDTO.getTitle())
                                    .setVideoGamePublisher(videoGameDTO.getPublisher())
                                    .setTimestamp(Timestamp.from(Instant.now()))
                                    .setQuantity(videoGameView.getQuantity())
                                    .setUserId(user.getId())
                                    .build();

                            orderService.save(order);
                            videoGameView.updateVideoGameToObservableList(videoGameDTO, videoGameDTO.getStock() - videoGameView.getQuantity());
                            videoGameView.addDisplayAlertMessage("Success", "Order Created", "The video game order was successfully made!");
                        } else {
                            videoGameView.addDisplayAlertMessage("Order Error", "Problem at ordering", "There was a problem with the order. Please try again.");
                        }
                    }

                } else {
                    videoGameView.addDisplayAlertMessage("Order Error", "Problem at updating video game", "");
                }
            }

        }
    }
    public class LogoutButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout Confirmation");
            alert.setHeaderText("Are you sure you want to log out?");
            alert.setContentText("Your session will be closed.");

            ButtonType confirmButton = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(confirmButton, cancelButton);

            alert.showAndWait().ifPresent(response -> {
                if (response == confirmButton) {
                    Stage currentStage = (Stage) videoGameView.getVideoGameTableView().getScene().getWindow();
                    currentStage.close();
                    EmployeeComponentFactory.resetInstance();

                    Stage loginStage = new Stage();
                    LoginView loginView = new LoginView(loginStage);

                    new LoginController(loginView, authenticationService, false);
                }
            });
        }
    }




}
