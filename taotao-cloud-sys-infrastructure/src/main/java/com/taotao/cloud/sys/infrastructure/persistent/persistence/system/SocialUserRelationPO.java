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
import com.taotao.boot.webagg.entity.SuperEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

/**
 * 角色-菜单第三方表
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2021-10-09 21:07:31
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(fluent = true)
@Entity
@Table(name = SocialUserRelationPO.TABLE_NAME)
@TableName(SocialUserRelationPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = SocialUserRelationPO.TABLE_NAME)
public class SocialUserRelationPO extends SuperEntity<SocialUserRelationPO, Long> {

	public static final String TABLE_NAME = "ttc_social_user_relation";

	/**
	 * 角色ID
	 */
	@Column(name = "`user_id`", columnDefinition = "bigint not null comment '角色ID'")
	private Long userId;

	/**
	 * 菜单ID
	 */
	@Column(name = "`social_user_id`", columnDefinition = "bigint not null comment '菜单ID'")
	private Long socialUserId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long roleId) {
		this.userId = roleId;
	}

	public Long getSocialUserId() {
		return socialUserId;
	}

	public void setSocialUserId(Long resourceId) {
		this.socialUserId = resourceId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		SocialUserRelationPO roleResource = (SocialUserRelationPO) o;
		return getId() != null && Objects.equals(getId(), roleResource.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
