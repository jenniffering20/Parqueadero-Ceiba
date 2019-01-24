

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootApplication
@SpringBootTest
public class Application{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MongoMappingContext mongoMappingContext) {
		MappingMongoConverter convert = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory),mongoMappingContext);
		convert.setTypeMapper(new DefaultMongoTypeMapper(null));
		
		MongoTemplate mongoTemplate =new MongoTemplate(mongoDbFactory,convert);
		
		return mongoTemplate;

    }
	
}

