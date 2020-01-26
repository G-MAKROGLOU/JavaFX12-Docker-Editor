package alerts;

import javafx.scene.control.Alert;

public class DockerfileSavedSuccessfully implements GeneralAlert {
    public DockerfileSavedSuccessfully() {

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
        return "Dockerfile status";
    }

    private String getConnectionErrorHeader() {
        return "Successfully saved changes in Dockerfile";
    }

    private String getConnectionErrorContent() {
        return "All changes made in this Dockerfile were\n" +
                "saved successfully.Do you want to make \n" +
                "any other changes?";
    }

    private Alert.AlertType getAlertType() {
        return Alert.AlertType.CONFIRMATION;
    }
}
