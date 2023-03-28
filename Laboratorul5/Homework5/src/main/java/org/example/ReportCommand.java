package org.example;
import freemarker.template.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class ReportCommand implements Command{
    private Catalog catalog;

    public ReportCommand(Catalog catalog) {
        this.catalog = catalog;
    }
    public void implementCommand() throws InvalidCommandException {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
            cfg.setClassForTemplateLoading(this.getClass(), "C:\\Users\\Stefania\\OneDrive\\Desktop\\Homework5");
            Template template = cfg.getTemplate("report.ftl");
            cfg.setDirectoryForTemplateLoading(new File("C:\\Users\\Stefania\\OneDrive\\Desktop\\Homework5"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
            cfg.setFallbackOnNullLoopVariable(false);
            cfg.setSQLDateAndTimeTimeZone(TimeZone.getDefault());

            Map<String, Object> data = new HashMap<>();
            data.put("documents", catalog.getCatalog());

            Writer out = new OutputStreamWriter(System.out);//new FileOutputStream("report.html")
            template.process(data, out);
            //out.close();
            //Desktop.getDesktop().open(new File("C:\\Users\\Stefania\\OneDrive\\Desktop\\Homework5\\report.html"));

        } catch (IOException | TemplateException e) {
            throw new InvalidCommandException("Error generating the report" + e.getMessage());
        }
    }
}
