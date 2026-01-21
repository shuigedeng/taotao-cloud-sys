package com.taotao.cloud.sys.infrastructure;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.tool.api.export.Exporter;
import org.hibernate.tool.api.export.ExporterFactory;
import org.hibernate.tool.api.export.ExporterType;
import org.hibernate.tool.api.metadata.MetadataDescriptor;
import org.hibernate.tool.api.metadata.MetadataDescriptorFactory;
import org.hibernate.tool.api.reveng.RevengStrategy;
import org.hibernate.tool.internal.reveng.strategy.DefaultStrategy;
import org.hibernate.tool.internal.reveng.strategy.OverrideRepository;

import java.io.File;
import java.util.Properties;

import static org.hibernate.tool.api.export.ExporterConstants.*;

public class Hibernate7ReverseEngineering {

	public static void main( String[] args ) {
		try {
			// 1. 配置数据库连接
			Properties properties = new Properties();
			properties.setProperty(
				AvailableSettings.JAKARTA_JDBC_DRIVER,
				"com.mysql.cj.jdbc.Driver"
			);
			properties.setProperty(
				AvailableSettings.JAKARTA_JDBC_URL,
				"jdbc:mysql://192.168.218.2:3306/taotao-cloud-sys?useSSL=false&serverTimezone=Asia/Shanghai&nullCatalogMeansCurrent=true&useInformationSchema=true&nullNamePatternMatchesAll=true"
			);
			properties.setProperty(
				AvailableSettings.JAKARTA_JDBC_USER,
				"root"
			);
			properties.setProperty(
				AvailableSettings.JAKARTA_JDBC_PASSWORD,
				"123456"
			);
			properties.setProperty(
				AvailableSettings.DIALECT,
				"org.hibernate.dialect.MySQLDialect"
			);
			properties.put(AvailableSettings.HBM2DDL_AUTO, "none");

			OverrideRepository or = new OverrideRepository();
			RevengStrategy strategy = new DefaultStrategy();
			or.addResource("hibernate.reveng.xml");
			strategy = or.getReverseEngineeringStrategy(strategy);
			// 2. 创建反向工程策略
			RevengStrategy reverseEngineeringStrategy = or.getReverseEngineeringStrategy(strategy);

			// 3. 创建 MetadataDescriptor
			MetadataDescriptor descriptor = MetadataDescriptorFactory
				.createReverseEngineeringDescriptor(reverseEngineeringStrategy, properties);

			// 5. 创建并配置导出器
			Exporter exporter = ExporterFactory.createExporter(ExporterType.JAVA);

			// 设置属性
			Properties exporterProperties = new Properties();
			exporterProperties.setProperty("ejb3", "true");
			exporterProperties.setProperty("jdk5", "true");

			exporter.getProperties().putAll(exporterProperties);
			exporter.getProperties().put(METADATA_DESCRIPTOR, descriptor);
			exporter.getProperties().put(DESTINATION_FOLDER, new File("src/main/java"));
			exporter.getProperties().put(FILE_PATTERN, "{package-name}/{class-name}.java");

			// 6. 执行导出
			exporter.start();

			System.out.println("✅ Entities generated successfully!");

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("❌ Reverse engineering failed: " + e.getMessage());
		}
	}
}
