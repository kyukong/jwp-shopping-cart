package woowacourse.shoppingcart.application.dto.request;

public class CustomerUpdateRequest {

    private String nickname;

    private CustomerUpdateRequest() {
    }

    public CustomerUpdateRequest(final String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
