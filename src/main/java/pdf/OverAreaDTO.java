package pdf;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OverAreaDTO {

    private String key;

    private String value;

    private Integer pageNum;

    private Float x;

    private Float y;

    private Float width;

    private Float height;
}
