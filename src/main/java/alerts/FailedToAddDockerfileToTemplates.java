package alerts;

import javafx.scene.control.Alert;

public class FailedToAddDockerfileToTemplates implements GeneralAlert {

    public FailedToAddDockerfileToTemplates() {

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
        return "Dockerfile Templates status";
    }

    private String getConnectionErrorHeader() {
        return "Error adding new Dockerfile";
    }

    private String getConnectionErrorContent() {
        return "Failed to add Dockerfile from the filesystem to\n" +
                "Workspace templates. Check if the location of the\n" +
                "file or it's name were changed and try again.";
    }

    private Alert.AlertType getAlertType() {
        return Alert.AlertType.ERROR;
    }
}
