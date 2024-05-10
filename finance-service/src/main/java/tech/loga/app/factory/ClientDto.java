package tech.loga.app.factory;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    String ifu;
    String name;
    String contact;
    String address;
}
