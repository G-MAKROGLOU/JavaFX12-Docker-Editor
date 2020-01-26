package docker_management;

import alerts.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;

import java.io.*;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class DockerMain implements Initializable {
    private DockerMain dockerMainController;
    private File openedFiledLocation;
    private boolean isOpened;
    private String initialEditorContent;

    private String getInitialEditorContent() {
        return initialEditorContent;
    }

    void setInitialEditorContent(String initialEditorContent) {
        this.initialEditorContent = initialEditorContent;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    private File getOpenedFiledLocation() {
        return openedFiledLocation;
    }

    void setOpenedFiledLocation(File openedFiledLocation) {
        this.openedFiledLocation = openedFiledLocation;
    }

    private DockerMain getDockerMainController() {
        return dockerMainController;
    }

    public void setDockerMainController(DockerMain dockerMainController) {
        this.dockerMainController = dockerMainController;
    }

    private HashMap<AnchorPane, String[]> defaultDockerFilesWithNameAndPath = new HashMap<>();

    private HashMap<AnchorPane, String[]> getDefaultDockerFilesWithNameAndPath() {
        return defaultDockerFilesWithNameAndPath;
    }

    @FXML private WebView dockerEditorBase;
    @FXML private VBox defaultDockerfileVBox;
    @FXML private SplitPane splitPane;

    @FXML private Button toggleTemplatesButton;
    @FXML private ComboBox editorThemes;

    @FXML private Button eraseEditorButton;
    @FXML private Button openDockerFileButton;
    @FXML private Button saveDockerfileButton;
    @FXML private Button addTemplateButton;
    @FXML private Button refreshTemplatesButton;
    @FXML private Button removeTemplateButton;
    @FXML private Button openTemplateButton;

    private Button getEraseEditorButton() {
        return eraseEditorButton;
    }

    private Button getOpenDockerFileButton() {
        return openDockerFileButton;
    }

    private Button getSaveDockerfileButton() {
        return saveDockerfileButton;
    }

    private Button getAddTemplateButton() {
        return addTemplateButton;
    }

    private Button getRefreshTemplatesButton() {
        return refreshTemplatesButton;
    }

    private Button getRemoveTemplateButton() {
        return removeTemplateButton;
    }

    private Button getOpenTemplateButton() {
        return openTemplateButton;
    }

    private Button getToggleTemplatesButton() { return toggleTemplatesButton; }

    private SplitPane getSplitPane() { return splitPane; }

    WebView getDockerEditorBase() { return dockerEditorBase; }

    private VBox getDefaultDockerfileVBox() { return defaultDockerfileVBox; }

    private ComboBox getEditorThemes() { return editorThemes; }



    private void loadDockerEditor(){
        WebEngine engine = getDockerEditorBase().getEngine();
        String url = DockerMain.class.getResource("/docker_editor/editor.html").toExternalForm();
        engine.load(url);
    }



    public void openDockerfileFromFileSystem(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Dockerfile",  "*.*"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile != null) {
            setOpened(true);
            setOpenedFiledLocation(selectedFile);
            showContentOnEditor(selectedFile);
            setInitialEditorContent((String) getDockerEditorBase().getEngine().executeScript("editor.getValue()"));
        }else{
            CouldNotOpenDockerfileAlert alert = new CouldNotOpenDockerfileAlert();
            alert.alertWithNoReturnType();
        }
    }



    private void showContentOnEditor(File selectedFile){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(selectedFile));
            String line;
            StringBuilder content = new StringBuilder();
            while((line = bufferedReader.readLine()) != null){
                line = line.replace("'", "\\'") ;
                line = line.replace("\n", "\\n");
                line = line.replace("\r", "\n");
                content.append(line).append("\\n");
            }
            getDockerEditorBase().getEngine().executeScript("editor.setValue('"+content+"')");
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace( );
        }
    }



    private PopOver createPopOver(Parent root){
        PopOver saveOptions = new PopOver();
        saveOptions.setAutoFix(true);
        saveOptions.setCornerRadius(15);
        saveOptions.setCloseButtonEnabled(true);
        saveOptions.setTitle("Save options");
        saveOptions.setContentNode(root);
        saveOptions.show(getSaveDockerfileButton());
        return saveOptions;
    }



    public void saveDockerFile(){
        String content = (String) getDockerEditorBase().getEngine().executeScript("editor.getValue()");
        if(content.isEmpty()){
            EmptyEditorAlert alert = new EmptyEditorAlert();
            alert.alertWithNoReturnType();
        }else{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/docker_management/saveOptions.fxml"));
            Parent root = null;
            try{
                root = loader.load();
            }catch (IOException exception){
                exception.printStackTrace();
            }
            assert root != null;
            PopOver popOver = createPopOver(root);
            SaveOptions controller = loader.getController();
            controller.setDockerMainController(getDockerMainController());
            controller.setContent(content);
            controller.setPopOver(popOver);
            controller.setSaveOptionsController(controller);
            if(isOpened()){
                controller.getSaveChangesInFile().setDisable(false);
                controller.setOpenedFiledLocation(getOpenedFiledLocation());
                controller.setOpened(isOpened());
                controller.setInitialContent(getInitialEditorContent());
            }
        }
    }



    void loadDefaultDockerFiles(){
        getDefaultDockerfileVBox().getChildren().clear();
        DirectoryStream<Path> streamToDockerDirectory = null;
        File file = new File("C:\\Users\\GMKRG\\Desktop\\GitHubRepos\\Java\\Workspace\\DockerEditor\\src\\main\\resources\\fxml\\docker_management\\dockerfiles");
        try{
            Path pathToDockerFolder = Paths.get(file.getPath());
            streamToDockerDirectory = Files.newDirectoryStream(pathToDockerFolder);
        }catch( IOException exception){
            exception.printStackTrace();
        }
        assert streamToDockerDirectory != null;
        for(Path path : streamToDockerDirectory){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/docker_management/defaultDockerfile.fxml"));
            Parent root = null;
            try{
                root = loader.load();
            }catch (IOException exception){
                exception.printStackTrace();
            }
            assert root != null;
            DefaultDockerfile controller = loader.getController();
            getDefaultDockerfileVBox().getChildren().add(root);
            getDefaultDockerFilesWithNameAndPath().put((AnchorPane) root, new String[]{path.getFileName().toString(), path.toString()});
            configureEachDefaultDockerfile(controller, path);
        }
    }



    private void configureEachDefaultDockerfile(DefaultDockerfile controller, Path path){
        controller.setFileName(path.getFileName().toString());

        controller.setFilePath(path.toString());

        controller.getRootPane().setOnMouseClicked(event -> {
            for(Map.Entry hashMap : getDefaultDockerFilesWithNameAndPath().entrySet()){
                AnchorPane root = (AnchorPane) hashMap.getKey();
                CheckBox cb = (CheckBox)root.getChildren().get(2);
                if(cb.isSelected()){
                    cb.setSelected(false);
                    root.setStyle("-fx-background-color: #282828; -fx-background-radius: 15;");
                }
            }
            controller.getSelectionCheckBox().setSelected(true);
            controller.getRootPane().setStyle("-fx-border-color: white; -fx-border-radius: 15; -fx-opacity: 0.5");
        });

        Font font = new Font("Trebuchet MS", 14);
        Text dockerfileName = new Text("Name: " + path.getFileName());
        dockerfileName.setFont(font);
        dockerfileName.setFill(Color.TOMATO);
        controller.getDockerFileInformation().getChildren().addAll(dockerfileName);
    }




    public void openTemplateDockerfileInTextArea(){
        for(Map.Entry map : getDefaultDockerFilesWithNameAndPath().entrySet()){
            AnchorPane pane = (AnchorPane) map.getKey();
            CheckBox cb = (CheckBox) pane.getChildren().get(2);
            if(cb.isSelected()){
                String[] values = (String[]) map.getValue();
                File file = new File(values[1]+"\\Dockerfile");
                showContentOnEditor(file);
                setOpened(true);
                setOpenedFiledLocation(file);
                setInitialEditorContent((String)getDockerEditorBase().getEngine().executeScript("editor.getValue()"));
                cb.setSelected(false);
                loadDefaultDockerFiles();
                break;
            }
        }
    }




    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void addTemplateDockerfile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Dockerfiles", "*.*", "*.txt", "*.Dockerfile"));
        File fileToBeMoved = fileChooser.showOpenDialog(null);
        if(fileToBeMoved != null){
            File destinationFolder = new File("src/main/resources/fxml/docker_management/dockerfiles/" + fileToBeMoved.getParentFile().getName());
            destinationFolder.mkdir( );
            File dockerfile = new File(destinationFolder.getPath()+"/"+fileToBeMoved.getName());
            try{
                Files.copy(fileToBeMoved.toPath(), dockerfile.toPath());
                loadDefaultDockerFiles();
                DockerfileAddedToTemplatesAlert alert = new DockerfileAddedToTemplatesAlert();
                alert.alertWithNoReturnType();
            }catch(IOException exception){
                FailedToAddDockerfileToTemplates alert = new FailedToAddDockerfileToTemplates();
                alert.alertWithNoReturnType();
                exception.printStackTrace();
            }
        }
    }



    public void refreshTemplates(){
        loadDefaultDockerFiles();
    }



    public void clearEditor(){
        if(isOpened()){
            Alert alert = new UnsavedChangesInFileAlert().alertWithReturnType();
            if(alert.getResult() == ButtonType.OK){
                setOpened(false);
                setOpenedFiledLocation(null);
                getDockerEditorBase().getEngine().executeScript("editor.setValue('')");
            }else{
                openTemplateDockerfileInTextArea();
            }
        }else{
            getDockerEditorBase().getEngine().executeScript("editor.setValue('')");
        }
    }



    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void removeTemplateDockerfile() {
        ObservableList<Node> children = getDefaultDockerfileVBox( ).getChildren( );
        ArrayList<AnchorPane> childrenArrList = new ArrayList<>( );
        for(Map.Entry map : getDefaultDockerFilesWithNameAndPath().entrySet()){
            AnchorPane pane = (AnchorPane) map.getKey();
            String[] values = (String[]) map.getValue();
            CheckBox cb = (CheckBox) pane.getChildren().get(2);
            if(cb.isSelected()){
                DockerFileDeletionConfirmation alert = new DockerFileDeletionConfirmation();
                alert.setDockerfileName(values[0]);
                if(alert.alertWithReturnType().getResult() == ButtonType.OK){
                    File folder = new File(values[1]);
                    File[] files = folder.listFiles();
                    assert files != null;
                    for(File f : files){
                        f.delete();
                    }
                    if(folder.delete()){
                        for (Node n : children) {
                            childrenArrList.add((AnchorPane) n);
                        }
                        for (AnchorPane anchorPane : childrenArrList) {
                            CheckBox checkBox = (CheckBox) anchorPane.getChildren( ).get(2);
                            if (checkBox.isSelected( )) {
                                getDefaultDockerfileVBox( ).getChildren( ).remove(anchorPane);
                            }
                        }

                        DockerfileDeletedSuccessfully alert2 = new DockerfileDeletedSuccessfully();
                        alert2.setDockerfileName(values[0]);
                        alert2.alertWithNoReturnType();
                    }else{
                        FailedToDeleteDockerfileDirectory alert3 = new FailedToDeleteDockerfileDirectory();
                        alert3.alertWithNoReturnType();
                    }
                }
            }
        }
    }




    private void showHideTemplates(){
        SimpleBooleanProperty isToggled = new SimpleBooleanProperty();
        isToggled.bind(getSplitPane().getDividers().get(0).positionProperty().isEqualTo(0, 0.01 ));
        getToggleTemplatesButton().textProperty().bind(Bindings.when(isToggled).then("Expand").otherwise("Collapse"));
        getToggleTemplatesButton().setOnAction(event -> {
            double target = isToggled.get() ? 0.3 : 0.0;
            KeyValue keyValue = new KeyValue(getSplitPane().getDividers().get(0).positionProperty(), target);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), keyValue));
            timeline.play();
        });
    }




    @SuppressWarnings("unchecked")
    private void changeEditorTheme(){
        ObservableList<EditorStyles> styles = FXCollections.observableArrayList(EditorStyles.values());
        getEditorThemes().setItems(styles);
        getEditorThemes().setValue(EditorStyles.GOB);
        getEditorThemes().setVisibleRowCount(4);
        getEditorThemes().setOnAction(event -> {
            String newTheme = getEditorThemes().getValue().toString();
            getDockerEditorBase().getEngine().executeScript("editor.setTheme('ace/theme/"+ newTheme.toLowerCase() +"')");
        });
    }




    private void setTooltips(){
        Font font = new Font("Trebuchet MS", 14);

        Tooltip tooltip = new Tooltip("Opens a Dockerfile\nfrom the filesystem");
        tooltip.setFont(font);
        getOpenDockerFileButton().setTooltip(tooltip);

        tooltip = new Tooltip("Saves the opened\nDockerfile");
        tooltip.setFont(font);
        getSaveDockerfileButton().setTooltip(tooltip);

        tooltip = new Tooltip("Expands / Collapses\nDockerfile templates");
        tooltip.setFont(font);
        getToggleTemplatesButton().setTooltip(tooltip);

        tooltip = new Tooltip("Adds a new template from the filesystem");
        tooltip.setFont(font);
        getAddTemplateButton().setTooltip(tooltip);

        tooltip = new Tooltip("Refreshes the list of templates");
        tooltip.setFont(font);
        getRefreshTemplatesButton().setTooltip(tooltip);

        tooltip = new Tooltip("Removes template from the list");
        tooltip.setFont(font);
        getRemoveTemplateButton().setTooltip(tooltip);

        tooltip = new Tooltip("Opens the selected template\non the editor");
        tooltip.setFont(font);
        getOpenTemplateButton().setTooltip(tooltip);

        tooltip = new Tooltip("Changes the editor's theme colors");
        tooltip.setFont(font);
        getEditorThemes().setTooltip(tooltip);

        tooltip = new Tooltip("Clears the editor");
        tooltip.setFont(font);
        getEraseEditorButton().setTooltip(tooltip);
    }


    private void setComboBoxStyle(){
        getEditorThemes().setStyle("-fx-font: 14px \"Trebuchet MS\";" +
                "-fx-text-fill: white;" +
                "-fx-background-color: transparent;" +
                "-fx-border-color: lightblue;" +
                "-fx-border-radius: 5;" +
                "-fx-border-width: 2px");
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDockerEditor();
        loadDefaultDockerFiles();
        showHideTemplates();
        changeEditorTheme();
        setTooltips();
        setComboBoxStyle();
    }


}
