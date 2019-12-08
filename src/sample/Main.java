package sample;

import logic.DynamicThreads;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/resources/View.fxml"));
        Parent rootLayout = loader.load();
        primaryStage.setScene(new Scene(rootLayout,1000,700));
        primaryStage.setTitle("Problem Plecakowy Wielowątkowo");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
	
    public static void main(String[] args) throws IOException {
    	launch(args);
        /*int minBFSize = 20;
        int maxBFSize = 36;
        int threadsMax = 8;
        int repeats = 10;
        String fileName = "data.txt";

        String[] testSets = {
        		"500 120 103 155 54 81 79 49 54 168 120",
        		"500 120 103 155 54 81 79 49 54 168 120 5 12",
        		"500 120 103 155 54 81 79 49 54 168 120 5 12 46 78",
        		"500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33",
        		"500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12",
        		"500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15",
        		"500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120",
        		"500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155",
        		"500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155 54 81",
        		"500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155 54 81 79 49",
        		"500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155 54 81 79 49 54 168",
        		"500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155 54 81 79 49 54 168 120 5",
        		"500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155 54 81 79 49 54 168 120 5 12 46",
        		"500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90",
        		"500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55",
        		"500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20",
        		"500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60",
        		"500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 66 66",
        		"500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 66 66 77 77",
        		"500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 66 66 77 77 11 11",
        		"500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 66 66 77 77 11 11 66 66",
        		"500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 66 66 77 77 11 11 66 66 77 77",
        		"500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 66 66 77 77 11 11 66 66 77 77 84 63",
        		"500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 66 66 77 77 11 11 66 66 77 77 84 63 12 99",
                "500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 66 66 77 77 11 11 66 66 77 77 84 63 12 99 54 68",
                "500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 66 66 77 77 11 11 66 66 77 77 84 63 12 99 54 68 29 44",
                "500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 66 66 77 77 11 11 66 66 77 77 84 63 12 99 54 68 29 44 21 3",
                "500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 66 66 77 77 11 11 66 66 77 77 84 63 12 99 54 68 29 44 21 3 15 15",
                "500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 66 66 77 77 11 11 66 66 77 77 84 63 12 99 54 68 29 44 21 3 15 15 33 66",
                "500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 66 66 77 77 11 11 66 66 77 77 84 63 12 99 54 68 29 44 21 3 15 15 33 66 9 3",
                "500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 66 66 77 77 11 11 66 66 77 77 84 63 12 99 54 68 29 44 21 3 15 15 33 66 9 3 21 19",
        };

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        writer.write("Size\t");
        for(int i = 1 ; i < threadsMax ; i++) {
            writer.write(Integer.toString(i) + "\t");
        }
        writer.newLine();
        writer.flush();
        writer.newLine();

        for(int i = minBFSize ; i < maxBFSize ; i++) {
        	
            /*writer.write(Integer.toString(i) + "\t");
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
                writer.write(Long.toString(total) + "\t");
            }
        }

        for(int i = 0 ; i < testSets.length ; i++) {
            writer.write(Integer.toString((testSets[i].split(" ").length - 1) / 2) + "\t");
            for(int k = 1 ; k < threadsMax ; k++) {
                long total = 0;
                for(int j = 0 ; j < repeats ; j++) {
                    DynamicThreads dft = new DynamicThreads();
                    dft.loadData(testSets[i]);
                    dft.setThreads(k);
                    long start = System.currentTimeMillis();
                    dft.solve();
                    long finish = System.currentTimeMillis();
                    long elapsed = finish - start;
                    total += elapsed;
                }
                total = total / repeats;
                writer.write(Long.toString(total) + "\t");
            }
            writer.newLine();
            writer.flush();
        }
        writer.close();
    }*/
    }
}
