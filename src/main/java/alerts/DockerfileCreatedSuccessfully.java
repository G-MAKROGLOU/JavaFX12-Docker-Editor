package alerts;

import javafx.scene.control.Alert;

public class DockerfileCreatedSuccessfully implements GeneralAlert {
    private String folderName;


    public DockerfileCreatedSuccessfully() {

    }

    private String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
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
        return "Dockerfile creation status";
    }

    private String getAlertHeader() {
        return "Successfully created Dockerfile";
    }

    private String getAlertContent() {
        return "The dockerfile was created successfully under folder:\n" +
                getFolderName();
    }

    private Alert.AlertType getAlertType() {
        return Alert.AlertType.INFORMATION;
    }
}
