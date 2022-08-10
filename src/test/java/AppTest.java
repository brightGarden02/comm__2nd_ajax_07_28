import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AppTest {

    @Test
    void 테스트_assertTHat() {
        int rs = 10 + 20;
        assertThat(rs).isEqualTo(30);
    }
}
