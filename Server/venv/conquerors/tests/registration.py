import requests 
import json


url = 'http://127.0.0.1:80/'


def test_home():
    print('dupa')
    response = requests.get(url=url)
    print(response.content)


def create_user(email, username, password, birth_date):
    print('creating user')
    return {
        "email" : email,
        "username" : username,
        "password" : password,
        "birthDate" : birth_date, 
    }


def register_user():
    user = create_user('dzik@gmail.com', 'dziku', 'dzikidzik', '22/06/1998')
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'register'
    print('sending register request')
    response = requests.post(url=url+endpoint, headers=headers, data=json.dumps(user))
    print(response.content)


if __name__ == '__main__':
    print('testing registration route')
    register_user()

    #test_home()

