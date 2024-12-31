# Will not be paired with manager
# Can only work if the manager column is updated in the database
# Someone paired with someone one month will not be paired with someone at least for the next 6 months
import math
import random
import smtplib
import sqlite3
from datetime import date
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from time import strftime, gmtime

from dateutil.relativedelta import relativedelta

# Who's running it
owner = "Aman"
# Email Credentials
email = 'coffeeroulette.capacitas@gmail.com'
app_password = 'vpnu luav gask rkvr'
# password = 'P@55word!'
CEO = 'sameenahassam@capacitas.co.uk'
database = 'CoffeeRoulette.db'
# database = 'CoffeeRouletteTest - Copy.db'


def get_manager_id(person):
    person_id = person[0]
    connect = sqlite3.connect(database)
    cursor = connect.cursor()
    manager = cursor.execute('SELECT manager FROM Peeps WHERE id = ?'
                             , [person_id])
    manager_result = manager.fetchall()
    return manager_result


def had_coffee_in_last_six_months(first_id: int, second_id: int):
    connect = sqlite3.connect(database)
    cursor = connect.cursor()
    p1id = first_id
    p2id = second_id
    if first_id > second_id:
        p1id = second_id
        p2id = first_id
    seven_months_ago = date.today() + relativedelta(months=-7)
    coffees_from_seven_months = cursor.execute('SELECT date FROM Pairs WHERE p1id = ? AND p2id = ? AND date >= date(?) '
                                               , (p1id, p2id, seven_months_ago))
    pcd_result = coffees_from_seven_months.fetchall()
    if len(pcd_result) == 0:
        return False
    else:
        return True


def create_pairs(pairs):
    today = strftime("%Y-%m-%d", gmtime())
    connect = sqlite3.connect(database)
    cursor = connect.cursor()
    for pair in pairs:
        first_person = pair[0]
        second_person = pair[1]
        fp_id = first_person[0]
        sp_id = second_person[0]
        if fp_id < sp_id:
            cursor.execute('INSERT INTO Pairs (p1id, p2id, date) VALUES (?,?,?)', (fp_id, sp_id, today))
        else:
            cursor.execute('INSERT INTO Pairs (p1id, p2id, date) VALUES (?,?,?)', (sp_id, fp_id, today))
        connect.commit()
    print("Pairs updated")


def send_email(recipient, body):
    full_message = MIMEMultipart()
    full_message['From'] = email
    full_message['To'] = recipient
    full_message['Subject'] = 'Coffee Roulette!'
    full_message.attach(MIMEText(body, 'plain'))

    server = smtplib.SMTP('smtp.gmail.com', 587)
    server.starttls()
    server.login(email, app_password)
    server.sendmail(email, recipient, full_message.as_string())
    server.quit()


def email_ceo(leader, victims, duplicate_pairs):
    victims_message = ""
    for victim in victims:
        victims_message = victims_message + victim[1] + "\n"
    dp_message = ""
    for pair in duplicate_pairs:
        first_person_name = pair[0][1]
        second_person_name = pair[1][1]
        dp_message = dp_message + first_person_name + " and " + second_person_name + "\n"
    message = f'Hi,\n\n' \
              f'The Coffee Roulette wheel has been spun again!' \
              f' The following employees have been selected for this months Leadership Team Coffees:\n' \
              f' \n{victims_message}\n\n\n' \
              f'Of these, the following are already paired with members of the leadership team:' \
              f'\n{dp_message}\n\n\n' \
              f'Thanks\n\n' \
              f'Coffee Roulette@Capacitas'

    send_email(leader, message)

    print("Leadership Email sent!\n")

def coffee_pair_message(name, paired_person):
    body = f"Hi {name}," \
           f"\n\nThe Coffee Roulette wheel has been spun again!\n\n" \
           f" For this month Your Coffee Roulette Partner is {paired_person}!\n\n" \
           f"The next Coffee Roulette Spin will take place at the beginning of the next month!\n\n" \
           f"If you got this email twice, this is because there are currently an odd number of employees in Capacitas," \
           f" so you can either have a three way coffee roulette or have two separate ones.\n\n" \
           f"If you would like to opt out of coffee roulette, please message {owner}\n\n" \
           f"Thanks\n\n" \
           f"Coffee Roulette@Capacitas"
    return body


def email_pairs(pairs):
    for pair in pairs:
        first_person = pair[0]
        second_person = pair[1]
        fp_name = first_person[1]
        sp_name = second_person[1]
        fp_email_address = first_person[4]
        sp_email_address = second_person[4]
        fp_body = coffee_pair_message(fp_name, sp_name)
        sp_body = coffee_pair_message(sp_name, fp_name)

        send_email(fp_email_address, fp_body)
        send_email(sp_email_address, sp_body)

    print("Employees emailed!")


def update_coffees(pairs):
    connect = sqlite3.connect(database)
    cursor = connect.cursor()
    for pair in pairs:
        first_person = pair[0]
        second_person = pair[1]
        fp_id = first_person[0]
        sp_id = second_person[0]
        if fp_id < sp_id:
            cursor.execute('UPDATE Coffees SET coffees = coffees + 1 WHERE pid = ? AND oid = ?', (fp_id, sp_id))
        else:
            cursor.execute('UPDATE Coffees SET coffees = coffees + 1 WHERE pid = ? AND oid = ?', (sp_id, fp_id))
        connect.commit()
    print("Coffee sessions updated")


def accept():
    is_valid = False
    user_input = ''
    while not is_valid:
        print('\nEnter \n1 to Confirm Pairings\n0 to Respin')
        user_input = input()
        if user_input == '1' or user_input == '0':
            is_valid = True
        else:
            print('Invalid input')
    return user_input


def create_csv(pairs, victims, already_paired):
    today = date.today()
    f = open("Coffee Roulette Summary - " + str(today) + ".csv", "x")
    f.write("Date,Person 1, Person 2\n")
    for pair in pairs:
        p1 = pair[0]
        p2 = pair[1]
        p1_name = p1[1]
        p2_name = p2[1]
        f.write(str(today) + "," + p1_name + "," + p2_name + "\n")
    f.write("\nLeadership Coffees\n")
    for victim in victims:
        victim_name = victim[1]
        f.write(victim_name + "\n")
    f.write("\nAlready paired:\n")
    for dupe_pair in already_paired:
        p1 = dupe_pair[0]
        p2 = dupe_pair[1]
        p1_name = p1[1]
        p2_name = p2[1]
        f.write(p1_name + "," + p2_name + "\n")
    print("CSV generated!\n")


def main():
    final_pairs = []
    final_pairs_id = []
    final_victims = []
    final_dupes = []
    pairs = []
    connect = sqlite3.connect(database)
    cursor = connect.cursor()
    peeps_table = cursor.execute("SELECT * FROM Peeps WHERE opted = 1")
    pt_result = peeps_table.fetchall()
    random.shuffle(pt_result)
    middle_index = math.ceil(len(pt_result) / 2)
    peeps = pt_result[:middle_index]
    peeps_copy = pt_result[middle_index:]
    if (len(pt_result) % 2) == 1:
        up_to = 1
        last_in_peeps = peeps[len(peeps) - 1]
        paired_person = random.choice(peeps_copy)
        paired_person_id = paired_person[0]
        manager_id = get_manager_id(last_in_peeps)
        while (had_coffee_in_last_six_months(last_in_peeps[0], paired_person[0])) or (
                manager_id == paired_person_id):
            paired_person = random.choice(peeps_copy)
        paired = [last_in_peeps, paired_person]
        print(last_in_peeps[1] + ", " + paired_person[1])
        pairs.append(paired)
    else:
        up_to = 0
    i = 0
    while i < len(peeps) - up_to:
        person = peeps[i]
        paired_person = random.choice(peeps_copy)
        paired_person_id = paired_person[0]
        manager_id = get_manager_id(person)
        while (had_coffee_in_last_six_months(person[0], paired_person[0])) or (manager_id == paired_person_id):
            paired_person = random.choice(peeps_copy)
        peeps_copy.remove(paired_person)
        paired = [person, paired_person]
        print(person[1] + ", " + paired_person[1])
        pairs.append(paired)
        i = i + 1
    pairs_id = []
    for pair in pairs:
        first_person_id = pair[0][0]
        second_person_id = pair[1][0]
        if first_person_id < second_person_id:
            pair_id = [first_person_id, second_person_id]
        else:
            pair_id = [second_person_id, first_person_id]
        pairs_id.append(pair_id)
    print(str(len(pairs)) + " pairs made\n")
    for pair_id in pairs_id:
        final_pairs_id.append(pair_id)
    for pair in pairs:
        final_pairs.append(pair)


    connect = sqlite3.connect(database)
    cursor = connect.cursor()
    slt_table = cursor.execute("SELECT * FROM Peeps WHERE ls = 1")
    slt = slt_table.fetchall()
    number_of_slt = len(slt)
    victim_table = cursor.execute("SELECT * FROM Peeps WHERE ls = 0 AND opted = 1")
    prospective_victims = victim_table.fetchall()
    victims = []
    i = 0
    while i < number_of_slt:
        victim = random.choice(prospective_victims)
        victims.append(victim)
        prospective_victims.remove(victim)
        i = i + 1
    print("Victims are: ")
    for victim in victims:
        print(victim[1])
    already_paired = []
    for victim in victims:
        for slt_person in slt:
            victim_id = victim[0]
            slt_id = slt_person[0]
            if victim_id < slt_id:
                pair_id_to_find = [victim_id, slt_id]
            else:
                pair_id_to_find = [slt_id, victim_id]
            number_of_times = final_pairs_id.count(pair_id_to_find)
            if number_of_times != 0:
                slt_pair = [victim, slt_person]
                already_paired.append(slt_pair)
    if len(already_paired) != 0:
        print("\nAlready paired:")
        for slt_pair in already_paired:
            print(str(slt_pair[0][1]) + " and " + str(slt_pair[1][1]))
    for victim in victims:
        final_victims.append(victim)
    for slt_pair in already_paired:
        final_dupes.append(slt_pair)

    print('\nLeadership Coffees Selection Accepted\n')
    create_csv(final_pairs, final_victims, final_dupes)
    email_ceo(CEO, final_victims, final_dupes)
    email_pairs(final_pairs)
    create_pairs(final_pairs)
    update_coffees(final_pairs)


if __name__ == '__main__':
    main()
