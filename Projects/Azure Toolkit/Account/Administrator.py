import base64
from Account.SQLHelper import SQLHelper
from Account.User import User
from Account import AccountHandler


# Views the password of a given user
def viewPassword(username: str):
    AccountHandler.getPassword(username)


# Still in development
class Administrator(User):
    def __init__(self, username):
        super().__init__(username)
        username = input("Which user's password would you like to view?\n")
        password_result = SQLHelper().getUserPassword(username)
        password_bytes = password_result[0][0]
        password_bytes = base64.b64decode(password_bytes)
        password = password_bytes.decode('utf-8')
        print(password)
