import requests 
import json


url = 'http://127.0.0.1:80/'


def login_user():
    user = {
        "username" : "izabelka",
        "password" : "izabelaizabela"
    }
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'login'
    print('sending login request')
    response = requests.post(url=url+endpoint, headers=headers, data=json.dumps(user))
    print(response.content)


if __name__ == '__main__':
    print('testing registration route')
    login_user()

    #test_home()

