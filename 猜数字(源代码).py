import random

def main():
    answer = random.randint(1, 100)
    print("猜一个1到100之间的数字：")
    while True:
        try:
            guess = int(input("请输入你的猜测："))
            if guess < answer:
                print("太小了！")
            elif guess > answer:
                print("太大了！")
            else:
                print("恭喜你，猜对了！")
                break
        except ValueError:
            print("请输入有效的整数。")

if __name__ == "__main__":
    main()
