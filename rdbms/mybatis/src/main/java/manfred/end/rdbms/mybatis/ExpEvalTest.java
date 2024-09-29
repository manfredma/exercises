package manfred.end.rdbms.mybatis;

import org.apache.ibatis.scripting.xmltags.ExpressionEvaluator;

public class ExpEvalTest {

    public static void main(String[] args) {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
        Condition cond = new Condition();
        System.out.println(expressionEvaluator.evaluateBoolean("csuType!=null", cond));
        System.out.println(expressionEvaluator.evaluateBoolean("csuType2!=null", cond));

    }

    static class Condition {
        private String type;
        private Short csuType;
        private short csuType2;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Short getCsuType() {
            return csuType;
        }

        public void setCsuType(Short csuType) {
            this.csuType = csuType;
        }

        public short getCsuType2() {
            return csuType2;
        }

        public void setCsuType2(short csuType2) {
            this.csuType2 = csuType2;
        }
    }
}
