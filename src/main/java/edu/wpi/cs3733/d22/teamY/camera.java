package edu.wpi.cs3733.d22.teamY;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.util.ImageUtils;
import edu.wpi.cs3733.d22.teamY.controllers.PersonalSettings;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class camera {

  private static String imageFolder =
      "src/main/resources/edu/wpi/cs3733/d22/teamY/views/profilePics/";
  private static String fileType = ".jpg";
  static Webcam webcam = Webcam.getDefault();

  public static void newPfp() throws IOException {
    webcam.open();
    Dimension frame = webcam.getViewSize();
    double x = frame.getWidth();
    double y = frame.getHeight();
    System.out.println("hi");
    System.out.println(x + "," + y);
    BufferedImage image = webcam.getImage();
    BufferedImage image2 = image.getSubimage((int) ((x - y) / 2), 0, (int) y, (int) y);
    ImageIO.write(
        image2,
        ImageUtils.FORMAT_JPG,
        new File(imageFolder + PersonalSettings.currentEmployee.getIDNumber() + fileType));
    webcam.close();
  }
}
