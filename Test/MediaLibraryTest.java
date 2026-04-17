import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MediaLibraryTest {

    @org.junit.jupiter.api.Test
    void createMedia() {

        MediaLibrary ml = new MediaLibrary();

        //ASSERT
        assertEquals("The Godfather", ml.getAllMedia().getFirst().title);
        assertEquals(9.2, ml.getAllMedia().getFirst().rating);
    }
}