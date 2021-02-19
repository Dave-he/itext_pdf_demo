package pdf;

import com.itextpdf.kernel.font.PdfFont;
import lombok.Data;

import java.util.Map;

@Data
public class OverTextDTO {
    private String sourceFilePath;

    private String finishFilePath;

    private PdfFont font;

    private Map<String,String> replaceMap;
}
