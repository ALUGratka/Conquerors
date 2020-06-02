from conquerors import db
from conquerors.helpers.fill_db_treasures_enemies import fill_database_with_enemies, fill_database_with_treasures


if __name__=='__main__':
    db.create_all()
    fill_database_with_enemies(db)
    fill_database_with_treasures(db)
