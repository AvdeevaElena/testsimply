import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;

import static org.junit.Assert.*;

public class getJSONTest {

    private getJSON getJSON;

    @Before                                       // перед запуском теста создаем объект
    public  void initTest() throws IOException{
        getJSON = new getJSON();

    }


    @Test                                              // тест получает ответ был ли возвращенный методом JSON объект наполнен данными
    public void readJsonFromUrl() throws IOException {
         String  url ="http://94.140.216.17:8888/";
         assertNotNull(getJSON.readJsonFromUrl(url));

    }


    @Rule
    /* Проверка кода на предмет правильной работы в исключительных ситуациях является одной из важных задач в тестировании.
     В JUnit есть возможность проверить, что в процессе выполнения бросается нужное исключение
     @Rule  ExpectedException позволяет убедиться , что ваш код генерирует определенное исключение. Это дает возможность не влиять на ваши существующие тесты.
     После указания типа ожидаемого исключения ваш тест будет успешным, когда выдается такое исключение, и не будет выполнен
    , если выдается другое исключение или его нет*/

    public final ExpectedException thrown = ExpectedException.none();

    @Test                                                           // @Test - аннотация, что данный метод должен пройти тест
    public void testIsEmptyAdress()  throws IOException {           // тест-метод провряет на выброс исключения при передаче пустой ссылке на сервер
                                                                    // throws IOException, так как мтоды тестируемого класса-оригинала могут выбросить исключения принадлежащие к классу IOException

        thrown.expect(MalformedURLException.class);          // thrown-имя объекта класса ExpectedException
                                                             // expect(), который в данном случае отлавливает ошибку типа MalformedURLException

        JSONObject js = getJSON.readJsonFromUrl(null);      // собственно говоря,  здесь весь тестовый прецедент
    }


    @Test                                                            // тест неверного URL адреса
    public void testWrongAdress()  throws IOException {              // тест-метод провряет на выброс исключения при передаче по ссылке неверногоадреса на сервер
                                                                     // throws IOException, так как мтоды тестируемого класса-оригинала могут выбросить исключения принадлежащие к классу IOException

        thrown.expect(JSONException.class);                          // thrown-имя объекта класса ExpectedException
                                                                     // expect(), который в данном случае отлавливает ошибку типа JSONException

        JSONObject js = getJSON.readJsonFromUrl("https://mail.ru/"); // создание тестового прецедента
    }
}
