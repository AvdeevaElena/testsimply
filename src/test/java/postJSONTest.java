import com.sun.net.httpserver.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;

import static org.junit.Assert.*;

public class postJSONTest {


    private postJSON postJSON;


    @Before                                       // перед запуском теста создаем объект
    public  void initTest() throws IOException{
       postJSON = new postJSON();

    }

    /*Аннотация After обозначает методы, которые будут вызваны после выполнения теста, методы должны быть public void.
     Здесь размещаются операции освобождения ресурсов после теста,в нашем случае — очистка очистка инициализации создания объекта
     Это, анологично правилу использования многопоточности - открыли поток затем  после использования - закрыли.*/
    @After
    public void afterTest() {
        postJSON = null;
    }


    @Test                                          // тест получает ответ получен ли адрес на локальном сервере
    public void checkLocal() throws IOException {

        assertEquals(true,postJSON.server.getAddress().getAddress().isAnyLocalAddress());
        postJSON = null;
    }

}