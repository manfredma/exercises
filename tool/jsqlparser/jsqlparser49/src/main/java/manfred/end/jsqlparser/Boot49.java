package manfred.end.jsqlparser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.update.Update;

import java.io.StringReader;

public class Boot49 {
    public static void main(String[] args) throws JSQLParserException {
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Update update = (Update) parserManager.parse(new StringReader("update table_a A left join table_B B on A.id = B.a_id set A.x = 3 where A.id = 4"));
        System.out.println(update);
        Statement statement = parserManager.parse(new StringReader("select ifnull(sum(case when cross_sorting_status = 40 then 1 else 0 end), 0) as completed_task_count, count(*) as total_task_count from cross_sorting cs where cs.cross_sorting_status in (10, 20, 30, 40)"));
        System.out.println(statement);

        Statement statement1 = parserManager.parse(new StringReader("insert into table_a(a) values(?)"));
        Statement statement2 = parserManager.parse(new StringReader("insert into table_a values(?, ?, ?)"));
        Statement statement3 = parserManager.parse(new StringReader("insert into table_a values(?, ?, ?),(?, ?, ?)"));
        Statement statement4 = parserManager.parse(new StringReader("INSERT INTO table_a (a, b, c)\n" +
                "SELECT id, name, value \n" +
                "FROM table_b \n" +
                "WHERE status = 'active';"));
        System.out.println(statement);
    }
}
