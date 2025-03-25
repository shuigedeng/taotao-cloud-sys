package com.taotao.cloud.sys.application.shared.monitor;

import lombok.*;

import java.util.List;
import java.util.Properties;


@Setter
@Getter
@ToString
public class RedisCacheInfoDTO {

    private Properties info;
    private Object dbSize;
    private List<CommonStatusDTO> commandStats;

    @Setter
@Getter
@ToString
    public static class CommonStatusDTO {
        private String name;
        private String value;
    }

}
