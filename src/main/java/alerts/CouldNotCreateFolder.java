package alerts;

import javafx.scene.control.Alert;

public class CouldNotCreateFolder implements GeneralAlert {

    private String folderName;

    private String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public CouldNotCreateFolder() {

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
        return "Folder creation error";
    }

    private String getAlertHeader() {
        return "Failed to create folder";
    }

    private String getAlertContent() {
        return "The folder: " + getFolderName() + " could not be created\n" +
                "Check the folder name, or any special character in it\n" +
                "and try again";
    }

    private Alert.AlertType getAlertType() {
        return Alert.AlertType.ERROR;
    }
}
