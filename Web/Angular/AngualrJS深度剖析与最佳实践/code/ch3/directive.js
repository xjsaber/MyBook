anuglar.module('com.xjsaber.angular').directive('directiveLift', function($log){
    return {
        restrict: 'EA',
        transclude: true,
        replace: true,
        template: '<div><h2>count: {{count}}</h2><p ng-transclude></p>/div>',
        scope: {
            count: '='
        },
        compile: function(scope, elm, iAttrs){
            $log.info('compile', 'count value from attribute:' + iAttrs.count);
            return {
                pre: function(scope, elm, iAttrs){
                    $log.info('pre-link', 'count value from attribute:' + iAttrs.count, 'count value from scope:' + scope.count)
                }, 
                post: function(scope, elm, iAttrs){
                    $log.info('post-link', 'count value from attribute:' + iAttrs.count, 'count value from scope:' + scope.count)
                }
            }
        },
        controller: function($scope){
            $log.info('controller', 'count value from controller:' + $scope.count);
        }
    }
}),
angular.module('com.xjsaber.angular').controller('DemoController', function() {
    var vm = this;
    return vm;
})