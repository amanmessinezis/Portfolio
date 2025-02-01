import base64
import regex as re
import requests
from Account.SQLHelper import SQLHelper


# Checks if email inputted by user is already being used by another account
def emailInUse(emailAddress):
    result = SQLHelper().emailExists(emailAddress)
    if len(result) != 0:
        return True


# Checks if username is already being used by another user
def usernameTaken(username):
    result = SQLHelper().usernameExists(username)
    if len(result) != 0:
        return True


# Adds user details to database
def pushNewDetails(username, fName, lName, emailAddress, password: str):
    utf_8_password = str.encode(password)
    encrypted_password = base64.b64encode(utf_8_password)
    SQLHelper().newAccount(username, fName, lName, emailAddress, encrypted_password)


# Checks if username and password maps with existing account
def correctAccountDetails(username, inputted_password: str):
    user_password_details = SQLHelper().getUserPassword(username)
    if len(user_password_details) == 0:
        return False
    actual_password = user_password_details[0][0]
    decrypted_password = base64.b64decode(actual_password).decode()
    if decrypted_password == inputted_password:
        return True


# Checks if email address is valid
def validateEmailAddress(emailAddress):
    api_key = "ad9c409d-63f9-42e6-a55b-af76e365fcd5"
    response = requests.get(
        "https://isitarealemail.com/api/email/validate",
        params={'email': emailAddress},
        headers={'Authorization': "Bearer " + api_key})
    status = response.json()['status']
    if status == "valid":
        return True
    elif status == "invalid":
        return False
    else:
        return "Unresponsive"


# Checks if username follows the syntax
def username_syntax(username):
    syntax_error = not re.search("^[a-zA-Z0-9]{4,10}$", username) is None
    return syntax_error


def password_check(password) -> dict:
    # calculating the length
    length_error = (len(password) < 8) or (len(password) > 64)

    # searching for digits
    digit_error = re.search(r"\d", password) is None

    # searching for uppercase
    uppercase_error = re.search(r"[A-Z]", password) is None

    # searching for lowercase
    lowercase_error = re.search(r"[a-z]", password) is None

    # overall result
    password_ok = not (length_error or digit_error or uppercase_error or lowercase_error)

    return {
        'password_ok': password_ok,
        'length_error': length_error,
        'digit_error': digit_error,
        'uppercase_error': uppercase_error,
        'lowercase_error': lowercase_error,
    }


# Decrypts the password of a given user
def getPassword(username: str):
    user_password_details = SQLHelper().getUserPassword(username)
    if len(user_password_details) == 0:
        return False
    actual_password = user_password_details[0][0]
    decrypted_password = base64.b64decode(actual_password).decode()
    return decrypted_password


# Checks the account type of a user
def accountType(username: str):
    account_type = SQLHelper().userType(username)
    return account_type[0][5]