from datetime import datetime, date
from conquerors import db


class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)

    authenticated = db.Column(db.Boolean, default=False)
    email = db.Column(db.String(120), unique=True, nullable=False)
    username = db.Column(db.String(20), unique=True, nullable=False)
    password = db.Column(db.String(60), nullable=False)
    # relations
    characters = db.relationship('Character', backref='user_id', lazy=True)
    lastLoggedInCalendar = db.relationship('LastLoggedIn', backref='last_logged_in', lazy=True)

    def __repr__(self):
        return f"User('{self.id}', '{self.username}')"

    def to_dict(self):
        return {
            'id': self.id,
            'email': self.email,
            'username': self.username,
            'password': self.password
        }


class Character(db.Model):
    id = db.Column(db.Integer, primary_key=True)

    level = db.Column(db.Integer, unique=False, nullable=False)

    charisma = db.Column(db.Integer, unique=False, nullable=False)
    intelligence = db.Column(db.Integer, unique=False, nullable=False)
    agility = db.Column(db.Integer, unique=False, nullable=False)
    strength = db.Column(db.Integer, unique=False, nullable=False)
    skillPoints = db.Column(db.Integer, unique=False, default=0)

    nickname = db.Column(db.String(20), unique=False, nullable=False)
    sex = db.Column(db.Integer, unique=False, nullable=False)  # 0 - Man, 1 - Woman
    characterClass = db.Column(db.Integer, unique=False, nullable=False)  # bard, warrior, thief, wizard
    hair = db.Column(db.Integer, unique=False, nullable=False)  # 0 - blond, 1 - brown, 2 - black
    hat = db.Column(db.Integer, unique=False, nullable=False)  # some hats
    eyeColor = db.Column(db.Integer, unique=False, nullable=False)  # 0 - blue, 1 - brown, 2 - green
    blouse = db.Column(db.Integer, unique=False, nullable=False)  # some shirts
    pants = db.Column(db.Integer, unique=False, nullable=False)  # some pants
    shoes = db.Column(db.Integer, unique=False, nullable=False)  # some shoes
    # foreign keys
    userId = db.Column(db.Integer, db.ForeignKey('user.id'), nullable=False)
    # relations
    lastLoggedInCalendar = db.relationship('LastLoggedIn', backref='character_id', lazy=True)

    def __repr__(self):
        return f"Character('{self.id}', '{self.nickname}', '{self.level}', '{self.characterClass}')"

    def to_dict(self):
        return {
            'id': self.id,
            'level': self.level,
            'charisma': self.charisma,
            'intelligence': self.intelligence,
            'agility': self.agility,
            'strength': self.strength,
            'nickname': self.nickname,
            'sex': self.sex,
            'characterClass': self.characterClass,
            'hair': self.hair,
            'hat': self.hat,
            'eyeColor': self.eyeColor,
            'blouse': self.blouse,
            'pants': self.pants,
            'shoes': self.shoes,
            'userId': self.userId,
            'skillPoints': self.skillPoints
        }

    def to_statistic_dict(self):
        user = User.query.get(self.userId)
        return {
            'id': self.id,
            'level': self.level,
            'charisma': self.charisma,
            'intelligence': self.intelligence,
            'agility': self.agility,
            'strength': self.strength,
            'nickname': self.nickname,
            'characterClass': self.characterClass,
            'userId': self.userId,
            'skillPoints': user.skillPoints
        }


class Treasure(db.Model):
    id = db.Column(db.Integer, primary_key=True)

    name = db.Column(db.String(100), unique=False, nullable=False)
    description = db.Column(db.String(200), unique=False, nullable=False)

    charisma = db.Column(db.Integer, unique=False, nullable=False)
    intelligence = db.Column(db.Integer, unique=False, nullable=False)
    agility = db.Column(db.Integer, unique=False, nullable=False)
    strength = db.Column(db.Integer, unique=False, nullable=False)
    skillPoints = db.Column(db.Integer, unique=False, nullable=False)

    def __repr__(self):
        return f"Treasure('{self.id}', '{self.name}', '{self.description}')"

    def to_dict(self):
        return {
            'id': self.id,
            'name': self.name,
            'description': self.description,
            'charisma': self.charisma,
            'intelligence': self.intelligence,
            'agility': self.agility,
            'strength': self.strength,
            'skillPoints': self.skillPoints
        }


class Enemy(db.Model):
    id = db.Column(db.Integer, primary_key=True)

    name = db.Column(db.String(100), unique=False, nullable=False)
    description = db.Column(db.String(200), unique=False, nullable=False)

    charisma = db.Column(db.Integer, unique=False, nullable=False)
    intelligence = db.Column(db.Integer, unique=False, nullable=False)
    agility = db.Column(db.Integer, unique=False, nullable=False)
    strength = db.Column(db.Integer, unique=False, nullable=False)

    def __repr__(self):
        return f"Enemy('{self.id}', '{self.name}', '{self.description}')"

    def to_dict(self):
        return {
            'id': self.id,
            'name': self.name,
            'description': self.description,
            'charisma': self.charisma,
            'intelligence': self.intelligence,
            'agility': self.agility,
            'strength': self.strength
        }


class GameplayEnemiesAchievements(db.Model):
    id = db.Column(db.Integer, primary_key=True)

    objectPositionX = db.Column(db.String(100), unique=False, nullable=False)
    objectPositionY = db.Column(db.String(200), unique=False, nullable=False)
    # foreign keys
    enemyId = db.Column(db.Integer, db.ForeignKey('enemy.id'), nullable=True)
    gameplayId = db.Column(db.Integer, db.ForeignKey('gameplay.id'), nullable=False)
    defeatedByCharacterId = db.Column(db.Integer, db.ForeignKey('character.id'), default=0, nullable=True)

    def __repr__(self):
        return f"GameplayEnemiesAchievements('{self.id}', '{self.enemyId}', '{self.gameplayId}', '{self.defeatedByCharacterId}')"

    def to_dict(self):
        return {
            'id': self.id,
            'objectPositionX': self.objectPositionX,
            'objectPositionY': self.objectPositionY,
            'enemyId': self.enemyId,
            'gameplayId': self.gameplayId,
            'defeatedByCharacterId': self.defeatedByCharacterId
        }


class GameplayTreasuresAchievements(db.Model):
    id = db.Column(db.Integer, primary_key=True)

    objectPositionX = db.Column(db.String(100), unique=False, nullable=False)
    objectPositionY = db.Column(db.String(200), unique=False, nullable=False)
    # foreign keys
    treasureId = db.Column(db.Integer, db.ForeignKey('treasure.id'), nullable=True)
    gameplayId = db.Column(db.Integer, db.ForeignKey('gameplay.id'), nullable=False)
    obtainedByCharacterId = db.Column(db.Integer, db.ForeignKey('character.id'), default=0, nullable=True)

    def __repr__(self):
        return f"GameplayTreasuresAchievements('{self.id}', '{self.treasureId}', '{self.gameplayId}', '{self.obtainedByCharacterId}')"

    def to_dict(self):
        return {
            'id': self.id,
            'objectPositionX': self.objectPositionX,
            'objectPositionY': self.objectPositionY,
            'treasureId': self.treasureId,
            'gameplayId': self.gameplayId,
            'obtainedByCharacterId': self.obtainedByCharacterId
        }


class Gameplay(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    # foreign keys
    player1id = db.Column(db.Integer, db.ForeignKey('user.id'), nullable=False)
    player2id = db.Column(db.Integer, db.ForeignKey('user.id'), nullable=False)

    def __repr__(self):
        return f"Gameplay('{self.id}', '{self.player1id}', '{self.player2id}')"

    def to_dict(self):
        return {
            'id': self.id,
            'player1id': self.player1id,
            'player2id': self.player2id
        }


class UserRelationship(db.Model):
    id = db.Column(db.Integer, primary_key=True)

    user1Id = db.Column(db.Integer, db.ForeignKey('user.id'), nullable=False)
    user2Id = db.Column(db.Integer, db.ForeignKey('user.id'), nullable=False)
    canDelete = db.Column(db.Boolean, nullable=False, default=False)
    canInvite = db.Column(db.Boolean, nullable=False, default=True)
    canAccept = db.Column(db.Boolean, nullable=False, default=False)
    canReject = db.Column(db.Boolean, nullable=False, default=False)
    canUninvite = db.Column(db.Boolean, nullable=False, default=False)

    def __repr__(self):
        return f"UserRelationship('{self.user1Id}', '{self.user2Id}')"

    def to_dict(self):
        return {
            'user1Id': self.user1Id,
            'user2Id': self.user2Id,
            'canDelete': self.canDelete,
            'canInvite': self.canInvite,
            'canAccept': self.canAccept,
            'canReject': self.canReject,
            'canUninvite': self.canUninvite
        }


class LastLoggedIn(db.Model):
    id = db.Column(db.Integer, primary_key=True)

    lastLoginDate = db.Column(db.DateTime, nullable=False, default=datetime.utcnow)
    # foreign keys
    userId = db.Column(db.Integer, db.ForeignKey('user.id'), nullable=False)
    characterId = db.Column(db.Integer, db.ForeignKey('character.id'), nullable=False)

    def __repr__(self):
        return f"LastLoggedIn('{self.id}', '{self.userId}', '{self.characterId}', '{self.lastLoginDate}')"

    def to_dict(self):
        return {
            'id': self.id,
            'lastLoginDate': self.lastLoginDate,
            'userId': self.userId,
            'characterId': self.characterId
        }


class LastPrize(db.Model):
    id = db.Column(db.Integer, primary_key=True)

    lastDate = db.Column(db.Date, nullable=False, default=date.today())
    # foreign keys
    userId = db.Column(db.Integer, db.ForeignKey('user.id'), nullable=False)

    def __repr__(self):
        return f"LastPrize('{self.id}', '{self.userId}', '{self.lastDate}')"

    def to_dict(self):
        return {
            'id': self.id,
            'lastDate': self.lastDate,
            'userId': self.userId
        }