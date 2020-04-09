from conquerors import app

# rejestracja odp zwracac id uzytkownika i success
# logowanie - zwracac id uzytkownika i success
#  

@app.route("/")
def home():
    return "Hello, Flask!"
