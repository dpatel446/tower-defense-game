package towerdefense;

import com.almasb.fxgl.app.GameApplication;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class WelcomeScreenTest {
    private static final int TIMEOUT = 200;
    private String renameInput1;
    private String renameInput2;
    private String renameInput3;

    private String nameTester;

    private boolean proceed;

    @Before
    public void setup() {
        //renameInput1 = new TextField(null);
        renameInput2 = "";
        renameInput3 = "    ";

        nameTester = "";

        proceed = false;
    }

    @Test(timeout = TIMEOUT)
    public void testICSName() {
        //Test null
        nameTester = renameInput1;
        if ((nameTester != null)
                && (!(nameTester.isEmpty()))
                && (!(nameTester.trim().isEmpty()))) {
            proceed = true;
        }
        assertEquals(false, proceed);
        proceed = false;

        //Test empty
        nameTester = renameInput2;
        if ((nameTester != null)
                && (!(nameTester.isEmpty()))
                && (!(nameTester.trim().isEmpty()))) {
            proceed = true;
        }
        assertEquals(false, proceed);
        proceed = false;

        //Test whitespace
        nameTester = renameInput3;
        if ((nameTester != null)
                && (!(nameTester.isEmpty()))
                && (!(nameTester.trim().isEmpty()))) {
            proceed = true;
        }
        assertEquals(false, proceed);
        proceed = false;

        //Test valid
        nameTester = "Player";
        if ((nameTester != null)
                && (!(nameTester.isEmpty()))
                && (!(nameTester.trim().isEmpty()))) {
            proceed = true;
        }
        assertEquals(true, proceed);
    }
}
