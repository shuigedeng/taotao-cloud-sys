package com.taotao.cloud.sys.facade.weather.acl;

import com.taotao.boot.ddd.acl.BaseAclService;
import com.taotao.cloud.sys.application.acl.WeatherAclService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class WeatherAclServiceImpl extends BaseAclService implements WeatherAclService {
}
