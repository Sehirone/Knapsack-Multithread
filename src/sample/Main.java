package sample;

import logic.BruteForceThreads;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int minBFSize = 18;
        int maxBFSize = 26;
        int threadsMax = 10;
        int repeats = 5;
        String fileName = "data.txt";

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        writer.write("Size\t");
        for(int i = 1 ; i < threadsMax ; i++) {
            writer.write(Integer.toString(i) + "\t");
        }
        writer.write("\n");
        writer.flush();

        for(int i = minBFSize ; i < maxBFSize ; i++) {

            writer.write(Integer.toString(i) + "\t");
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
            writer.write("\n");
            writer.flush();
        }
        writer.close();
    }
}
