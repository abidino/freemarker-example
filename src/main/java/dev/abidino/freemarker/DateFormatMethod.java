package dev.abidino.freemarker;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.text.SimpleDateFormat;
import java.util.List;

public class DateFormatMethod implements TemplateMethodModelEx {
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        if (arguments.size() != 2) {
            throw new TemplateModelException("formatDate method requires 2 arguments: date and format");
        }

        String date = ((SimpleScalar) arguments.get(0)).getAsString();
        String format = ((SimpleScalar) arguments.get(1)).getAsString();

        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat outputFormat = new SimpleDateFormat(format);
            return outputFormat.format(inputFormat.parse(date));
        } catch (Exception e) {
            throw new TemplateModelException("Error parsing or formatting date", e);
        }
    }
}