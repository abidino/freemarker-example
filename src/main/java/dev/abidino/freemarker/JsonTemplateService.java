package dev.abidino.freemarker;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

@Service
public class JsonTemplateService {

    private final ObjectMapper objectMapper;

    public JsonTemplateService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String transformJson(String inputJson, String templateName) throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        String templateContent = JsonFileReader.readJsonFile(templateName);
        Map<String, Object> dataModel = objectMapper.readValue(inputJson, new TypeReference<>() {
        });
        Template template = new Template(templateName, new StringReader(templateContent), cfg);
        StringWriter writer = new StringWriter();
        template.process(dataModel, writer);
        return writer.toString();
    }
}
