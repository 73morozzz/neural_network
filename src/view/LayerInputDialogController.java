package view;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nnet.MainApp;
import nnet.SigmoidLayer;

public class LayerInputDialogController {
	@FXML
	private TextField inputSize;
	@FXML
	private TextField laySize;
	@FXML
	private RadioButton doorStep;
	@FXML
	private RadioButton linear;
	@FXML
	private RadioButton sigmoid;
	@FXML
	private RadioButton tg;
	@FXML
	private TextArea createdLays;

	private MainApp mainApp;
    private Stage dialogStage;
    
	public LayerInputDialogController () {	
	}

	@FXML
	private void initialize() {
		createdLays.setEditable(false);
	}

	public void setMainApp(MainApp mainApp) {
	    this.mainApp = mainApp;
	}

	/**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	
	@FXML
	private void handleNewLayer() {
		Integer i1;
	    Integer i2;
		try { 
	        i1 = new Integer(inputSize.getText());
	        i2 = new Integer(laySize.getText());        
	    }catch (NumberFormatException e) {  
//	        System.err.println("Неверный формат строки!"); 
	    	return;
	    }
		SigmoidLayer.TypeLayer type;
		type = SigmoidLayer.TypeLayer.SIGMOID;
		if (doorStep.isSelected())
			type = SigmoidLayer.TypeLayer.DOORSTEP;
		else
			if (linear.isSelected())
				type = SigmoidLayer.TypeLayer.LINEAR;
			else
				if (tg.isSelected())
					type = SigmoidLayer.TypeLayer.TG;
		
		ArrayList <SigmoidLayer> LayerArray = mainApp.getLayers();
		if (LayerArray.size()==0){
			SigmoidLayer layer= new SigmoidLayer(i1, i2, type);// creating of the layers 
			mainApp.addLayer(layer);
			inputSize.setEditable(false);
		}else{
			SigmoidLayer layer= new SigmoidLayer(LayerArray.get(LayerArray.size()-1).getSize(), i2, type);// creating of the layers 
			mainApp.addLayer(layer);
		}
		
		laySize.setText("");
		printLayers();
	} 

	private void printLayers() {
		ArrayList <SigmoidLayer> LayerArray = new ArrayList <SigmoidLayer>();
		LayerArray=mainApp.getLayers();
		if (LayerArray.size() > 0){
			String outStr = "Размер входного вектора:" + LayerArray.get(0).getInputSize() + "\n";
			outStr += "N Размер слоя  Тип слоя\n";
			for (int i=0; i<LayerArray.size();i++) {
				outStr += (i+1) + "  " + LayerArray.get(i).getSize() + "				"+ LayerArray.get(i).getType() + "\n";
			}
			createdLays.setText(outStr);
		}
		else{
			createdLays.setText("");
		}
		
		
	} 

	@FXML
	private void handleDeleteLayers() {
		mainApp.getLayers().clear();
		inputSize.setEditable(true);
		laySize.setText("");
		printLayers();
	} 
	
	@FXML
	private void handleCreateNetwork() {
		mainApp.creatNetwork();
		dialogStage.close();
	} 

}
