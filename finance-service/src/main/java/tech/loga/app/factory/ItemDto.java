package tech.loga.app.factory;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto{
    protected String code;
    protected String name;
    protected Integer price;
    protected Float quantity;
    protected String taxGroup;
    protected Integer taxSpecific;
    protected Integer originalPrice;
    protected String priceModification;
}
