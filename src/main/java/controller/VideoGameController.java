package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import mapper.VideoGameMapper;
import service.VideoGameService;
import view.VideoGameView;
import view.model.VideoGameDTO;
import view.model.builder.VideoGameDTOBuilder;

import javax.sound.sampled.BooleanControl;

public class VideoGameController {

   private final VideoGameView videoGameView;
   private final VideoGameService videoGameService;
   public VideoGameController(VideoGameService videoGameService, VideoGameView videoGameView){
       this.videoGameView = videoGameView;
       this.videoGameService = videoGameService;

       this.videoGameView.addSaveButtonListener(new SaveButtonListener());
       this.videoGameView.addDeleteButtonListener(new DeleteButtonListener());
   }
   private class SaveButtonListener implements EventHandler<ActionEvent> {
       @Override
       public void handle(ActionEvent event) {
           String title = videoGameView.getTitle();
           String publisher = videoGameView.getPublisher();
           String genre = videoGameView.getGenre();

           if (title.isEmpty() || publisher.isEmpty() || genre.isEmpty()) {
               videoGameView.addDisplayAlertMessage("Save Error", "Problem at Title or Publisher or Genre fields", "Can not have an empty Title or Publisher or Genre field.");
           } else {
               VideoGameDTO videoGameDTO = new VideoGameDTOBuilder().setTitle(title).setPublisher(publisher).setGenre(genre).build();
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



}
