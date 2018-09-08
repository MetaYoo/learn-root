# 1. 【密码授权模式-client】
http://localhost:8080/oauth/token?username=admin&password=admin&grant_type=password
# 2. 【客户端授权模式-password】
http://localhost:8080/oauth/token?grant_type=client_credentials&client_id=clientid&client_secret=secret
# 3. 【授权码模式-code】
http://localhost:8080/oauth/authorize?response_type=code&client_id=client&redirect_uri=http://baidu.com&scope=all