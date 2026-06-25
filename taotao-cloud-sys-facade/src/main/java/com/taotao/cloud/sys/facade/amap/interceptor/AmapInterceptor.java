package com.taotao.cloud.sys.facade.amap.interceptor;

import com.taotao.boot.client.gateway.interceptor.GatewayPostInterceptor;
import com.taotao.boot.client.gateway.model.GatewayContext;
import com.taotao.boot.client.gateway.model.GatewayResponse;

/**
 * AmapInterceptor
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
public class AmapInterceptor<T> implements GatewayPostInterceptor<T> {
	@Override
	public void intercept(GatewayResponse<T> response, GatewayContext context) {

	}

	@Override
	public boolean shouldFilter(GatewayContext context) {
		return context.getCatchedException() != null;
	}
}
