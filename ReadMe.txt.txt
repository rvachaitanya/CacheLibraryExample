1. What was the reasoning behind your implementation of the cache?
Ans) 1) If the cache can be of unlimited size then using HashMap implementation we can directly store everything in the cache. This is not ideal as the cache size may increase exponentially when the database size increases.
This will be counter intutive and would reduce the performance.
2) Using an LRU cache(Least recently used) on the other hand would greatly improve the performance as we can decide the size of the data to be stored in the cache area.
3) A user who has not recently accessed the website will be removed from the cache. 


2. How does your cache improve performance?
Ans) 1) LRU cache(Least recently used) greatly improve the performance as we can decide the size of the data to be stored in the cache area.
2) we will hit the cache to get the locally stored data instead of hitting the database.

3. What are the various usage patterns that make the cache more or less effective in terms of performance?
Discuss why the cache will be more effective under some scenarios and less effective under others. For example if hasUserLoggedInWithin24 is called 80 times in a row for the same person, the cache is very effective. When is it less effective?
Ans) 
1) We can define a cron job which runs every few minutes to update the cache. This helps in removing the stale cache.
2) A cache will be less effcient incase if all the entries are only new entries and there are no repeat hits to the website.


