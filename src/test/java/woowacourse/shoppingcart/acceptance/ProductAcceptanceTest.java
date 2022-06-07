package woowacourse.shoppingcart.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import woowacourse.shoppingcart.application.dto.response.ProductResponse;
import woowacourse.shoppingcart.domain.Product;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static woowacourse.fixture.ProductFixture.findProductById;
import static woowacourse.fixture.ProductFixture.findProductsInPage;
import static woowacourse.fixture.ProductFixture.getProductId;

@DisplayName("상품 관련 기능 인수테스트")
public class ProductAcceptanceTest extends AcceptanceTest {

    @DisplayName("상품을 추가한다")
    @Test
    void addProduct() {
        ExtractableResponse<Response> response = 상품_등록_요청("치킨", 10_000, "http://example.com/chicken.jpg");

        상품_추가됨(response);
    }

    @DisplayName("상품 목록을 조회한다")
    @Test
    void getProducts() {
        Long productId1 = 상품_등록되어_있음("치킨", 10_000, "http://example.com/chicken.jpg");
        Long productId2 = 상품_등록되어_있음("맥주", 20_000, "http://example.com/beer.jpg");

        ExtractableResponse<Response> response = 상품_목록_조회_요청();

        조회_응답됨(response);
        상품_목록_포함됨(productId1, productId2, response);
    }

    @DisplayName("상품을 조회한다.")
    @Test
    void findById() {
        // given
        Long productId = getProductId("치킨", 10_000, "http://example.com/chicken.jpg");

        // when
        ExtractableResponse<Response> response = findProductById(productId);

        // then
        ProductResponse resultProductResponse = response.as(ProductResponse.class);
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(resultProductResponse.getId()).isEqualTo(productId),
                () -> assertThat(resultProductResponse.getName()).isEqualTo("치킨"),
                () -> assertThat(resultProductResponse.getPrice()).isEqualTo(10_000),
                () -> assertThat(resultProductResponse.getImageUrl()).isEqualTo("http://example.com/chicken.jpg")
        );
    }

    @DisplayName("존재하지 않는 상품을 조회할 경우 404 에러가 발생한다.")
    @Test
    void getProductFalse() {
        // when
        ExtractableResponse<Response> response = findProductById(1L);

        // then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.NOT_FOUND.value()),
                () -> assertThat(response.body().jsonPath().getString("message")).isEqualTo(
                        "존재하지 않는 상품입니다."
                )
        );
    }

    @DisplayName("현재 페이지에 해당하는 상품 목록을 조회한다.")
    @Test
    void findInPage() {
        // given
        getProductId("치킨", 10_000, "http://example.com/chicken.jpg");
        getProductId("치킨2", 10_000, "http://example.com/chicken.jpg");
        getProductId("치킨3", 10_000, "http://example.com/chicken.jpg");
        getProductId("치킨4", 10_000, "http://example.com/chicken.jpg");

        // when
        ExtractableResponse<Response> response = findProductsInPage(1L, 5L);

        // then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        );
    }

    @ParameterizedTest
    @ValueSource(longs = {-1L, 0L})
    @DisplayName("상품 목록을 조회할 때 페이지가 0 이하이면 404 에러가 발생한다.")
    void findProductsInPageInvalidPageException(Long pageNum) {
        // when
        ExtractableResponse<Response> response = findProductsInPage(pageNum, 5L);

        // then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.NOT_FOUND.value()),
                () -> assertThat(response.body().jsonPath().getString("message")).isEqualTo(
                        "페이지는 1 이상이어야 합니다."
                )
        );
    }

    @ParameterizedTest
    @ValueSource(longs = {-1L, 0L})
    @DisplayName("상품 목록을 조회할 때 상품을 조회할 개수가 0 이하이면 404 에러가 발생한다.")
    void findProductsInPageInvalidLimitException(Long limitCount) {
        // when
        ExtractableResponse<Response> response = findProductsInPage(1L, limitCount);

        // then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.NOT_FOUND.value()),
                () -> assertThat(response.body().jsonPath().getString("message")).isEqualTo(
                        "상품을 조회할 개수는 1 이상이어야 합니다."
                )
        );
    }

    @DisplayName("상품을 삭제한다")
    @Test
    void deleteProduct() {
        Long productId = 상품_등록되어_있음("치킨", 10_000, "http://example.com/chicken.jpg");

        ExtractableResponse<Response> response = 상품_삭제_요청(productId);

        상품_삭제됨(response);
    }

    public static ExtractableResponse<Response> 상품_등록_요청(String name, int price, String imageUrl) {
        Product productRequest = new Product(name, price, imageUrl);

        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(productRequest)
                .when().post("/api/products")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 상품_목록_조회_요청() {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/api/products")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 상품_조회_요청(Long productId) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/api/products/{productId}", productId)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 상품_삭제_요청(Long productId) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().delete("/api/products/{productId}", productId)
                .then().log().all()
                .extract();
    }

    public static void 상품_추가됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("Location")).isNotBlank();
    }

    public static Long 상품_등록되어_있음(String name, int price, String imageUrl) {
        ExtractableResponse<Response> response = 상품_등록_요청(name, price, imageUrl);
        return Long.parseLong(response.header("Location").split("/products/")[1]);
    }

    public static void 조회_응답됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    public static void 상품_목록_포함됨(Long productId1, Long productId2, ExtractableResponse<Response> response) {
        List<Long> resultProductIds = response.jsonPath().getList(".", Product.class).stream()
                .map(Product::getId)
                .collect(Collectors.toList());
        assertThat(resultProductIds).contains(productId1, productId2);
    }

    public static void 상품_조회됨(ExtractableResponse<Response> response, Long productId) {
        Product resultProduct = response.as(Product.class);
        assertThat(resultProduct.getId()).isEqualTo(productId);
    }

    public static void 상품_삭제됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}
