package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.domain.Menu;
import christmas.domain.Menu.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuTest {

    Menu menu = new Menu();

    @DisplayName("주어진 메뉴의 가격을 반환함")
    @Test
    void ConfirmPriceByFoodNameOfMenu() {
        //given
        String foodName = "제로콜라";
        int price;

        //when
        price = menu.getPrice(foodName);

        //then
        assertThat(price).isEqualTo(3000);
    }

    @DisplayName("주어진 메뉴가 없는 메뉴일 경우 가격을 반환하지 않고 예외를 발생함")
    @Test
    void ConfirmPriceByFoodNameOfNotMenu() {
        //given
        String foodName = "제로사이다";

        //when, then
        assertThatThrownBy(() -> menu.getPrice(foodName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 존재하지 않는 메뉴 입니다.");
    }

    @DisplayName("주어진 메뉴가 메뉴판에 있을 경우 true를 반환함")
    @Test
    void ConfirmAllCategoryContainByFoodNameOfMenu() {
        //given
        String foodName = "제로콜라";

        //when, then
        assertThat(menu.isAllCategoryContain(foodName)).isEqualTo(true);
    }

    @DisplayName("주어진 메뉴가 메뉴판에 없을 경우 false를 반환함")
    @Test
    void ConfirmAllCategoryContainByFoodNameOfNotMenu() {
        //given
        String foodName = "제로사이다";

        //when, then
        assertThat(menu.isAllCategoryContain(foodName)).isEqualTo(false);
    }

    @DisplayName("주어진 메뉴가 특정 카테고리에 있을 경우 true를 반환함")
    @Test
    void ConfirmSpecificCategoryContainByFoodNameOfMenu() {
        //given
        String foodName = "제로콜라";
        Category categoryName = Category.BEVERAGE;

        //when, then
        assertThat(menu.isSpecificCategoryContain(categoryName, foodName)).isEqualTo(true);
    }

    @DisplayName("주어진 메뉴가 특정 카테고리에 없을 경우 false를 반환함")
    @Test
    void ConfirmSpecificCategoryContainByFoodNameOfNotMenu() {
        //given
        String foodName = "제로사이다";
        Category categoryName = Category.BEVERAGE;

        //when, then
        assertThat(menu.isSpecificCategoryContain(categoryName, foodName)).isEqualTo(false);
    }
}
