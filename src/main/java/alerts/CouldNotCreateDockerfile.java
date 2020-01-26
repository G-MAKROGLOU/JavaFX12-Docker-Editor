package alerts;

import javafx.scene.control.Alert;

public class CouldNotCreateDockerfile implements GeneralAlert {
    private String folderName;


    public CouldNotCreateDockerfile() {

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
        return "Error creating Dockerfile";
    }

    private String getAlertContent() {
        return "The dockerfile under folder:" + getFolderName() + "\n" +
                "could not be created. Check the naming convention you used\n" +
                "and try again";
    }

    private Alert.AlertType getAlertType() {
        return Alert.AlertType.ERROR;
    }
}
