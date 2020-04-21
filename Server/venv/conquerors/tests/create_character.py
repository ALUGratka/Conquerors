import requests 
import json


url = 'http://127.0.0.1:80/'


def create_character():
    # to do
    character = {
        "userId" : 1,
        "level" : 0,
        "charisma" : 2,
        "intelligence": 5,
        "agility" : 1,
        "strength":2,
        "nickname" : "magoslaw",
        "sex" : 0,
        "characterClass" : 3,
        "hair" : 0,
        "hat" : 0,
        "eyeColor" : 0,
        "blouse" : 0,
        "pants" : 0,
        "shoes" : 0
    }
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'create-character'
    print('sending create character post request')
    response = requests.post(url=url+endpoint, headers=headers, data=json.dumps(character))
    print(response.content)


if __name__ == '__main__':
    print('testing create user route')
    create_character()