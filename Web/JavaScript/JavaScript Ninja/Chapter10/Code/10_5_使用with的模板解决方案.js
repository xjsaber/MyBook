/**
 * Created by xjsaber on 2016/6/17.
 * 10_5_使用with的模板解决方案
 */
(function(){
    var cache = {};

    this.tmpl = function tmpl(str, data) {
        /**
         * 判断是否获取一个模板，或者是否需要加载模板并确保进行缓存
         */
        var fn = !/\W/.test(str) ?
            cache[str] = cache[str] || tmpl(document.getElementById(str).innerHTML):
            /**
             * 生成一个可复用函数，作为模板生成器（并且进行缓存）
             */
            new Function("obj", "var p=[],print=function(){p.push.apply(p, arguments);};" +
                    //在with作用于内引入data作为局部变量
                    "with(obj){p.push('" +
                    //将模板转化为纯JavaScript
                    str.replace(/[\r\t\n]/g, " ")
                        .split("<%").join("\t")
                        .replace(/((^|%>)[^\t]*)'/g, "$1\r")
                        .replace(/\t=(.*?)%>/g,"',$1,'")
                        .split("\t").join("');")
                        .split("%>").join("p.push('")
                        .split("\r").join("\\'")
                + "');}return p.join(' ');");
        return data ? fn (data) : fn;
    };
})();

