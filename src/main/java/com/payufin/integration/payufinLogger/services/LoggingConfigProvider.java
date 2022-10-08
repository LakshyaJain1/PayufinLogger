package com.payufin.integration.payufinLogger.services;

import com.payufin.integration.payufinLogger.models.LoggingConfigModel;
import org.springframework.stereotype.Service;

/**
 * Author - lakshya.jain <br>
 * Date - 08/10/2022
 *
 *
 * <p>
 * This is a hook which needs to be implemented by user, here user needs to implement <br>
 * the {@link #getLoggingConfigFromSource} method which can return the configs from DB and <br>
 * set in {@link LoggingConfigModel}
 * </p>
 */


@Service
public interface LoggingConfigProvider {

    LoggingConfigModel getLoggingConfigFromSource();

}
