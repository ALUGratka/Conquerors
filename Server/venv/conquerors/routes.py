from flask import request, make_response
from conquerors import app, db, bcrypt
from conquerors.models import User, Character, LastPrize, Enemy, Treasure, GameplayAchievements
import json
from datetime import date


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
            message = user.to_dict()
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


@app.route("/user", methods=['GET'])
def get_user_by_username():
    if request.method == 'GET':
        username = request.args.get('username')

        user = User.query.filter_by(username=username).first() 

        if user:
            message = user.to_dict()
            response = make_response(json.dumps(message))
            response.headers['Content-Type'] = 'application/json'
            response.status_code = 200 # success
            return response

        message = {
            'response' : 'User with given id does not exists in db'
        }
        response = make_response(json.dumps(message))
        response.headers['Content-Type'] = 'application/json'
        response.status_code = 400 # bad request
        return response


@app.route("/user", methods=['DELETE'])
def delete_user_by_email():
    if request.method == 'DELETE':
        email = request.args.get('email')

        user = User.query.filter_by(email=email).first()

        if user:
            db.session.delete(user)
            db.session.commit()
            
            message = {
                'response' : 'User deleted from db'
            }
            response = make_response(json.dumps(message))
            response.headers['Content-Type'] = 'application/json'
            response.status_code = 200 # success
            return response

    message = {
        'response' : 'User with given email not found in db'
    }
    response = make_response(json.dumps(message))
    response.headers['Content-Type'] = 'application/json'
    response.status_code = 400 # bad request
    return response


@app.route("/user", methods=['PUT'])
def update_user():
    if request.method == 'PUT':
        data = json.loads(request.data)
        id = data['id']
        email = data['email']
        username = data['username']
        password = data['password']

        user = User.query.filter_by(id=id).first()

        if user.email != email:
            if User.query.filter_by(email=email).first():
                message = {
                    'response' : 'Email taken'
                }
                response = make_response(json.dumps(message))
                response.headers['Content-Type'] = 'application/json'
                return response
            user.email = email

        if user.username != username:
            if User.query.filter_by(username=username).first():
                message = {
                    'response' : 'Username taken'
                }
                response = make_response(json.dumps(message))
                response.headers['Content-Type'] = 'application/json'
                return response
            user.username = username

        if bcrypt.check_password_hash(user.password, password):
            message = {
                'response' : 'Same password'
            }
            response = make_response(json.dumps(message))
            response.headers['Content-Type'] = 'application/json'
            return response
        else:
            hashed_password = bcrypt.generate_password_hash(password).decode('utf-8')
            user.password = hashed_password

        db.session.commit()

        message = {
            'response' : 'User updated'
        }
        response = make_response(json.dumps(message))
        response.headers['Content-Type'] = 'application/json'
        response.status_code = 200 # success
        return response

    message = {
        'response' : 'Error, user not updated'
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
        hat = data['hat']
        eyeColor = data['eyeColor']
        blouse = data['blouse']
        pants = data['pants']
        shoes = data['shoes']
        
        # check if user with given id exists
        if User.query.filter_by(id=int(userId)).first():
            # if so create character and add to db
            character = Character(level=level, charisma=charisma, intelligence=intelligence,
            agility=agility, strength=strength, nickname=nickname, sex=sex,
            characterClass=characterClass, hair=hair, hat=hat, eyeColor=eyeColor, blouse=blouse,
            pants=pants, shoes=shoes, userId=userId)

            print(character)
            db.session.add(character)
            db.session.commit()
            # send response
            message = character.to_dict()
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


# get list of characters created by user with given userId
@app.route('/get-characters', methods=['GET'])
def get_characters():
    if request.method == 'GET':
        userId = request.args.get('userId')
        characters = Character.query.filter_by(userId=int(userId))
        if characters.length()>0:
            print(characters)
            message = []
            for character in characters:
                message.append(character.to_dict())
            response = make_response(json.dumps(message))
            response.headers['Content-Type'] = 'application/json'
            response.status_code = 200
            return response
        message = {
            'response' : 'This user did not created any characters'
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


@app.route("/prize", methods=['POST'])
def createPrizeDate():
    if request.method == 'POST':
        data = json.loads(request.data)

        userId = data['userId']
        lastDate = date.today()

        # check if user with given id exists
        if User.query.filter_by(id=int(userId)).first():
            if(LastPrize.query.filter_by(userId=int(userId), lastDate=str(lastDate)).first()):
                message = {
                    'response': 'You already got a prize today'
                }
                response = make_response(json.dumps(message))
                response.headers['Content-Type'] = 'application/json'
                response.status_code = 403
                return response
            else:
                # if so create character and add to db
                prizeDate = LastPrize(lastDate=lastDate, userId=userId)
                db.session.add(prizeDate)
                user =  User.query.get(userId)
                user.skillPoints = user.skillPoints+2
                db.session.commit()
                # send response
                message = {
                    'response': 'Prize Date created'
                }
                response = make_response(json.dumps(message))
                response.headers['Content-Type'] = 'application/json'
                response.status_code = 201  # created
                return response
        else:
            message = {
                'response': 'User with given id does not exists in db'
            }
            response = make_response(json.dumps(message))
            response.headers['Content-Type'] = 'application/json'
            return response

    # if any other error occours
    message = {
        'response': 'Error'
    }
    response = make_response(json.dumps(message))
    response.headers['Content-Type'] = 'application/json'
    response.status_code = 404
    return response


@app.route("/users/<userId>/prizes", methods=['GET'])
def get_user_prizes(userId):
    if request.method == 'GET':
        prizes = LastPrize.query.filter_by(userId=int(userId)).all()
        prizesJson = []
        for prize in prizes:
            prizesJson.append(prize.to_dict())
        response = make_response(json.dumps(prizesJson, default=str))
        response.headers['Content-Type'] = 'application/json'
        response.status_code = 200 # success
        return response

@app.route("/users/<userId>/characters", methods=['GET'])
def get_user_characters(userId):
    if request.method == 'GET':
        characters = Character.query.filter_by(userId=int(userId)).all()
        charactersJson = []
        for character in characters:
            charactersJson.append(character.to_dict())
        response = make_response(json.dumps(charactersJson, default=str))
        response.headers['Content-Type'] = 'application/json'
        response.status_code = 200 # success
        return response


@app.route("/characters/<characterId>/statistic", methods=['GET'])
def get_character_statistic(characterId):
    if request.method == 'GET':
        character = Character.query.get(characterId)
        message = character.to_statistic_dict()
        response = make_response(json.dumps(message, default=str))
        response.headers['Content-Type'] = 'application/json'
        response.status_code = 200 # success
        return response


@app.route("/enemies", methods=['GET'])
def get_enemies():
    if request.method == 'GET': 
        enemies = Enemy.query.all()

        if enemies.length() > 0:
            message = []
            for enemy in enemies:
                message.append(enemy.to_dict())
            response = make_response(json.dumps(message))
            response.headers['Content-Type'] = 'application/json'
            response.status_code = 200 # success
            return response

        message = {
            'response' : 'No enemies in db'
        }
        response = make_response(json.dumps(message))
        response.headers['Content-Type'] = 'application/json'
        response.status_code = 400 # bad request
        return response


@app.route("/treasures", methods=['GET'])
def get_treasures():
    if request.method == 'GET': 
        treasures = Treasure.query.all()

        if treasures.length() > 0:
            message = []
            for treasure in treasures:
                message.append(treasure.to_dict())
            response = make_response(json.dumps(message))
            response.headers['Content-Type'] = 'application/json'
            response.status_code = 200 # success
            return response

        message = {
            'response' : 'No treasures in db'
        }
        response = make_response(json.dumps(message))
        response.headers['Content-Type'] = 'application/json'
        response.status_code = 400 # bad request
        return response


@app.route("/gameplay-achievement", methods=['POST', 'PUT'])
def gameplay_achievement():
    if request.method == 'POST':
        data = json.loads(request.data)
        objectPositionX = data['objectPositionX']
        objectPositionY = data['objectPositionY']
        objectId = data['objectId']
        gameplayId = data['gameplayId']
        achievedByCharacterId = data['achievedByCharacterId']

        gameplay_achievement = GameplayAchievements(
            objectPositionX=objectPositionX, objectPositionY=objectPositionY,
            objectId=objectId, gameplayId=gameplayId, 
            achievedByCharacterId=achievedByCharacterId)

        print(gameplay_achievement)
        db.session.add(gameplay_achievement)
        db.session.commit()

        message = gameplay_achievement.to_dict()
        response = make_response(json.dumps(message))
        response.headers['Content-Type'] = 'application/json'
        response.status_code = 200 # success
        return response

    if request.method == 'PUT':
        data = json.loads(request.data)
        id = data['id']
        objectPositionX = data['objectPositionX']
        objectPositionY = data['objectPositionY']
        objectId = data['objectId']
        gameplayId = data['gameplayId']
        achievedByCharacterId = data['achievedByCharacterId']

        gameplay_achievement = GameplayAchievements.query.filter_by(id=id).first()
        gameplay_achievement.objectPositionX = objectPositionX
        gameplay_achievement.objectPositionY = objectPositionY
        gameplay_achievement.objectId = objectId
        gameplay_achievement.gameplayId = gameplayId
        gameplay_achievement.achievedByCharacterId = achievedByCharacterId

        print(gameplay_achievement)

        db.session.commit()

        message = gameplay_achievement.to_dict()
        response = make_response(json.dumps(message))
        response.headers['Content-Type'] = 'application/json'
        response.status_code = 200 # success
        return response

    message = {
        'response' : 'Gameplay achievement invalid'
    }
    response = make_response(json.dumps(message))
    response.headers['Content-Type'] = 'application/json'
    response.status_code = 400 # bad request
    return response


# get gameplay achievements by gameplay ID
@app.route("/gameplay-achievements", methods=['GET'])
def gameplay_achievements():
    if request.method == 'GET':
        gameplayId = request.args.get('gameplayId')
        gameplay_achievements = GameplayAchievements.query.filter_by(
            gameplayId=int(gameplayId)).first()
        if(gameplay_achievements.length()>0):
            message = []
            for achievement in gameplay_achievements:
                message.append(achievement.to_dict())
            response = make_response(json.dumps(message))
            response.headers['Content-Type'] = 'application/json'
            response.status_code = 200 # success
            return response

    message = {
        'response' : 'Invalid gameplay achievements request'
    }
    response = make_response(json.dumps(message))
    response.headers['Content-Type'] = 'application/json'
    response.status_code = 400 # bad request
    return response

