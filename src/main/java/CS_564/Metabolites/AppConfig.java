package CS_564.Metabolites;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public CreateGraph createGraph() throws FileNotFoundException, IOException, ParseException {

        return new CreateGraph();
    }
}