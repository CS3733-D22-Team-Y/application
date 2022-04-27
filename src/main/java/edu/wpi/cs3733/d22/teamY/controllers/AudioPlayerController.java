package edu.wpi.cs3733.d22.teamY.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXSlider;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javax.sound.sampled.*;

public class AudioPlayerController {

  @FXML AnchorPane pane;
  @FXML MFXButton PausePlayButton;
  @FXML MFXButton NextButton;
  @FXML MFXButton PrevButton;
  @FXML MFXButton ShuffleButton;
  @FXML MFXButton musicButton;
  @FXML MFXSlider slider;
  boolean notPlaying;

  @FXML
  public void initialize()
      throws UnsupportedAudioFileException, IOException, LineUnavailableException {}
}
