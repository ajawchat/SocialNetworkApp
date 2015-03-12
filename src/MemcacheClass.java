// This class contains all of the methods required for interfacing with the Memcached distributed cache.
// This is done to relive controller having all the connections initialized from one place

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

class MemcacheClass {
	
	private static final int TIME_IN_CACHE = 60*60*24*30;
	
	private MemcachedClient c = null;
	
	//==================================================================================================================
	public MemcacheClass(){
		try {
			c = new MemcachedClient(new InetSocketAddress("localhost", 11211));
		} catch (IOException e1) {
			System.out.println("Error in adding data to cache...");
		}
	}
	
	//==================================================================================================================
	public void addDataToCache(String userName, String password){
		System.out.println("Starting");
		c.set(userName,TIME_IN_CACHE,password);
		System.out.println("added userName and password to cache...");
	
	}

	//==================================================================================================================
		public String getDataFromCache(String userName){
			// Try to get a value, for up to 5 seconds, and cancel if it doesn't return
			String password=null;
			
			Future<Object> f=c.asyncGet(userName);
			try {
			    try {
			    	password=(String) f.get(5, TimeUnit.SECONDS);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			} catch(TimeoutException e) {
			    // Since we don't need this, go ahead and cancel the operation.  This
			    // is not strictly necessary, but it'll save some work on the server.
			    System.out.println("Connection timed out...Accessing the DB for the requested info...");
				f.cancel(false);
			    
				// If timeout occurs or string is null, access the database
				password = "TIMEOUT";
			}
			
			return password;
		}

	
	
	public static void main(String[] args) {
		
		
	}

}
