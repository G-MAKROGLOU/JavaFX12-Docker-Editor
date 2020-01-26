package alerts;

import javafx.scene.control.Alert;

public class CouldNotOpenDockerfileAlert implements GeneralAlert {

    public CouldNotOpenDockerfileAlert() {

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
        return "File system status";
    }

    private String getConnectionErrorHeader() {
        return "Failed to open file";
    }

    private String getConnectionErrorContent() {
        return "The file you selected could not be opened by Workspace\n" +
                "Docker editor. Make sure that the selected file is a document\n" +
                "and that it is not moved or used by another resource and try again.";
    }

    private Alert.AlertType getAlertType() {
        return Alert.AlertType.WARNING;
    }
}
