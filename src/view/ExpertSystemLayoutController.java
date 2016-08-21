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
	private String []resultName = {"Однослойный персептрон\n", 
			"Многослойный персептрон\n",
			"Сети обратного распространения ошибки\n",
			"Сеть, обучаемая по методу имитации отжига\n",
			"Сеть с генетическим алгоритмом обучения\n",
			"Входная звезда / Инстар\n",
			"Выходная звезда\n",
			"Сеть Кохонена / Самоорганизующаяся карта признаков Кохонена\n",
			"Сеть встречного распространения\n",
			"Сеть Хопфилда / Модель Хопфилда / Ассоциативная память, адресуемая по содержанию\n",
			"Сеть Хемминга / Нейросетевая модель ассоциативной памяти, основанная на вычислении расстояния Хемминга\n",
			"Сеть теории адаптивного резонанса / Сеть адаптивной резонансной теории - 1 (АРТ-1))\n",
			"Двунаправленноя ассоциативная память\n",
			"Машина Больцмана\n",
			"Нейросетевой гауссов классификатор / Гауссов классификатор, реализованный на персептроне\n",
			"Сеть поиска максимума с прямыми связями / Сеть поиска максимума, основанная на двоичном дереве и нейросетевых компараторах\n",
			"Сеть поиска максимума / сеть поиска максимума с конкуренцией посредством латерального торможения\n",
			"Сеть радиально-симметричных скрытых нейронов\n",
			"КОГНИТРОН: самоорганизующаяся многослойная нейросеть\n",
			"НЕОКОГНИТРОН\n",
			"обучаемая по дельта-правилу\n"};
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
		String str="Вам подходят следующие виды сетей:\n";
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
				label.setText("Какая топология Вам подходит?");
				radioButton1.setText("полносвязная (каждый нейрон связан совсеми остальными нейронами, в том\n числе и сам с собой)");
				radioButton2.setText("многослойная (нейроны располагаютсяслоями, и каждый нейрон последующего\n слоя связан со всеми нейронами текущего слоя)");
			}
		}
		if (stage == 1){
			label.setText("Выберете организацию обучения:");
			radioButton1.setText("с учителем (нейронную сеть обучают,подавая на вход значения обучающей выборки и\n предоставляя требуемые выходные значения)");
			radioButton2.setText("без учителя (на входы нейронной сетиподают множество объектов, и нейронная сеть\n сама делит их на кластеры или классы)");
		}
		if (stage == 2){
			label.setText("Тип структуры нейронной сети:");
			radioButton1.setText("нейроны с одним типом функции активации (все нейроны сети имеют одну функцию\n активации f(x), например линейную)");
			radioButton2.setText("нейроны с несколькими типами функций активации (нейроны сети имеют различные\n функции активации)");
		}
		if (stage == 3){
			label.setText("Тип связей:");
			radioButton1.setText("прямого распространения (без обратныхсвязей между нейронами; к таким сетям\n относятся однослойный и многослойный персептроны,сеть радиальных базисных функций)");
			radioButton2.setText("рекуррентный (с обратной связью, отвыходов нейронов к входам; к таким сетям\n относятся соревновательные сети и сетьХопфилда)");
		}
		if (stage == 4){
			label.setText("Тип сигнала:");
			radioButton1.setText("бинарные (на входы подаются тольконули и единицы)");
			radioButton2.setText("аналоговые (на входы нейроновподаются значения непрерывных функций)");
		}
		if (stage == 5){
			label.setText("Изменение состояния нейронной сети:");
			radioButton1.setText("синхронное");
			radioButton2.setText("асинхронное");
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
