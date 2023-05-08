package manfred.end.ascii.graph;

import com.github.freva.asciitable.AsciiTable;

public class HelloAsciiTable {
    public static void main(String[] args) {
        String[] headers = {"", "Name", "Diameter", "Mass", "Atmosphere"};
        String[][] data = {
                {"1", "Mercury", "0.382", "0.06", "minimal"},
                {"2", "Venus", "0.949", "0.82", "Carbon dioxide, Nitrogen"},
                {"3", "Earth", "1.000", "1.00", "Nitrogen, Oxygen, Argon"},
                {"4", "Mars", "0.532", "0.11", "Carbon dioxide, Nitrogen, Argon"}};

        System.out.println(AsciiTable.getTable(headers, data));
    }
}
