package manfred.end;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaPackage;
import com.thoughtworks.qdox.model.JavaSource;
import com.thoughtworks.qdox.model.JavaType;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author xxx
 */
public class Main {
    public static void main(String[] args) throws IOException {

        JavaProjectBuilder builder = new JavaProjectBuilder();
        builder.addSource(new File("tool/parser/qdox/src/main/java/manfred/end/Main.java"));

        JavaClass cls = builder.getClassByName("manfred.end.Main");

        JavaPackage pkg = cls.getPackage();            // "com.blah.foo"
        String name = cls.getName();               // "MyClass"
        String fullName = cls.getCanonicalName(); // "com.blah.foo.MyClass";
        String canonicalName = cls.getFullyQualifiedName(); // "com.blah.foo.MyClass";
        boolean isInterface = cls.isInterface();       // false

        boolean isPublic = cls.isPublic();   // true
        boolean isAbstract = cls.isAbstract(); // true
        boolean isFinal = cls.isFinal();    // false

        JavaType superClass = cls.getSuperClass(); // "com.base.SubClass";
        List<JavaType> imps = cls.getImplements(); // {"java.io.Serializable",
        //  "com.custom.CustomInterface"}

        String author = cls.getTagsByName("author").get(0).getValue(); // "joe"

//        JavaField nameField = cls.getFields().get(0);
//        JavaMethod doStuff = cls.getMethods().get(0);
//        JavaMethod getNumber = cls.getMethods().get(1);

        JavaSource javaSource = cls.getParentSource();

        System.out.println(
                "pkg = " + pkg
                        + "\nname = " + name
                        + "\nfullName = " + fullName
                        + "\nauthor = " + author
                        + "\njavaSource = " + javaSource
        );


    }
}