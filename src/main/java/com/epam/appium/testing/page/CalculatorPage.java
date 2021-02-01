package com.epam.appium.testing.page;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CalculatorPage extends BasePage {

    @AndroidFindBy(id = "user.customcalculator:id/priceEdit")
    private MobileElement priceEdit;

    @AndroidFindBy(id = "user.customcalculator:id/dutyValueEuro")
    private MobileElement dutyValueEuro;

    @AndroidFindBy(id = "user.customcalculator:id/dutyValueUsd")
    private MobileElement dutyValueUsd;

    @AndroidFindBy(id = "user.customcalculator:id/dutyValueByn")
    private MobileElement dutyValueByn;

    @AndroidFindBy(id = "user.customcalculator:id/limitExcessView")
    private MobileElement limitExcessView;

    @AndroidFindBy(id = "user.customcalculator:id/dutyExcessView")
    private MobileElement dutyExcessView;

    @AndroidFindBy(id = "user.customcalculator:id/customsFeeView")
    private MobileElement customsFeeView;

    @AndroidFindBy(id = "user.customcalculator:id/postFeeView")
    private MobileElement postFeeView;

    @AndroidFindBy(id = "user.customcalculator:id/weightCheckBox")
    private MobileElement weightCheckBox;

    @AndroidFindBy(id = "user.customcalculator:id/weightEdit")
    private MobileElement weightEdit;

    @AndroidFindBy(id = "user.customcalculator:id/usdRadioButton")
    private MobileElement usdRadioButton;

    @AndroidFindBy(id = "user.customcalculator:id/euroRadioButton")
    private MobileElement euroRadioButton;

    @AndroidFindBy(id = "user.customcalculator:id/bynRadioButton")
    private MobileElement bynRadioButton;

    @AndroidFindBy(id = "user.customcalculator:id/clearButton")
    private MobileElement clearButton;

    @AndroidFindBy(xpath = "//*[contains(@resource-id, 'dutyValue')]")
    private List<MobileElement> dutyValues;

    public CalculatorPage selectEuroCurrency() {
        euroRadioButton.click();
        return this;
    }

    public CalculatorPage enterPackagePrice(String price) {
        priceEdit.sendKeys(price);
        return this;
    }

    public CalculatorPage checkWeightCheckBox() {
        weightCheckBox.click();
        return this;
    }

    public CalculatorPage enterPackageWeight(String weight) {
        priceEdit.sendKeys(weight);
        return this;
    }

    public String getEuroDutyValue() {
        return dutyValueEuro.getText();
    }

    public List<String> getDutyValues() {
        return dutyValues
                .stream()
                .map(RemoteWebElement::getText)
                .collect(Collectors.toList());
    }
}
