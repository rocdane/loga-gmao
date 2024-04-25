package tech.loga.vendor;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ReferenceBuilder {

    public String clean(String text){
        return text.replaceAll("\\s", "");
    }

    public String build(String customer){
        StringBuilder reference =
                new StringBuilder()
                        .append(clean(customer).substring(0,13).toUpperCase())
                        .append(clean(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()))));
        return reference.toString();
    }
}

