package tech.loga.vendor;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Money {
    private static Money instance;

    private Money(){
    }

    public static Money getInstance(){
        if(instance==null){
            instance = new Money();
        }
        return instance;
    }

    public String format(double amt){
        BigDecimal total = new BigDecimal(amt);
        String value = total.stripTrailingZeros().toPlainString();
        return value;
    }
}
