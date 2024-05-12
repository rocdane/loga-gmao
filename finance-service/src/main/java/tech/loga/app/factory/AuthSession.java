package tech.loga.app.factory;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthSession implements Serializable
{
    private String host, token;
    private Boolean closed;
    private Date createdAt;
    private Date closedAt;
}
