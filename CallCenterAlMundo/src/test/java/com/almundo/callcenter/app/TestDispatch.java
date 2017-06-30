package com.almundo.callcenter.app;

/**
 * TEST JUNIT  Aunque no deberia poder probarse algo concurrente
 * el test comprueba si es posible que se lance una exception 
 * En teoria...
 * @author LUIS SOTO
 *
 */
class TestDispatch extends AppTest{
	
	public TestDispatch(String testName) {
		super(testName);
		// TODO Auto-generated constructor stub
	}

	public static void assertDoesNotThrow(InterruptedException ex){
	    try{
	      Dispatch d = new Dispatch();
	      d.dispatchCall(10, 30);
	    }
	    catch(Exception e){
	      fail("No deberia lanzar exception!");
	    }
	  }
	}
