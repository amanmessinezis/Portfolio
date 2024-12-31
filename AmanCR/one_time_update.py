import csv
import sqlite3
from datetime import date
from time import strftime, gmtime

database = 'CoffeeRoulette.db'
today = strftime("%Y-%m-%d", gmtime())


def main():
    file = open('3-1-23.csv')
    type(file)
    csvreader = csv.reader(file)
    header = next(csvreader)
    print(header)
    pairs = []
    for row in csvreader:
        pairs.append(row)
    file.close()
    for pair in pairs:
        p1 = pair[1]
        p2 = pair[2]
        p1_id = get_id(p1)
        p2_id = get_id(p2)
        if p1_id < p2_id:
            update_coffees(p1_id, p2_id)
        else:
            update_coffees(p2_id, p1_id)


def get_id(name):
    connect = sqlite3.connect(database)
    cursor = connect.cursor()
    name_id = cursor.execute('SELECT id FROM Peeps WHERE name = ?', [name])
    id_result = name_id.fetchone()[0]
    return id_result


def insert_pairs(p1_id, p2_id):
    connect = sqlite3.connect(database)
    cursor = connect.cursor()
    cursor.execute('INSERT INTO Pairs (p1id, p2id, date) VALUES (?,?,?)', (p1_id, p2_id, today))
    connect.commit()


def update_coffees(p1_id, p2_id):
    connect = sqlite3.connect(database)
    cursor = connect.cursor()
    cursor.execute('UPDATE Coffees SET coffees = coffees + 1 WHERE pid = ? AND oid = ?', (p1_id, p2_id))
    connect.commit()


if __name__ == '__main__':
    main()
