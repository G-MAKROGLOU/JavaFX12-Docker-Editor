package alerts;

import javafx.scene.control.Alert;

public class NoSaveOptionSelected implements GeneralAlert {

    public NoSaveOptionSelected() {

    }

    @Override
    public void alertWithNoReturnType() {
        Alert alert = new Alert(getAlertType());
        alert.setTitle(getAlertTitle());
        alert.setHeaderText(getAlertHeader());
        alert.setContentText(getAlertContent());
        alert.showAndWait();
    }

    @Override
    public Alert alertWithReturnType() { return null; }


    private String getAlertTitle() {
        return "Save options";
    }

    private String getAlertHeader() {
        return "No save option was selected";
    }

    private String getAlertContent() {
        return "No save option was selected from the pop up.\n" +
                "Try again with a selected option if you want\n" +
                "to save a file.";
    }

    private Alert.AlertType getAlertType() {
        return Alert.AlertType.ERROR;
    }
}
