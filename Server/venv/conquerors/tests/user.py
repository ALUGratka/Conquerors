import requests 
import json


url = 'http://127.0.0.1:80/'


def get_user():
    user_params = {
        "username" : "izabelka"
    }
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'user'
    print('sending get user request')
    response = requests.get(url=url+endpoint, headers=headers, params=user_params)
    print(response.url)
    print(response.content.decode('UTF-8'))


def delete_user():
    user_params = {
        "email" : "izabela2@gmail.com"
    }
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'user'
    print('sending delete user request')
    response = requests.delete(url=url+endpoint, headers=headers, params=user_params)
    print(response.content)


def update_user_by_id():
    user = {
        "id" : 3,
        "email" : "izabela2@gmail.com",
        "username" : "izabelka",
        "password" : "izabelaizabela"
    }       
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'user'
    print('sending update user request')
    response = requests.put(url=url+endpoint, headers=headers, data=json.dumps(user))
    print(response.content)

if __name__ == '__main__':
    # print('testing get user by email route')
    # get_user()

    # print('testing update user by id route')
    # update_user_by_id()

    print('testing delete user by email route')
    delete_user()

    # print('testing get user by email route')
    # get_user()
