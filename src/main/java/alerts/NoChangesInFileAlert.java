package alerts;

import javafx.scene.control.Alert;

public class NoChangesInFileAlert implements GeneralAlert {
    public NoChangesInFileAlert() {

    }

    @Override
    public void alertWithNoReturnType() {
        Alert alert = new Alert(getAlertType());
        alert.setTitle(getConnectionErrorTitle());
        alert.setHeaderText(getConnectionErrorHeader());
        alert.setContentText(getConnectionErrorContent());
        alert.showAndWait();
    }

    @Override
    public Alert alertWithReturnType() {
        return null;
    }


    private String getConnectionErrorTitle() {
        return "File save status";
    }

    private String getConnectionErrorHeader() {
        return "No changes detected";
    }

    private String getConnectionErrorContent() {
        return "No changes were made in the Dockerfile you are trying to save.\n" +
                "Edit the Dockerfile to save changes or open another Dockerfile\n" +
                "in the editor and start working on it.";
    }

    private Alert.AlertType getAlertType() {
        return Alert.AlertType.INFORMATION;
    }
}
