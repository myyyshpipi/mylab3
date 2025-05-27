package org.example.handlers;

import org.example.model.*;
import java.io.File;
import java.util.List;

public class TestParser {
    public static void main(String[] args) throws Exception {

        FileImportExportHandler handler3;

        YamlHandler yamlHandler = new YamlHandler();
        XmlHandler xmlHandler = new XmlHandler();
        JsonHandler jsonHandler = new JsonHandler();

        yamlHandler.setNextHandler(xmlHandler);
        xmlHandler.setNextHandler(jsonHandler);
        handler3 = yamlHandler;
        try {
            List<Monstr> monsters = handler3.importData(new File("./data/Bestiarum1.yaml") );
            handler3.exportData(monsters, new File("./data/Bestiarum_exported2.json"));
            handler3.exportData(monsters, new File("./data/Bestiarum_exported2.yaml"));
            handler3.exportData(monsters, new File("./data/Bestiarum_exported2.xml"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
