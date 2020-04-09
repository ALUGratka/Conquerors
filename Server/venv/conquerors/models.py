from datetime import datetime
from conquerors import db


class User(db.Model):

    id = db.Column(db.Integer, primary_key=True)
    
    authenticated = db.Column(db.Boolean, default=False)
    email = db.Column(db.String(120), unique=True, nullable=False)
    username = db.Column(db.String(20), unique=True, nullable=False)
    password = db.Column(db.String(60), nullable=False)
    skillPoints = db.Column(db.Integer, unique=False, nullable=False)
    birthDate = db.Column(db.String(10), nullable=False) #day/month/year
    # relations
    characters = db.relationship('Character', backref='userId', lazy=True)
    lastLoggedInCalendar = db.relationship('LastLoggedIn', backref='userId', lazy=True)

    def __repr__(self):
        return f"User('{self.id}', '{self.username}')"

class Character(db.Model):

    id = db.Column(db.Integer, primary_key=True)

    level = db.Column(db.Integer, unique=False, nullable=False)
    nickname = db.Column(db.String(20), unique=False, nullable=False)
    sex = db.Column(db.Integer, unique=False, nullable=False) # 0 - Man, 1 - Woman
    characterClass = db.Column(db.Integer, unique=False, nullable=False) # bard, warrior, thief, wizard
    hair = db.Column(db.Integer, unique=False, nullable=False) # 0 - blond, 1 - brown, 2 - black
    eyeColor = db.Column(db.Integer, unique=False, nullable=False) # 0 - blue, 1 - brown, 2 - green
    blouse = db.Column(db.Integer, unique=False, nullable=False) # some shirts
    pants = db.Column(db.Integer, unique=False, nullable=False) # some pants
    shoes = db.Column(db.Integer, unique=False, nullable=False) # some shoes
    # foreign keys
    userId = db.Column(db.Integer, db.ForeignKey('user.id'), nullable=False)
    # relations
    lastLoggedInCalendar = db.relationship('LastLoggedIn', backref='characterId', lazy=True)

    def __repr__(self):
        return f"Character('{self.id}', '{self.nickname}', '{self.level}', '{self.characterClass}')"

class LastLoggedIn(db.Model):

    id = db.Column(db.Integer, unique=True, nullable=False)

    lastLogin = db.Column(db.DateTime, nullable=False, default = datetime.utcnow)
    # foreign keys
    userId =  db.Column(db.Integer, db.ForeignKey('user.id'), nullable=False)
    characterId = db.Column(db.Integer, db.ForeignKey('character.id'), nullable=False)
    
    def __repr__(self):
        return f"LastLoggedIn('{self.id}', '{self.userId}', '{self.characterId}', '{self.lastLogin}')"