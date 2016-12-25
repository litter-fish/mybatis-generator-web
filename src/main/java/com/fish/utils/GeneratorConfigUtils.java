package com.fish.utils;

import org.dom4j.Document;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yudin on 2016/12/18.
 */
public class GeneratorConfigUtils {


    public static void generate(Document document) {
        try {
            generate(parse(document));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static org.w3c.dom.Document parse(Document doc) throws Exception {
        if (doc == null) {
            return (null);
        }
        java.io.StringReader reader = new java.io.StringReader(doc.asXML());
        org.xml.sax.InputSource source = new org.xml.sax.InputSource(reader);
        javax.xml.parsers.DocumentBuilderFactory documentBuilderFactory =
                javax.xml.parsers.DocumentBuilderFactory.newInstance();
        javax.xml.parsers.DocumentBuilder documentBuilder = documentBuilderFactory.
                newDocumentBuilder();
        return (documentBuilder.parse(source));
    }

    protected  static void generate(org.w3c.dom.Document document) {
        List<String> warnings = new ArrayList<String>();
        Set<String> fullyqualifiedTables = new HashSet<String>();
        Set<String> contexts = new HashSet<String>();
        try {

            MyConfigurationParser myConfigurationParser = new MyConfigurationParser(warnings);
            Configuration config = myConfigurationParser.parseConfiguration(document);

            DefaultShellCallback shellCallback = new DefaultShellCallback(false);

            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, shellCallback, warnings);

            ProgressCallback progressCallback = new VerboseProgressCallback();

            myBatisGenerator.generate(progressCallback, contexts, fullyqualifiedTables);

        } catch (IOException | XMLParserException | InterruptedException | InvalidConfigurationException | SQLException e) {
            e.printStackTrace();
        }
    }
}
