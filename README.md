# lv-etrack(核心事件跟踪平台)

   ## module介绍：
   
   1、lv-etrack-core:底层es操作
   2、lv-etrack-client:日志推送客户端
   3、lv-etrack-server:日志记录、监控、报警
     
   ### 组件引用说明：
   1、依赖pom：
        
        <dependency>
            <groupId>com.lv.cloud</groupId>
            <artifactId>lv-etrack-client</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
        
   2、添加注解：@EnableEventLogger
   
   3、application添加配置：
   
       lvcloud.etrack.appname=lv-etrack-test
       
       spring.kafka.bootstrap-servers=10.113.9.17:9092,10.113.9.16:9092,10.113.9.19:9092
       spring.kafka.producer.retries=3
       #procedure要求leader在考虑完成请求之前收到的确认数，用于控制发送记录在服务端的持久化，其值可以为如下：
       #acks = 0 如果设置为零，则生产者将不会等待来自服务器的任何确认，该记录将立即添加到套接字缓冲区并视为已发送。在这种情况下，无法保证服务器已收到记录，并且重试配置将不会生效（因为客户端通常不会知道任何故障），为每条记录返回的偏移量始终设置为-1。
       #acks = 1 这意味着leader会将记录写入其本地日志，但无需等待所有副本服务器的完全确认即可做出回应，在这种情况下，如果leader在确认记录后立即失败，但在将数据复制到所有的副本服务器之前，则记录将会丢失。
       #acks = all 这意味着leader将等待完整的同步副本集以确认记录，这保证了只要至少一个同步副本服务器仍然存活，记录就不会丢失，这是最强有力的保证，这相当于acks = -1的设置。
       spring.kafka.producer.acks=1
       #每当多个记录被发送到同一分区时，生产者将尝试将记录一起批量处理为更少的请求， 
       #这有助于提升客户端和服务器上的性能，此配置控制默认批量大小（以字节为单位），默认值为16384
       spring.kafka.producer.batch-size=16384
       #生产者可用于缓冲等待发送到服务器的记录的内存总字节数，默认值为33554432
       spring.kafka.producer.buffer-memory=33554432
       spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
       spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer
       
   ### 使用
   
   例子：EventLogger logger = EventLoggerFactory.getLog(EventLogTestController.class);
   logger.infoLogicLog(...);logger.errorLogicLog(...);logger.outputSystemLog(...);
       