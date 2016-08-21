package view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import nnet.MainApp;

public class RootLayoutContoller {
	// ������ �� ������� ����������
    private MainApp mainApp;

    /**
     * ���������� ������� �����������, ����� �������� ������ �� ������ ����.
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
     * ��������� FileChooser, ����� ������������ ���� �����������
     * ������� ��� ��������.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // ����� ������ ����������
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "NNT files (*.nnt)", "*.nnt");
        fileChooser.getExtensionFilters().add(extFilter);

        // ���������� ������ �������� �����
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        
        if (file != null) {
            mainApp.loadDataFromFile(file);
        }
    }

    /**
     * ��������� ���� � ����, ������� � ��������� ����� ������.
     * ���� ���� �� ������, �� ������������ ������ "save as".
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
     * ��������� FileChooser, ����� ������������ ���� �����������
     * ������� ����, ���� ����� ��������� ������
     */
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // ����� ������ ����������
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "NNT files (*.nnt)", "*.nnt");
        fileChooser.getExtensionFilters().add(extFilter);

        // ���������� ������ ���������� �����
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
     * ��������� ���������� ���� about.
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
     * ��������� ����������.
     */
    @FXML
    private void handleExit() {
    	System.exit(0);
    }
}
