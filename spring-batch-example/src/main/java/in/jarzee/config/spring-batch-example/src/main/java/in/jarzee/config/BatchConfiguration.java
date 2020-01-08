package in.jarzee.config;

import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import in.jarzee.entity.EmployeeEntity;
import in.jarzee.model.Employee;

@Configuration
public class BatchConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(BatchConfiguration.class);

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Bean(name = "job")
	public Job getJob() {
		return jobBuilderFactory.get("job")
				.listener(listener())
				.incrementer(new RunIdIncrementer())
				.flow(step1())
				.end()
				.build();
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobExecutionListener() {

			@Override
			public void beforeJob(JobExecution jobExecution) {
				LOGGER.info("JOB STARTED ::: {}", jobExecution);

			}

			@Override
			public void afterJob(JobExecution jobExecution) {
				LOGGER.info("JOB END ::: {}", jobExecution);

			}
		};
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<Employee, EmployeeEntity>chunk(1000)
				.reader(step1Reader())
				.processor(step1Processor())
				.writer(step1Writer())
				.taskExecutor(taskExecutor())
				.build();
	}

	@Bean
	public TaskExecutor taskExecutor() {
		return new SimpleAsyncTaskExecutor("spring_batch");
	}

	@Bean
	public ItemWriter<EmployeeEntity> step1Writer() {
//		JsonObjectMarshaller<EmployeeEntity> objectMarshaller = new JacksonJsonObjectMarshaller<>();
//		JsonFileItemWriter<EmployeeEntity> writer = new JsonFileItemWriter<>(new FileSystemResource("E:\\Training\\data"+ index++ +".json"), objectMarshaller);
//		return writer;
		
		return new JpaItemWriterBuilder<EmployeeEntity>()
				.entityManagerFactory(entityManagerFactory)
				.build();
	}

	@Bean
	public ItemProcessor<Employee, EmployeeEntity> step1Processor() {
		return item -> {
			EmployeeEntity entity = new EmployeeEntity();
			BeanUtils.copyProperties(item, entity);
			return entity;
		};
	}

	@Bean
	public ItemReader<Employee> step1Reader() {
		JsonItemReader<Employee> build = new JsonItemReaderBuilder<Employee>()
				.resource(new FileSystemResource("E:\\Training\\data.json"))
				.jsonObjectReader(new JacksonJsonObjectReader<>(Employee.class))
				.name("jsonReader")
				.build();
		return build;
	}
}
