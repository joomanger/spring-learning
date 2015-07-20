package com.isd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isd.aop.MyDependency;

@Component("myBean")
public class MyBean {
	@Autowired
	private MyDependency myDependency;

	public void setMyDependency(MyDependency myDependency) {
		this.myDependency = myDependency;
	}

	public void execute() {
		myDependency.foo(101);

		System.out.println();
		myDependency.foo(100);

		System.out.println();
		myDependency.bar();
	}

}
