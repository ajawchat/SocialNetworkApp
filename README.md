#### This project is a self learning project to learn multiple aspects of programming scalable systems.  

Features
* MemcacheD: Using MemcacheD for faster authentication of credentials
 - Rather than reaching out to the DB for small accesses, I combine the username password as a key-value pair and insert it into an instance of memcached installed on my local system.
 - The cache holds the data for a maximum period of 30 days
 - It usually takes lesser time to access data from the cache, which earlier took 17 ms to access it from the DB
 - In my case, I noticed that the time to access cache was more than the DB access, but that might be because both are loaded on the same system, the data represented in mongoDB is same as that in the cache [key-value pairs of strings]. In cases of more complex data blobs stored in the database, with multiple servers, it would take much lesser time to access the cache [as it has the data in memory rather than on disk].
 

* mongoDB: Using MongoDB as the backend database for storing user profile information and credentials
 - I have chosen mongoDB mainly due to the flexibility (schema less database) and the option of horizontal scalability, which is evident in a popular networking site.
 - I also realized the ease of accessing the DB when I started coding the data model for my application
  
* Logging using Log4j
