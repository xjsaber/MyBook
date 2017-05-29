import tensorflow as tf


g1 = tf.Graph()
with g1.as_default():
    # 在计算图g1中定义变量“v”，并设置初始值为0
    v = tf.get_variable(
        "v", initializer=tf.zeros_initializer(shape=[1]))


g2 = tf.Graph()
with g2.as_default():
    # 在计算图g2中定义变量“v”，并设置初始值为1
    v = tf.get_variable(
        "v", initializer=tf.ones_initializer(shape=[1]))


# 计算图g1中读取变量“v”的取值
with tf.Session(graph=g1) as sess:
    tf.initialize_all_variables().run()
    with tf.variable_op_scope("", reuse=True):
        # 在计算图g1中，变量“v”的取值应该为0，所以下面这行会输出[0.]
        print(sess.run(tf.get_variable("v")))


# 计算图g2中读取变量“v”的取值
with tf.Session(graph=g2) as sess:
    tf.initialize_all_variables().run()
    with tf.variable_op_scope("", reuse=True):
        # 在计算图g2中，变量“v”的取值应该为1，所以下面这行会输出[1.]。
        print(sess.run(tf.get_variable("v")))
