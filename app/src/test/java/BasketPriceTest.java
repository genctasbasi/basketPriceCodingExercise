import com.escmobile.bjss.controller.MainController;
import com.escmobile.bjss.controller.MainControllerEventListener;
import com.escmobile.bjss.model.RateJSON;
import com.google.gson.JsonObject;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * TODO: Add more unit & activity tests & handle async retrofit callbacks!
 * Created by genctasbasi on 27/04/15.
 */
public class BasketPriceTest {

    MainController sut;

    @Before
    public void setup() {
        sut = new MainController();
    }

    @Test
    public void sutInitialized() {
        assertTrue(sut != null);
    }

    @Test
    public void currencyListCallSucceeds() {

        sut.setMainControllerEventListener(new MainControllerEventListener() {
            @Override
            public void onCurrencyListCallSucceed(JsonObject currencyCodeJSON) {
                assertTrue(currencyCodeJSON != null);
                assertTrue(currencyCodeJSON.toString() != null);
                assertTrue(currencyCodeJSON.toString().length() > 0);
            }

            @Override
            public void onCurrencyListCallFailed(String message) {
                fail("Currency list call failed: " + message);
            }

            @Override
            public void onGetRateCallSucceed(String currencyTo, RateJSON currencyJSON) {
            }

            @Override
            public void onGetRateCallFailed(String message) {
            }
        });

        sut.makeGetCurrencyListCall();
    }

    @Test
    public void getRateCallSucceeds() {

        final String TEST_CURRENCY = "USD";

        sut.setMainControllerEventListener(new MainControllerEventListener() {
            @Override
            public void onGetRateCallSucceed(String currencyTo, RateJSON currencyJSON) {
                assertTrue(currencyJSON != null);
                assertTrue(currencyJSON.toString() != null);
                assertTrue(currencyJSON.toString().length() > 0);
            }

            @Override
            public void onGetRateCallFailed(String message) {
                fail("Get rate call failed: " + message);
            }

            @Override
            public void onCurrencyListCallSucceed(JsonObject currencyCodeJSON) {
            }

            @Override
            public void onCurrencyListCallFailed(String message) {
            }
        });

        sut.makeGetRateCall(TEST_CURRENCY);
    }
}
