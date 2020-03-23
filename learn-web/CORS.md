# CORS (Cross-Origin Resource Sharing)
https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Access_control_CORS
https://www.we45.com/blog/3-ways-to-exploit-misconfigured-cross-origin-resource-sharing-cors
https://www.w3.org/TR/cors/

## CORS攻击
> 跨域资源共享（CORS）是一种网络机制，使Web浏览器在受控的情况下通过XmlHttpRequest API执行跨域请求。这些跨域请求具有Origin标头，用于标识发起请求的域。它定义了在Web浏览器和服务器之间使用的协议，以确定是否允许跨域请求


## 有许多与CORS相关的HTTP的header，但以下三个和安全最为相关
1. Access-Control-Allow-Origin 指定哪些域可以访问本域的资源。例如，如果requester.com想要访问provider.com的资源，那么开发人员可以用此授予requester.com访问provider.com资源的权限。
2. Access-Control-Allow-Credentials 指定浏览器是否将使用请求发送cookie。仅当allow-credentials标头设置为true时，才会发送cookie。
3. Access-Control-Allow-Methods 指定可以使用哪些HTTP请求方法（GET，PUT，DELETE等）来访问资源。开发人员可用其进一步加强控制力，增强安全性。

## 三个攻击场景

1.CORS中header滥用通配符（*）：

> 最常见的CORS配置错误就是错误地使用诸如（*）之类的通配符去规定允许访问本域资源的外来域。 这通常设置为默认值，这意味着任何域都可以访问本域上的资源。例如，以下请求：
  GET /api/userinfo.php
  Host:www.victim.com 
  Origin:www.victim.com
  当你发送上述请求时，你可以看到具有Access-Control-Allow-Originheader的响应，如以下所示：
  HTTP/1.0 200 OK
  Access-Control-Allow-Origin：* 
  Access-Control-Allow-Credentials：true
  

2.信任域的配置存在缺陷


3.使用XSS实现CORS攻击

> 开发人员用于对抗CORS攻击的一种防御机制是将一些频繁请求的域列入白名单。但是，这也有缺陷，若白名单中某个域的子域容易受到其他攻击（如XSS）的影响，它也会影响CORS的安全性。
  如下示例，以下代码表示允许requester.com的子域访问provider.com的资源。
  