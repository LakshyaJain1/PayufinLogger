package com.payufin.integration.payufinLogger.controllers;

import com.payufin.integration.payufinLogger.services.LoggingConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author - lakshya.jain <br>
 * Date - 08/10/2022
 *
 * <p>
 * This controller can be used to refresh the logging configs from source/DB such as <br>
 *
 * - logPath <br>
 * - serviceName <br>
 * - rootLogLevel <br>
 * - consoleLogMinLevel <br>
 * - consoleLogMaxLevel <br>
 * - fileLogMinLevel <br>
 * - fileLogMaxLevel <br>
 *
 * </p>
 *
 */


@RestController
@RequestMapping("/logging")
public class LoggingConfigController {

    @Autowired
    LoggingConfigService loggingConfigService;

    @GetMapping(value = "/refreshLoggingConfigs")
    public void refreshLoggingConfigs() {
        loggingConfigService.refreshLoggingConfigs();
    }

}
