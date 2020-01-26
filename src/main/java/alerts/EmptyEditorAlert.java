package alerts;

import javafx.scene.control.Alert;

public class EmptyEditorAlert implements GeneralAlert {

    public EmptyEditorAlert() {

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
        return "Workspace Docker editor";
    }

    private String getConnectionErrorHeader() {
        return "Editor content status";
    }

    private String getConnectionErrorContent() {
        return "The Workspace Docker editor has no content.\n" +
                "To avoid file overload, Workspace does not allow\n" +
                "the creation of empty files. Once you have your script\n" +
                "ready, try again.";
    }

    private Alert.AlertType getAlertType() {
        return Alert.AlertType.WARNING;
    }
}
