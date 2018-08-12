# blocking io
参考资料：

1. http://gee.cs.oswego.edu/dl/cpjslides/nio.pdf

基本上所有的网络处理程序都有以下基本的处理过程:
 1. Read request
 2. Decode request
 3. Process service
 4. Encode reply
 5. Send reply
 
 
server导致阻塞的原因：

1、ServerSocket的accept方法，阻塞等待client连接，直到client连接成功。

2、线程从socket InputStream读入数据，会进入阻塞状态，直到全部数据读完。

3、线程向socket OutputStream写入数据，会阻塞直到全部数据写完。

client导致阻塞的原因：

1、client建立连接时会阻塞，直到连接成功。

2、线程从socket输入流读入数据，如果没有足够数据读完会进入阻塞状态，直到有数据或者读到输入流末尾。

3、线程从socket输出流写入数据，直到输出所有数据。

4、socket.setsolinger()设置socket的延迟时间，当socket关闭时，会进入阻塞状态，直到全部数据都发送完或者超时。

改进：采用基于事件驱动的设计，当有事件触发时，才会调用处理器进行数据处理。
