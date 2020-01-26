package alerts;

import javafx.scene.control.Alert;

public class FailedToSaveDockerfile implements GeneralAlert {
    public FailedToSaveDockerfile() {

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
    public Alert alertWithReturnType() { return null; }


    private String getConnectionErrorTitle() {
        return "Dockerfile status";
    }

    private String getConnectionErrorHeader() {
        return "Failed to save Dockerfile";
    }

    private String getConnectionErrorContent() {
        return "The changes in this Dockerfile were not saved.\n" +
                "Make sure that the file is still in the same\n" +
                "since you opened it.";
    }

    private Alert.AlertType getAlertType() {
        return Alert.AlertType.INFORMATION;
    }
}
