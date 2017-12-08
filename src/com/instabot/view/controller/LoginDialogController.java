package com.instabot.view.controller;

import com.instabot.view.controller.AbstractController;
import com.instabot.service.LoginService;
import com.instabot.service.PreferencesService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginDialogController extends AbstractController {

    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CheckBox checkBox;

    @Autowired
    private PreferencesService preferencesService;

    @Autowired
    private LoginService loginService;

    private Stage dialogStage;
    private Instagram4j instagram;


    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(final Stage dialogStage) {
        this.dialogStage = dialogStage;

        if (preferencesService.get(PreferencesService.SAVE_CREDENTIALS) != null) {
            loginField.setText(preferencesService.get(PreferencesService.USER_USERNAME));
            passwordField.setText(preferencesService.get(PreferencesService.USER_PASSWORD));
            checkBox.setSelected(true);
        }
    }

    /**
     * Sets the instagram instance
     */
    public Instagram4j getInstagram() {
        return instagram;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            final String username = loginField.getText();
            final String password = passwordField.getText();
            this.instagram = loginService.login(username, password);
            if (this.instagram != null) {
                if (checkBox.isSelected()) {
                    preferencesService.save(PreferencesService.USER_USERNAME, loginField.getText());
                    preferencesService.save(PreferencesService.USER_PASSWORD, passwordField.getText());
                } else {
                    preferencesService.save(PreferencesService.USER_USERNAME, null);
                    preferencesService.save(PreferencesService.USER_PASSWORD, null);
                }
                dialogStage.close();
            }
        }
    }

    /**
     * Called when the user clicks on checkbox.
     */
    @FXML
    private void handleCheckBox() {
        if (checkBox.isSelected()) {
            preferencesService.save(PreferencesService.SAVE_CREDENTIALS, "true");
        } else {
            preferencesService.save(PreferencesService.SAVE_CREDENTIALS, null);
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (loginField.getText() == null || loginField.getText().length() == 0) {
            errorMessage += "No valid user login!\n";
        }
        if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "No valid user password!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
