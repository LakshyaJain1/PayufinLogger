package com.payufin.integration.payufinLogger.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author - lakshya.jain <br>
 * Date - 08/10/2022
 *
 <p>
 * This is Logging config model which can store the value which needs to be refreshed <br>
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
 * @see <a href="https://payufin.atlassian.net/wiki/spaces/EN/pages/1123123207/Logging+Framework">Documentation</a> documentation for more details about these values.
 *
 *
 *
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoggingConfigModel {

    String logPath;
    String serviceName;
    String rootLogLevel;
    String consoleLogMinLevel;
    String consoleLogMaxLevel;
    String fileLogMinLevel;
    String fileLogMaxLevel;

}
