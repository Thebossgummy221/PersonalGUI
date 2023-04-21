package com.musicfx.musicfxfun;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class MusicController implements Initializable {
    public ComboBox<String> comboBox1 = new ComboBox<>();
  // public Media[] myMusic;
   public LinkedList<String>fileNames = new LinkedList<>();
   File[] Directory;
    String[] myArray;
   ObservableList<String> SongNames;
    MediaPlayer MP;
   private void setUpStuff(){
       //myMusic = new Media[10];
       try{
           File FileDirectory = new File("src/main/resources/com/musicfx/musicfxfun/mp3Files");
           Directory = FileDirectory.listFiles();
           assert Directory != null;
           for(var fName: Directory){
               this.fileNames.add(fName.getName());
           }
          myArray = fileNames.toArray(new String[0]);
           SongNames = FXCollections.observableArrayList(myArray);
           comboBox1.setItems(SongNames);
           comboBox1.show();
       }
       catch (Exception E){
           System.out.println(E.getMessage());
       }
   }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      setUpStuff();

    }

    public void play() {
       if(MP!=null){
           MP.stop();
       }
       if(comboBox1.getSelectionModel().getSelectedItem()==null){
           return;
       }
       int index = comboBox1.getSelectionModel().getSelectedIndex();

       String path = Directory[index].toString();
       Media m1 = new Media(new File(path).toURI().toString());
       MP = new MediaPlayer(m1);
       MP.setBalance(-0.25);
       MP.play();

    }

    public void stop() {
       if(MP!=null){
           MP.stop();
       }
       comboBox1.getSelectionModel().clearSelection();

    }

}