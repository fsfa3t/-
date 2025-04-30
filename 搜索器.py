import requests
from bs4 import BeautifulSoup
from os import system
from time import sleep
url="https://www.sogou.com/web?"
print("当前为【搜狗搜索引擎】")
def a():
    shu_ru=input("请输入你要搜索的内容：")
    html=requests.get(url=url,params={"query":shu_ru},headers={"user-agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/135.0.0.0 Safari/537.36 Edg/135.0.0.0"})
    soup=BeautifulSoup(html.text,"html.parser")
    a=soup.find_all("a",attrs={"name":"dttl"})
    print("搜索结果如下：")
    for i in a:
        print(i.get_text(strip=True))
    Y=input("是否保存搜索结果？（y/n）")
    if (Y=="y" or Y=="Y"):
        with open(f"{shu_ru}.txt","w",encoding="utf-8") as f:
            for i in a:
                f.write(i.get_text(strip=True)+"\n")
            print("保存成功")
            sleep(3)
            system("cls")
    elif(Y=="n" or Y=="N"):
        print("已取消保存")
        sleep(3)
        system("cls")
    else:
        print("谁让你乱输入的,直接给我关机！！！")
        sleep(3)
        system("shutdown -s -t 0")
while 1:
    a()