package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import nnet.MainApp;

public class ExpertSystemLayoutController {
	private MainApp mainApp;

	@FXML
	private Label label;
	@FXML
	private RadioButton radioButton1;
	@FXML
	private RadioButton radioButton2;
	@FXML
	private RadioButton radioButtonTask1;
	@FXML
	private RadioButton radioButtonTask2;
	@FXML
	private RadioButton radioButtonTask3;
	@FXML
	private RadioButton radioButtonTask4;
	@FXML
	private RadioButton radioButtonTask5;
	@FXML
	private RadioButton radioButtonTask6;
	@FXML
	private RadioButton radioButtonTask7;
	@FXML
	private Pane pane1;
	@FXML
	private Pane pane2;
	@FXML
	private Pane pane3;
	@FXML
	private Pane pane4;
	@FXML
	private TextArea resultArea;
	@FXML
	private Button next;
	
	
	
	private int stage;
	private int questionCount = 8;
	private int resultCount = 21;
	private String []resultName = {"����������� ����������\n", 
			"������������ ����������\n",
			"���� ��������� ��������������� ������\n",
			"����, ��������� �� ������ �������� ������\n",
			"���� � ������������ ���������� ��������\n",
			"������� ������ / ������\n",
			"�������� ������\n",
			"���� �������� / ������������������ ����� ��������� ��������\n",
			"���� ���������� ���������������\n",
			"���� �������� / ������ �������� / ������������� ������, ���������� �� ����������\n",
			"���� �������� / ������������ ������ ������������� ������, ���������� �� ���������� ���������� ��������\n",
			"���� ������ ����������� ��������� / ���� ���������� ����������� ������ - 1 (���-1))\n",
			"��������������� ������������� ������\n",
			"������ ���������\n",
			"������������ ������� ������������� / ������� �������������, ������������� �� �����������\n",
			"���� ������ ��������� � ������� ������� / ���� ������ ���������, ���������� �� �������� ������ � ������������ ������������\n",
			"���� ������ ��������� / ���� ������ ��������� � ������������ ����������� ������������ ����������\n",
			"���� ���������-������������ ������� ��������\n",
			"���������: ������������������ ������������ ���������\n",
			"������������\n",
			"��������� �� ������-�������\n"};
	private int []answer = new int[questionCount];
	private int [][]pattern = {{1,1,1,1,0},
			{0,1,1,0,0},
			{0,1,1,0,0},
			{0,0,0,0,0},
			{0,1,1,0,0},
			{0,0,0,0,0},
			{0,0,0,0,0},
			{0,1,1,0,0},
			{0,0,0,0,0},
			{0,0,0,0,1},
			{1,1,1,1,1},
			{1,0,0,1,0},
			{1,0,0,1,0},
			{1,1,1,1,1},
			{1,1,1,1,0},
			{1,0,0,1,0},
			{0,0,0,0,0},
			{0,1,1,0,0},
			{1,0,0,1,0},
			{0,0,0,0,0},
			{0,0,0,0,0}};
	private boolean []results = new boolean[resultCount];
	
	
	public ExpertSystemLayoutController () {	
	}

	public void Results () {	
		for (int i=0; i < resultCount; i++){
			results[i]=true;
		}
		
		for (int i=1; i < 6; i++){//questionCount; i++){
			for(int j=0; j < resultCount; j++){
				if (pattern[j][i-1] != answer[i]){
					results[j]=false;
				}
			}
		}
		String str="��� �������� ��������� ���� �����:\n";
		for (int i=0; i < resultCount; i++){
			if (results[i]) str+=resultName[i];
			//System.out.println(results[i] + " ");
		}
		resultArea.setText(str);
	}
	
	@FXML
	private void initialize() {
		stage = 0;
		next.setDisable(true);
	}

	public void setMainApp(MainApp mainApp) {
	    this.mainApp = mainApp;
	}
	
	@FXML
	private void handleNext() {
		if (radioButton1.isSelected())
			answer[stage]=1;
		else
			answer[stage]=0;
		//System.out.println(stage + " ");
		if (stage == 0){
			if (radioButton1.isSelected()){
				pane2.setVisible(false);
				pane3.setVisible(true);
			}else{
				label.setText("����� ��������� ��� ��������?");
				radioButton1.setText("������������ (������ ������ ������ ������� ���������� ���������, � ���\n ����� � ��� � �����)");
				radioButton2.setText("������������ (������� �������������������, � ������ ������ ������������\n ���� ������ �� ����� ��������� �������� ����)");
			}
		}
		if (stage == 1){
			label.setText("�������� ����������� ��������:");
			radioButton1.setText("� �������� (��������� ���� �������,������� �� ���� �������� ��������� ������� �\n ������������ ��������� �������� ��������)");
			radioButton2.setText("��� ������� (�� ����� ��������� ���������� ��������� ��������, � ��������� ����\n ���� ����� �� �� �������� ��� ������)");
		}
		if (stage == 2){
			label.setText("��� ��������� ��������� ����:");
			radioButton1.setText("������� � ����� ����� ������� ��������� (��� ������� ���� ����� ���� �������\n ��������� f(x), �������� ��������)");
			radioButton2.setText("������� � ����������� ������ ������� ��������� (������� ���� ����� ���������\n ������� ���������)");
		}
		if (stage == 3){
			label.setText("��� ������:");
			radioButton1.setText("������� ��������������� (��� �������������� ����� ���������; � ����� �����\n ��������� ����������� � ������������ �����������,���� ���������� �������� �������)");
			radioButton2.setText("������������ (� �������� ������, ��������� �������� � ������; � ����� �����\n ��������� ���������������� ���� � ������������)");
		}
		if (stage == 4){
			label.setText("��� �������:");
			radioButton1.setText("�������� (�� ����� �������� ���������� � �������)");
			radioButton2.setText("���������� (�� ����� ���������������� �������� ����������� �������)");
		}
		if (stage == 5){
			label.setText("��������� ��������� ��������� ����:");
			radioButton1.setText("����������");
			radioButton2.setText("�����������");
		}
		if (stage == 6){
			label.setText("");
			radioButton1.setText("");
			radioButton2.setText("");
			
			pane2.setVisible(false);
			pane1.setVisible(true);
		}
		
	
		next.setDisable(true);
		radioButton1.setSelected(false);
		radioButton2.setSelected(false);
		stage++;
	}
	

	@FXML
	private void handleRadioButton() {
		next.setDisable(false);
	}
	
	@FXML
	private void handleResult() {
		pane1.setVisible(false);
		pane4.setVisible(true);
		Results();
	}
	
	@FXML
	private void handleCreateNS() {
		mainApp.showLayerInputDialog();
	}
}
