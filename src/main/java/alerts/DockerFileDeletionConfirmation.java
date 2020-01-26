package alerts;

import javafx.scene.control.Alert;

public class DockerFileDeletionConfirmation implements GeneralAlert {
    private String dockerfileName;


    @Override
    public void alertWithNoReturnType() { }

    @Override
    public Alert alertWithReturnType() {
        Alert alert = new Alert(getAlertType());
        alert.setTitle(getAlertTitle());
        alert.setHeaderText(getAlertHeader());
        alert.setContentText(getAlertContent());
        alert.showAndWait();
        return alert;
    }

    private String getDockerfileName() {
        return dockerfileName;
    }

    public void setDockerfileName(String dockerfileName) {
        this.dockerfileName = dockerfileName;
    }

    private String getAlertTitle() {
        return "Dockerfile deletion dialog";
    }

    private String getAlertHeader() {
        return "Deletion confirmation";
    }

    private String getAlertContent() {
        return "Are you sure you want to delete\n" +
                "the selected Dockerfile?\n" +
                "Name: " + getDockerfileName();
    }

    private Alert.AlertType getAlertType() {
        return Alert.AlertType.CONFIRMATION;
    }
}
