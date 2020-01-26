package alerts;

import javafx.scene.control.Alert;

public class UnsavedChangesInFileAlert implements GeneralAlert{
    public UnsavedChangesInFileAlert() {

    }

    @Override
    public void alertWithNoReturnType() {
    }

    @Override
    public Alert alertWithReturnType() {
        Alert alert = new Alert(getAlertType());
        alert.setTitle(getConnectionErrorTitle());
        alert.setHeaderText(getConnectionErrorHeader());
        alert.setContentText(getConnectionErrorContent());
        alert.showAndWait();

        return alert;
    }


    private String getConnectionErrorTitle() {
        return "File save status";
    }

    private String getConnectionErrorHeader() {
        return "Unsaved changes";
    }

    private String getConnectionErrorContent() {
        return "The are unsaved changes in the current Dockerfile.\n" +
                "All unsaved changes will be lost. Do you want to continue?";
    }

    private Alert.AlertType getAlertType() {
        return Alert.AlertType.CONFIRMATION;
    }
}
