# 第3章 类、对象和变量 Classes,Objects,and Variables #

	class Song
	  def initialize(name, artist, duration)
		@name = name
		@artist = artist
		@duration = duration
	  end
	end
initialize是一个特殊的方法。当你调用Song.new创建一个新的Song对象时，Ruby首先分配一些内存来保存未初始化的对象，然后调用对象的
initizlize方法，并把调用new时所使用的参数传入该方法。

initialize遵循了局部变量的命名的约定，即以小写字母开头。

每个Song对象带有自己的歌曲名、演唱者和时长，这意味着我们需要将这些值作为实例变量（instance variables）保存在对象中。对象内的所有方法都可以访问实例对象，每个对象都有实例变量的拷贝。

在Ruby中，实例变量就是一个由@开头的名字。

inspect方法（可以发送给任何对象）默认将对象的ID和实例变量格式化。
to_s可以发送给任何一个想要输出字符串表示的对象。

## 3.1 继承和消息 Inheritance and Messages ##
挤成允许你创建一个类，作为另一个类的精炼（refinement）和特化（specialization）。
类定义一行中的“< Song”告诉Ruby，KaraokeSone是Song的子类（subclass）。

## 3.2 对象和属性 Objects and Attribute ##
一个对象的外部可见部分被称为其属性（attribute）。

### 3.2.1 可写的属性 Writable Attributes ###
在Ruby中，访问对象属性就像访问其他变量一样，在Ruby中，你可以通过创建一个名字以等号结尾的方法来达成这一目标。

	class Song
	  def duration = (new_duration)
		@duration = new_duration
	  end
	end
	song = Song.new("Bicyclops", "Flect", 260)
	song.duration ->260
	song.duration = 257 # set attribute with updated value
	song.duration ->256
	#同理
	class Song
	  attr_writer :duration
	end
	song...
	song.duration = 257

### 3.2.2 虚拟属性 Virtual Attribute ###
这类属性访问的方法并不必须是对象实例变量的简单包装（wrapper）。



