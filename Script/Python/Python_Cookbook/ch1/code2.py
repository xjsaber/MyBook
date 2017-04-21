# Q:从任意长度的可迭代对象中分解元素
def split():
    # A: mime
    # data = [1, 2, 3, 4, 5]
    # for i in data:
    #    print(i)

    # A: answer
    p = (4, 5)
    x, y = p
    data = ['ACME', 50, 91.1, (2012, 12, 21)]
    name, shares, price, date = data


if __name__ == "__main__":
    split()
