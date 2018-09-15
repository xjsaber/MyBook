const TTL = 10;

var obj = {
    $digest: function() {
        var watch, value, last, 
        watchers,
        asyncQueue = this.$$asyncQueue,
        postDigestQueue = this.$$postDigestQueue,
        length,
        dirty, tll = TTL, //TTL默认值为10;
        next, current, target = this,
        watchLog = [],
        logIdx, logMsg, asyncTask;
        beginPhase('$digest');// 设置$$phase状态为$digest中。
        $browser.$$checkUrlChange();
        lastDirtyWatch = null;
        do { // “脏检查机制”循环开始
            dirty = false;
            current = target;
            // 首先执行由$scope.$evalAsync注册的异步对象
            while (asyncQueue.length){
                try {
                    asyncTask = asyncQueue.shift();
                    asyncTask.scope.$eval(asyncTask.expression);
                } catch (e){
                    clearPhase();
                    $exceptionHandler(e);
                }
                lastDirtyWatch = null;
            }
            traverseScopesLoop:
                do { // 对当前的$scope以及子$scope循环
                    if (watchers = current.$$watchers){
                        // 对当前$scope的watcher函数询问
                        length = watchers.length;
                        while (length--){
                            try {
                                watch = watchers[length];
                                // 首先会使用JavaScript的===运算来比较,因为他性能更快，然后再根据是否需要深度angular.equals比较
                                if (watch){
                                    if ((value = watch.get(current)) !== (last = watch.last) && !(watch.eq ? equals(value, last) : (typeof value === 'number' && typeof last === 'number' && isNaN(value) && isNaN(last)))) {
                                        dirty = true;
                                        lastDirtyWatch = watch;
                                        // 执行watcher的监听函数， 参数为：新值、旧值、当前$scope。
                                        watch.fn(value, ((last === initWatchVal) ? value : last), current);
                                        if (ttl < 5){
                                            //.... log message
                                        }
                                    } else if (watch === lastDirtyWatch){
                                        dirty = false;
                                        break traverseScopesLoop;
                                    }
                                }
                            } catch(e){
                                clearPhase(); //去除$$phase的$digest状态
                                $exceptionHandler(e);
                            }
                        }
                    }
                    // 对子以及子孙$scope的循环，它们中的watcher函数都会被询问、检查。
                    if (!(next = (current.$$childHead || 
                            (current !== target && current.$$nextSibling )){
                                current = current.$parent;
                            } 
                        }
                    } while ((current = next)); 
                }
        }
    }
}