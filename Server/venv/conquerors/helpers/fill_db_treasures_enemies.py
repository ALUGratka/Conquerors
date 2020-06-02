from conquerors.models import Enemy, Treasure


def fill_database_with_treasures(db):
    treasures = []
    treasures.append(Treasure(name='Mistyczna bransoleta', description='Z pozoru niewinna błyskotka,'+
    ' jednak bije z niej intensywna magiczna aura.',
     charisma=0, intelligence=0, agility=0, strength=0, skillPoints=4))
    treasures.append(Treasure(name='Lutnia przodków', description='Stary instrument.'+
    ' Pociągając za struny wybrzmiewa hipnotyzująca melodia.',
     charisma=3, intelligence=2, agility=0, strength=0, skillPoints=0))
    treasures.append(Treasure(name='Miecz przeznaczenia', description='Posiada dwa ostrza.',
     charisma=0, intelligence=0, agility=4, strength=5, skillPoints=0))
    treasures.append(Treasure(name='Księga wikingów', description='Pradawna księga,'+
    ' spisane w niej są sekrety najsilniejszych wikingów.',
     charisma=0, intelligence=2, agility=0, strength=5, skillPoints=0))
    treasures.append(Treasure(name='Medalion pradawnych elfów', description='Mały, lekki,'+
    ' jednak nosząc go czujesz że twoje nogi mogą cię doprowadzić gdziekolwiek zechcesz.',
     charisma=0, intelligence=0, agility=5, strength=0, skillPoints=0))
    treasures.append(Treasure(name='Broszka z ważką', description='Nosząc ją, nabywasz umiejętności dyplomatycznych.',
     charisma=6, intelligence=0, agility=0, strength=0, skillPoints=0))
    treasures.append(Treasure(name='Kamień', description='Mówi się, że ludzcy przodkowie rzucali nim w dinozaury.',
     charisma=0, intelligence=0, agility=0, strength=0, skillPoints=1))
    treasures.append(Treasure(name='Stokrotka', description='Włożona za ucho dodaje ci uroku.',
     charisma=2, intelligence=0, agility=0, strength=0, skillPoints=0))
    treasures.append(Treasure(name='Tarcza woja', description='Chroni cię przed mocnymi atakami.',
     charisma=0, intelligence=0, agility=0, strength=3, skillPoints=1))
    treasures.append(Treasure(name='Księgowidło', description='Księga bohaterów, która rozwija wybrane umiejętności.',
     charisma=0, intelligence=0, agility=0, strength=0, skillPoints=3))
    treasures.append(Treasure(name='Worek na ziemniaki', description='Ziemniaki bogate w witaminy, rozwija wybrane umiejętności.',
     charisma=0, intelligence=0, agility=0, strength=0, skillPoints=3))
    treasures.append(Treasure(name='Trąbka Eustachiusza', description='Magiczna trąbka, każdy jest oczarowny jej dźwiękiem.',
     charisma=5, intelligence=0, agility=0, strength=0, skillPoints=0))
    treasures.append(Treasure(name='Sterydy anaboliczne', description='Twoja postać po zażyciu ich czuje zwierzęcą siłę.',
     charisma=0, intelligence=0, agility=0, strength=5, skillPoints=0))
    treasures.append(Treasure(name='Fontanna umiejętności', description='Łyk wody z fontanny napełnia cię doświadczeniem.',
     charisma=2, intelligence=0, agility=0, strength=0, skillPoints=3))
    treasures.append(Treasure(name='Smocza kula', description='Spełni jedno z twoich życzeń.',
     charisma=2, intelligence=0, agility=0, strength=0, skillPoints=1))

    db.session.add_all(treasures)
    db.session.commit()


def fill_database_with_enemies(db):
    enemies = []
    enemies.append(Enemy(name='Stary drwal', description='Często spotykany w lasach,'+
    ' jego toporek choć niepozorny może zadać duże obrażenia.',
    charisma=2, intelligence=1, agility=5, strength=30))
    enemies.append(Enemy(name='Złoty smok', description='Gatunek uważany za wymarły,'+
    ' jednak staje się realny w momencie gdy zieje struminiem ognia w twoją stronę.',
     charisma=0, intelligence=10, agility=10, strength=30))
    enemies.append(Enemy(name='Krasnoludzki zielarz', description=
    'Bardzo sie denerwuje gdy stąpasz mu po grządkach.',
     charisma=20, intelligence=10, agility=2, strength=2))
    enemies.append(Enemy(name='Samotny czarodziej', description='Po 30 latach bez miłości'+
    ' zyskał niespotykane moce, poznając arkana magii.',
     charisma=2, intelligence=25, agility=2, strength=0))
    enemies.append(Enemy(name='Zielony ogr', description='Potężny stwór jest zielony,'+
    ' cały zielony...',
     charisma=0, intelligence=0, agility=0, strength=20))
    enemies.append(Enemy(name='Chochlik', description='Mały stworek, słodki lecz potrafi gryźć po stopach.',
     charisma=3, intelligence=0, agility=5, strength=0))
    enemies.append(Enemy(name='Przystojny Karol', description='Przed starciem z nim zmów paciorek.',
     charisma=10, intelligence=0, agility=5, strength=6))
    enemies.append(Enemy(name='Vectra Włodka', description='Jej metalowa zbroja pokryta'+
    ' jest rdzą, przez co jej ruchy są ograniczone.',
     charisma=5, intelligence=6, agility=1, strength=2))
    enemies.append(Enemy(name='Witold', description='Duch zmarłego szlachica, '+
    'lubi piękne dziewki i szalone biesiady.',
     charisma=15, intelligence=4, agility=4, strength=6))
    enemies.append(Enemy(name='Twoja mama', description='Wściekła bo nie pozmywałeś po obiedzie.',
     charisma=5, intelligence=6, agility=3, strength=3))
    enemies.append(Enemy(name='Lodowy gigant', description='Jest tak wielki, że jego głowa sięga koron drzew.'+
    ' Wydaje się zagubiony.',
     charisma=1, intelligence=1, agility=3, strength=8))
    enemies.append(Enemy(name='Dziki wilk', description='Niesamowicie szybki, trudno go zaskoczyć.',
     charisma=0, intelligence=0, agility=5, strength=1))
    enemies.append(Enemy(name='Szerszeń', description='Najbardziej przerażający ze wszystkich owadów.',
     charisma=0, intelligence=0, agility=8, strength=1))
    enemies.append(Enemy(name='Zakapurzony złodziejaszek', description='Uważaj, możesz łatwo stracić swoją sakwę.',
     charisma=2, intelligence=4, agility=8, strength=0))
    enemies.append(Enemy(name='Niewidomy mędrzec', description='Ma gadane.',
     charisma=7, intelligence=8, agility=0, strength=1))

    db.session.add_all(enemies)
    db.session.commit()
