
该项目主要是用来验证学习携程开源的配置中心 apollo

Apollo客户端会把从服务端获取到的配置在本地文件系统缓存一份，用于在遇到服务不可用，或网络不通的时候，依然能从本地恢复配置，不影响应用正常运行。

本地缓存路径默认位于以下路径，所以请确保/opt/data或C:\opt\data\目录存在，且应用有读写权限。

Mac/Linux: /opt/data/{appId}/config-cache
Windows: C:\opt\data\{appId}\config-cache
本地配置文件会以下面的文件名格式放置于本地缓存路径下：

{appId}+{cluster}+{namespace}.properties

appId就是应用自己的appId，如100004458
cluster就是应用使用的集群，一般在本地模式下没有做过配置的话，就是default
namespace就是应用使用的配置namespace，一般是application

项目主要是 Java 客户端程序的使用
入门指南：
https://github.com/ctripcorp/apollo/wiki/Quick-Start

Java 应用接入
https://github.com/ctripcorp/apollo/wiki/Java%E5%AE%A2%E6%88%B7%E7%AB%AF%E4%BD%BF%E7%94%A8%E6%8C%87%E5%8D%97