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

package com.taotao.cloud.sys;

import com.taotao.boot.core.startup.StartupSpringApplication;
import com.taotao.boot.web.annotation.TaoTaoBootApplication;
//import com.taotao.cloud.bootstrap.annotation.TaoTaoCloudApplication;

/**
 * 系统管理中心
 * <p>异常原因：
 * <p>
 * 自从 JDK9 中引入了模块化功能后，再到 JDK17，对于包扫描和反射的权限控制更加的严格。常见的库比如（Spring）大量用到包扫描和反射，所以常出现此错误。
 * <p>
 * 解决方案：
 * <p>
 * 一个粗暴的解决办法是将没开放的 module 强制对外开放，即保持和 Java9 之前的版本一致。
 * <p>
 * --add-exports 导出包，意味着其中的所有公共类型和成员都可以在编译和运行时访问。 --add-opens 打开包，意味着其中的所有类型和成员（不仅是公共类型）都可以在运行时访问。
 * 主要区别在于 --add-opens 允许 “深度反射”，即非公共成员的访问，才可以调用 setAccessible(true)
 *
 * <pre class="code">
 *                   --add-opens java.base/sun.reflect.generics.reflectiveObjects=ALL-UNNAMED
 *                   --add-opens java.base/java.io=ALL-UNNAMED
 *                   --add-opens java.base/java.lang=ALL-UNNAMED
 *                   --add-opens java.base/java.math=ALL-UNNAMED
 *                   --add-opens java.base/java.net=ALL-UNNAMED
 *                   --add-opens java.base/java.net.spi=ALL-UNNAMED
 *                   --add-opens java.base/java.nio=ALL-UNNAMED
 *                   --add-opens java.base/java.security=ALL-UNNAMED
 *                   --add-opens java.base/java.text=ALL-UNNAMED
 *                   --add-opens java.base/java.text.spi=ALL-UNNAMED
 *                   --add-opens java.base/java.time=ALL-UNNAMED
 *                   --add-opens java.base/java.util=ALL-UNNAMED
 *                   --add-opens java.base/javax.crypto=ALL-UNNAMED
 *                   --add-opens java.base/javax.net=ALL-UNNAMED
 *                   --add-opens java.base/javax.net.ssl=ALL-UNNAMED
 *                   --add-opens java.base/sun.net.util=ALL-UNNAMED
 *                   --add-opens java.base/sun.reflect.annotation=ALL-UNNAMED
 *                   --add-opens java.base/jdk.internal.vm=ALL-UNNAMED
 *                   --add-opens java.compiler/javax.annotation.processing=ALL-UNNAMED
 *                   --add-opens java.desktop/java.applet=ALL-UNNAMED
 *                   --add-opens java.desktop/java.awt=ALL-UNNAMED
 *                   --add-opens java.datatransfer/java.awt.datatransfer=ALL-UNNAMED
 *                   --add-opens java.desktop/java.beans=ALL-UNNAMED
 *                   --add-opens java.desktop/java.beans.beancontext=ALL-UNNAMED
 *                   --add-opens java.desktop/javax.accessibility=ALL-UNNAMED
 *                   --add-opens java.desktop/javax.imageio=ALL-UNNAMED
 *                   --add-opens java.desktop/javax.print=ALL-UNNAMED
 *                   --add-opens java.desktop/javax.sound.midi=ALL-UNNAMED
 *                   --add-opens java.desktop/javax.swing=ALL-UNNAMED
 *                   --add-opens java.sql/java.sql=ALL-UNNAMED
 *                   --add-opens java.net.http/java.net.http=ALL-UNNAMED
 *                   --add-opens java.compiler/javax.lang.model=ALL-UNNAMED
 *                   --add-opens java.management/javax.management=ALL-UNNAMED
 *                   --add-opens java.management/java.lang.management=ALL-UNNAMED
 *                   --add-opens java.management/sun.management=ALL-UNNAMED
 *                   --add-opens java.management/com.sun.jmx.mbeanserver=ALL-UNNAMED
 *                   --add-opens java.management.rmi/javax.management.remote.rmi=ALL-UNNAMED
 *                   --add-opens java.naming/javax.naming=ALL-UNNAMED
 *                   --add-opens java.rmi/sun.rmi.transport=ALL-UNNAMED
 *                   --add-opens java.rmi/java.rmi=ALL-UNNAMED
 *                   --add-opens java.scripting/javax.script=ALL-UNNAMED
 *                   --add-opens java.security.jgss/org.ietf.jgss=ALL-UNNAMED
 *                   --add-opens java.security.jgss/javax.security.auth.kerberos=ALL-UNNAMED
 *                   --add-opens java.security.sasl/javax.security.sasl=ALL-UNNAMED
 *                   --add-opens java.smartcardio/javax.smartcardio=ALL-UNNAMED
 *                   --add-opens java.sql/javax.sql=ALL-UNNAMED
 *                   --add-opens java.sql.rowset/javax.sql.rowset=ALL-UNNAMED
 *                   --add-opens java.compiler/javax.tools=ALL-UNNAMED
 *                   --add-opens java.transaction.xa/javax.transaction.xa=ALL-UNNAMED
 *                   --add-opens java.instrument/java.lang.instrument=ALL-UNNAMED
 *                   --add-opens java.xml/javax.xml=ALL-UNNAMED
 *                   --add-opens java.xml/org.xml.sax=ALL-UNNAMED
 *                   --add-opens java.xml/jdk.xml.internal=ALL-UNNAMED
 *                   --add-opens jdk.accessibility/com.sun.java.accessibility.util=ALL-UNNAMED
 *                   --add-opens jdk.jdi/com.sun.jdi=ALL-UNNAMED
 *                   --add-opens jdk.httpserver/com.sun.net.httpserver=ALL-UNNAMED
 *                   --add-opens jdk.httpserver/com.sun.net.httpserver.spi=ALL-UNNAMED
 *                   --add-opens jdk.sctp/com.sun.nio.sctp=ALL-UNNAMED
 *                   --add-opens jdk.security.auth/com.sun.security.auth=ALL-UNNAMED
 *                   --add-opens jdk.security.auth/com.sun.security.auth.callback=ALL-UNNAMED
 *                   --add-opens jdk.compiler/com.sun.source.doctree=ALL-UNNAMED
 *                   --add-opens jdk.compiler/com.sun.source.tree=ALL-UNNAMED
 *                   --add-opens jdk.compiler/com.sun.source.util=ALL-UNNAMED
 *                   --add-opens jdk.attach/com.sun.tools.attach=ALL-UNNAMED
 *                   --add-opens jdk.attach/com.sun.tools.attach.spi=ALL-UNNAMED
 *                   --add-opens jdk.compiler/com.sun.tools.javac=ALL-UNNAMED
 *                   --add-opens jdk.jconsole/com.sun.tools.jconsole=ALL-UNNAMED
 *                   --add-opens jdk.management/com.sun.management=ALL-UNNAMED
 *                   --add-opens jdk.management.jfr/jdk.management.jfr=ALL-UNNAMED
 *                   --add-opens jdk.dynalink/jdk.dynalink=ALL-UNNAMED
 *                   --add-opens jdk.javadoc/jdk.javadoc.doclet=ALL-UNNAMED
 *                   --add-opens jdk.jfr/jdk.jfr=ALL-UNNAMED
 *                   --add-opens jdk.jfr/jdk.jfr.consumer=ALL-UNNAMED
 *                   --add-opens jdk.jshell/jdk.jshell=ALL-UNNAMED
 *                   --add-opens jdk.net/jdk.net=ALL-UNNAMED
 *                   --add-opens jdk.net/jdk.nio=ALL-UNNAMED
 *                   --add-opens jdk.nio.mapmode/jdk.nio.mapmode=ALL-UNNAMED
 *                   --add-opens jdk.jartool/jdk.security.jarsigner=ALL-UNNAMED
 *                   --add-opens jdk.jsobject/netscape.javascript=ALL-UNNAMED
 *                   --add-opens java.base/java.lang=ALL-UNNAMED
 *                   --add-opens java.base/java.lang.reflect=ALL-UNNAMED
 *                   --add-opens java.base/java.lang.invoke=ALL-UNNAMED
 *                   --add-opens java.base/java.util=ALL-UNNAMED
 *                   --add-opens java.base/sun.net=ALL-UNNAMED
 *                   --add-opens java.base/java.math=ALL-UNNAMED
 *                   --add-opens java.base/sun.reflect.annotation=ALL-UNNAMED
 *                   --add-opens java.base/sun.net=ALL-UNNAMED
 *                   --add-opens java.desktop/sun.awt=ALL-UNNAMED
 *                   --add-opens java.desktop/sun.font=ALL-UNNAMED
 *                   --add-opens jdk.management/com.sun.management.internal=ALL-UNNAMED
 *                   --add-opens java.base/sun.security.action=ALL-UNNAMED
 *                   --add-opens java.base/java.lang=ALL-UNNAMED
 *                   --add-opens java.base/java.math=ALL-UNNAMED
 *                   --add-opens java.base/java.util=ALL-UNNAMED
 *                   --add-opens java.base/sun.util.calendar=ALL-UNNAMED
 *                   --add-opens java.base/java.util.concurrent=ALL-UNNAMED
 *                   --add-opens java.base/java.util.concurrent.locks=ALL-UNNAMED
 *                   --add-opens java.base/java.security=ALL-UNNAMED
 *                   --add-opens java.base/jdk.internal.loader=ALL-UNNAMED
 *                   --add-opens java.management/com.sun.jmx.mbeanserver=ALL-UNNAMED
 *                   --add-opens java.base/java.net=ALL-UNNAMED
 *                   --add-opens java.base/sun.nio.ch=ALL-UNNAMED
 *                   --add-opens java.management/java.lang.management=ALL-UNNAMED
 *                   --add-opens jdk.management/com.sun.management.internal=ALL-UNNAMED
 *                   --add-opens java.management/sun.management=ALL-UNNAMED
 *                   --add-opens java.base/sun.security.action=ALL-UNNAMED
 *                   --add-opens java.management/java.lang.management=ALL-UNNAMED
 *                   --add-opens jdk.management/com.sun.management.internal=ALL-UNNAMED
 *                   --add-opens java.management/sun.management=ALL-UNNAMED
 *                   --add-opens java.base/java.time=ALL-UNNAMED
 *                   --add-opens java.base/java.util.concurrent=ALL-UNNAMED
 *                   --add-opens java.base/java.util.concurrent.locks=ALL-UNNAMED
 *                   --add-opens java.base/java.security=ALL-UNNAMED
 *                   --add-opens java.base/jdk.internal.loader=ALL-UNNAMED
 *                   --add-opens java.management/com.sun.jmx.mbeanserver=ALL-UNNAMED
 *                   --add-opens java.base/java.net=ALL-UNNAMED
 *                   --add-opens java.base/sun.nio.ch=ALL-UNNAMED
 *                   --add-opens java.base/java.lang=ALL-UNNAMED
 *                   --add-opens java.base/java.math=ALL-UNNAMED
 *                   --add-opens java.base/java.util=ALL-UNNAMED
 *                   --add-opens java.base/sun.util.calendar=ALL-UNNAMED
 *                   --add-opens java.base/sun.net.util=ALL-UNNAMED
 *                   --add-opens java.base/sun.net.util=ALL-UNNAMED
 *                   --add-opens java.base/java.lang=ALL-UNNAMED
 *                   --add-opens java.base/java.math=ALL-UNNAMED
 *                   --add-opens java.base/java.util=ALL-UNNAMED
 *                   --add-opens java.base/sun.util.calendar=ALL-UNNAMED
 *                   --add-opens java.base/java.util.concurrent=ALL-UNNAMED
 *                   --add-opens java.base/java.util.concurrent.locks=ALL-UNNAMED
 *                   --add-opens java.base/java.security=ALL-UNNAMED
 *                   --add-opens java.base/jdk.internal.loader=ALL-UNNAMED
 *                   --add-opens java.management/com.sun.jmx.mbeanserver=ALL-UNNAMED
 *                   --add-opens java.base/java.net=ALL-UNNAMED
 *                   --add-opens java.base/sun.nio.ch=ALL-UNNAMED
 *                   --add-opens java.management/java.lang.management=ALL-UNNAMED
 *                   --add-opens jdk.management/com.sun.management.internal=ALL-UNNAMED
 *                   --add-opens java.management/sun.management=ALL-UNNAMED
 *                   --add-opens java.base/sun.security.action=ALL-UNNAMED
 *                   --add-opens java.base/sun.net.util=ALL-UNNAMED
 *                   --add-opens java.base/java.time=ALL-UNNAMED
 *                   --add-opens java.base/java.lang.reflect=ALL-UNNAMED
 *                   --add-opens java.base/java.io=ALL-UNNAMED
 *                   --add-opens java.base/java.security=ALL-UNNAMED
 *                   --add-opens java.base/jdk.internal.misc=ALL-UNNAMED
 *                   --add-opens java.base/java.text=ALL-UNNAMED
 *                   --add-opens java.base/java.nio=ALL-UNNAMED
 *                   --add-opens java.base/jdk.internal.access=ALL-UNNAMED
 *                   --add-opens java.base/java.time=ALL-UNNAMED
 *                   --add-opens java.base/java.io=ALL-UNNAMED
 *                   --add-opens java.base/java.net=ALL-UNNAMED
 *                   --add-opens java.base/java.lang=ALL-UNNAMED
 *                   --add-opens java.base/java.lang.reflect=ALL-UNNAMED
 *                   --add-opens java.base/java.util=ALL-UNNAMED
 *                   --add-opens jdk.management/com.sun.management.internal=ALL-UNNAMED
 *                   --add-opens java.base/sun.reflect.annotation=ALL-UNNAMED
 *                   --add-opens java.base/sun.net=ALL-UNNAMED
 *                   --add-opens java.base/java.math=ALL-UNNAMED
 *                   --add-opens java.desktop/sun.awt=ALL-UNNAMED
 *
 * </pre>
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2020/11/30 下午3:33
 */
// @MapperScan(basePackages = {"com.taotao.cloud.sys.infrastructure.persistent.*.mapper"})
// @EnableJpaRepositories(
//	basePackages = {"com.taotao.cloud.sys.infrastructure.persistent.*.repository.inf"},
//	repositoryFactoryBeanClass = JpaExtendRepositoryFactoryBean.class)
// @ComponentScan(basePackages = {
//	"com.taotao.cloud.sys.biz.repository.cls"
// } )
@TaoTaoBootApplication
//@TaoTaoCloudApplication
public class TaoTaoCloudSysApplication {

    public static void main(String[] args) {
        System.setProperty("com.google.protobuf.use_unsafe_pre22_gencode", "true");

        new StartupSpringApplication(TaoTaoCloudSysApplication.class)
                .setTtcBanner()
                .setTtcProfileIfNotExists("dev")
                .setTtcApplicationProperty("taotao-cloud-sys")
                .setTtcAllowBeanDefinitionOverriding(true)
                .run(args);
    }
}
