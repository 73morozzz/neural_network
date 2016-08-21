package nnet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.ExpertSystemLayoutController;
import view.LayerInputDialogController;
import view.MainController;
import view.RootLayoutContoller;

public class MainApp extends Application{
 
	  private Stage primaryStage;
	  private BorderPane rootLayout;
	  
	  private File file;
	 
	  private static ArrayList <SigmoidLayer> LayerArray = new ArrayList <SigmoidLayer>();
	  
	  static BackpropNetwork network;
	  
	  public void addLayer (SigmoidLayer layer){
		  LayerArray.add(layer);
	  }
	  
	  public ArrayList<SigmoidLayer> getLayers (){
		  return LayerArray;
	  }
	  
	  public BackpropNetwork getNetwork (){
		  return network;
	  }
	  
		 /**
	     * Constructor
	     */
	    public MainApp() {
	    	
	    }
	    
	    /**
	     * Returns the main stage.
	     * @return
	     */
	    public Stage getPrimaryStage() {
	        return primaryStage;
	    }
	    
	    public void saveDataToFile(File file){
	    	network.saveToFile(file);
	    	this.file = file;
	    }
	    
	    public void loadDataFromFile(File file){
	    	network = (BackpropNetwork) Network.loadFromFile(file);
	    	this.file = file;
	    	initMainLayout();
	    }
	    
	    public File getFile(){
	    	return file;
	    }
	    
	    
	    @Override
	    public void start(Stage primaryStage) {
	        this.primaryStage = primaryStage;
	        this.primaryStage.setTitle("neural network");

	        initRootLayout();
	        //initMainLayout();
	    }	 
	    
	    /**
	     * Initializes the root layout.
	     */
	    public void initRootLayout() {
	        try {
	            // Загружаем корневой макет из fxml файла.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class
	                    .getResource("../view/RootLayout.fxml"));
	            rootLayout = (BorderPane) loader.load();

	            // Отображаем сцену, содержащую корневой макет.
	            Scene scene = new Scene(rootLayout);
	            primaryStage.setScene(scene);

	            // Даём контроллеру доступ к главному прилодению.
	            RootLayoutContoller controller = loader.getController();
	            controller.setMainApp(this);

	            primaryStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        // Пытается загрузить последний открытый файл.
//	        File file = getPersonFilePath();
//	        if (file != null) {
//	            loadPersonDataFromFile(file);
//	        }
	    }
	    
	    public void initMainLayout() {
	        try {
	        	// Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/MainLayout.fxml"));
	            AnchorPane mainLayout = (AnchorPane) loader.load();

	            // Set person overview into the center of root layout.
	            rootLayout.setCenter(mainLayout);
	            
	            // Give the controller access to the main app.
	            MainController controller = loader.getController();
	            controller.setMainApp(this);
	            controller.DrawNetwork();
	            controller.PrintWeightNetwork();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public void initExpertSystemLayout() {
	        try {
	        	// Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/ExpertSystemLayout.fxml"));
	            AnchorPane mainLayout = (AnchorPane) loader.load();

	            // Set person overview into the center of root layout.
	            rootLayout.setCenter(mainLayout);
	            
	            // Give the controller access to the main app.
	            ExpertSystemLayoutController controller = loader.getController();
	            controller.setMainApp(this);
	            //controller.DrawNetwork();
	            //controller.PrintWeightNetwork();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    
	    public boolean showLayerInputDialog() {
	        try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/LayerInputDialog.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Создать НС");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(primaryStage);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            LayerInputDialogController controller = loader.getController();
	            controller.setMainApp(this);
	            controller.setDialogStage(dialogStage);
	            //controller.setPerson(person);

	            // Show the dialog and wait until the user closes it
	            dialogStage.showAndWait();
	            initMainLayout();
	            return true;// controller.isOkClicked();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    public static void creatNetwork() {
	    	SigmoidLayer[] stockArr = new SigmoidLayer[LayerArray.size()];
	    	stockArr = LayerArray.toArray(stockArr);
	    	network = new BackpropNetwork(stockArr);
	    }
	    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        launch(args);

	}	
}