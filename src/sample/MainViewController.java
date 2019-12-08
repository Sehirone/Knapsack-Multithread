package sample;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import logic.BruteForceThreads;
import logic.DynamicThreads;

public class MainViewController implements Initializable{
	@FXML
	TextArea resultsTA, SpecifiedInstanceTA;
	@FXML 
	JFXComboBox<String> algorythmChoiceCB;
	@FXML
	JFXComboBox<Integer> minThreadCB, maxThreadCB;
	@FXML 
	JFXButton calculateRandomInstancesButton, calculateSpecifiedInstanceButton;
	@FXML
	JFXTextField repeatsTA, minInstanceSizeTA, maxInstanceSizeTA;
	public void initialize(URL location, ResourceBundle resources) {
		algorythmChoiceCB.getItems().add("Przegląd Zupełny");
		algorythmChoiceCB.getItems().add("Programowanie dynamiczne");
		algorythmChoiceCB.setStyle("-fx-font: 16px \"System\";");
		for(int i = 1; i<9; i++) {
			minThreadCB.getItems().add(i);
			maxThreadCB.getItems().add(i);
		}
		minThreadCB.setStyle("-fx-font: 16px \"System\";");
		maxThreadCB.setStyle("-fx-font: 16px \"System\";");
	}
	public void calculateRandomInstanceButtonClicked(MouseEvent mouseEvent) {
		int threadsMax = maxThreadCB.getValue();
		int threadsMin = minThreadCB.getValue();
		int minSetSize = Integer.parseInt(minInstanceSizeTA.getText());
		int maxSetSize = Integer.parseInt(maxInstanceSizeTA.getText()) + 1;
		int repeats = Integer.parseInt(repeatsTA.getText());
        resultsTA.clear();
        resultsTA.appendText("Size\t");
        for(int i = threadsMin ; i < threadsMax ; i++) {
        	 resultsTA.appendText(Integer.toString(i) + "\t");
        }
        resultsTA.appendText("\n");
        if (algorythmChoiceCB.getValue().equals("Przegląd Zupełny")) {
            for(int i = minSetSize ; i < maxSetSize ; i++) {
            	resultsTA.appendText(Integer.toString(i) + "\t");
                for(int k = 1 ; k < threadsMax ; k++) {
                    long total = 0;
                    for(int j = 0 ; j < repeats ; j++) {
                        BruteForceThreads bft = new BruteForceThreads();
                        bft.loadRandomData(i);
                        bft.setThreads(k);
                        long start = System.currentTimeMillis();
                        bft.solve();
                        long finish = System.currentTimeMillis();
                        long elapsed = finish - start;
                        total += elapsed;
                    }
                    total = total / repeats;
                    resultsTA.appendText(Long.toString(total) + "\t");
                }
                resultsTA.appendText("\n");
            }
        }
        else {
            for(int i = minSetSize ; i < maxSetSize ; i++) {
            	resultsTA.appendText(Integer.toString(i) + "\t");
                for(int k = 1 ; k < threadsMax ; k++) {
                    long total = 0;
                    for(int j = 0 ; j < repeats ; j++) {
                        DynamicThreads dft = new DynamicThreads();
                        dft.loadRandomData(i);
                        dft.setThreads(k);
                        long start = System.currentTimeMillis();
                        dft.solve();
                        long finish = System.currentTimeMillis();
                        long elapsed = finish - start;
                        total += elapsed;
                    }
                    total = total / repeats;
                    resultsTA.appendText(Long.toString(total) + "\t");
                }
                resultsTA.appendText("\n");
            }
        }

	}
	public void calculateSpecifiedInstanceButtonClicked(MouseEvent mouseEvent) {
		int threadsMax = maxThreadCB.getValue();
		int threadsMin = minThreadCB.getValue();
        resultsTA.clear();
        resultsTA.appendText("Size\t");
        for(int i = threadsMin ; i < threadsMax ; i++) {
        	 resultsTA.appendText(Integer.toString(i) + "\t");
        }
        resultsTA.appendText("\n");
		if (algorythmChoiceCB.getValue().equals("Przegląd Zupełny")) {
    		resultsTA.appendText(Integer.toString((SpecifiedInstanceTA.getText().split(" ").length - 1) / 2) + "\t");
            for(int k = 1 ; k < threadsMax ; k++) {
                long total = 0;
                BruteForceThreads bft = new BruteForceThreads();
                bft.loadData(SpecifiedInstanceTA.getText());
                bft.setThreads(k);
                long start = System.currentTimeMillis();
                bft.solve();
                long finish = System.currentTimeMillis();
                long elapsed = finish - start;
                total += elapsed;
                resultsTA.appendText(Long.toString(total) + "\t");
            }
            resultsTA.appendText("\n");
		}
        else {
    		resultsTA.appendText(Integer.toString((SpecifiedInstanceTA.getText().split(" ").length - 1) / 2) + "\t");
            for(int k = 1 ; k < threadsMax ; k++) {
                long total = 0;
                DynamicThreads dft = new DynamicThreads();
                dft.loadData(SpecifiedInstanceTA.getText());
                dft.setThreads(k);
                long start = System.currentTimeMillis();
                dft.solve();
                long finish = System.currentTimeMillis();
                long elapsed = finish - start;
                total += elapsed;
                resultsTA.appendText(Long.toString(total) + "\t");
            }
            resultsTA.appendText("\n");
        }
	}
}
