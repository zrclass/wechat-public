package org.zrclass.wechat.common.domain;

/**
 * ErrorCode
 *
 * @blame Android Team
 */
public interface ErrorCode {

    String SYSTEM_CODE = "SystemCode";

    int getCode();

    String getDesc();
}
