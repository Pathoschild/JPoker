import com.jplamondonw.jpoker.framework.HandType;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * Unit tests for hand types.
 */
public class HandTypeTests {
    // Unit tests
    //******************************
    /**
     * Assert that each hand type has a unique name and value.
     */
    @Test
    public void handTypes_areUnique()
    {
        HashMap<String, Boolean> names = new HashMap<>();
        HashMap<Integer, Boolean> values = new HashMap<>();

        for(HandType type : HandType.values())
        {
            Assert.assertFalse("The name " + type.name + " is used for multiple ranks.", names.containsKey(type.name));
            Assert.assertFalse("The value " + type.value + " is used for multiple ranks.", values.containsKey(type.value));

            names.put(type.name, true);
            values.put(type.value, true);
        }
    }
}
