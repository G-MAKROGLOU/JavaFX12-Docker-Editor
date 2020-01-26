package docker_management;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class DefaultDockerfile  implements Initializable {
    private boolean isSelected = false;
    private String fileName;
    private String filePath;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() { return filePath; }

    void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @FXML private TextFlow dockerFileInformation;
    @FXML private AnchorPane rootPane;
    @FXML private CheckBox selectionCheckBox;
    @FXML private ImageView dockerLogo;

    CheckBox getSelectionCheckBox() {
        return selectionCheckBox;
    }

    AnchorPane getRootPane() {
        return rootPane;
    }

    TextFlow getDockerFileInformation() {
        return dockerFileInformation;
    }

    public void setDockerFileInformation(TextFlow dockerFileInformation) {
        this.dockerFileInformation = dockerFileInformation;
    }

    private ImageView getDockerLogo() {
        return dockerLogo;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getDockerLogo().setImage(new Image(new FileInputStream("C:\\Users\\GMKRG\\Desktop\\GitHubRepos\\Java\\Workspace\\DockerEditor\\images\\docker_50px.png")));
        } catch (IOException e) {
            e.printStackTrace( );
        }

    }
}