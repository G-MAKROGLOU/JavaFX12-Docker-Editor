# JavaFX12-Docker-Editor
Docker Editor built with JavaFX12 and ace.js on Maven 3.6.0  

You can use it as a standalone application, or embed it in your own project easily by following the ```Embed in my application``` instructions. 

## Project structure:
            
            root
              |___ src
              |     |___main
              |           |____ java
              |           |      |_____ mainScene
              |           |      |            |_______ MainScene.java
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
              |                                      |____________ dockerfiles
              |                                      |                 |__________ rpi_stretch_java
              |                                      |                                     |__________ Dockerfile
              |                                      |
              |                                      |_______ defaultDockerfile.fxml
              |                                      |_______ dockerfileTemplateFolderName.fxml
              |                                      |_______ dockerMain.fxml
              |                                      |_______ saveOptions.fxml
              |
              |____ pom.xml

