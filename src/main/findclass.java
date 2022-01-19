package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
 
public class findclass {
	public static void main(String[] args) throws Exception {
		List jarName1 = getJarName("C:\\Program Files\\Java\\jdk1.8.0_20\\jre\\lib\\rt.jar");
		System.out.println("第1个jar包类数量: "+jarName1.size());
		
		fileWrite("1.txt", jarName1);
		List jarName2 = getJarName("C:\\Program Files\\Java\\jre1.8.0_31\\lib\\rt.jar");
		System.out.println("第2个jar包类数量: "+jarName2.size());
		fileWrite("2.txt", jarName2);
		
		List list1_2 = removeAll(jarName1, jarName2);
		System.out.println("第1个jar包独立类: "+list1_2.size());
		printlist(list1_2);
		
		List list2_1 = removeAll(jarName2, jarName1);
		System.out.println("第2个jar包独立类: "+list2_1.size());
		printlist(list2_1);
		
		List list1and2 = retainAll(jarName1, jarName2);
		System.out.println("2个jar包的交集类数量: "+list1and2.size());
     }
	public static List removeAll(List list1, List list2) {
		List list3 = new ArrayList();
		list3.addAll(list1);
		list3.removeAll(list2);
		return list3;
	}
	
	public static List retainAll(List list1, List list2) {
		List list3 = new ArrayList();
		list3.addAll(list1);
		list3.retainAll(list2);
		return list3;
	}
	
	
	public static void printlist(List list) {
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			String className = (String) iterator.next();
			System.out.println(className);
		}
	}
	public static void fileWrite(String path, List list) throws Exception {
		FileWriter writer = new FileWriter(path);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			String className = (String) iterator.next();
			writer.write(className+"\r\n");
		}
		writer.close();
	}
	public static List getJarName(String jarFile) throws Exception {
		try{
			//通过将给定路径名字符串转换为抽象路径名来创建一个新File实例
			File f = new File(jarFile);
			URL url1 = f.toURI().toURL();
			URLClassLoader myClassLoader = new URLClassLoader(new URL[]{url1},Thread.currentThread().getContextClassLoader());
			//通过jarFile和JarEntry得到所有的类
			JarFile jar = new JarFile(jarFile);
			//返回zip文件条目的枚举
			Enumeration<JarEntry> enumFiles = jar.entries();
			JarEntry entry;
			List classlist = new ArrayList();
			//测试此枚举是否包含更多的元素
			while(enumFiles.hasMoreElements()){
				entry = (JarEntry)enumFiles.nextElement();
				if(entry.getName().indexOf("META-INF")<0){
					String classFullName = entry.getName();
					if(!classFullName.endsWith(".class")){
						classFullName = classFullName.substring(0,classFullName.length()-1);
					} else{
						//去掉后缀.class
						String className = classFullName.substring(0,classFullName.length()-6).replace("/", ".");
						//打印类名
						classlist.add(className);
					}
				}
			}
			Collections.sort(classlist);
			return classlist;
		} catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
}