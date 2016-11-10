package org.sergei.batch;

import org.springframework.batch.item.ItemWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Sergei_Doroshenko on 11/10/2016.
 */
public class MyItemWriter implements ItemWriter<String> {
    private BufferedWriter writer;

    public MyItemWriter(String fileName) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(new File(fileName)));
    }

    @Override
    public void write(List<? extends String> items) throws Exception {
        items.forEach(i -> {
            try {
                System.out.println(i);
                writer.write(i + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        writer.close();
    }
}
