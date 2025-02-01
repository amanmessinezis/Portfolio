from datetime import datetime, timezone, timedelta
from isEmpty import isEmpty
from azure.identity import AzureCliCredential
from azure.mgmt.costmanagement import CostManagementClient
from tabulate import tabulate
from QueryPackage.SQLHelper import SQLHelper


# Checks if a number input is valid based on the maximum value it can take
def isInvalidNumberInput(selection: str, max_property: int):
    if selection.isnumeric():
        if (int(selection) > 0) and (int(selection) <= max_property):
            return False
    else:
        return True


# Returns boolean value of whether a resource exists in a list
def inList(resource: str, resource_list: list):
    count = resource_list.count(resource)
    if count > 0:
        return True
    else:
        return False


# Split a date in DD/MM/YYYY format into three parts
# and strip placeholder values for day and month if they exist
def splitDate(date: str):
    splittedDate = date.split("/")
    day = splittedDate[0]
    month = splittedDate[1]
    year = splittedDate[2]
    if day[0] == "0":
        day = day[1]
    if month[0] == "0":
        month = month[1]
    return day, month, year


# Tabulate queries from database for dashboard
def tabulateQueries(username: str):
    db_result = SQLHelper().getAllQueries(username)
    col_names = ["Query Name", "From", "To", "Resource(s)", "Alerts"]
    data = []
    for i in db_result:
        query_id = i[0]
        clients = []
        client_result = SQLHelper().getResourcesFromQuery(query_id)
        for j in client_result:
            clients.append(j[0])
        clients_text: str = ""
        for j in clients:
            clients_text = clients_text + j + "\n"
        alerts = []
        alert_result = SQLHelper().getQueryAlerts(query_id)
        for j in alert_result:
            alerts.append(j[0])
        alerts_text: str = ""
        for j in alerts:
            alerts_text = alerts_text + j + "\n"
        query_name = i[3]
        query_date_from = i[1]
        query_date_to = i[2]
        current_query_row = [query_name, query_date_from, query_date_to, clients_text, alerts_text]
        data.append(current_query_row)
    alert_table = tabulate(data, headers=col_names, tablefmt="fancy_grid")
    return alert_table


# Get the start and end dates for resource cost detail information on Azure
def getStartAndEndDate(timeframe, resource):
    if (timeframe == "BillingMonthToDate") or (timeframe == "MonthToDate"):
        parameters = {
            "type": "Usage",
            "timeframe": "BillingMonthToDate",
            "dataset": {
                "granularity": "daily",
                "aggregation": {
                    "totalCost": {
                        "name": "Cost",
                        "function": "Sum"
                    }
                },
                "grouping": [
                    {
                        "type": "Dimension",
                        "name": "ResourceGroup"
                    }
                ]
            }
        }
        credentials = AzureCliCredential()
        query_client = CostManagementClient(credentials)
        start_date = str(query_client.query.usage(
            "subscriptions/4df8145b-40fe-44ed-b169-2c1e0a81f9c5/resourceGroups/"
            + resource, parameters).rows[0][1])
        s_day = start_date[6:8]
        s_month = start_date[4:6]
        s_year = start_date[0:4]
        start_date = s_day + "/" + s_month + "/" + s_year
        end_date = datetime.today()
        end_date = str(end_date - timedelta(days=1))
        e_day = end_date[8:10]
        e_month = end_date[5:7]
        e_year = end_date[0:4]
        end_date = e_day + "/" + e_month + "/" + e_year
        return start_date, end_date
    elif "TheLast" in timeframe:
        parameters = {
            "type": "Usage",
            "timeframe": "TheLastBillingMonth",
            "dataset": {
                "granularity": "daily",
                "aggregation": {
                    "totalCost": {
                        "name": "Cost",
                        "function": "Sum"
                    }
                },
                "grouping": [
                    {
                        "type": "Dimension",
                        "name": "ResourceGroup"
                    }
                ]
            }
        }
        credentials = AzureCliCredential()
        query_client = CostManagementClient(credentials)
        returned_list = query_client.query.usage(
            "subscriptions/4df8145b-40fe-44ed-b169-2c1e0a81f9c5/resourceGroups/"
            + resource, parameters)
        start_date = str(returned_list.rows[0][1])
        s_day = start_date[6:8]
        s_month = start_date[4:6]
        s_year = start_date[0:4]
        start_date = s_day + "/" + s_month + "/" + s_year
        end_date = returned_list.rows[-1][1]
        e_day = end_date[6:8]
        e_month = end_date[4:6]
        e_year = end_date[0:4]
        end_date = e_day + "/" + e_month + "/" + e_year
        return [start_date, end_date]


# Create a new query by connecting to the Azure API
def newQuery(username: str):
    print("Please wait...")
    resource_group_list = []
    credentials = AzureCliCredential()
    parameters = {
        "type": "Usage",
        "timeframe": "TheLastMonth",
        "dataset": {
            "grouping": [
                {
                    "type": "Dimension",
                    "name": "ResourceGroup"
                }
            ]
        }
    }
    query_client = CostManagementClient(credentials)
    returned_resource_group_list = query_client.query.usage("subscriptions/4df8145b-40fe-44ed-b169-2c1e0a81f9c5",
                                                            parameters)
    for i in returned_resource_group_list.rows:
        resource = i[0]
        if not inList(resource, resource_group_list):
            resource_group_list.append(resource)
    number = 1
    for i in resource_group_list:
        print(str(number) + ". " + i)
        number += 1
    selection = input("Which resource would you like cost information on?\n")
    while (isEmpty(selection)) or (isInvalidNumberInput(selection, number)):
        selection = input("Valid input required: ")
    selection = int(selection)
    resource = resource_group_list[selection - 1]
    print("1. Billing month to date")
    print("2. Month to date")
    print("3. Last billing month")
    print("4. Last month")
    print("5. Custom")
    timeframeSelection = input("Timeframe\n")
    while (isEmpty(timeframeSelection)) or (isInvalidNumberInput(timeframeSelection, 5)):
        timeframeSelection = input("Timeframe\n")
    timeframe = ""
    from_day = ""
    from_month = ""
    from_year = ""
    to_day = ""
    to_month = ""
    to_year = ""
    if timeframeSelection == "5":
        from_day = input("From day\nNo zeroes like 03\n")
        while isEmpty(from_day) or (int(from_day) <= 0) or (int(from_day) > 31):
            from_day = input("Valid input required: ")
        from_month = input("From month\nNo zeros like 03\n")
        while isEmpty(from_month) or (int(from_month) <= 0) or (int(from_month) > 12):
            from_month = input("Valid input required: ")
        from_year = input("From year\n")
        while isEmpty(from_year) or (int(from_year) <= 0):
            from_year = input("Valid input required: ")
        to_day = input("To day\nNo zeroes like 03\n")
        while isEmpty(to_day or (int(from_day) < 1) or (int(from_day) > 31)):
            to_day = input("Valid input required: ")
        to_month = input("To month\nNo zeros like 03\n")
        while isEmpty(to_month) or (int(from_month) <= 0) or (int(from_month) > 12):
            to_month = input("Valid input required: ")
        to_year = input("To year\n")
        while isEmpty(to_year):
            to_year = input("Valid input required: ")
    elif timeframeSelection == "1":
        timeframe = "BillingMonthToDate"
    elif timeframeSelection == "2":
        timeframe = "MonthToDate"
    elif timeframeSelection == "3":
        timeframe = "TheLastBillingMonth"
    elif timeframeSelection == "4":
        timeframe = "TheLastMonth"
    print("1. Daily")
    print("2. Monthly")
    granularitySelection = input("Granularity\n")
    while (isEmpty(granularitySelection)) or (isInvalidNumberInput(granularitySelection, 2)):
        granularitySelection = input("Valid input required: ")
    granularity = ""
    if granularitySelection == "1":
        granularity = "daily"
    elif granularitySelection == "2":
        granularity = "monthly"
    print("1. Run Query")
    print("2. Save Query")
    print("3. Save and Run")
    selection = input("What would you like to do?\n")
    while (isEmpty(selection)) or (isInvalidNumberInput(selection, 3)):
        selection = input("Valid input required: ")
    if selection == "1":
        if from_day != "":
            parameters = {
                "type": "Usage",
                "timeframe": "Custom",
                "time_period": {
                    "from_property": datetime(int(from_year), int(from_month), int(from_day), tzinfo=timezone.utc),
                    "to": datetime(int(to_year), int(to_month), int(to_day), tzinfo=timezone.utc)
                },
                "dataset": {
                    "granularity": granularity,
                    "aggregation": {
                        "totalCost": {
                            "name": "Cost",
                            "function": "Sum"
                        }
                    },
                }
            }
            runQuery(resource, parameters)
        else:
            parameters = {
                "type": "Usage",
                "timeframe": timeframe,
                "dataset": {
                    "granularity": granularity,
                    "aggregation": {
                        "totalCost": {
                            "name": "Cost",
                            "function": "Sum"
                        }
                    },
                }
            }
            runQuery(resource, parameters)
    elif selection == "2":
        if from_day == "":
            dates = getStartAndEndDate(timeframe, resource)
            split_from_date = splitDate(dates[0])
            split_to_date = splitDate(dates[1])
            from_day = split_from_date[0]
            from_month = split_from_date[1]
            from_year = split_from_date[2]
            to_day = split_to_date[0]
            to_month = split_from_date[1]
            to_year = split_from_date[2]
            saveQuery(username, dates[0], dates[1], resource, granularity, from_day, from_month, from_year, to_day,
                      to_month, to_year)
        else:
            from_date = from_day + "/" + from_month + "/" + from_year
            to_date = to_day + "/" + to_month + "/" + to_year
            saveQuery(username, from_date, to_date, resource, granularity, from_day, from_month, from_year, to_day,
                      to_month, to_year)
    elif selection == "3":
        if from_day == "":
            dates = getStartAndEndDate(timeframe, resource)
            split_from_date = splitDate(dates[0])
            split_to_date = splitDate(dates[1])
            from_day = split_from_date[0]
            from_month = split_from_date[1]
            from_year = split_from_date[2]
            to_day = split_to_date[0]
            to_month = split_from_date[1]
            to_year = split_from_date[2]
            saveQuery(username, dates[0], dates[1], resource, granularity, from_day, from_month, from_year, to_day,
                      to_month, to_year)
        else:
            from_date = from_day + "/" + from_month + "/" + from_year
            to_date = to_day + "/" + to_month + "/" + to_year
            saveQuery(username, from_date, to_date, resource, granularity, from_day, from_month, from_year, to_day,
                      to_month, to_year)
        if from_day != "":
            parameters = {
                "type": "Usage",
                "timeframe": "Custom",
                "time_period": {
                    "from_property": datetime(int(from_year), int(from_month), int(from_day), tzinfo=timezone.utc),
                    "to": datetime(int(to_year), int(to_month), int(to_day), tzinfo=timezone.utc)
                },
                "dataset": {
                    "granularity": granularity,
                    "aggregation": {
                        "totalCost": {
                            "name": "Cost",
                            "function": "Sum"
                        }
                    },
                }
            }
            runQuery(resource, parameters)
        else:
            parameters = {
                "type": "Usage",
                "timeframe": timeframe,
                "dataset": {
                    "granularity": granularity,
                    "aggregation": {
                        "totalCost": {
                            "name": "Cost",
                            "function": "Sum"
                        }
                    },
                }
            }
            runQuery(resource, parameters)


# Store query in database for later use
def saveQuery(username, datefrom, dateto, resource, granularity, fromDay, fromMonth, fromYear, toDay, toMonth, toYear):
    while True:
        query_name = input("Query Name: ")
        while isEmpty(query_name):
            query_name = input("Valid input required: ")
        result = SQLHelper().findQuery(username, query_name)
        if len(result) > 0:
            print("A query with this name already exists")
        else:
            break
    resource_check = SQLHelper().findResource(resource)
    if len(resource_check) == 0:
        SQLHelper().addResource(resource)
    SQLHelper().addQuery(username, query_name, datefrom, dateto, granularity, fromDay, fromMonth, fromYear, toDay,
                         toMonth, toYear)
    SQLHelper().addResourceQueryRecord(resource, query_name)


# Run a query that had just been created
def runQuery(resource, parameters):
    print("Please wait...")
    credentials = AzureCliCredential()
    query_client = CostManagementClient(credentials)
    cost_information = query_client.query.usage("subscriptions/4df8145b-40fe-44ed-b169-2c1e0a81f9c5/resourceGroups/"
                                                + resource, parameters).rows
    col_names = ["Cost", "Date"]
    data = []
    total = 0
    for i in cost_information:
        current_cost = i[0]
        total = total + current_cost
        current_cost = "{:.2f}".format(current_cost)
        current_cost = "£" + current_cost
        current_date = str(i[1])
        year = current_date[0:4]
        month = current_date[4:6]
        day = current_date[6:8]
        current_date = day + "/" + month + "/" + year
        current_row = [current_cost, current_date]
        data.append(current_row)
    cost_table = tabulate(data, headers=col_names, tablefmt="fancy_grid")
    print(cost_table)
    total = "{:.2f}".format(total)
    total = "£" + total
    print("Total cost: " + str(total))


# Run a query that's been saved prior
def runFromSaved(username):
    print("Please wait...")
    allQueries = SQLHelper().getAllQueries(username)
    number = 1
    for i in allQueries:
        print(str(number) + ". " + i[3])
        number += 1
    selection = input("Which query would you like to run?\n")
    while (isEmpty(selection)) or (isInvalidNumberInput(selection, number)):
        selection = input("Valid input required: ")
    selection = int(selection)
    query = allQueries[selection - 1]
    query_id = query[0]
    resourceFromQuery = SQLHelper().getResourcesFromQuery(query_id)
    resource = resourceFromQuery[0][0]
    granularity = query[4]
    from_day = int(query[5])
    from_month = int(query[6])
    from_year = int(query[7])
    to_day = int(query[8])
    to_month = int(query[9])
    to_year = int(query[10])
    from_date = datetime(from_year, from_month, from_day, tzinfo=timezone.utc)
    to_date = datetime(to_year, to_month, to_day, tzinfo=timezone.utc)
    parameters = {
        "type": "Usage",
        "timeframe": "Custom",
        "time_period": {
            "from_property": from_date,
            "to": to_date
        },
        "dataset": {
            "granularity": granularity,
            "aggregation": {
                "totalCost": {
                    "name": "Cost",
                    "function": "Sum"
                }
            },
        }
    }
    runQuery(resource, parameters)


# Still in development
class QueryHandler:

    def deleteQuery(self, queryName: str):
        pass

    def openQuery(self, queryName: str):
        pass

    def editQuery(self, username):
        pass
