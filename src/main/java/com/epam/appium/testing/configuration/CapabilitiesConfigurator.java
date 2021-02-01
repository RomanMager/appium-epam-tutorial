package com.epam.appium.testing.configuration;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.*;
import static io.appium.java_client.remote.MobileCapabilityType.APP;
import static io.appium.java_client.remote.MobileCapabilityType.UDID;

public class CapabilitiesConfigurator {

    private CapabilitiesConfigurator() {
    }

    public static DesiredCapabilities gelLocalCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(UDID, ConfigurationReader.getInstance().localDeviceUdid());
        capabilities.setCapability(AVD, ConfigurationReader.getInstance().localDeviceName());
        capabilities.setCapability(APP_PACKAGE, ConfigurationReader.getInstance().appPackage());
        capabilities.setCapability(APP_ACTIVITY, ConfigurationReader.getInstance().appActivity());
        // Absolute locale path to the application
        capabilities.setCapability(APP, new File(ConfigurationReader.getInstance().appPath()).getAbsolutePath());
        return capabilities;
    }
}
