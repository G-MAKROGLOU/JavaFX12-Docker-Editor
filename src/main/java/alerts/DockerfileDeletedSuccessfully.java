package alerts;

import javafx.scene.control.Alert;

public class DockerfileDeletedSuccessfully implements GeneralAlert {

    private String dockerfileName;

    public String getDockerfileName() {
        return dockerfileName;
    }

    public void setDockerfileName(String dockerfileName) {
        this.dockerfileName = dockerfileName;
    }

    public DockerfileDeletedSuccessfully() {

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
        return "Dockerfile deletion dialog";
    }

    private String getConnectionErrorHeader() {
        return "Dockerfile deleted successfully";
    }

    private String getConnectionErrorContent() {
        return "The Dockerfile: " + getDockerfileName() + " was deleted successfully\n" +
                "from Workspace. Do you want to delete another database?";
    }

    private Alert.AlertType getAlertType() {
        return Alert.AlertType.INFORMATION;
    }
}
