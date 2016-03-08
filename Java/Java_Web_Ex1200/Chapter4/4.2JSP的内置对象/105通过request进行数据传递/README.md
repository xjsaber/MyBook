request.setAttribute(String name, Object object);
参数说明
name:表示变量名，为String类型，在转发后的页面取数据时，就是通过这个变量名来获取数据的。
object：用于指定需要在request范围内传递的数据，为Object类型。
request.getAttribute(String name);
参数说明
name：表示变量名，该变量名在request范围内有效。
