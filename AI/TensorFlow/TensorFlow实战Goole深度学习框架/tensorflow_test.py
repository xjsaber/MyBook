import tensorflow as tf


b = tf.Variable(tf.zeros(100))
W = tf.Variable(tf.random_uniform([784, 100], -1, 1))
x = tf.placeholder(name="x")
relu = tf.nn.relu(tf.matmul(W, x) + b)
C = [...]
s = tf.Session()
for step in range(0, 10):
    input = ...construct 100-D input array...
    result = s.run(C, feed_dict={x: input})
    print(step, result)
