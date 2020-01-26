package docker_management;

import alerts.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.PopOver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class SaveOptions implements Initializable {
    private ArrayList<CheckBox> checkBoxes;
    private String content;
    private PopOver popOver;
    private String folderName;
    private SaveOptions saveOptionsController;
    private DockerMain dockerMainController;
    private boolean isOpened;
    private File openedFiledLocation;
    private String initialContent;

    private String getInitialContent() {
        return initialContent;
    }

    void setInitialContent(String initialContent) {
        this.initialContent = initialContent;
    }

    public boolean isOpened() { return isOpened; }

    public void setOpened(boolean opened) { isOpened = opened; }

    private File getOpenedFiledLocation() { return openedFiledLocation; }

    void setOpenedFiledLocation(File openedFiledLocation) { this.openedFiledLocation = openedFiledLocation; }

    @FXML private CheckBox localOnly;
    @FXML private CheckBox templatesOnly;
    @FXML private CheckBox saveChangesInFile;

    private String getFolderName() { return folderName; }

    void setFolderName(String folderName) { this.folderName = folderName; }

    private ArrayList<CheckBox> getCheckBoxes() { return checkBoxes; }

    private void setCheckBoxes(ArrayList<CheckBox> checkBoxes) { this.checkBoxes = checkBoxes; }

    private String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    private PopOver getPopOver() { return popOver; }

    void setPopOver(PopOver popOver) { this.popOver = popOver; }

    private SaveOptions getSaveOptionsController() { return saveOptionsController; }

    void setSaveOptionsController(SaveOptions saveOptionsController) { this.saveOptionsController = saveOptionsController; }

    private DockerMain getDockerMainController() {
        return dockerMainController;
    }

    void setDockerMainController(DockerMain dockerMainController) {
        this.dockerMainController = dockerMainController;
    }

    private CheckBox getLocalOnly() { return localOnly; }

    private CheckBox getTemplatesOnly() { return templatesOnly; }

    CheckBox getSaveChangesInFile() { return saveChangesInFile; }



    public void confirmSaveOption(){
        if(getLocalOnly().isSelected())
            saveInFileSystem();
        else if(getTemplatesOnly().isSelected())
            saveInTemplates();
        else if(getSaveChangesInFile().isSelected())
            saveChangesInFile();
        else{
            NoSaveOptionSelected alert = new NoSaveOptionSelected();
            alert.alertWithNoReturnType();
            getPopOver().hide();
        }
    }


    private void saveChangesInFile(){
        if(getContent().compareTo(getInitialContent()) == 0){
            NoChangesInFileAlert alert = new NoChangesInFileAlert();
            alert.alertWithNoReturnType();
        }else{
            try{
                File file = new File(getOpenedFiledLocation().getPath());
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write(getContent());
                bufferedWriter.close();
                Alert alert = new DockerfileSavedSuccessfully().alertWithReturnType();
                if(alert.getResult() == ButtonType.OK){
                    getDockerMainController().setOpened(isOpened());
                    getDockerMainController().setOpenedFiledLocation(getOpenedFiledLocation());
                    getDockerMainController().setInitialEditorContent(getInitialContent());
                }else{
                    setOpened(false);
                    setOpenedFiledLocation(null);
                    getDockerMainController().setOpenedFiledLocation(null);
                    getDockerMainController().setOpened(isOpened());
                    getDockerMainController().setInitialEditorContent(null);
                    getDockerMainController().getDockerEditorBase().getEngine().executeScript("editor.setValue('')");
                    getDockerMainController().loadDefaultDockerFiles();
                }
            }catch (IOException exception){
                FailedToSaveDockerfile alert = new FailedToSaveDockerfile();
                alert.alertWithNoReturnType();
                exception.printStackTrace();
            }
        }
    }


    private void saveInFileSystem(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Dockerfile", "*.*"));
        File locationToBeSaved = fileChooser.showSaveDialog(null);
        if(locationToBeSaved != null){
            if(locationToBeSaved.mkdir()){
                String path = locationToBeSaved.getPath()+"/Dockerfile";
                File dockerfile = new File(path);
                boolean writeSucceededOrNot = exportEditorToFile(dockerfile, getContent());
                if(writeSucceededOrNot){
                    getDockerMainController().loadDefaultDockerFiles();
                    DockerfileCreatedSuccessfully alert = new DockerfileCreatedSuccessfully();
                    alert.setFolderName(locationToBeSaved.getName());
                    alert.alertWithNoReturnType();
                }else{
                    CouldNotCreateDockerfile alert = new CouldNotCreateDockerfile();
                    alert.setFolderName(locationToBeSaved.getName());
                    alert.alertWithNoReturnType();
                }
            }else{
                CouldNotCreateFolder alert = new CouldNotCreateFolder();
                alert.setFolderName(locationToBeSaved.getName());
                alert.alertWithNoReturnType();
            }
        }
    }



    private boolean exportEditorToFile(File fileToBeSaved, String content){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToBeSaved));
            bufferedWriter.write(content);
            bufferedWriter.close();
            return true;
        }catch (IOException exception){
            exception.printStackTrace();

        }
        return false;
    }



    private void saveInTemplates(){
        showFolderNamePopUp();
        File dockerfile = new File("C:\\Users\\GMKRG\\Desktop\\GitHubRepos\\Java\\Workspace\\DockerEditor\\src\\main\\resources\\fxml\\docker_management\\dockerfiles\\"+getFolderName()+"/Dockerfile");
        boolean writeSucceededOrNot = exportEditorToFile(dockerfile, getContent());
        if(writeSucceededOrNot){
            getDockerMainController().loadDefaultDockerFiles();
            DockerfileCreatedSuccessfully alert = new DockerfileCreatedSuccessfully();
            alert.setFolderName(getFolderName());
            alert.alertWithNoReturnType();
        }else{
            CouldNotCreateDockerfile alert = new CouldNotCreateDockerfile();
            alert.setFolderName(getFolderName());
            alert.alertWithNoReturnType();
        }
    }



    private void showFolderNamePopUp(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/docker_management/dockerfileTemplateFolderName.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }catch (IOException exception){
            exception.printStackTrace();
        }
        assert root != null;
        DockerfileTemplateFolderName controller = loader.getController();
        Scene scene = new Scene(root, 350, 120);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Folder name");
        stage.setResizable(false);

        controller.setPopUpStage(stage);
        controller.setController(getSaveOptionsController());

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.showAndWait();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCheckBoxes(new ArrayList<>(Arrays.asList(
                getSaveChangesInFile(),
                getLocalOnly(),
                getTemplatesOnly()
        )));

        for(CheckBox cb : getCheckBoxes()){
            cb.setOnAction(event -> {
                for(CheckBox checkBox : getCheckBoxes()){
                    if(checkBox.isSelected()){
                        checkBox.setSelected(false);
                    }
                }
                cb.setSelected(true);
            });
        }

    }


}

