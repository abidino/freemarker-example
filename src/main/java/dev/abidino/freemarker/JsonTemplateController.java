package dev.abidino.freemarker;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/json")
public class JsonTemplateController {

    private final JsonTemplateService jsonTemplateService;

    public JsonTemplateController(JsonTemplateService jsonTemplateService) {
        this.jsonTemplateService = jsonTemplateService;
    }

    @PostMapping(value = "/transform/{templateName}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String transformJson(@RequestBody String inputJson, @PathVariable String templateName) throws Exception {
        return jsonTemplateService.transformJson(inputJson, templateName);
    }
}