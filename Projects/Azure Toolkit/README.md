# Azure Cost Tracking Toolkit

## Overview
A command-line Python application developed during my work-based learning at Capacitas. This in-house system automates the monthly process of tracking Azure cloud costs, helping project managers determine how much clients should be charged for virtual machine usage. The toolkit replaces manual operations on Azure Portal, reducing human error and saving significant time.

## 🔧 Key Features
- 📊 **Cost Breakdown and Visualisation**: Generate and optionally view visual summaries of client-specific Azure usage over time.
- 🧠 **Saved Queries and Alerts**: Users can create, save, and schedule queries with automated alerts sent via email.
- 🔗 **Integration with Azure APIs**: Uses Azure SDK to fetch cost data per client/resource group.
- 📬 **Automated Email Notifications**: Sends scheduled billing summaries via SMTP.
- 🗂 **SQLite-based User Management**: Secure login and account management using a local database.
- ⏱️ **Query Execution & Scheduling**: Ability to run queries immediately or schedule recurring reports.

## 🧱 Architecture
- **Front-end**: Command-line interface with numbered navigation
- **Back-end**: Modular Python classes (e.g. `AccountHandler`, `QueryHandler`, `AlertHandler`) with SQL database interaction
- **Database**: SQLite for storing users, alerts, and query metadata
- **External Services**: Azure SDK, SMTP (email), PyInstaller (for packaging)

## 🧪 Testing
- Functional testing was completed across all major use cases, including logins, query generation, database error handling, and Azure connection failures.
- Scalability and non-functional aspects (reliability, performance, and security) were also explored.


## 🧪 Sample Use Case
1. User logs in via CLI
2. Navigates to **Create Query**
3. Selects a client and date range
4. Chooses granularity (e.g. daily, monthly)
5. Saves the query and optionally sets an alert
6. Receives monthly cost summary via email

## 💡 Technologies Used
- **Python**
- **SQLite**
- **Azure SDK for Python**
- **SMTP**
- **PyInstaller**

## 📦 Installation & Setup
> The app is precompiled and runs via `main.exe`.

## 📄 Documentation
The following documents provide further technical and user detail:
- Design Document
- Implementation Report
- Requirements Specification
- User Guide

## 🏁 Outcome
- ✅ 100% of user requirements met
- 🧩 Used modular, maintainable code
- 🤝 Collaborated across technical and finance teams
- 📈 Enabled accurate monthly billing for Azure cloud resources

## 👤 Author
**Aman Messinezis**  
BSc Computer Science (Professional Pathway)  
Side Of Work Project at **Capacitas**

