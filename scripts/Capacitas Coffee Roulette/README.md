# ☕ Coffee Roulette Automator

This project automates **Coffee Roulette** at Capacitas — a monthly initiative that randomly pairs employees for casual coffee chats to promote team bonding and cross-team interaction. The system handles participant selection, pairing logic, historical tracking, email notifications, and generates a CSV summary of each month’s pairings.

---

## 📌 Features

- ✅ Fair random pairing system using a SQLite database
- 🚫 Ensures no manager-employee pairings
- 🗓️ Prevents repeat pairings within a 6-month period  
  > *If two people are paired in a given month, they won’t be paired again for at least the next six months*
- 📧 Automatically sends personalized emails to all participants
- 👤 Special logic to pair SLT (Senior Leadership Team) with other employees
- 📄 Generates a CSV summary for each run
- 🧠 Respin functionality in case of undesired pairings

---

## 🧠 How It Works

- Pulls opted-in employees from a SQLite database (`CoffeeRoulette.db`)
- Randomizes and pairs people while checking:
  - They haven’t had coffee in the last 6 months
  - They are not in a direct manager-report relationship
- Emails are sent to each pair
- SLT members are each paired with a random non-SLT member
- Database and coffee counts are updated automatically
- Summary saved as `Coffee Roulette Summary - YYYY-MM-DD.csv`

---

## 🗂️ File Overview

- `main.py` – Core script: runs the pairing logic, sends emails, updates the database, and creates the CSV report
- `one_time_update.py` – Script to update historical coffee records from a CSV (for legacy data or migrations)

---

## ⚙️ Tech Stack

- **Python 3**
- **SQLite3** – Lightweight database to store user info and pairing history
- **smtplib / email.mime** – For sending emails to participants
- **csv** – For summary report generation
- **dateutil** – For calculating 6-month pairing gaps

---

## 🔐 Notes

- Uses Gmail SMTP with TLS encryption
- Requires an [app-specific password](https://support.google.com/accounts/answer/185833) for email authentication
- Credentials and sensitive data should not be committed to version control
- This project is intended for internal team-building and is provided as-is

---

## 🙌 Acknowledgements

Created by **Aman** during university and implemented in a real-world setting to encourage cross-team conversation and connection at Capacitas. A small but meaningful automation to bring people together over coffee.
