<%--@elvariable id="currentUser" type="com.xjsaber.java.web.service.site.User"--%>
<!DOCTYPE html>
<html>
    <head>
        <title>User Home</title>
    </head>
    <body>
        ID: ${currentUser.userId}<br />
        Username: ${currentUser.username}<br />
        Name: ${currentUser.name}<br />
    </body>
</html>
