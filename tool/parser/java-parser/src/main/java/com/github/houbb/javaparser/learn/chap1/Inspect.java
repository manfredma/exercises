package com.github.houbb.javaparser.learn.chap1;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.printer.DotPrinter;
import com.github.javaparser.printer.XmlPrinter;
import com.github.javaparser.printer.YamlPrinter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Inspect {
    public static void main(String[] args) throws IOException {
        // Parse the code you want to inspect:
        CompilationUnit cu = StaticJavaParser.parse("class X { int x; }");
        // Now comes the inspection code:
        System.out.println(cu);

        // Now comes the inspection code:
        YamlPrinter yamlPrinter = new YamlPrinter(true);
        System.out.println(yamlPrinter.output(cu));

        // Now comes the inspection code:
        XmlPrinter xmlPrinter = new XmlPrinter(true);
        System.out.println(xmlPrinter.output(cu));

        // Now comes the inspection code:
        DotPrinter dotPrinter = new DotPrinter(true);
        try (FileWriter fileWriter = new FileWriter("tmp/ast.dot");
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(dotPrinter.output(cu));
        }
    }
}
