package manfred.end.jsqlparser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.update.Update;

import java.io.StringReader;

public class Boot {
    public static void main(String[] args) throws JSQLParserException {
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Update update = (Update)parserManager.parse(new StringReader("update table_a A left join table_B B on A.id = B.a_id set A.x = 3 where A.id = 4"));
        System.out.println(update);
    }
}
