import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GarySheppardM3Tests {
    @Test
    public void testTokenAddSub() {
        IntegerProperty testIceTokens = TowerDefense.getIceTowerTokens();
        Assertions.assertEquals(0, testIceTokens.getValue());
        Tower iceTowerObject = new IceTower();
        if (500 >= iceTowerObject.getCost()) {
            testIceTokens.setValue(testIceTokens.intValue() + 1);
        }
        Assertions.assertEquals(1, testIceTokens.getValue());

        Tower testTowerSelected = new IceTower();
        if (testTowerSelected != null) {
            if ((testTowerSelected instanceof IceTower)
                    && (testIceTokens.getValue() > 0)) {
                testIceTokens.setValue(testIceTokens.getValue() - 1);
            }
        }
        Assertions.assertEquals(0, testIceTokens.getValue());
    }

    @Test
    public void testTokenDisplay() {
        Tower iceTowerObject = new IceTower();
        IntegerProperty testIceTokens = new SimpleIntegerProperty(0);
        StringProperty testIceTowerStored = new SimpleStringProperty("");
        testIceTowerStored.bind(testIceTokens.asString());
        Assertions.assertEquals("0", testIceTowerStored.getValue());
        if (500 >= iceTowerObject.getCost()) {
            testIceTokens.setValue(testIceTokens.intValue() + 1);
        }
        Assertions.assertEquals("1", testIceTowerStored.getValue());
    }
}
