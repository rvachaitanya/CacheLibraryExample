package cachingServiceOne;


import java.util.Date;

import loginDataBaseOne.DBEx;
import loginDataBaseOne.FakeDBAccess;
import loginServiceOne.LoginService;

public class CacheImplementation implements LoginService {
	int value =0;
	FakeDBAccess dbDetails = new FakeDBAccess();
	Date sysdate = new Date();
	private static final int MAX_ENTRIES = 100;
	LRUCache cache = new LRUCache(MAX_ENTRIES);
	@Override
	public boolean hasUserLoggedInWithin24(String userId) {
		// TODO Auto-generated method stub
		
		Date lastLogin = dbDetails.getLastLoginForUser(userId);
		
		if(lastLogin.compareTo(sysdate)<24){
			return true;
		}
		return false;
	}

	@Override
	public void userJustLoggedIn(String userId) {
		// TODO Auto-generated method stub
		try {
			dbDetails.setLastLoginForUser(userId,sysdate);
		} catch (DBEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Ideally we would want to move to this implementation 
	//so that we can get cache expiry and other functionality
	/*public CachedObject(Object obj, Object id, int minutesToLive)
    {
      this.object = obj;
      this.identifier = id;
      // minutesToLive of 0 means it lives on indefinitely.
      if (minutesToLive != 0)
      {
        dateofExpiration = new java.util.Date();
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(dateofExpiration);
        cal.add(cal.MINUTE, minutesToLive);
        dateofExpiration = cal.getTime();
      }
    }*/
	
	 public int UpdateCache(String userId){
		 if(cache.cache.contains(userId)){
			 return 0; //cache.get(userId);
		 }
		 else{
			 //If the value is not defined in the cache then the value has to be picked from 
			 // the database and the value has to be updated in the cache
			 value = dbDetails.getValueFromDB(userId);
			 cache.set(userId,value);
			 return value;
		 }
	 }
	
	 //This method should ideally run every few hours and update the cache
	 //This can be implemented using the cron job where we can write an 
	 //SQL query. The query should be written in such a way that duplicate users (who login multiple times)
	 //are not there in the query. 
	public void UpdateStaleCache(String userId){
		
	}
}
