import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    Service service = new Service();
    //User u=new User("test", "testkode1");

    @BeforeEach
    void setUp() {

    }

    @Test
    void createUserDataString() {


        String actual= service.createUserDataString().getFirst();
        String expected="Ledut;password;The Godfather;Some Like It Hot;";

        assertEquals(expected, actual);


    }
}