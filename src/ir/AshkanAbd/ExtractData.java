package ir.AshkanAbd;

import org.apache.pdfbox.cos.*;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.graphics.*;
import org.apache.pdfbox.pdmodel.graphics.form.*;
import org.apache.pdfbox.pdmodel.graphics.image.*;
import org.apache.pdfbox.text.*;

import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

public class ExtractData {

    private File src, path;
    private PDDocument pdDocument;
    private PrintWriter writer;

    public ExtractData(File src, File path) throws Exception {
        this.src = src;
        this.path = path;
        pdDocument = PDDocument.load(src);
    }

    void extractText() throws Exception {
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(pdDocument);
        Word wordFile = new Word(path);
        wordFile.toWord(text);
    }

    void extractImages() throws Exception {
        int count = 0;
        for (RenderedImage image : getImagesFromPDF(pdDocument)) {
            ImageIO.write(image, "png", new File(path, "Image" + (count++) + ".png"));
        }
    }

    public File getPath() {
        return path;
    }

    public List<RenderedImage> getImagesFromPDF(PDDocument document) throws Exception {
        List<RenderedImage> images = new ArrayList<>();
        for (PDPage page : document.getPages()) {
            images.addAll(getImagesFromResources(page.getResources()));
        }
        return images;
    }

    private List<RenderedImage> getImagesFromResources(PDResources resources) throws Exception {
        List<RenderedImage> images = new ArrayList<>();
        for (COSName xObjectName : resources.getXObjectNames()) {
            PDXObject xObject = resources.getXObject(xObjectName);
            if (xObject instanceof PDFormXObject) {
                images.addAll(getImagesFromResources(((PDFormXObject) xObject).getResources()));
            } else if (xObject instanceof PDImageXObject) {
                images.add(((PDImageXObject) xObject).getImage());
            }
        }
        return images;
    }

    public File getSrc() {
        return src;
    }
}
