from Connection.SQLHelper import SQLHelper as parentSQL


class SQLHelper(parentSQL):

    # Get all queries from database of a particular user
    def getAllQueries(self, username):
        self.database.cursor.execute('SELECT * FROM Queries WHERE Username = ?', [username])
        db_result = self.database.cursor.fetchall()
        return db_result

    # Get all resources used for a query
    def getResourcesFromQuery(self, queryID):
        self.database.cursor.execute('SELECT ResourceName FROM Resources '
                                     'INNER JOIN QueryResource QR on Resources.ResourceID = QR.ResourceID '
                                     'INNER JOIN Queries Q on Q."Query ID" = QR.QueryID '
                                     'WHERE "QueryID" = ?', [queryID])
        db_result = self.database.cursor.fetchall()
        return db_result

    # Get all alerts
    def getQueryAlerts(self, queryID):
        self.database.cursor.execute('SELECT Alerts.AlertName FROM Alerts '
                                     'INNER JOIN QueryAlerts QA on Alerts."Alert ID" = QA.AlertID '
                                     'INNER JOIN Queries Q on QA.QueryID = Q."Query ID" '
                                     'WHERE "Query ID" = ?', [queryID])
        db_result = self.database.cursor.fetchall()
        return db_result

    # Finds a query made by a particular user
    def findQuery(self, username, query_name):
        self.database.cursor.execute('SELECT QueryName FROM Queries WHERE Username = ? AND QueryName = ?', (username,
                                                                                                            query_name))
        db_result = self.database.cursor.fetchall()
        return db_result

    # Find a resource
    def findResource(self, resourceName):
        self.database.cursor.execute('SELECT ResourceName FROM Resources WHERE ResourceName = ?', [resourceName])
        db_result = self.database.cursor.fetchall()
        return db_result

    # Add a resource to the database
    def addResource(self, resourceName):
        self.database.cursor.execute('INSERT INTO Resources (ResourceName) VALUES (?)', [resourceName])
        self.database.connect.commit()
        self.database.cursor.close()

    # Add a query to the database
    def addQuery(self, username, query_name, dateFrom, dateTo, granularity, fromDay, fromMonth, fromYear, toDay,
                 toMonth, toYear):
        self.database.cursor.execute(
            'INSERT INTO Queries (Username,QueryName,DateFrom,DateTo,'
            'Granularity,FromDay,FromMonth,FromYear,ToDay,ToMonth,ToYear) '
            'VALUES (?,?,?,?,?,?,?,?,?,?,?)',
            (username, query_name, dateFrom, dateTo, granularity, fromDay, fromMonth, fromYear, toDay, toMonth, toYear))
        self.database.connect.commit()
        self.database.cursor.close()

    # Add a record that shows the relationship between a resource and a query
    def addResourceQueryRecord(self, resource_name, query_name):
        self.database.cursor.execute('SELECT ResourceID FROM Resources WHERE ResourceName = ?',
                                     [resource_name])
        resourceID = self.database.cursor.fetchall()[0][0]
        self.database.cursor.execute('SELECT "Query ID" FROM Queries WHERE QueryName = ?',
                                     [query_name])
        queryID = self.database.cursor.fetchall()[0][0]
        self.database.cursor.execute('INSERT INTO QueryResource VALUES (?,?)', (queryID, resourceID))
        self.database.connect.commit()
        self.database.cursor.close()