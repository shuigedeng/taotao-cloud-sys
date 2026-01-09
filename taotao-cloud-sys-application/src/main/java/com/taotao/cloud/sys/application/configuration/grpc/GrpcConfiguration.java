package com.taotao.cloud.sys.application.configuration.grpc;

import com.taotao.cloud.sys.api.grpc.DictGrpcServiceGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.grpc.client.GrpcChannelFactory;
import org.springframework.stereotype.Component;

/**
 * GrpcTest 类
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2026/1/9
 */
@Component
public class GrpcConfiguration {
	@Bean
	DictGrpcServiceGrpc.DictGrpcServiceBlockingStub stub( GrpcChannelFactory channels) {
		return DictGrpcServiceGrpc.newBlockingStub(channels.createChannel("local"));
	}
//	@Bean
//	@GlobalServerInterceptor
//	AuthenticationProcessInterceptor jwtSecurityFilterChain( GrpcSecurity grpc) throws Exception {
//		return grpc
//			.authorizeRequests(requests ->
//				requests.allRequests().permitAll())
//			.build();
//	}
}
