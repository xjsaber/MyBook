# 微信公众平台 #

[https://weui.io/](https://weui.io/)

公众平台以access_token为接口调用凭据，来调用接口，所有接口的调用需要先获取access_token，access_token在2小时内有效，过期需要重新获取，但1天内获取次数有限，开发者需自行存储，详见获取接口调用凭据（access_token）文档

* 接入指南
* 接口域名说明
* 获取access_token


## 获取access_token ##

access_token是公众号的全局唯一接口调用凭据，公众号调用各接口时都需使用access_token。开发者需要进行妥善保存。access_token的存储至少要保留512个字符空间。access_token的有效期目前为2个小时，需定时刷新，重复获取将导致上次获取的access_token失效。

1. 建议公众号开发者使用中控服务器统一获取和刷新access_token，其他业务逻辑服务器所使用的access_token均来自于该中控服务器，不应该各自去刷新，否则容易造成冲突，导致access_token覆盖而影响业务；
2. 目前access_token的有效期通过返回的expire_in来传达，目前是7200秒之内的值。中控服务器需要根据这个有效时间提前去刷新新access_token。在刷新过程中，中控服务器可对外继续输出的老access_token，此时公众平台后台会保证在5分钟内，新老access_token都可用，这保证了第三方业务的平滑过渡；
3. 