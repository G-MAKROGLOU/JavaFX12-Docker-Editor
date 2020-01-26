package mainScene;

import docker_management.DockerMain;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;


public class MainScene extends Application {
    private Stage stage;

    private Stage getStage() { return stage; }
    private void setStage(Stage stage) { this.stage = stage; }


    public void start(Stage stage) throws Exception {
        setStage(stage);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/mainScene/mainScene.fxml"));
        Parent root;
        root = loader.load();
        assert root != null;
        stage.setScene(new Scene(root));
        stage.setTitle("Docker Editor FX");
        stage.setResizable(true);
        stage.show();
        Platform.runLater(this::loadEditor);
    }


    private void loadEditor(){
        Thread thread = new Thread(() -> {
            FXMLLoader editorLoader = new FXMLLoader();
            editorLoader.setLocation(getClass().getResource("/fxml/docker_management/dockerMain.fxml"));
            Parent root = null;
            try{
                root = editorLoader.load();
            }catch(IOException exception){
                exception.printStackTrace();
            }
            assert root != null;
            DockerMain controller = editorLoader.getController();
            controller.setDockerMainController(controller);
            getStage().setScene(new Scene(root, getStage().getWidth(), getStage().getHeight()));
        });
        thread.run();
    }



    @Override
    public void stop()  {
        Platform.exit();
        System.exit(0);
    }


    public static void main(String[] args){
        launch(args);
    }

}
