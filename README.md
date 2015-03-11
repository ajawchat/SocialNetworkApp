#### This project is a self learning project to learn multiple aspects of programming scalable systems.  

Features
* MemcacheD: Using MemcacheD for faster authentication of credentials
 - Rather than reaching out to the DB for small accesses, I combine the username password as a key-value pair and insert it into an instance of memcached installed on my local system.
 - The cache holds the data for a maximum period of 30 days
 - It usually takes __ ms to access data from the cache, which earlier took 17 ms to access it from the DB
 

* mongoDB: Using MongoDB as the backend database for storing user profile information and credentials
 - I have chosen mongoDB mainly due to the flexibility (schema less database) and the option of horizontal scalability, which is evident in a popular networking site.
 - I also realized the ease of accessing the DB when I started coding the data model for my application
