import static org.junit.Assert.*;
import org.ff4j.FF4j;
import org.ff4j.conf.XmlParser;
import org.junit.Test;

public class FF4jHelloTest {
    @Test
    public void testMyFirst() {
        assertNotNull(getClass().getClassLoader().getResourceAsStream("ff4j.xml"));

        // When: init
        FF4j ff4j = new FF4j(new XmlParser(), "ff4j.xml");

        assertEquals(5, ff4j.getFeatures().size());
        // 是否存在特性
        assertTrue(ff4j.exist("hello"));
        // 特性是否生效
        assertTrue(ff4j.check("hello"));

        // Usage
        if (ff4j.check("hello")) {
            System.out.println("Hello FF4j !");
        }

        ff4j.disable("hello");
        assertFalse(ff4j.check("hello"));
    }

    @Test
    public void testAutoCreate() {
        assertNotNull(getClass().getClassLoader().getResourceAsStream("ff4j.xml"));

        // When: init
        FF4j ff4j = new FF4j(new XmlParser(), "ff4j.xml");

        try {
            // 如果没有开始自动创建，会抛异常
            if (ff4j.check("notExistFeature")) {
                // do nothing
            }
        } catch (Exception e) {
            System.out.println("ff4j throw exception when feature not exist !");
        }

        // 开启后，如果不存在会自动创建，默认不生效
        ff4j.setAutocreate(true);
        if (!ff4j.check("notExistFeature")) {
            System.out.println("feature toggle off or not exist");
        }
    }

    @Test
    public void testGroups() {
        assertNotNull(getClass().getClassLoader().getResourceAsStream("ff4j.xml"));

        // When: init
        FF4j ff4j = new FF4j(new XmlParser(), "ff4j.xml");

        assertTrue(ff4j.exist("userStory3_1"));
        assertTrue(ff4j.exist("userStory3_2"));

        // 获取分组
        assertTrue(ff4j.getFeatureStore().readAllGroups().contains("sprint_3"));
        assertEquals("sprint_3", ff4j.getFeature("userStory3_1").getGroup());
        assertEquals("sprint_3", ff4j.getFeature("userStory3_2").getGroup());

        assertFalse(ff4j.check("userStory3_1"));
        assertFalse(ff4j.check("userStory3_2"));

        // 启用分组，这里用分组可以同时开启或关闭多个特性功能
        ff4j.getFeatureStore().enableGroup("sprint_3");

        assertTrue(ff4j.check("userStory3_1"));
        assertTrue(ff4j.check("userStory3_2"));
    }
}
