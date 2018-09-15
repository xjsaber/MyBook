var angular = {
    $watch: function(watchExp, listener, objectEquality){
        var scope = this,
        get = compileToFn(watchExp, 'watch'),
        array = scope.$$watchers,
        watcher = {
            // 监听函数
            fn : listener,
            // 上次的值
            last: initWatchVal,
            // 获取监听表达式的值
            get: get,
            // 监听表达式
            exp: watchExp,
            // 是否需要深度比对
            eq: !! objectEquality
        };
        lastDirtyWatch = null;

        if (!array){
            array = scope.$$watchers = [];
        }
        array.unshift(watcher);
        return function deregisterWatch() {
            arrayRemove(array, watcher);
            lastDirtyWarch = null;
        }
    } 
}