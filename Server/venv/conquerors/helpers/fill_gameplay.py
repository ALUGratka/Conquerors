from conquerors.models import Gameplay


def fill_gameplay(db):
    gameplays = []
    gameplays.append(Gameplay(player1id=15, player2id=2, character1id=1, character2id=2,
                              turn=5, round=5, player1PositionX=2, player1PositionY=3, player2PositionX=4,
                              player2PositionY=3, canPlay1=True, canPlay2=False, canAccept1=True, canAccept2=False))

    db.session.add_all(gameplays)
    db.session.commit()
