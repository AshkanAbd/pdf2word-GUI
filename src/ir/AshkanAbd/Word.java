package ir.AshkanAbd;

import org.docx4j.openpackaging.packages.*;
import org.docx4j.openpackaging.parts.WordprocessingML.*;

import java.io.*;
import java.util.*;

public class Word {
    private File path;

    public Word(File path) {
        this.path = path;
    }

    void toWord(String text) throws Exception{
        WordprocessingMLPackage wordprocessing = WordprocessingMLPackage.createPackage();
        MainDocumentPart document = wordprocessing.getMainDocumentPart();
        document.addParagraphOfText(text);
        File wordFile = new File(path,"text.docx");
        wordprocessing.save(wordFile);
    }
}
