from conquerors.models import Enemy, Treasure


def fill_database_with_treasures(db):
    treasures = []
    treasures.append(Treasure(name='Mistyczna bransoleta', description='Z pozoru niewinna blyskotka,'+
    ' jednak bije z niej intensywna magiczna aura.',
     charisma=0, intelligence=0, agility=0, strength=0, skillPoints=4))
    treasures.append(Treasure(name='Lutnia przodkow', description='Stary instrument.'+
    ' Pociagajac za struny wybrzmiewa hipnotyzujaca melodia.',
     charisma=3, intelligence=2, agility=0, strength=-1, skillPoints=0))
    treasures.append(Treasure(name='Miecz przeznaczenia', description='Posiada dwa ostrza.',
     charisma=-2, intelligence=0, agility=4, strength=5, skillPoints=0))
    treasures.append(Treasure(name='Ksiega wikingow', description='Pradawna ksiega,'+
    ' spisane w niej sa sekrety najsilniejszych wikingow.',
     charisma=0, intelligence=2, agility=-3, strength=5, skillPoints=0))
    treasures.append(Treasure(name='Medalion pradawnych elfow', description='Maly, lekki,'+
    ' jednak noszac go czujesz ze twoje nogi moga cie doprowadzic gdziekolwiek zechcesz.',
     charisma=-1, intelligence=0, agility=5, strength=-1, skillPoints=0))
    treasures.append(Treasure(name='Broszka z wazka', description='Noszac ja, nabywasz umiejetnosci dyplomatycznych.',
     charisma=6, intelligence=0, agility=-2, strength=-2, skillPoints=0))
    treasures.append(Treasure(name='Kamien', description='Mowi sie, ze ludzcy przodkowie rzucali nim w dinozaury.',
     charisma=0, intelligence=0, agility=0, strength=0, skillPoints=1))
    treasures.append(Treasure(name='Stokrotka', description='Wlozona za ucho dodaje ci uroku.',
     charisma=2, intelligence=0, agility=0, strength=0, skillPoints=0))
    treasures.append(Treasure(name='Tarcza woja', description='Chroni cie przed mocnymi atakami.',
     charisma=-1, intelligence=0, agility=0, strength=3, skillPoints=1))
    treasures.append(Treasure(name='Ksiegowidlo', description='Ksiega bohaterow, ktora rozwija wybrane umiejetnosci.',
     charisma=-2, intelligence=0, agility=0, strength=0, skillPoints=3))
    treasures.append(Treasure(name='Worek na ziemniaki', description='Ziemniaki bogate w witaminy, rozwija wybrane umiejetnosci.',
     charisma=-1, intelligence=0, agility=0, strength=0, skillPoints=3))
    treasures.append(Treasure(name='Trabka Eustachiusza', description='Magiczna trabka, kazdy jest oczarowny jej dzwiekiem.',
     charisma=5, intelligence=0, agility=0, strength=-2, skillPoints=0))
    treasures.append(Treasure(name='Sterydy anaboliczne', description='Twoja postac po zazyciu ich czuje zwierzeca sile.',
     charisma=0, intelligence=-4, agility=0, strength=5, skillPoints=0))
    treasures.append(Treasure(name='Fontanna umiejetnosci', description='lyk wody z fontanny napelnia cie doswiadczeniem.',
     charisma=2, intelligence=0, agility=0, strength=0, skillPoints=3))
    treasures.append(Treasure(name='Smocza kula', description='Spelni jedno z twoich zyczen.',
     charisma=2, intelligence=0, agility=0, strength=0, skillPoints=1))

    db.session.add_all(treasures)
    db.session.commit()


def fill_database_with_enemies(db):
    enemies = []
    enemies.append(Enemy(name='Stary drwal', description='Czesto spotykany w lasach,'+
    ' jego toporek choc niepozorny moze zadac duze obrazenia.',
    charisma=2, intelligence=1, agility=5, strength=30))
    enemies.append(Enemy(name='Piekielny smok', description='Gatunek uwazany za wymarly,'+
    ' jednak staje sie realny w momencie gdy zieje struminiem ognia w twoja strone.',
     charisma=0, intelligence=10, agility=10, strength=30))
    enemies.append(Enemy(name='Krasnoludzki zielarz', description=
    'Bardzo sie denerwuje gdy stapasz mu po grzadkach.',
     charisma=20, intelligence=10, agility=2, strength=2))
    enemies.append(Enemy(name='Samotny czarodziej', description='Po 30 latach bez milosci'+
    ' zyskal niespotykane moce, poznajac arkana magii.',
     charisma=2, intelligence=25, agility=2, strength=0))
    enemies.append(Enemy(name='Zielony ogr', description='Potezny stwor jest zielony,'+
    ' caly zielony...',
     charisma=0, intelligence=0, agility=0, strength=20))
    enemies.append(Enemy(name='Chochlik', description='Maly stworek, slodki lecz potrafi gryzc po stopach.',
     charisma=3, intelligence=0, agility=5, strength=0))
    enemies.append(Enemy(name='Przystojny Karol', description='Przed starciem z nim zmow paciorek.',
     charisma=10, intelligence=0, agility=5, strength=6))
    enemies.append(Enemy(name='Vectrawlodka', description='Jej zbroja pokryta'+
    ' jest rdza, przez co jej ruchy sa ograniczone.',
     charisma=5, intelligence=6, agility=1, strength=2))
    enemies.append(Enemy(name='Witold', description='Duch zmarlego szlachica, '+
    'lubi piekne dziewki i szalone biesiady.',
     charisma=15, intelligence=4, agility=4, strength=6))
    enemies.append(Enemy(name='Twoja mama', description='Wsciekla bo nie pozmywales po obiedzie.',
     charisma=5, intelligence=6, agility=3, strength=3))
    enemies.append(Enemy(name='Lodowy gigant', description='Jest tak wielki, ze jego glowa siega koron drzew.'+
    ' Wydaje sie zagubiony.',
     charisma=1, intelligence=1, agility=3, strength=8))
    enemies.append(Enemy(name='Zmutowany wilk', description='Niesamowicie szybki, trudno go zaskoczyc.',
     charisma=0, intelligence=0, agility=5, strength=1))
    enemies.append(Enemy(name='Szerszeni', description='Najbardziej przerazajacy ze wszystkich wojownikow owadow.',
     charisma=0, intelligence=0, agility=8, strength=1))
    enemies.append(Enemy(name='Zakapurzony zlodziejaszek', description='Uwazaj, mozesz latwo stracic swoja sakwe.',
     charisma=2, intelligence=4, agility=8, strength=0))
    enemies.append(Enemy(name='Niewidomy medrzec', description='Ma gadane.',
     charisma=7, intelligence=8, agility=0, strength=1))

    db.session.add_all(enemies)
    db.session.commit()
