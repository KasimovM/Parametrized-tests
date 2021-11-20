package gmail.mrkvktrvch;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class ParameterizedTestsForDemoQa {

    @DisplayName("Поиск корма различных брэндов")
    @ParameterizedTest(name = "Проверка отображения корма брэнда {0}")
    @ValueSource(strings = {"Almo Nature", "Applaws"})
    void searchFeedFromDifferentBrandsTest(String brandName) {


        open("https://www.petshop.ru/");
        $(".MuiInputBase-input").setValue(brandName).pressEnter();
        $("#catalogue-wrapper").shouldHave(text(brandName));
    }

    @DisplayName("Внесение информации о новом сотруднике в таблицу")
    @Tag("Blocker")
    @ParameterizedTest(name = "Внесение информации о сотруднике {1} {0}")
    @CsvSource(value = {
            "Mark | Kasimov | mrkvktrvch@gmail.com | 28 | 100500 | IT",
            "Ivan | Petrov | yasobaka@mail.com | 35 |50000| Security"
    },
            delimiter = '|')

    void addNewEmployeeTest(String inputFirstname, String inputLastName, String inputEmail, String inputAge, String inputSalary,
                            String inputDepartment) {
        Selenide.open("https://demoqa.com/webtables");
        $("#addNewRecordButton").click();
        $("#firstName").setValue(inputFirstname);
        $("#lastName").setValue(inputLastName);
        $("#userEmail").setValue(inputEmail);
        $("#age").setValue(inputAge);
        $("#salary").setValue(inputSalary);
        $("#department").setValue(inputDepartment);
        $("#submit").click();
        $(".rt-table").shouldHave(text(inputFirstname), text(inputLastName), text(inputEmail), text(inputAge),
        text(inputSalary), text(inputDepartment));
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
}










