package manfred.end.error.prone;

import java.util.HashSet;
import java.util.Set;

public class ShortSet {
  public static void main (String[] args) {
    Set<Short> s = new HashSet<>();
    for (short i = 0; i < 100; i++) {
      s.add(i);
      // 下面这行去掉注释会出现编译问题！
      // s.remove(i - 1);
    }
    System.out.println(s.size());
  }
}