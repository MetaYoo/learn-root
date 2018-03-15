登录涉及知识
基于Token模式验证

用户第一次访问，需要提供账号和密码登录；后续可以提供token令牌访问。

用户做每次操作需要携带token，验证token有效性。

SSO单点登录

用户管理、认证系统独立部署，之后其他界面和系统通过服务调用方式访问用户认证系统。

浏览器登录时采用Ajax访问认证系统后台服务；

其他系统需要通过RestTemplate访问认证系统后台服务。

Ajax跨域访问

在后台服务系统中添加了一个Filter或Interceptor设置Response参数Access-Control-Allow-Origin和Access-Control-Allow-Methods

Restful 服务

利用SpringBoot开发一个后台服务http://localhost:8881/user/token

token生成采用UUID+UserID算法生成

token存储

客户端界面采用sessionStorage存储（或cookie）
服务端采用缓存(redis\全局令牌管理对象)
密码MD5+加盐处理

Md5(密码+盐)