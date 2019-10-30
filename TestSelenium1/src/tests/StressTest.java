package tests;

/**
 * Simulate 5 simultaneous users, by running this.
 * 
 * This throws an exception right at the beginning, but this does not matter.
 * 
 * @author michel
 *
 */
public class StressTest  {

	class T1 implements Runnable {
		@Override
		public void run() {
			Testcases1_10 t1_10 = new Testcases1_10();
			Testcases1_10.before();
			t1_10.test_0();
			t1_10.test_1();
			t1_10.test_2();
			t1_10.test_3();
			t1_10.test_4();
			t1_10.test_5();
			t1_10.test_6();
			t1_10.test_7();
			t1_10.test_8();
			t1_10.test_9();
			t1_10.test_10();
			Testcases11_20.after();
			
			
		}	
	}
	
	class T2 implements Runnable {
		@Override
		public void run() {
			Testcases11_20 t = new Testcases11_20();
			Testcases11_20.before();
			t.test_11();
			t.test_12();
			t.test_13();
			t.test_14();
			t.test_15();
			t.test_16();
			t.test_17();
			t.test_18();
			t.test_19();
			t.test_20();
			Testcases11_20.after();
			
		}	
	}
	
	class T3 implements Runnable {
		@Override
		public void run() {
			Testcases21_30 t = new Testcases21_30();
			Testcases21_30.before();
			t.test_21();
			t.test_22();
			t.test_23();
			t.test_24();
			t.test_25();
			t.test_26();
			t.test_27();
			t.test_28();
			t.test_29();
			t.test_30();
			Testcases21_30.after();
			
		}	
	}
	
	class T4 implements Runnable {
		@Override
		public void run() {
			Testcases31_40 t = new Testcases31_40();
			Testcases31_40.before();
			t.test_31();
			t.test_32();
			t.test_33();
			t.test_34();
			t.test_35();
			t.test_36();
			t.test_37();
			t.test_38();
			t.test_39();
			t.test_40();
			Testcases31_40.after();
			
		}	
		
		
	}
	
	class T5 implements Runnable {
		@Override
		public void run() {
			Testcases41_50 t = new Testcases41_50();
			Testcases41_50.before();
			t.test_41();
			t.test_42();
			t.test_43();
			t.test_44();
			t.test_45();
			t.test_46();
			t.test_47();
			t.test_48();
			t.test_49();
			t.test_50();
			Testcases41_50.after();
			
		}	
		
		
	}
	
	
	void go() {
		T1 t1 = new T1();
		Thread thread1 = new Thread (t1);
		
		T1 t1b = new T1();
		Thread thread1b = new Thread (t1b);
		
		T2 t2 = new T2();
		Thread thread2 = new Thread (t2);
		
		T3 t3 = new T3();
		Thread thread3 = new Thread (t3);
		
		T4 t4 = new T4();
		Thread thread4 = new Thread (t4);
		
		T5 t5 = new T5();
		Thread thread5 = new Thread (t5);
		
		int MAX_LOOPS = 3;
		
		for (int i=1;i<MAX_LOOPS;i++) {
			thread1.start();
			thread1b.start();
			thread2.start();
			thread3.start();
			thread4.start();
			thread5.start();
			
		}

	}
	
	
	public static void main(String[] args) {
		StressTest t = new StressTest();
		t.go();
		
			}

	
	
	
	
}
