package com.taotao.cloud.sys.facade.weather.acl;

import com.taotao.boot.ddd.acl.BaseAclService;
import com.taotao.cloud.sys.application.acl.WeatherAclService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * WeatherAclServiceImpl
 *
 * @author shuigedeng
 * @version 2026.01
 * @since 2025-12-19 09:30:45
 */
@AllArgsConstructor
@Service
public class WeatherAclServiceImpl extends BaseAclService implements WeatherAclService {}
