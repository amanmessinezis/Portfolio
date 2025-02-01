from Connection.SQLHelper import SQLHelper as parentSQL


class SQLHelper(parentSQL):

    # Returns records for all alerts of a username from the Alerts table
    def getAllAlerts(self, username):
        self.database.cursor.execute('SELECT * FROM Alerts WHERE Username = ?', [username])
        db_result = self.database.cursor.fetchall()
        return db_result

    # Gets queries of an alert
    def getAlertQueries(self, alertID):
        self.database.cursor.execute('SELECT QueryName FROM Queries '
                                     'INNER JOIN QueryAlerts QA on Queries."Query ID" = QA.QueryID '
                                     'INNER JOIN Alerts A on A."Alert ID" = QA.AlertID '
                                     'WHERE "Alert ID" = ?', [alertID])
        db_result = self.database.cursor.fetchall()
        return db_result

    # Adds an alert to the database
    def addAlert(self, username, recurrence, next_email, alert_name):
        self.database.cursor.execute('INSERT INTO Alerts (Username, Recurrence, NextEmail, AlertName)'
                                     'VALUES (?,?,?,?)',
                                     (username, recurrence, next_email, alert_name))
        self.database.connect.commit()
        self.database.cursor.close()

    # Returns rows of all queries made by a particular user
    def getAllQueries(self, username):
        self.database.cursor.execute('SELECT * FROM Queries WHERE Username = ?', [username])
        db_result = self.database.cursor.fetchall()
        return db_result

    # Adds a new record that shows the link between a query and a new alert
    def addQueryAlertRecord(self, queryID, alert_name):
        print(queryID)
        self.database.cursor.execute('SELECT "Alert ID" FROM Alerts WHERE AlertName = ?',
                                     [alert_name])
        alertID = self.database.cursor.fetchall()[0][0]
        print(alertID)
        self.database.cursor.execute('INSERT INTO QueryAlerts VALUES (?,?)', (queryID, alertID))
        self.database.connect.commit()
        self.database.cursor.close()

    # Find alert made by a user
    def findAlert(self, username, alert_name):
        self.database.cursor.execute('SELECT AlertName FROM Alerts WHERE Username = ? AND AlertName = ?', (username,
                                                                                                           alert_name))
        db_result = self.database.cursor.fetchall()
        return db_result
