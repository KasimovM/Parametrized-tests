package gmail.mrkvktrvch;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ParameterizedTests {

    @DisplayName("Поиск корма различных брэндов")
    @ParameterizedTest(name = "Проверка отображения корма брэнда {0}")
    @ValueSource(strings = {
            "Almo Nature",
            "Applaws"
    })

    void searchFeedFromDifferentBrandsTestWithValueSource(String brandName) {

        open("https://www.petshop.ru/");
        $(".MuiInputBase-input").setValue(brandName).pressEnter();
        $("#catalogue-wrapper").shouldHave(text(brandName));
    }

    @DisplayName("Внесение информации о новом сотруднике в таблицу")
    @Tag("Blocker")
    @ParameterizedTest(name = "Внесение информации о сотруднике {1} {0}")
    @CsvSource(value = {
            "Mark | Kasimov | mrkvktrvch@gmail.com | 28 | 100500 | IT",
            "Ivan | Petrov | yasobaka@mail.com | 35 |50000| Security"},
            delimiter = '|')

    void addNewEmployeeTestWithCsvSource(String firstname, String lastName, String email, String age, String salary,
                            String department) {

        Selenide.open("https://demoqa.com/webtables");
        $("#addNewRecordButton").click();
        $("#firstName").setValue(firstname);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#age").setValue(age);
        $("#salary").setValue(salary);
        $("#department").setValue(department);
        $("#submit").click();
        $(".rt-table").shouldHave(text(firstname), text(lastName), text(email), text(age),
                text(salary), text(department));
    }

    @EnumSource(UsersList.class)
    @DisplayName("Внесение информации о новом сотруднике в таблицу")
    @ParameterizedTest(name = "Внесение информации о сотруднике {0}")

    void addNewEmployeeTestWithEnumSource(UsersList usersList) {

        Selenide.open("https://demoqa.com/webtables");
        $("#addNewRecordButton").click();
        $("#firstName").setValue(usersList.getName());
        $("#lastName").setValue(usersList.getLastName());
        $("#userEmail").setValue(usersList.getEmail());
        $("#age").setValue(usersList.getAge());
        $("#salary").setValue(usersList.getSalary());
        $("#department").setValue(usersList.getDepartment());
        $("#submit").click();
        $(".rt-table").shouldHave(text(usersList.getName()), text(usersList.getLastName()), text(usersList.getEmail()),
                text(usersList.getAge()), text(usersList.getSalary()), text(usersList.getDepartment()));
    }

    static Stream<Arguments> addNewEmployeeTestWithMethodSource() {
        return Stream.of(
                Arguments.of("Mark", "Kasimov", "mrkvktrvch@gmail.com", "28", "100500", "IT"),
                Arguments.of("Ivan", "Petrov", "yasobaka@mail.ru", "35", "50000", "Security")
        );
    }

    @MethodSource
    @DisplayName("Внесение информации о новом сотруднике в таблицу")
    @ParameterizedTest(name = "Внесение информации о сотруднике {1} {0}")

    void addNewEmployeeTestWithMethodSource(String name, String lastname, String email, String age, String salary,
                                            String department) {

        open("https://demoqa.com/webtables");
        $("#addNewRecordButton").click();
        $("#firstName").setValue(name);
        $("#lastName").setValue(lastname);
        $("#userEmail").setValue(email);
        $("#age").setValue(age);
        $("#salary").setValue(salary);
        $("#department").setValue(department);
        $("#submit").click();
        $(".rt-table").shouldHave(text(name), text(lastname), text(email), text(age), text(salary), text(department));
    }
}
