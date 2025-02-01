from Alert import AlertHandler
from QueryPackage import QueryHandler


class User:
    def logout(self) -> None:
        self.loggedIn = False
        return

    # Setups up the dashboard that displays the queries and alerts made by the user
    def __init__(self, username: str):
        self.username: str = username
        self.queriesSaved = QueryHandler.tabulateQueries(username)
        print(self.queriesSaved)
        self.alertsSaved = AlertHandler.tabulateAlerts(username)
        print(self.alertsSaved)
        self.loggedIn = True
        self.accountType: str = "User"
