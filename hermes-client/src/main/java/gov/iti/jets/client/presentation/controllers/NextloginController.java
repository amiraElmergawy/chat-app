package gov.iti.jets.client.presentation.controllers;

import java.net.URL;
import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import common.business.dtos.UserAuthDto;
import gov.iti.jets.client.business.services.util.ServiceFactory;
import gov.iti.jets.client.presentation.models.UserModel;
import gov.iti.jets.client.presentation.util.ModelsFactory;
import gov.iti.jets.client.presentation.util.StageCoordinator;
import gov.iti.jets.client.presistance.network.RMIConnection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class NextloginController implements Initializable {
    
    @FXML
    private Button signInButton;

    private final StageCoordinator stageCoordinator = StageCoordinator.INSTANCE;
	private final ModelsFactory modelsFactory = ModelsFactory.INSTANCE;

	private UserModel userModel = modelsFactory.getUserModel();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			// lookup("Login") = Login same interface name.
			Registry registry = LocateRegistry.getRegistry();
			for (var s : registry.list()) {
				System.out.println(s);
			}

		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		// nameTextField.textProperty().bindBidirectional(userModel.userNameProperty());
		// passwordTextField.textProperty().bindBidirectional(userModel.passwordProperty());

	}
    @FXML
    void signinButtonAction(ActionEvent event) {
        Platform.runLater(() -> {

            try {
                RMIConnection.INSTANCE.getServer().login(ServiceFactory.INSTANCE.getClientImpl(),
                        new UserAuthDto("01149056691", "456"));
    
            } catch (RemoteException e) {
    
                e.printStackTrace();
            }
    
            stageCoordinator.switchtoHomePageScene();
        });
    }

    @FXML
    void signinKeyPressed(KeyEvent event) {
        if(event.getCode()==KeyCode.ENTER){
			stageCoordinator.switchToLoginScene();
		}
    }
   

}