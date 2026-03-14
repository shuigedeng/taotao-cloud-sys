package com.taotao.cloud.sys.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 管理员状态枚举
 *
 * @author ouyucheng
 * @date 2025/12/31
 */
@Getter
public enum EnabledEnum {

    /**
     * 开启
     */
	ENABLED("ENABLED", "开启"),

    /**
     * 关闭
     */
    DISABLED("DISABLED", "关闭");


    @EnumValue
    private final String code;
    private final String description;

    EnabledEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据编码获取管理员状态
     *
     * @param code 编码
     * @return 管理员状态
     * @throws IllegalArgumentException 如果编码不存在
     */
    public static EnabledEnum fromCode(String code) {
        for (EnabledEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("未知的管理员状态编码: " + code);
    }

    /**
     * 根据编码安全地获取管理员状态，如果编码不存在则返回null
     *
     * @param code 编码
     * @return 管理员状态，如果编码不存在则返回null
     */
    public static EnabledEnum fromCodeSafe(String code) {
        if (code == null) {
            return null;
        }
        for (EnabledEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }

    /**
     * 根据编码获取管理员状态，如果编码不存在则返回默认值
     *
     * @param code 编码
     * @param defaultValue 默认值
     * @return 管理员状态，如果编码不存在则返回默认值
     */
    public static EnabledEnum fromCodeOrDefault(String code, EnabledEnum defaultValue) {
        if (code == null) {
            return defaultValue;
        }
        for (EnabledEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return defaultValue;
    }

}
