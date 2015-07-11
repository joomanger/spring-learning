package com.isd;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.isd.lookupmethod.AbstractLookupDemoBean;
import com.isd.lookupmethod.DemoBean;
import com.isd.lookupmethod.MyHelper;
import com.isd.lookupmethod.StandardLookupDemoBean;

@Component("main")
public class Spring00 {
	@Resource(name = "set")
	private Set<String> set;
	@Resource(name = "prop")
	private Properties prop;
	@Resource(name = "map")
	private Map<String, Object> map;
	@Resource(name = "list")
	private List<String> list;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new ClassPathXmlApplicationContext("META-INF/spring/appl-context.xml");
		User user = ac.getBean("user", User.class);

		System.out.println(user);

		ApplicationContext ac_annot = new ClassPathXmlApplicationContext("META-INF/spring/appl-context-annotation.xml");
		User user2 = ac_annot.getBean("user", User.class);
		System.out.println(user2);

		Spring00 spr = ac.getBean("main", Spring00.class);
		spr.displayInfo();

		Spring00 spr2 = ac_annot.getBean("main", Spring00.class);
		spr2.displayInfo();

		DemoBean stand = ac.getBean("standardLookupDemoBean", StandardLookupDemoBean.class);
		DemoBean abstr = ac.getBean("abstractLookupDemoBean", AbstractLookupDemoBean.class);
		spr2.showMe(stand);
		spr2.showMe(abstr);
		
		
		System.out.println("The end!");
	}

	public void showMe(DemoBean bean) {
		MyHelper h1 = bean.getMyHelper();
		MyHelper h2 = bean.getMyHelper();

		System.out.println("Helper instance the Same? " + (h1 == h2));

		StopWatch stopwatch = new StopWatch();
		stopwatch.start("lookupDemo");
		for (int x = 0; x < 100000; x++) {
			MyHelper helper = bean.getMyHelper();
			helper.doSomething();
		}
		stopwatch.stop();
		System.out.println("100000 gets took " + stopwatch.getTotalTimeMillis() + "ms");

	}

	public void setSet(Set<String> set) {
		this.set = set;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public void displayInfo() {
		// Отобразим карту
		System.out.println("Map:");
		for (Map.Entry<String, Object> m : map.entrySet()) {
			System.out.println(m.getKey() + ":" + m.getValue());
		}

		// Отобразим Set
		System.out.println("Set:");
		for (Object s : set) {
			System.out.println(s);
		}

		// Отобразим свойства
		System.out.println("Props:");
		for (Map.Entry<Object, Object> p : prop.entrySet()) {
			System.out.println(p.getKey() + ":" + p.getValue());
		}

		// Отобразим список
		System.out.println("List:");
		for (Object t : list) {
			System.out.println(t);
		}

	}

}
