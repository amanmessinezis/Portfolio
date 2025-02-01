from Account.Employee import Employee
from Account.Administrator import Administrator
from Account import AccountHandler
from isEmpty import isEmpty


# Boundary class. This is where the user interacts

# Create a user account for employees that haven't already created an account
def createAccount():
    fName = input("First name: ")
    while isEmpty(fName):
        fName = input("First name: ")
    lName = input("Last name: ")
    while isEmpty(lName):
        lName = input("Last name: ")
    while True:
        emailAddress = input("Email address: ")
        while isEmpty(emailAddress):
            emailAddress = input("Email address: ")
        realEmail = AccountHandler.validateEmailAddress(emailAddress)
        if realEmail == "Unresponsive":
            print("API to validate email address currently unresponsive. Please try again later")
            quit()
        elif not realEmail:
            print("Invalid email address")
        else:
            emailInUse = AccountHandler.emailInUse(emailAddress)
            if emailInUse:
                print("Email already in use")
            else:
                break
    while True:
        username = input("Pick a username: ")
        while isEmpty(username):
            username = input("Pick a username: ")
        username = username.lower()
        correct_username_syntax = AccountHandler.username_syntax(username)
        if not correct_username_syntax:
            print("Username must be between 4 to 10 characters and may only contains letters and numbers")
        else:
            usernameTaken = AccountHandler.usernameTaken(username)
            if usernameTaken:
                print("Username already exists")
            else:
                break
    while True:
        password = input("Set up a password: ")
        while isEmpty(password):
            password = input("Set up a password: ")
        password_validator = AccountHandler.password_check(password)
        if not password_validator.get('password_ok'):
            if password_validator.get('length_error'):
                print("Password needs to be between 8 and 64 characters")
            if password_validator.get('digit_error'):
                print("Password must have at least one digit")
            if password_validator.get('uppercase_error'):
                print("Password must have at least one uppercase character")
            if password_validator.get('lowercase_error'):
                print("Password must have at least one lowercase character")
        else:
            break
    repeatPassword = input("Repeat password: ")
    while isEmpty(repeatPassword):
        repeatPassword = input("Repeat password: ")
    while password != repeatPassword:
        print("Passwords do not match")
        repeatPassword = input("Repeat password: ")
    AccountHandler.pushNewDetails(username, fName, lName, emailAddress, password)
    print("Taking you to the login page...")
    print("------------------------------------------------------------")
    login()


def login():
    while True:
        username = input("Enter username: ")
        while isEmpty(username):
            username = input("Enter username: ")
        password = input("Enter password: ")
        while isEmpty(password):
            password = input("Enter password: ")
        valid = AccountHandler.correctAccountDetails(username, password)
        if not valid:
            print("Incorrect details")
        else:
            break
    account_type = AccountHandler.accountType(username)
    if account_type == "Employee":
        Employee(username)
    if account_type == "Administrator":
        Administrator(username)


class Terminal:

    def __init__(self):
        while True:
            print("1. Log In\n2. Create an account\n3. Close Application")
            option = input()
            while (option != '1') and (option != '2') and (option != '3'):
                print("1. Log In\n2. Create an account\n3. Close Application")
                option = input("Valid input required: ")
            if option == "1":
                login()
            elif option == "2":
                createAccount()
            elif option == "3":
                exit()


Terminal()

