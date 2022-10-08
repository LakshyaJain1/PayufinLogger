package com.payufin.integration.payufinLogger.services;

import com.payufin.integration.payufinLogger.models.LoggingConfigModel;
import org.apache.logging.log4j.core.config.Configurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static com.payufin.integration.payufinLogger.utils.Constants.consoleLogMaxLevel;
import static com.payufin.integration.payufinLogger.utils.Constants.consoleLogMinLevel;
import static com.payufin.integration.payufinLogger.utils.Constants.fileLogMaxLevel;
import static com.payufin.integration.payufinLogger.utils.Constants.fileLogMinLevel;
import static com.payufin.integration.payufinLogger.utils.Constants.logPath;
import static com.payufin.integration.payufinLogger.utils.Constants.rootLogLevel;
import static com.payufin.integration.payufinLogger.utils.Constants.serviceName;

/**
 * Author - lakshya.jain <br>
 * Date - 08/10/2022
 *
 * <p>
 * This is helper service which you can autowire and use its {@link #refreshLoggingConfigs} method
 * in post construct annotated function of your application class in order to refresh the
 * logging config at application start
 *
 * </p>
 *
 * @see <a href="https://payufin.atlassian.net/wiki/spaces/EN/pages/1123123207/Logging+Framework">Documentation</a> documentation for more details regarding this.
 */

@Service
public class LoggingConfigService {

    @Autowired
    LoggingConfigProvider loggingConfigProvider;

    public void refreshLoggingConfigs() {
        LoggingConfigModel loggingConfigModel = loggingConfigProvider.getLoggingConfigFromSource();
        updateConfigModel(loggingConfigModel);
        updateSystemProperties(loggingConfigModel);
    }

    private void updateSystemProperties(LoggingConfigModel loggingConfigModel) {
        System.setProperty(logPath, loggingConfigModel.getLogPath());
        System.setProperty(serviceName, loggingConfigModel.getServiceName());
        System.setProperty(rootLogLevel, loggingConfigModel.getRootLogLevel());
        System.setProperty(consoleLogMinLevel, loggingConfigModel.getConsoleLogMinLevel());
        System.setProperty(consoleLogMaxLevel, loggingConfigModel.getConsoleLogMaxLevel());
        System.setProperty(fileLogMinLevel, loggingConfigModel.getFileLogMinLevel());
        System.setProperty(fileLogMaxLevel, loggingConfigModel.getFileLogMaxLevel());
        Configurator.reconfigure();
    }

    private void updateConfigModel(LoggingConfigModel loggingConfigModel) {
        loggingConfigModel.setLogPath(getProperty(System.getProperty(logPath), loggingConfigModel.getLogPath()));
        loggingConfigModel.setServiceName(getProperty(System.getProperty(serviceName), loggingConfigModel.getServiceName()));
        loggingConfigModel.setRootLogLevel(getProperty(System.getProperty(rootLogLevel), loggingConfigModel.getRootLogLevel()));
        loggingConfigModel.setConsoleLogMinLevel(getProperty(System.getProperty(consoleLogMinLevel), loggingConfigModel.getConsoleLogMinLevel()));
        loggingConfigModel.setConsoleLogMaxLevel(getProperty(System.getProperty(consoleLogMaxLevel), loggingConfigModel.getConsoleLogMaxLevel()));
        loggingConfigModel.setFileLogMinLevel(getProperty(System.getProperty(fileLogMinLevel), loggingConfigModel.getFileLogMinLevel()));
        loggingConfigModel.setFileLogMaxLevel(getProperty(System.getProperty(fileLogMaxLevel), loggingConfigModel.getFileLogMaxLevel()));
    }

    private String getProperty(String systemProperty, String sourceValue) {
        if (StringUtils.hasLength(sourceValue)) {
            return sourceValue;
        } else {
            return systemProperty;
        }
    }

}

