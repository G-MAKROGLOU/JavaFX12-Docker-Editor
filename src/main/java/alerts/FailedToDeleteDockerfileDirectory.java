package alerts;

import javafx.scene.control.Alert;

public class FailedToDeleteDockerfileDirectory implements GeneralAlert {
    public FailedToDeleteDockerfileDirectory() {

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
        return "Error deleting Dockerfile directory";
    }

    private String getConnectionErrorContent() {
        return "Failed to delete the parent directory of the\n" +
                "selected Template. The folder contains more files\n" +
                "and/or folders in it and Docker editor tracks only\n" +
                "Dockerfiles. For security reasons, if you want to delete\n" +
                "this folder, remove all non Docker related files.";
    }

    private Alert.AlertType getAlertType() {
        return Alert.AlertType.ERROR;
    }
}
