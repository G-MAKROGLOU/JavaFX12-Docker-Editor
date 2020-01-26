# JavaFX12-Docker-Editor
Docker Editor built with JavaFX12 and ace.js on Maven 3.6.0  

Extracted from a bigger application. More components of that application will be uploaded soon so you can choose a component or the whole app. This is a single person made application so keep in mind that minor fixes in some components might be needed. (E.g DBMS Supports only very basic types for now [CHAR, VARCHAR, INT, FLOAT, BLOB,
etc.]) Some components might need additional libraries and/or api keys to run.


##Project structure:##
            
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


##Add the following dependenices in your pom.xml:## 

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>${jackson.version}</version>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>${jackson.version}</version>
        </dependency>

        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-controls</artifactId>
            <version>${javafx.version}</version>
        </dependency>

        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-web</artifactId>
            <version>${javafx.version}</version>
        </dependency>

        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-fxml</artifactId>
            <version>${javafx.version}</version>
        </dependency>

        <dependency>
            <groupId>org.jetbrains</groupId>
            <artifactId>annotations</artifactId>
            <version>16.0.2</version>
        </dependency>

        <dependency>
            <groupId>org.kordamp.ikonli</groupId>
            <artifactId>ikonli-javafx</artifactId>
            <version>11.3.4</version>
        </dependency>

        <dependency>
            <groupId>org.kordamp.ikonli</groupId>
            <artifactId>ikonli-fontawesome-pack</artifactId>
            <version>11.3.4</version>
        </dependency>

        <dependency>
            <groupId>org.kordamp.ikonli</groupId>
            <artifactId>ikonli-typicons-pack</artifactId>
            <version>11.3.4</version>
        </dependency>

        <dependency>
            <groupId>org.controlsfx</groupId>
            <artifactId>controlsfx</artifactId>
            <version>11.0.0</version>
        </dependency>
