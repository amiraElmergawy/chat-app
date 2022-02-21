package gov.iti.jets.client.presentation.util;

import java.util.regex.Pattern;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;

public enum Utils {

  INSTANCE;

  public boolean checkNumberInString(String phoneNumber) {

    Pattern pattern = Pattern.compile(".*[^0-9].*");
    return !pattern.matcher(phoneNumber).matches();

  }

  public void invitationNotification(String phone) {

    System.out.println("FROM UTILS TO SHOW INVITATION DIALOG FOR" + phone);
    Platform.runLater(()->{

      Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Delete",
              ButtonType.OK, ButtonType.CANCEL);
      alertBox.setTitle("Invitation Message");
      alertBox.setContentText("A Client Whos Number is " +phone+ " is trying you to add you");
      alertBox.initModality(Modality.APPLICATION_MODAL);
      alertBox.initOwner( StageCoordinator.INSTANCE.getPrimaryStage());
      alertBox.showAndWait();

      if (alertBox.getResult() == ButtonType.OK) {
        // CALL SERVICE TO ADD A PRIVATE CHAT TO DATA BASE BETWEEN SENDER AND RECEIVER

        System.out.println("YES");


      } else {
        // DO NOTHING

        alertBox.close();
      }
    });

  }

}

/*

 */