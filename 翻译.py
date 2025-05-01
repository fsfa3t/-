from requests import post
import json
english='https://cn.bing.com/ttranslatev3?isVertical=1&&IG=22BEB547C3C9466FAD419B8DE64928B2&IID=translator.5025'
chinese='https://cn.bing.com/ttranslatev3?isVertical=1&&IG=71D1C6DA27B641FCA725927852079B29&IID=translator.5025'
head={
    "user-agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/135.0.0.0 Safari/537.36 Edg/135.0.0.0"
}
def English(shu_ru):
    text={
        "fromLang": "en-GB",
        "to":"zh-Hans",
        "text": shu_ru,
        "tryFetchingGenderDebiasedTranslations":"true",
        "token":"tlncx2jXErmzEQFBJ603cKXOReUT1Nxb",
        "key": "1746015385815"
    }
    po=post(english,text,headers=head)
    js=po.json()
    print("翻译结果：", js[0]['translations'][0]['text']+"\t"+js[0]['translations'][0]['transliteration']['text'])
def zhong_wen(shu_ru):
    text={
        "fromLang": "zh-Hans",
        "to":"en-GB",
        "text": shu_ru,
        "tryFetchingGenderDebiasedTranslations":"true",
        "token":"hnzeGjZi6mCVSVgRaaYIBmvPaihzXvhM",
        "key": "1746015500618"
    }
    po=post(chinese,text,headers=head)
    js=po.json()
    print("翻译结果：", js[0]['translations'][0]['text']+"\t"+js[1]['inputTransliteration'])
def pan_duan(shuru):
    for ch in shuru:
        if '\u4e00' <= ch <= '\u9fff':
            zhong_wen(shuru)
            break
    else:
        English(shuru)
while 1:
    pan_duan(input("请输入要翻译的内容："))
