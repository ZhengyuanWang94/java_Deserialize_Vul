import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;
import java.util.Comparator;

public class test {
    @Test
    void test_jdk() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        System.out.println(comparator.compare(1, 2));
    }
}
