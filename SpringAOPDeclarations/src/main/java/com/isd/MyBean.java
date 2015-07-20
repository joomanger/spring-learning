package com.isd;

public class MyBean {
	private MyDependency dep;

	public void setDep(MyDependency dep) {
		this.dep = dep;
	}

	public void execute() {
		dep.foo();
		dep.bar();
	}
}
