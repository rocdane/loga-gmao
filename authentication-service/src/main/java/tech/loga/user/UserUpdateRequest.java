package tech.loga.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserUpdateRequest extends UserRegisterRequest{
    private Long id;
    private Boolean active;

    public UserUpdateRequest(Long id, Boolean active){
        super();
        this.id=id;
        this.active=active;
    }
}
