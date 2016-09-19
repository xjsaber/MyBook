var hi = function(name){
    return "Hi" + name;
};

var greeting = function(name) {
    return hi(name);
};

hi;
// function(name){
//  return "Hi " + name
// }

hi("jonas");
// "Hi jonas"

var greeting = hi;

greeting("times");
// "Hi times"