/**
 * Created by xjsaber on 2016/9/20.
 */
(function(){

})();

// (function(foo, bar){
//     alert(foo * bar);
// })(10, 2);

var baz;

(function() {
    var foo = 10;
    var bar = 2;
    baz = function(){
        return foo * bar;
    };
})();
console.log(baz());
