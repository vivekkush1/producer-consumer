package producerconsumer;
import java.util.*;

public class producerconsumer {
	// Create a queue shared by producer and consumer 
	// Size of buffer is 2. 
	static Queue<Integer> queue = new LinkedList<>(); 
	static int buffersize = 2; 
	static int data = 0;
	static ProduceAndConsume pAndc = new ProduceAndConsume();
	public static void main(String args[])
	{
		try {
		//thread for producing data	
		class Producer extends Thread{
			@Override
			public void run(){
				try{
					pAndc.produce();
				}
				catch(Exception e){
					
				}
			}
		}
		
		//thread for consuming data
		class Consumer extends Thread{
			@Override
			public void run(){
				try{
					pAndc.consume();
				}
				catch(Exception e){
					
				}
			}
		}
		
		//start producer and consumer
		Producer p1 = new Producer();
		p1.start();
		Consumer c1 = new Consumer();
		c1.start();
		
		//join thread to main thread
		p1.join();
		c1.join();
		}
		catch(Exception e) {
			System.out.println("error in main");
		}
	}
	
	
	//class for defining produce and consume method
	static public class ProduceAndConsume{
		
		//method for consuming data
		synchronized public void consume(){
			try {
			while(true) {	
				//if buffer is empty no need to consume
				if(queue.size() == 0) {
					wait();
				}
				//else consume data and notify producer
				else {
					int Cdata=queue.remove();
					System.out.println("consumer consume data :" + Cdata);
				}
				Thread.sleep(1000);
				notify();
			}
			}
			catch(Exception e) {
				System.out.println("error occured in consume method");
			}
		}
		//method for producing data
		synchronized public void produce() {
			try {
				while(true) {	
					//if buffer is full no need to produce data
					if(queue.size() == buffersize) {
						wait();
					}
					//else produce data and notify consumer
					else {
						data++;
						queue.add(data);
						System.out.println("producer produce data :"+ data);
					}
					notify();
					Thread.sleep(1000);
				}
				}
				catch(Exception e) {

					System.out.println("error occured in produce method");
				}		
		}
	}
}
	
