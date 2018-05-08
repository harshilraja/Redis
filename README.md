# Redis

Step 1 )Install Redis

1)First we need to enable the EPEL repository on our machine
wget https://dl.fedoraproject.org/pub/epel/epel-release-latest-7.noarch.rpm

If wget is not recognized, try running yum install wget before the above command.

2)sudo rpm -ivh epel-release-7-5.noarch.rpm

3) sudo yum -y update

Note that this may take a while to complete. Now you may install Redis on your machine, by running:

4)sudo yum install redis -y

5)sudo systemctl start redis.service

6) sudo systemctl status redis.service

Finally, let's test our Redis setup by running: redis-cli ping

7)redis-benchmark -q -n 1000 -c 10 -P 5

The above command is saying that we want redis-benchmark to run in quiet mode, with 1000 total requests, 10 parallel connections and pipeline 5 requests. 

Step 2) Configure Redis Master

1)sudo vi /etc/redis.conf

2)Set a sensible value to the keepalive timer for TCP: tcp-keepalive 60

3)#bind 127.0.0.1

4)requirepass your_redis_master_password (Welcome)

5)maxmemory-policy noeviction

6)appendonly yes  -  yes / no  (Based on Read & Write)
appendfilename "appendonly.aof"

7)sudo systemctl restart redis.service

Step 3) Configure Redis Slave

1)#bind 127.0.0.1

2)requirepass your_redis_slave_password (Welcome)

3)slaveof your_redis_master_ip(cloud external ip address) 6379

4)masterauth your_redis_master_password (Welcome)

5)sudo systemctl restart redis.service


Step 4 ) Verify the Master-Slave Replication

1)redis-cli -h 127.0.0.1 -p 6379

2)AUTH your_redis_master_password (Welcome)

3)INFO

In RedisConfig.java

connectionFactory.setHostName("35.234.10.254");
connectionFactory.setPort(6379);
connectionFactory.setPassword("Welcome");
