package com.taotao.cloud.sys.application.shared.monitor;

import lombok.Data;

import java.util.List;
import java.util.Properties;


@Data
public class RedisCacheInfoDTO {

    private Properties info;
    private Object dbSize;
    private List<CommonStatusDTO> commandStats;

    @Data
    public static class CommonStatusDTO {
        private String name;
        private String value;
    }

}
