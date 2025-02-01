from Alert import AlertHandler
from Account.User import User
from QueryPackage import QueryHandler
from isEmpty import isEmpty


# Checks if a number input is valid based on the maximum value it can take
def isInvalidNumberInput(selection: str, max_property: int):
    if selection.isnumeric():
        if (int(selection) > 0) and (int(selection) <= max_property):
            return False
    else:
        return True


# Some methods still under development
class Employee(User):

    # Creates a new query
    def newQuery(self):
        QueryHandler.newQuery(self.username)
        Employee(self.username)

    def saveQuery(self) -> str:
        pass

    def openQuery(self) -> None:
        pass

    def editQuery(self) -> None:
        pass

    def deleteQuery(self) -> None:
        pass

    # Runs a query that's already been saved
    def runQuery(self) -> None:
        QueryHandler.runFromSaved(self.username)
        Employee(self.username)

    # Creates a new alert
    def newAlert(self) -> None:
        AlertHandler.newAlert(self.username)
        Employee(self.username)

    def __init__(self, username: str):
        super().__init__(username)
        print("What would you like to do?")
        print("1. Create a query")
        print("2. Run query")
        print("3. Setup Alert")
        print("4. Log out")
        number = input()
        while (isEmpty(number)) or (isInvalidNumberInput(number, 4)):
            number = input("Valid input required: ")
        if number == "1":
            self.newQuery()
        elif number == "2":
            self.runQuery()
        elif number == "3":
            self.newAlert()
        elif number == "4":
            self.logout()
