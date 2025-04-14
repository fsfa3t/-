def login():
    print("欢迎使用登录系统！")
    name=input("请设置用户名：")
    passwd=input("请设置密码：")
    username = input("请输入用户名: ")
    password = input("请输入密码: ")
    # 假设用户名和密码都是 admin
    if username == name and password == passwd:
        print("登录成功！")
    else:
        print("用户名或密码错误。")

if __name__ == "__main__":
    login()