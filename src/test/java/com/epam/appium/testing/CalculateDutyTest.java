package com.epam.appium.testing;

import com.epam.appium.testing.page.CalculatorPage;
import org.junit.Test;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class CalculateDutyTest extends BaseTest {

    private static final String ZERO_DUTY = "0.0";

    @Test
    public void checkDutyForPriceLowerLimit() {
        CalculatorPage calculatorPage = new CalculatorPage();
        calculatorPage
                .selectEuroCurrency()
                .enterPackagePrice("20");

        SoftAssert softAssert = new SoftAssert();
        calculatorPage.getDutyValues().forEach(value -> {
            softAssert.assertTrue(
                    value.contains(ZERO_DUTY),
                    String.format("Duty %s does not contain value %s", value, ZERO_DUTY)
            );
        });
        softAssert.assertAll();
    }

    @Test
    public void checkDutyPriceUpperLimit() {
        CalculatorPage calculatorPage = new CalculatorPage();
        calculatorPage
                .selectEuroCurrency()
                .enterPackagePrice("30");

        Assert.assertNotEquals(calculatorPage.getEuroDutyValue(), ZERO_DUTY, "Duty calculated incorrectly");
    }

    @Test
    public void checkDutyPriceAndWeightUpperLimit() {
        CalculatorPage calculatorPage = new CalculatorPage();
        calculatorPage
                .selectEuroCurrency()
                .enterPackagePrice("30")
                .checkWeightCheckBox()
                .enterPackageWeight("50");

        Assert.assertNotEquals(calculatorPage.getEuroDutyValue(), ZERO_DUTY, "Duty calculated incorrectly");
    }
}
