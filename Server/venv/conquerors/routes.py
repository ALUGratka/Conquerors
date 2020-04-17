from flask import request, make_response
from conquerors import app, db, bcrypt
from conquerors.models import User, Character
import json


@app.route("/")
def home():
    return "Welcome!"


@app.route("/login", methods=['POST'])
def login():
    if request.method == 'POST':
        data = json.loads(request.data)
        username = data['username']
        password = data['password']
        user = User.query.filter_by(username=username).first() 

        if user and bcrypt.check_password_hash(user.password, password):
            message = {
                'response' : 'User authenticated'
            }
            response = make_response(json.dumps(message))
            response.headers['Content-Type'] = 'application/json'
            response.status_code = 200 # success
            return response
    
    message = {
        'response' : 'User not found'
    }
    response = make_response(json.dumps(message))
    response.headers['Content-Type'] = 'application/json'
    response.status_code = 400 # bad request
    return response


@app.route("/register", methods=['POST'])
def register():
    if request.method == 'POST':
        data = json.loads(request.data)
        email = data['email']
        username = data['username']
        password = data['password']
        birth_date = data['birthDate']
        hashed_password = bcrypt.generate_password_hash(password).decode('utf-8')
        # checking if username taken
        if User.query.filter_by(username=username).first():
            message = {
                'response' : 'User with given username already exists'
            }
            response = make_response(json.dumps(message))
            response.headers['Content-Type'] = 'application/json'
            return response
        # checking if email taken
        if User.query.filter_by(email=email).first():
            message = {
                'response' : 'User with given email already exists'
            }
            response = make_response(json.dumps(message))
            response.headers['Content-Type'] = 'application/json'
            return response
        # if not, create new user
        user = User(username=username, email=email, password=hashed_password,
         birthDate=birth_date)
        print(user)
        db.session.add(user)
        db.session.commit()
        # send response
        message = {
            'response' : 'User created'
        }
        response = make_response(json.dumps(message))
        response.headers['Content-Type'] = 'application/json'
        response.status_code = 201 # created
        return response
    # if else
    message = {
        'response' : 'Error'
    }
    response = make_response(json.dumps(message))
    response.headers['Content-Type'] = 'application/json'
    response.status_code = 404
    return response


@app.route('/create-character', methods=['POST'])
def create_character():
    if request.method == 'POST':
        data = json.loads(request.data)
        
        userId = data['userId']
        level = data['level']

        charisma = data['charisma']
        intelligence = data['intelligence']
        agility = data['agility']
        strength = data['strength']

        nickname = data['nickname']
        sex = data['sex']
        characterClass = data['characterClass']
        hair = data['hair']
        eyeColor = data['eyeColor']
        blouse = data['blouse']
        pants = data['pants']
        shoes = data['shoes']
        
        # check if user with given id exists
        if User.query.filter_by(id=int(userId)).first():
            # if so create character and add to db
            character = Character(level=level, charisma=charisma, intelligence=intelligence,
            agility=agility, strength=strength, nickname=nickname, sex=sex,
            characterClass=characterClass, hair=hair, eyeColor=eyeColor, blouse=blouse,
            pants=pants, shoes=shoes, userId=userId)

            print(character)
            db.session.add(character)
            db.session.commit()
            # send response
            message = {
                'response' : 'Character created'
            }
            response = make_response(json.dumps(message))
            response.headers['Content-Type'] = 'application/json'
            response.status_code = 201 # created
            return response
        else:
            message = {
                'response' : 'User with given id does not exists in db'
            }
            response = make_response(json.dumps(message))
            response.headers['Content-Type'] = 'application/json'
            return response

    # if any other error occours
    message = {
        'response' : 'Error'
    }
    response = make_response(json.dumps(message))
    response.headers['Content-Type'] = 'application/json'
    response.status_code = 404
    return response