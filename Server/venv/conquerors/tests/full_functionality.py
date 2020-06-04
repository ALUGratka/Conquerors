import requests 
import json


URL = 'http://127.0.0.1:80/'


def present_response(response):
    print('>Response:')
    print('Status code: ' + str(response.status_code))
    print('Url: ' + str(response.url))
    print('Headers: ' + str(response.headers))
    print('Content: ' + str(response.text))


def create_user(email, username, password, birth_date):
    return {
        "email" : email,
        "username" : username,
        "password" : password,
        "birthDate" : birth_date, 
    }


def register_user1():
    print('>Register user1:')
    user = create_user('izabela@gmail.com', 'izabelka', 'izabelaizabela', '22/06/1998')
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'register' 
    response = requests.post(url=URL+endpoint, headers=headers, data=json.dumps(user))
    present_response(response)


def register_user2():
    print('>Register user2:')
    user = create_user('marcysia@gmail.com', 'marcyska', 'marcysiamarcysia', '22/06/1998')
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'register' 
    response = requests.post(url=URL+endpoint, headers=headers, data=json.dumps(user))
    present_response(response)


def register_user3():
    print('>Register user3:')
    user = create_user('piotr@gmail.com', 'piotrus', 'piotrpiotr', '22/06/1998')
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'register' 
    response = requests.post(url=URL+endpoint, headers=headers, data=json.dumps(user))
    present_response(response)


def login_user():
    print('>Login user:')
    user = {
        "username" : "izabelka",
        "password" : "izabelaizabela"
    }
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'login'
    response = requests.post(url=URL+endpoint, headers=headers, data=json.dumps(user))
    present_response(response)


def get_user():
    print('>Get user:')
    user_params = {
        "username" : "izabelka"
    }
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'user'
    response = requests.get(url=URL+endpoint, headers=headers, params=user_params)
    present_response(response)


def update_user_by_id():
    print('>Update user:')
    user = {
        "id" : 1,
        "email" : "izabela2@gmail.com",
        "username" : "izabelka",
        "password" : "izabelaizabela"
    }       
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'user'
    response = requests.put(url=URL+endpoint, headers=headers, data=json.dumps(user))
    present_response(response)


def delete_user():
    print('>Delete user:')
    user_params = {
        "email" : "izabela2@gmail.com"
    }
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'user'
    response = requests.delete(url=URL+endpoint, headers=headers, params=user_params)
    present_response(response)


def create_character1():
    print('>Create character1:')
    character = {
        "userId" : 1,
        "level" : 0,
        "charisma" : 2,
        "intelligence": 5,
        "agility" : 1,
        "strength":2,
        "nickname" : "Izabela Czadowa",
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
    response = requests.post(url=URL+endpoint, headers=headers, data=json.dumps(character))
    present_response(response)


def create_character2():
    print('>Create character2:')
    character = {
        "userId" : 1,
        "level" : 0,
        "charisma" : 2,
        "intelligence": 2,
        "agility" : 1,
        "strength":5,
        "nickname" : "Izabela Zwycieska",
        "sex" : 0,
        "characterClass" : 2,
        "hair" : 0,
        "hat" : 0,
        "eyeColor" : 0,
        "blouse" : 0,
        "pants" : 0,
        "shoes" : 0
    }
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'create-character'
    response = requests.post(url=URL+endpoint, headers=headers, data=json.dumps(character))
    present_response(response)


def get_characters_list():
    print('>Get characters list:')
    user_params = {
        "userId" : "1"
    }
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'get-characters'
    response = requests.get(url=URL+endpoint, headers=headers, params=user_params)
    present_response(response)


def update_character():
    print('>Update character:')
    character_params = {
        'id': 1,
        'level': 1,
        'charisma': 3,
        'intelligence': 5,
        'agility': 2,
        'strength': 2,
        'skillPoints': 3
    }
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'update-character'
    response = requests.put(url=URL+endpoint, headers=headers, data=json.dumps(character_params))
    present_response(response)


def get_enemies():
    print('>Get enemies list:')
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'enemies'
    response = requests.get(url=URL+endpoint, headers=headers)
    present_response(response)


def get_treasures():
    print('>Get treasures list:')
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'treasures'
    response = requests.get(url=URL+endpoint, headers=headers)
    present_response(response)


def post_gameplay_12():
    print('>Post gameplay:')
    gameplay = {
        'player1id' : 1,
        'player2id' : 2,
    }
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'gameplay'
    response = requests.post(url=URL+endpoint, headers=headers, data=json.dumps(gameplay))
    present_response(response)    


def post_gameplay_23():
    print('>Post gameplay:')
    gameplay = {
        'player1id' : 2,
        'player2id' : 3,
    }
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'gameplay'
    response = requests.post(url=URL+endpoint, headers=headers, data=json.dumps(gameplay))
    present_response(response)    


def get_gameplay():
    print('>Get gameplay:')
    gameplay_params = {
        'id' : 1
    }
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'gameplay'
    response = requests.get(url=URL+endpoint, headers=headers, params=gameplay_params)
    present_response(response)


def get_gameplays_by_users():
    print('>Get gameplays by users:')
    gameplays_params = {
        'player1id' : 1,
        'player2id' : 2
    }
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'gameplays'
    response = requests.get(url=URL+endpoint, headers=headers, params=gameplays_params)
    present_response(response)


def put_gameplay():
    print('>Put gameplay:')
    gameplay = {
        'id':1
    }
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'gameplay'
    response = requests.put(url=URL+endpoint, headers=headers, data=json.dumps(gameplay))
    present_response(response)  


def delete_gameplay():
    print('>Delete gameplay:')
    gameplay = {
        'id':1
    }
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'gameplay'
    response = requests.delete(url=URL+endpoint, headers=headers, params=gameplay)
    present_response(response) 


def post_gameplay_achievement():
    print('>Post gameplay achievement:')
    achievement = {
        'objectPositionX' : 50,
        'objectPositionY' : 50,
        'objectId' : 3,
        'gameplayId' : 1,
        'objectType' : 0    # treasure
    }
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'gameplay-achievement'
    response = requests.post(url=URL+endpoint, headers=headers, data=json.dumps(achievement))
    present_response(response)


def update_gameplay_achievements():
    print('>Update gameplay achievements:')
    achievement = {
        'id' : 2,
        'achievedByCharacterId' : 2
    }
    headers = {'Content-Type' : 'application/json'}
    endpoint = 'gameplay-achievement'
    response = requests.put(url=URL+endpoint, headers=headers, data=json.dumps(achievement))
    present_response(response)


if __name__ == '__main__':
    # User operations

    register_user1()
    register_user2()
    register_user3()
    
    #login_user()
    #get_user()
    #update_user_by_id()
    #delete_user()

    # Character operations

    #create_character1() # id 1
    #create_character2() # id 2
    # nieważne :)
    # get_characters_list()
    # update_character()
    # get_characters_list()

    # Enemies and treasures operations

    #get_enemies()
    #get_treasures()

    # Gameplay operations



    # Gameplay achievements operations

    post_gameplay_achievement()
    #put_gameplay_achievement()

    


