# JavaFX12-Docker-Editor
### Docker Editor built with JavaFX12 and ace.js on Maven 3.6.0  

You can use it as a standalone application by following the ```Standalone``` instructions, or embed it in your own project easily by following the ```Embed in my application``` instructions. Because Dockerfiles are all named by default ```Dockerfile```, anytime you are asked for a file name you should type something that describes the Dockerfile best and a folder with that name and the actual Dockerfile inside it will be created. ```ace.js``` is loaded as a whole in for future scaling or forks that might want to expand the functionality beyond Docker. In order to function properly you should configure the paths in the classes under ```docker_management``` folder to match your computers filesystem. 

## Project structure:
            
            root
              |___ images
              |     |____ docker_50px.png
              |
              |___ src
              |     |___main
              |           |____ java
              |           |      |_____ alerts
              |           |      |         |____ GeneralAlert.java
              |           |      |         |____ CouldNotCreateDockerfile.java
              |           |      |         |____ CouldNotCreateFolder.java
              |           |      |         |____ CouldNotOpenDockerfileAlert.java
              |           |      |         |____ DockerfileAddedToTemplatesAlert.java
              |           |      |         |____ DockerfileCreatedSuccessfully.java
              |           |      |         |____ DockerfileDeletedSuccessfully.java
              |           |      |         |____ DockerfileDeletionConfirmation.java
              |           |      |         |____ DockerfileSavedSuccessfully.java
              |           |      |         |____ EmptyEditorAlert.java
              |           |      |         |____ FailedToAddDockerfileToTemplates.java
              |           |      |         |____ FailedToDeleteDockerfileDirectory.java
              |           |      |         |____ FailedToSaveDockerfile.java
              |           |      |         |____ NoChangesInFileAlert.java
              |           |      |         |____ NoSaveOptionSelected.java
              |           |      |         |____ UnsavedChangesInFileAlert.java
              |           |      |
              |           |      |
              |           |      |_____ mainScene
              |           |      |          |_______ MainScene.java
              |           |      |
              |           |      |_____ docker_management
              |           |      |            |__________ DefaultDockerfile.java
              |           |      |            |__________ DockerfileTemplateFolderName.java
              |           |      |            |__________ DockerMain.java
              |           |      |            |__________ EditorStyles.java
              |           |      |            |__________ SaveOptions.java
              |           |      |
              |           |      |
              |           |      |___ module-info.java
              |           |
              |           |___ resources
              |                   |
              |                   |____ docker_editor
              |                   |           |__________ editor.css
              |                   |           |__________ editor.html
              |                   |           |__________ editor.js
              |                   |           |__________ src-noconflict
              |                   |                             |______ ace.js (files)
              |                   |
              |                   |____ fxml
              |                          |____ docker_management
              |                          |           |____________ dockerfiles
              |                          |           |                 |__________ rpi_stretch_java
              |                          |           |                                     |__________ Dockerfile
              |                          |           |
              |                          |           |_______ defaultDockerfile.fxml
              |                          |           |_______ dockerfileTemplateFolderName.fxml
              |                          |           |_______ dockerMain.fxml
              |                          |           |_______ saveOptions.fxml
              |                          |
              |                          |____ mainScene
              |                                    |____ mainScene.fxml
              |                   
              |____ pom.xml




## Standalone:

You can either clone the whole repository, import it in your IDE, and run it, or use the ```pom.xml``` file in your own project to make sure you get the same ```Maven``` configurations and dependencies. To use it in a different setup, change the ```pom.xml``` file to match your environment.

#### javac --version
```javac 12.0.1```

#### java --version
```
java 12.0.1 2019-04-16
Java(TM) SE Runtime Environment (build 12.0.1+12)
Java HotSpot(TM) 64-Bit Server VM (build 12.0.1+12, mixed mode, sharing)
```

#### mvn -version
```Apache Maven 3.6.1  ....```
          
## Embed in existing application:

To embed the ```Docker Editor FX``` in a project you are developing, make sure your development enivronment matches the development environment of ```Docker Editor FX```, select the place in your code where you want to launch the editor and use the function :

            public void launchDockerManagement(){
                    Thread thread = new Thread(() -> {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/fxml/docker_management/dockerMain.fxml"));
                        Parent root = null;
                        try{
                            root = loader.load();
                        }catch(IOException exception){
                            exception.printStackTrace();
                        }
                        assert root != null;
                        DockerMain controller = loader.getController();
                        controller.setDockerMainController(controller);
                        Tab tab = new Tab("Docker Management");
                        tab.setContent(root);
                        getMainTabPane().getTabs().add(tab);
                        getMainTabPane().getSelectionModel().select(tab);
                    });
                    thread.run();
            }

As soon as ```root``` is loaded, you can use  it as content to any component you want; the example uses a ```Tab``` and then attaches the ```Tab``` to a central ```TabPane```. It is recomended to start it as ```Thread``` so the rest of your ```UI``` doesn't break. ```Docker Editor FX``` is also modularized so you can use it as a module with the ```module-info.java``` file. When used this way, ```mainScene``` is not needed because you are going to have your own base to mount the editor.
