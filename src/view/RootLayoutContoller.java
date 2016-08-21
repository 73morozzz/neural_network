package view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import nnet.MainApp;

public class RootLayoutContoller {
	// Ссылка на главное приложение
    private MainApp mainApp;

    /**
     * Вызывается главным приложением, чтобы оставить ссылку на самого себя.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    @FXML
    private void handleNew() {
    	mainApp.showLayerInputDialog();
    }

    /**
     * Открывает FileChooser, чтобы пользователь имел возможность
     * выбрать для загрузки.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Задаём фильтр расширений
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "NNT files (*.nnt)", "*.nnt");
        fileChooser.getExtensionFilters().add(extFilter);

        // Показываем диалог загрузки файла
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        
        if (file != null) {
            mainApp.loadDataFromFile(file);
        }
    }

    /**
     * Сохраняет файл в файл, который в настоящее время открыт.
     * Если файл не открыт, то отображается диалог "save as".
     */
    @FXML
    private void handleSave() {
        File file = mainApp.getFile();
        if (file != null) {
            mainApp.saveDataToFile(file);
        } else {
            handleSaveAs();
        }
        
    }

    /**
     * Открывает FileChooser, чтобы пользователь имел возможность
     * выбрать файл, куда будут сохранены данные
     */
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // Задаём фильтр расширений
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "NNT files (*.nnt)", "*.nnt");
        fileChooser.getExtensionFilters().add(extFilter);

        // Показываем диалог сохранения файла
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".nnt")) {
                file = new File(file.getPath() + ".nnt");
            }
            mainApp.saveDataToFile(file);
        }
    }

    @FXML
    private void handleExpertSystem() {
    	mainApp.initExpertSystemLayout();
    }
    
    @FXML
    private void handleNeuralNetwork() {
    	mainApp.initMainLayout();
    }
    
    /**
     * Открывает диалоговое окно about.
     */
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Nnet");
        alert.setHeaderText("About");
        alert.setContentText("Author: Jane");

        alert.showAndWait();
    }

    /**
     * Закрывает приложение.
     */
    @FXML
    private void handleExit() {
    	System.exit(0);
    }
}
