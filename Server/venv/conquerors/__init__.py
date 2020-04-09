from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from os import urandom
from flask_bcrypt import Bcrypt


app = Flask(__name__)
app.config['SECRET_KEY'] = urandom(24).hex()
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///site.db'

db = SQLAlchemy(app)
bcrypt = Bcrypt(app)

from conquerors import routes
