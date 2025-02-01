import sqlite3


class I_DBConnection:

    # Connects to the database
    def __init__(self):
        self.connect = sqlite3.connect('Azure Toolkit DB Connectivity/Database.db')
        self.cursor = self.connect.cursor()

    # Disconnects from database
    def disconnect(self):
        self.connect.close()
