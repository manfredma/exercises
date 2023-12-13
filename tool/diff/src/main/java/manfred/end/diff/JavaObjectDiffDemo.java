package manfred.end.diff;

import de.danielbechler.diff.ObjectDiffer;
import de.danielbechler.diff.ObjectDifferBuilder;
import de.danielbechler.diff.inclusion.Inclusion;
import de.danielbechler.diff.inclusion.InclusionResolver;
import de.danielbechler.diff.node.DiffNode;
import java.beans.Transient;
import java.util.Arrays;

public class JavaObjectDiffDemo {
    public static void main(String[] args) {
        ObjectDiffer differ = getObjectDiffer();

        System.out.println(differ.compare(Arrays.asList('a', 'b', 'c', 'd', 'e'),
                Arrays.asList('a', 'c', 'd', 'e')));
    }

    private static ObjectDiffer getObjectDiffer() {
        ObjectDiffer differ = ObjectDifferBuilder.startBuilding()
                .inclusion()
                .resolveUsing(new InclusionResolver() {
                    @Override
                    public Inclusion getInclusion(DiffNode node) {
                        if (node.getPropertyAnnotation(Transient.class) != null) {
                            return Inclusion.EXCLUDED;
                        }
                        return Inclusion.DEFAULT;
                    }

                    @Override
                    public boolean enablesStrictIncludeMode() {
                        return false;
                    }
                })
                .and()
                .build();
        return differ;
    }
}
