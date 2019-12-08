package sample;

import logic.BruteForceThreads;
import logic.DynamicThreads;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int minBFSize = 20;
        int maxBFSize = 36;
        int threadsMax = 8;
        int repeats = 10;
        String fileName = "data.txt";

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
            }*/
            writer.write(Integer.toString(i) + "\t");
            for(int k = 1 ; k < threadsMax ; k++) {
                long total = 0;
                for(int j = 0 ; j < repeats ; j++) {
                    DynamicThreads dft = new DynamicThreads();
                    dft.loadData("500 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 120 103 155 54 81 79 49 54 168 120 5 12 46 78 90 33 55 12 20 15 60 66 66 77 77 11 11 66 66 77 77 84 63 12 99 54 68");
                    //dft.loadRandomData(i);
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
    }
}
