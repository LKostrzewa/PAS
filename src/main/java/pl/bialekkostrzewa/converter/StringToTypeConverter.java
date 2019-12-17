package pl.bialekkostrzewa.converter;

import org.springframework.core.convert.converter.Converter;
import pl.bialekkostrzewa.model.ClientType;
import pl.bialekkostrzewa.model.NormalClient;
import pl.bialekkostrzewa.model.PremiumClient;
import pl.bialekkostrzewa.model.RegularClient;

public class StringToTypeConverter implements Converter<String, ClientType> {

    @Override
    public ClientType convert(String s) {
        switch (s){
            case "Regular":
                return new RegularClient();
            case "Premium":
                return new PremiumClient();
        }
        return new NormalClient();
    }
}
