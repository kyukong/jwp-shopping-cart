package woowacourse.shoppingcart.domain.customer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserIdTest {

    @DisplayName("아이디에 null 을 입력하면 안된다.")
    @Test
    void userIdNullException() {
        // when & then
        assertThatThrownBy(() -> new UserId(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("아이디를 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("아이디에 빈값을 입력하면 안된다.")
    void userIdBlankException(String userId) {
        // when & then
        assertThatThrownBy(() -> new UserId(userId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("아이디를 입력해주세요.");
    }

    @DisplayName("아이디가 이메일 형식이 아니면 안된다.")
    @Test
    void userIdFormatException() {
        // when & then
        assertThatThrownBy(() -> new UserId("userId"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("아이디는 이메일 형식으로 입력해주세요.");
    }
}
