package tech.loga.app.factory;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityElementsDto {
    String dateTime;
    String qrCode;
    String codeMECeFDGI;
    String counters;
    String num;
    String errorCode;
    String errorDesc;
}
