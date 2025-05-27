package org.example.handlers;

import org.example.handlers.JsonHandlerExample;
import org.example.model.*;

import java.io.File;
import java.util.List;

public class TestParser {
    public static void main(String[] args) throws Exception {
//        JsonHandlerExample handler1 = new JsonHandlerExample();
//        try {
//            List<Monstr> monsters = handler1.importDataExample(new File("./data/Bestiarum.json") );
//            //List<Monstr> monsters = handler1.importFromJson( "./data/Bestiarum1.json");
//            handler1.exportDataExample(monsters, new File("./data/Bestiarum_exported1.json"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        //JsonHandler handler2 = new JsonHandler();

        FileImportExportHandler handler3;
        YamlHandler yamlHandler = new YamlHandler();
        XmlHandler xmlHandler = new XmlHandler();
        JsonHandler jsonHandler = new JsonHandler();

        yamlHandler.setNextHandler(xmlHandler);
        xmlHandler.setNextHandler(jsonHandler);
        handler3 = yamlHandler;



        try {
            List<Monstr> monsters = handler3.importData(new File("./data/Bestiarum.yaml") );
            handler3.exportData(monsters, new File("./data/Bestiarum_exported2.json"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
