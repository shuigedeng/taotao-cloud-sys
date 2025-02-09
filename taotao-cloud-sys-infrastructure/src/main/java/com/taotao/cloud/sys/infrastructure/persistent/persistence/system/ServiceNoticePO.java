/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.taotao.cloud.sys.infrastructure.persistent.persistence.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.boot.webagg.entity.BaseSuperEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

/**
 * 服务订阅消息
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(fluent = true)
@Entity
@Table(name = ServiceNoticePO.TABLE_NAME,
	uniqueConstraints = {
		@UniqueConstraint(name = "uniq_goods_no", columnNames = "`goods_no`"),
	},
	indexes = {
		@Index(name = "idx_create_date", columnList = "`create_date`"),
	})
@TableName(ServiceNoticePO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = ServiceNoticePO.TABLE_NAME)
public class ServiceNoticePO extends BaseSuperEntity<ServiceNoticePO, Long> {

	public static final String TABLE_NAME = "ttc_service_notice";

	@Column(name = "`store_id`", columnDefinition = "varchar(255) not null default '' comment '商家id，为-1时，代表是平台发布的消息'")
	private String storeId;

	@Column(name = "`banner_image`", columnDefinition = "varchar(255) not null default '' comment 'banner图'")
	private String bannerImage;

	@Column(name = "`title`", columnDefinition = "varchar(255) not null default '' comment '标题'")
	private String title;

	@Column(name = "`sub_title`", columnDefinition = "varchar(255) not null default '' comment '副标题'")
	private String subTitle;

	@Column(name = "`to_url`", columnDefinition = "varchar(255) not null default '' comment '点击跳转(此内容与站内信内容只能有一个生效)'")
	private String toUrl;

	@Column(name = "`content`", columnDefinition = "varchar(255) not null default '' comment '站内信内容(富文本框编辑，可以上传图片的html)'")
	private String content;

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getToUrl() {
		return toUrl;
	}

	public void setToUrl(String toUrl) {
		this.toUrl = toUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		ServiceNoticePO that = (ServiceNoticePO) o;
		return getId() != null && Objects.equals(getId(), that.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
