//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.taotao.cloud.sys.api.enums;

import com.taotao.boot.common.utils.lang.StringUtils;
import java.util.Arrays;

public enum AuthUserGender {
  MALE("1", "男"),
  FEMALE("0", "女"),
  UNKNOWN("-1", "未知");

  private String code;
  private String desc;

  public static AuthUserGender getRealGender(String originalGender) {
    if (null != originalGender && !UNKNOWN.getCode().equals(originalGender)) {
      String[] males = new String[]{"m", "男", "1", "male"};
      return Arrays.asList(males).contains(originalGender.toLowerCase()) ? MALE : FEMALE;
    } else {
      return UNKNOWN;
    }
  }

  public static AuthUserGender getWechatRealGender(String originalGender) {
    return !StringUtils.isEmpty(originalGender) && !"0".equals(originalGender) ? getRealGender(originalGender) : UNKNOWN;
  }

  public String getCode() {
    return this.code;
  }

  public String getDesc() {
    return this.desc;
  }

  private AuthUserGender(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }
}
