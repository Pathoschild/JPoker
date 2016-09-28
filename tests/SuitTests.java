import com.jplamondonw.jpoker.framework.Suit;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * Unit tests for suits.
 */
public class SuitTests {
    // Tests
    //******************************
    /**
     * Assert that each suit has a unique symbol.
     */
    @Test
    public void areUnique()
    {
        HashMap<String, Boolean> symbols = new HashMap<>();

        for(Suit suit : Suit.values())
        {
            Assert.assertFalse("The suit symbol " + suit.symbol + " is used for multiple ranks.", symbols.containsKey(suit.symbol));
            symbols.put(suit.symbol, true);
        }
    }
}