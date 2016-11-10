package org.sergei.batch;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.AbstractLineTokenizer;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sergei_Doroshenko on 11/9/2016.
 */
@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration
public class BatchConfiguration {

    @Value("${name:Default}")
    private String name;

    @Value("${file.in}")
    private String inputFile;

    @Value("${file.out}")
    private String outputFile;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public ItemReader<String> reader() throws FileNotFoundException {
        return new MyItemReader(inputFile);
    }

    @Bean
    public ItemProcessor<String, String> processor() {
        return new ItemProcessor<String, String>() {
            @Override
            public String process(String item) throws Exception {
                return new StringBuffer(item).reverse().toString();
            }
        };
    }

    @Bean
    public ItemWriter<String> writer() throws IOException {
        return new MyItemWriter(outputFile);
    }

    @Bean
    public Step convertStep () throws IOException {
        return stepBuilderFactory.get("convertStep")
                .<String, String>chunk(20)
                .reader   ( reader()    )
                .processor( processor() )
                .writer   ( writer()    )
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("simpleStep")
                .tasklet(new Tasklet() {
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
                        System.out.println( "I'm started: " + name );
                        return null;
                    }
                })
                .build();
    }

    @Bean
    public Job job(Step convertStep) throws Exception {
        return jobBuilderFactory.get( name )
                .incrementer(new RunIdIncrementer())
                .start((Step) convertStep)
                .build();
    }
}
