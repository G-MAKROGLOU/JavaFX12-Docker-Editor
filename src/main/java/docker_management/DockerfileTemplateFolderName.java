package docker_management;

import alerts.CouldNotCreateFolder;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;

public class DockerfileTemplateFolderName {
    private String folderName;
    private SaveOptions controller;
    private Stage popUpStage;

    @FXML private TextField folderNameTextField;

    private Stage getPopUpStage() { return popUpStage; }

    void setPopUpStage(Stage popUpStage) { this.popUpStage = popUpStage; }

    private TextField getFolderNameTextField() { return folderNameTextField; }

    private String getFolderName() { return folderName; }

    private void setFolderName(String folderName) { this.folderName = folderName; }

    public SaveOptions getController() { return controller; }

    public void setController(SaveOptions controller) { this.controller = controller; }


    public void createFolder(){
        setFolderName(getFolderNameTextField().getText());
        File dockerFileFolder = new File("C:\\Users\\GMKRG\\Desktop\\GitHubRepos\\Java\\Workspace\\DockerEditor\\src\\main\\resources\\fxml\\docker_management\\dockerfiles\\"+getFolderName());
        if(dockerFileFolder.mkdir()){
            getController().setFolderName(getFolderName());
            closeStage();
        }else{
            CouldNotCreateFolder alert = new CouldNotCreateFolder();
            alert.setFolderName(dockerFileFolder.getName());
            alert.alertWithNoReturnType();
        }
    }


    public void closeStage(){
        getPopUpStage().close();
    }


}
