function () {
  //这里是定义一个控制器，用来对外开放计算接口
    oController = {
      addNumber : addNumber,
      addOpen : addOpen
  }

  //这里是符号对应的计算规则，这里采用闭包的对照表来处理
  var opMap = {
      "+": function (a, b) { return b + a}, //处理加法运算的闭包
      "-": function (a, b) { return b - a}, //处理减法运算的闭包
      "*": function (a, b) { return b * a}, //处理乘法运算的闭包
      "/": function (a, b) { return b / a}, //处理除法运算的闭包
      "=": function (a, b) { return a}     //处理最终结果
  }

  //用来储存数值、操作符和输入缓存的数据结构
  var oMemery = {
      numStack : [],                        //储存数值
      operStact : [],                       //储存字符串
      inBuffer : ""                         //输入显示缓存
  }

  function init()                           //初始化
  {
    with(oMemery)
    {
        numStack.length = 0;                //清空数值堆栈
        operStact.length = 0;               //清空操作符堆栈
        numStack.push(0);                   //在数值堆栈中推入一个0作为栈顶
        inBuffer = "";                      //清空输入缓存
        return inBuffer;                    //将清空后的缓存值（实际上是空字符串''）返回 
    }
  }

  function doOper()                         //计算表达式
  {
    with(oMemery)
    {
      if(operStact.length)                  //如果计算堆栈中有值
      {
        try
        {
            //取得栈顶运算符对应的操作方法
            var op = opMap[operStact.pop()];
            var args = [];

            //该方法需要提供几个操作数，可以通过op.length得到
            for(var i = 0; i < op.length; i++)
            {
              //从数值堆栈中依次取相应的操作数进行处理
              args.push(numStack.pop());
            }
        }
      catch(ex)
      {
        alert(ex.message);
      }
      }
      return numStackp[numStack.length - 1];
    }
  }

  //格式化显示的数值，主要为了符合计算器的习惯，比如0显示成0. (带小数点)
  function formatBuff(buf) {
      if (buf == "")
          return "0.";
      else {
          buf = parseFloat(buf);
          return /\./.test(buf) ? buf : buf + "."; //正则函数
      }
  }

  function addNumber(tok)   //输入数值
  {
      with (oMemery) {
        try
        {
          var token;
          if(tok = "\b" )   //如果输入的是一个退格
              token = inBuffer.slice(0,-1)  //把缓存中的内容去掉一个
          else
              token = inBuffer + tok.toString()  //否则接受新输入的数字
          //如果数值的第一位是小数点，显示上补上一个0
          if(/^([\d]+(\.)?[\d]*)?$/.test(token))
          {
            inBuffer = token;  //如果满足，则确认接受，写入缓存
          }   
        }
      }
  }
}