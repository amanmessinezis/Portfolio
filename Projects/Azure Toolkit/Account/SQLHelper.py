#!/usr/bin/python
# -*- coding: UTF-8 -*-
# from Account import AccountHandler
from typing import List

# from Account.AccountHandler import AccountHandler
import bcrypt

from Connection.SQLHelper import SQLHelper as parentSQL


class SQLHelper(parentSQL):

    # Adds a new account to the Users database
    def newAccount(self, username, fName, lName, emailAddress, password):
        self.database.cursor.execute(
            'INSERT INTO Users("Username", "FirstName", "LastName", "EmailAddress", Password, "AccountType") '
            'VALUES (?, ?, ?, ?, ?, "Employee")', (username, fName, lName, emailAddress, password))
        self.database.connect.commit()
        self.database.cursor.close()

    # Gets a specified user's password
    def getUserPassword(self, username):
        self.database.cursor.execute('SELECT Password FROM Users WHERE Username = ?', [username])
        encrypted_password = self.database.cursor.fetchall()
        return encrypted_password

    # Returns all records in the Users table with a given email address
    def emailExists(self, emailAddress):
        self.database.cursor.execute('SELECT * FROM Users WHERE "EmailAddress" = ?', [emailAddress])
        db_result = self.database.cursor.fetchall()
        return db_result

    # Returns all records in the Users table with a given username
    def usernameExists(self, username):
        self.database.cursor.execute('SELECT * FROM Users WHERE Username = ?', [username])
        db_result = self.database.cursor.fetchall()
        return db_result

    # Fetches a record with a given username and password
    def usernamePasswordValidation(self, username, password):
        self.database.cursor.execute('SELECT * FROM Users WHERE Username = ? AND Password = ?', (username, password))
        db_result = self.database.cursor.fetchone()
        return db_result

    # Returns the user type of a given username - either Employee or Admin
    def userType(self, username):
        self.database.cursor.execute('SELECT * FROM Users WHERE Username = ?', [username])
        db_result = self.database.cursor.fetchall()
        return db_result


# sql_helper = SQLHelper()
# sql_helper.testConnection()
# result: bytes = sql_helper.testConnection()[0][4]
# plain_txt_pw: str = "AmanAman2!"
# byte_plain_txt_pw = plain_txt_pw.encode()
# check = bcrypt.checkpw(byte_plain_txt_pw, result)
# print(check)
