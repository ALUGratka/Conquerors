from conquerors import db
from conquerors.models import User

def create_user():
    user = User(username='a', email='b', password='c', birthDate='d')
    print(user)
    db.session.add(user)
    db.session.commit()


def query_users():
    print(User.query.all())


if __name__ == '__main__':
    query_users()