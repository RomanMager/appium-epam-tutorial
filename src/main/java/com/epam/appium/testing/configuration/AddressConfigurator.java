package com.epam.appium.testing.configuration;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Optional;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.SESSION_OVERRIDE;

public class AddressConfigurator {

    private static final Logger LOG = LogManager.getRootLogger();
    private static AppiumDriverLocalService appiumDriverLocalService;

    private AddressConfigurator() {
    }

    public static AppiumDriverLocalService getAppiumDriverLocalService(int port) {
        if (appiumDriverLocalService == null) {
            startService(port);
        }

        return appiumDriverLocalService;
    }

    public static void startService(int port) {
        makePortAvailableIfOccupied(port);
        appiumDriverLocalService = new AppiumServiceBuilder()
                .withIPAddress(ConfigurationReader.getInstance().appiumAddress())
                .usingPort(port)
                .withArgument(SESSION_OVERRIDE)
                .withArgument(LOG_LEVEL, "error")
                .build();

        appiumDriverLocalService.start();
        LOG.info("Appium server started on port {}", port);
    }

    public static void stopService() {
        Optional.ofNullable(appiumDriverLocalService).ifPresent(service -> {
            service.stop();
            LOG.info("Appium server was stopped");
        });
    }

    private static void makePortAvailableIfOccupied(int port) {
        if (!isPortFree(port)) {
            try {
                Runtime.getRuntime().exec("taskkill /F /IM node.exe");
            } catch (IOException e) {
                LOG.error("Could not perform runtime command: {}", e.getMessage());
            }
        }
    }

    private static boolean isPortFree(int port) {
        boolean isFree = true;

        try (ServerSocket socket = new ServerSocket(port)) {
            LOG.info("Specified port – {} is available and ready to use", port);
        } catch (IOException e) {
            isFree = false;
            LOG.warn("Specified port – {} is occupied by a process, process will be terminated", port);
        }

        return isFree;
    }
}
