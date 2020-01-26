package alerts;

import javafx.scene.control.Alert;

public class DockerfileAddedToTemplatesAlert implements GeneralAlert {

    public DockerfileAddedToTemplatesAlert() {

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
        return "Successfully added new Dockerfile";
    }

    private String getConnectionErrorContent() {
        return "1 new Dockerfile was added to your Dockerfile templates\n" +
                "You will see it as soon as you close this pop up.";
    }

    private Alert.AlertType getAlertType() {
        return Alert.AlertType.INFORMATION;
    }
}
