package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Line;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Text;
import nnet.BackpropNetwork;
import nnet.Layer;
import nnet.MainApp;
import nnet.SigmoidLayer;

public class MainController {
@FXML
private AnchorPane anchorPane;
@FXML
private TextArea outputData;
@FXML
private GridPane drawPane;
@FXML
private TextArea WeightData;
@FXML
private TextField in1;
@FXML
private TextField in2;
@FXML
private TextField in3;
@FXML
private TextField in4;
@FXML
private TextField in5;
@FXML
private TextField out1;
@FXML
private TextField out2;
@FXML
private TextField out3;
@FXML
private TextField out4;
@FXML
private TextField out5;

private MainApp mainApp;

public MainController () {	
}

@FXML
private void initialize() {
	outputData.setEditable(false);
	
}

public void setMainApp(MainApp mainApp) {
    this.mainApp = mainApp;
}

public void PrintWeightNetwork() {
	BackpropNetwork network = mainApp.getNetwork();
	int size = network.getSize();
	String str="";
	for (int k=0; k<size; k++){
		str += "Матрица весов " + (k+1) + " слоя\n";
		Layer lay = network.getLayer(k);
		float[][][] matrix = lay.getMatrix();
		for (int i=0; i<lay.getSize(); i++){
			for (int j=0; j<lay.getInputSize(); j++){
				str += matrix[i][j][0] + " ";
			}
			str+="\n";
		}
		str+="\n";
	}
	WeightData.setText(str);
}

public void DrawNetwork() {
	
	
	final PhongMaterial redMaterial = new PhongMaterial();
    redMaterial.setDiffuseColor(Color.RED);
    redMaterial.setSpecularColor(Color.WHITE);
    final PhongMaterial greenMaterial = new PhongMaterial();
    greenMaterial.setDiffuseColor(Color.GREEN);
    greenMaterial.setSpecularColor(Color.WHITE);
    final PhongMaterial blueMaterial = new PhongMaterial();
    blueMaterial.setDiffuseColor(Color.BLUE);
    blueMaterial.setSpecularColor(Color.WHITE);
    final PhongMaterial goldMaterial = new PhongMaterial();
    goldMaterial.setDiffuseColor(Color.GOLD);
    goldMaterial.setSpecularColor(Color.WHITE);
    
    BackpropNetwork network = mainApp.getNetwork();
    int size = network.getSize();
    int inputSize = network.getInputSize();
    for (int i=0; i<inputSize; i++) {//входной вектор
    	Sphere sph = new Sphere(5.0);
    	sph.setDrawMode(DrawMode.LINE);
    	drawPane.add(new Text("x" +(i+1)), 0, (10-2*inputSize)/2 + 2*i);
    	drawPane.add(sph, 1, (10-2*inputSize)/2 + 2*i);
	}
    double x = drawPane.getLayoutX();
    double y = drawPane.getLayoutY();
    //связи между нейронами
    int sizeLayerOne = network.getLayer(0).getSize();
    for (int j=0; j<sizeLayerOne; j++){//цикл по нейронам слоя
		for (int k=0; k<inputSize; k++){//цикл по нейронам предыдущего слоя
			Line line = new Line();
			line.setStartX(x + 70);
			line.setStartY(y + 20*((10-2*sizeLayerOne)/2 + 2*j));
			line.setEndX(x + 40);
			line.setEndY(y + 20*((10-2*inputSize)/2 + 2*(k)));
			anchorPane.getChildren().add(line);
		}
	}
    if (size > 1){
	    for (int i=1; i<size;i++) {//цикл по слоям
			int sizeLayer = network.getLayer(i).getSize();
			int sizeLayer0 = network.getLayer(i-1).getSize();
			for (int j=0; j<sizeLayer; j++){//цикл по нейронам слоя
				for (int k=0; k<sizeLayer0; k++){//цикл по нейронам предыдущего слоя
					Line line = new Line();
					line.setStartX(x + 35*(i*2+2));
					line.setStartY(y + 20*((10-2*sizeLayer)/2 + 2*j));
					line.setEndX(x + 35*((i-1)*2+2) + 15);
					line.setEndY(y + 20*((10-2*sizeLayer0)/2 + 2*(k)));
					anchorPane.getChildren().add(line);
				}
			}
	    }
    }
    //нейроны
	for (int i=0; i<size;i++) {//цикл по слоям
		int sizeLayer = network.getLayer(i).getSize();
		for (int j=0; j<sizeLayer; j++){//цикл по нейронам слоя
			Sphere sph = new Sphere(10.0);
			if (network.getLayer(i).getType() == SigmoidLayer.TypeLayer.DOORSTEP){
				sph.setMaterial(redMaterial);
			}else{
				if (network.getLayer(i).getType()==SigmoidLayer.TypeLayer.LINEAR){
					sph.setMaterial(blueMaterial);
				}else{
					if (network.getLayer(i).getType()==SigmoidLayer.TypeLayer.SIGMOID){
						sph.setMaterial(greenMaterial);
					}else{
						sph.setMaterial(goldMaterial);
					}
				}
			}
			sph.setDrawMode(DrawMode.LINE);
			drawPane.add(sph, i*2+2, (10-2*sizeLayer)/2 + 2*j);
		}
	}
	int sizeOutput = network.getOutputSize();
	for (int i=0; i<sizeOutput;i++){
		drawPane.add(new Text("y" +(i+1)), size*2+1, (10-2*sizeOutput)/2 + 2*i);
	}
	
} 

@FXML
private void handleRandomWeight() {
	BackpropNetwork network = mainApp.getNetwork();
	network.randomize(0, 0.3f);
	PrintWeightNetwork();
} 

@FXML
private void handleCompute() {
	BackpropNetwork network = mainApp.getNetwork();
	int inputSize = network.getInputSize();
	int outputSize = network.getOutputSize();
	float[] input = new float[5];//inputSize];
	float[] output = new float[5];//outputSize];
	try { 
        input [0] = Float.parseFloat(in1.getText());
        input [1] = Float.parseFloat(in1.getText());
        input [2] = Float.parseFloat(in1.getText());
        input [3] = Float.parseFloat(in1.getText());
        input [4] = Float.parseFloat(in1.getText());
    }catch (NumberFormatException e) {  
//        System.err.println("Неверный формат строки!"); 
    	return;
    }
	output = network.computeOutput(input);
	String str = "Вычисление\nВходной вектор: ";
	for (int i=0; i<inputSize; i++){
		str+=input[i]+" ";
	}
	str += "\nВыходной вектор: ";
	for (int i=0; i<outputSize; i++){
		str+=output[i]+" ";
	}
	outputData.setText(str);
} 

@FXML
private void handlelearn() {
	BackpropNetwork network = mainApp.getNetwork();
	int inputSize = network.getInputSize();
	int outputSize = network.getOutputSize();
	float[] input = new float[5];//inputSize];
	float[] output = new float[5];//outputSize];
	try { 
        input [0] = Float.parseFloat(in1.getText());
        input [1] = Float.parseFloat(in2.getText());
        input [2] = Float.parseFloat(in3.getText());
        input [3] = Float.parseFloat(in4.getText());
        input [4] = Float.parseFloat(in5.getText());
        output [0] = Float.parseFloat(out1.getText());
        output [1] = Float.parseFloat(out2.getText());
        output [2] = Float.parseFloat(out3.getText());
        output [3] = Float.parseFloat(out4.getText());
        output [4] = Float.parseFloat(out5.getText());
    }catch (NumberFormatException e) {  
//        System.err.println("Неверный формат строки!"); 
    	return;
    }
	float error = network.learnPattern(input,output,(float)0.1,(float)0.1);
	String str = "Обучение\nВходной вектор: ";
	for (int i=0; i<inputSize; i++){
		str+=input[i]+" ";
	}
	str += "\nВыходной вектор: ";
	for (int i=0; i<outputSize; i++){
		str+=output[i]+" ";
	}
	str += "\nОшибка: " + error;
	outputData.setText(str);
	PrintWeightNetwork();
}


}
