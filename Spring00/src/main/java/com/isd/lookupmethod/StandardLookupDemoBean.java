package com.isd.lookupmethod;

public class StandardLookupDemoBean implements DemoBean {
	private MyHelper myHelper;

	public void setMyHelper(MyHelper myHelper) {
		this.myHelper = myHelper;
	}

	@Override
	public MyHelper getMyHelper() {
		// TODO Auto-generated method stub
		return myHelper;
	}

	@Override
	public void someOperation() {
		// TODO Auto-generated method stub
		myHelper.doSomething();
	}

}
