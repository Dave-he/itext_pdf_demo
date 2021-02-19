import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.parser.PdfCanvasProcessor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.IPdfTextLocation;
import com.itextpdf.kernel.pdf.canvas.parser.listener.RegexBasedLocationExtractionStrategy;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.maplestone.labstudio.common.utils.pdf.OverTextDTO;
import com.maplestone.labstudio.common.utils.pdf.ReplaceUtils;

import java.io.IOException;
import java.util.*;

public class PdfTest {


    public static void main(String[] args) throws Exception {
//        findPosition();
        test3();
    }

    private static void test3() throws IOException{
        String src = "D:\\2.pdf";
        String output = "D:\\5.pdf";
        PdfReader reader = new PdfReader(src);
        PdfDocument pdfDocument = new PdfDocument(reader, new PdfWriter(output));
        Document document = new Document(pdfDocument);
        Image image = new Image(ImageDataFactory.create("D:\\2.png"));
        image.scaleToFit(50, 50);
        image.setFixedPosition(530, 300);
//        Paragraph paragraph = new Paragraph("aaa").add(image);
        document.add(image);
        document.close();
    }
    /**
     * 关键字定位
     * @throws Exception
     */
    public static void findPosition() throws Exception {
        String sourceFolder2 = "D:\\4.pdf";
        String output = "D:\\3.pdf";
        PdfReader reader = new PdfReader(sourceFolder2);
        PdfDocument pdfDocument = new PdfDocument(reader, new PdfWriter(output));
        PdfPage lastPage = pdfDocument.getLastPage();
        RegexBasedLocationExtractionStrategy strategy = new RegexBasedLocationExtractionStrategy("ello");
        PdfCanvasProcessor canvasProcessor = new PdfCanvasProcessor(strategy);
        canvasProcessor.processPageContent(lastPage);
        Collection<IPdfTextLocation> resultantLocations = strategy.getResultantLocations();
        PdfCanvas pdfCanvas = new PdfCanvas(lastPage);
        pdfCanvas.setLineWidth(0.5f);
        List<IPdfTextLocation> sets = new ArrayList();
        for (IPdfTextLocation location : resultantLocations) {
            Rectangle rectangle = location.getRectangle();
            pdfCanvas.rectangle(rectangle);
            pdfCanvas.setStrokeColor(ColorConstants.RED);
            pdfCanvas.stroke();
            System.out.println(rectangle.getX() + "," + rectangle.getY() + "," + rectangle.getLeft() + "," +
                    rectangle.getRight() + "," + rectangle.getTop() + "," + rectangle.getBottom() + "," +
                    rectangle.getWidth() + "," + rectangle.getHeight());
            System.out.println(location.getText());
            sets.add(location);
        }
        sets.sort((o1, o2) -> o1.getRectangle().getY() - o2.getRectangle().getY() > 0 ? 1 : o1.getRectangle().getY() - o2.getRectangle().getY() == 0 ? 0 : -1);
//        System.out.println(sets.get(0).getRectangle().getY());
        pdfDocument.close();
    }

    private static void test() throws IOException {
        OverTextDTO overTextDTO = new OverTextDTO();

        overTextDTO.setSourceFilePath("D:\\test.pdf");
        overTextDTO.setFinishFilePath("D:\\1.pdf");

        PdfFont font = PdfFontFactory.createFont("D:/SIMSUN.TTF", PdfEncodings.IDENTITY_H);

        overTextDTO.setFont(font);

        Map<String,String> replaceMap = new HashMap<String, String>(25);

        replaceMap.put("name1","天真");
        replaceMap.put("name2","假如");
        replaceMap.put("idCard1","987654321123456789");
        replaceMap.put("idCard2","123456789987654321");
        replaceMap.put("moneyD","伍万贰仟圆整");
        replaceMap.put("moneyX","52000");
        replaceMap.put("beginYear","2019");
        replaceMap.put("beginMonth","8");
        replaceMap.put("beginDay","15");
        replaceMap.put("endYear","2020");
        replaceMap.put("endMonth","05");
        replaceMap.put("endDay","20");
        replaceMap.put("sumMonth","10");
        replaceMap.put("interest","0.05");
        replaceMap.put("sumInterest","5200");
        replaceMap.put("returnType","一次性付清");
        replaceMap.put("year","2020");
        replaceMap.put("month","05");
        replaceMap.put("day","20");
        replaceMap.put("moneyWD","伍佰贰拾元整");
        replaceMap.put("moneyWX","520");
        replaceMap.put("sender","天真");
        replaceMap.put("receiver","假如");
        replaceMap.put("signYear","2019");
        replaceMap.put("signMonth","08");
        replaceMap.put("signDay","15");


        overTextDTO.setReplaceMap(replaceMap);

        ReplaceUtils.doOverText(overTextDTO);
    }
}
