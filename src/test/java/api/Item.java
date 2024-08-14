package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {

    private String name;
    private Float price;
    private String currency;
    private Integer price_for_quantity;
    private String quantity_unit;

}
