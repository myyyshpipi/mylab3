package org.example.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Bestiary;
import org.example.model.Monstr;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonHandlerExample  {
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Monstr> importDataExample(File file) throws Exception {
        Bestiary bestiary = mapper.readValue(file, Bestiary.class);
        return bestiary.getBestiary();
    }

    public List<Monstr> importFromJson(String filepath) throws IOException {
        Bestiary wrapper = mapper.readValue(new File(filepath), Bestiary.class);
        return wrapper.getBestiary();
    }


    public void exportDataExample(List<Monstr> monsters, File file) throws Exception {

        Bestiary bestiary = new Bestiary();
        bestiary.setBestiary(monsters);
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, bestiary);
    }
}