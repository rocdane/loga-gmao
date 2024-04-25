package tech.loga.vendor;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ReferenceBuilder {

    private final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    public String clean(String text){
        return text.replaceAll("\\s", "");
    }

    public String build(String customer){
        StringBuilder reference =
                new StringBuilder()
                        .append(clean(customer).substring(0,15).toUpperCase())
                        .append(SDF.format(new Date(System.currentTimeMillis())));
        return reference.toString();
    }
}

