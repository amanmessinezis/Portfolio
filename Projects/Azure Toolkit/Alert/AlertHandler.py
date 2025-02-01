from datetime import datetime, timedelta
from tabulate import tabulate
from Alert.SQLHelper import SQLHelper
from isEmpty import isEmpty


# Checks if a number input is valid based on the maximum value it can take
def isInvalidNumberInput(selection: str, max_property: int):
    if selection.isnumeric():
        if (int(selection) > 0) and (int(selection) <= max_property):
            return False
    else:
        return True


# Tabulates alerts for user dashboard
def tabulateAlerts(username: str):
    db_result = SQLHelper().getAllAlerts(username)
    col_names = ["Alert Name", "Query", "End Date", "Recurrence", "Skip"]
    data = []
    for i in db_result:
        alert_id = i[0]
        queries = []
        query_result = SQLHelper().getAlertQueries(alert_id)
        for j in query_result:
            queries.append(j[0])
        queries_text: str = ""
        for j in queries:
            queries_text = queries_text + j + "\n"
        alert_name = i[6]
        end_date = i[4]
        recurrence = i[2]
        skip = i[3]
        current_alert_row = [alert_name, queries_text, end_date, recurrence, skip]
        data.append(current_alert_row)
    alert_table = tabulate(data, headers=col_names, tablefmt="fancy_grid")
    return alert_table


# Creation of a new alert
def newAlert(username: str):
    print("Please wait")
    allQueries = SQLHelper().getAllQueries(username)
    number = 1
    for i in allQueries:
        print(str(number) + ". " + i[3])
        number += 1
    selection = input("Which query would you like to have an alert setup for?\n")
    while (isEmpty(selection)) or (isInvalidNumberInput(selection, number)):
        selection = input()
    selection = int(selection)
    query = allQueries[selection - 1]
    query_id = query[0]
    frequency = ""
    nextEmail = None
    print("1. Weekly")
    print("2. Monthly")
    frequencySelection = input("How often would you like to be notified?\n")
    while (isEmpty(frequencySelection)) or isInvalidNumberInput(frequencySelection, 2):
        frequencySelection = input()
    if frequencySelection == "1":
        frequency = "Weekly"
        nextEmail = datetime.today()
        nextEmail = str(nextEmail + timedelta(days=7))
    elif frequencySelection == "2":
        frequency = "Monthly"
        nextEmail = datetime.today()
        nextEmail = str(nextEmail + timedelta(days=30))

    while True:
        alert_name = input("Alert Name: ")
        while isEmpty(alert_name):
            alert_name = input()
        result = SQLHelper().findAlert(username, alert_name)
        if len(result) > 0:
            print("An alert with this name already exists")
        else:
            break
    SQLHelper().addAlert(username, frequency, nextEmail, alert_name)
    SQLHelper().addQueryAlertRecord(query_id, alert_name)


# Still in development
class AlertHandler:

    def deleteAlert(self, username):
        pass

    def editAlert(self, username):
        pass
