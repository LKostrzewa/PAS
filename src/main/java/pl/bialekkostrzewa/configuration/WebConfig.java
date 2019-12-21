package pl.bialekkostrzewa.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import pl.bialekkostrzewa.converter.StringToTypeConverter;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
public class WebConfig {

    @Bean
    public FormattingConversionService conversionService(){
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        conversionService.addConverter(new StringToTypeConverter());

        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setDateTimeFormatter(DateTimeFormatter.ISO_DATE_TIME);
        registrar.registerFormatters(conversionService);


        return conversionService;
    }
}
