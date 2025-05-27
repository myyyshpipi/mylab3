package org.example.handlers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Monstr;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonHandler implements FileImportExportHandler {
    private FileImportExportHandler nextHandler;

    @Override
    public void setNextHandler(FileImportExportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public List<Monstr> importData(File file) throws Exception {
        if (!supports(file)) {
            if (nextHandler != null) return nextHandler.importData(file);
            throw new Exception("Unsupported format");
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(file);
        List<Monstr> monsters;

        if (root.has("creatures")) {
            monsters = mapper.readValue(
                    root.get("creatures").toString(),
                    new TypeReference<List<Monstr>>() {}
            );
        } else {
            monsters = mapper.readValue(
                    file,
                    new TypeReference<List<Monstr>>() {}
            );
        }

        monsters.forEach(m -> m.setSourceType("json"));
        return monsters;
    }

    @Override
    public void exportData(List<Monstr> monsters, File file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, List<Monstr>> data = new HashMap<>();
        data.put("creatures", monsters);
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, data);
    }

    @Override
    public boolean supports(File file) {
        return file.getName().toLowerCase().endsWith(".json");
    }
}