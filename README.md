* Handler 负责发送和处理消息
* Looper 消息泵，也就是负责取出消息交给Handler来处理。
* MessageQueue 消息队列，负责存取消息。
* Message 具体发送的消息。
* ThreadLocal 它主要用于做线程间的数据隔离用的，这里它在每个线程中存放各自对应的Looper。
