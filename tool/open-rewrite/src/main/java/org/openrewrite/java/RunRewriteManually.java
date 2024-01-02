package org.openrewrite.java;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.openrewrite.ExecutionContext;
import org.openrewrite.InMemoryExecutionContext;
import org.openrewrite.Recipe;
import org.openrewrite.Result;
import org.openrewrite.SourceFile;
import org.openrewrite.internal.InMemoryLargeSourceSet;

public class RunRewriteManually {
    public static void main(String[] args) throws IOException {

        // determine your project directory and provide a list of
        // paths to jars that represent the project's classpath
        Path projectDir = Paths.get("./tool/open-rewrite");
        List<Path> classpath = Collections.emptyList();


        // put any rewrite recipe jars on this main method's runtime classpath
        // and either construct the recipe directly or via an Environment
        // Environment environment = Environment.builder().scanRuntimeClasspath().build();
        Recipe recipe = new UseStaticImport("java.util.Collections emptyList()");

        // create a JavaParser instance with your classpath
        JavaParser javaParser = JavaParser.fromJavaVersion()
                .classpath(classpath)
                .build();

        // walk the directory structure where your Java sources are located
        // and create a list of them
        List<Path> sourcePaths = Files.find(projectDir, 999, (p, bfa) ->
                        bfa.isRegularFile() && p.getFileName().toString().endsWith(".java"))
                .collect(Collectors.toList());

        ExecutionContext ctx = new InMemoryExecutionContext(Throwable::printStackTrace);

        // parser the source files into LSTs
        List<SourceFile> cus = javaParser.parse(sourcePaths, projectDir, ctx)
                .collect(Collectors.toList());

        // collect results
        List<Result> results = recipe.run(new InMemoryLargeSourceSet(cus), ctx)
                .getChangeset().getAllResults();

        for (Result result : results) {
            // print diffs to the console
            System.out.println(result.diff(projectDir));

            // or overwrite the file on disk with changes.
            // Files.writeString(result.getAfter().getSourcePath(),
            //        result.getAfter().printAll());
        }

    }
}