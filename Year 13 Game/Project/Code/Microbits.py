from microbit import *
from random import *
def unsuccessful():
        while True:
            if button_a.is_pressed() or button_b.is_pressed():
                display.scroll("3", 50)
                display.scroll("2", 50)
                display.scroll("1", 50)
            else:
                break
# Attack and defense microbits
class PLAYERS:
    def __init__(self, button_a, button_b, currentPos, ID):
        while True:
            self.ID = ID
            self.button_a = button_a
            self.button_b = button_b
            self.currentPos = currentPos

    def adjacent(variable):
        variable = getRadioDistance()
        variable.playersSearch
        variable.messagereceived

    def steal(base, player):
        while True:
            unsuccessful()
            if adjacent(player) and button_a.is_pressed():
                flag = self.ID

class Attacker(PLAYERS):
    def __init__(self, button_a, button_b, currentPos, ID):
        self.button_a = button_a
        self.button_b = button_b
        self.currentPos = currentPos
        self.ID = ID

    def place():
        while True:
            flagretrieved()
            if flag == self.ID and adjacent(base) and button_a.is_pressed():
                score(point)

    def free():
        while True:
            unsuccessful()
            if adjacent(jailhouse) and button_b.is_pressed():
                closest = adjacent(teamattacker)
                closest.functions(true)
                cooldown(3)

class Defender(PLAYERS):
    def __init__(self, button_a, button_b, currentPos, ID):
        self.button_a = button_a
        self.button_b = button_b
        self.currentPos = currentPos
        self.ID = ID

    def capture():
        while True():
            unsuccessful()
            if adjacent(enemyattacker) and button_a.is_pressed():
                functions(false)

    def retrieve():
        while True:
            unsuccessful()
            if adjacent(teamflag) and button_a.is_pressed():
                flag = self.ID

    def place():
        while True:
            unsuccessful()
            if adjacent(teambase):
                if button_b.is_pressed():
                   if flag == self.ID:
                    flag = base

    def messagereceivedSTEAL():
        while True:
            radio.on()
            radio.config(group=0)
            if radio.receive() == "1101010011":
                radio.send_bytes() == "1100"

class Base(PLAYERS):
    def __init__(self, button_a, button_b, currentPos, ID):
        self.button_a = button_a
        self.button_b = button_b
        self.currentPos = currentPos
        self.ID = ID

    def startGameGUEST():
        radio.config(group = 3, power = 7)
        if radio.receive() == "PRESS A NOW!":
            if button_a.is_pressed():
                radio.send_bytes("10101")
                display.scroll("PREPARE TO PLAY IN")
                display.scroll("3")
                display.scroll("2")
                display.scroll("1")
                display.scroll("PLAY!")


    def startGameHOST():
        if button_a.is_pressed():
            radio.on()
            radio.config(group = 3, power = 7)
            radio.send("PRESS A NOW!")
            sleep(7000)
            if radio.recieve_bytes() == 1010101010:
                display.scroll("PREPARE TO PLAY IN")
                display.scroll("3")
                display.scroll("2")
                display.scroll("1")
                display.scroll("PLAY!")

    def messageReceivedSTEAL():
        while True:
            radio.on()
            radio.config(group=0)
            if radio.receive() == "1101010011":
                radio.send_bytes() == "1100"

class Jailhouse(PLAYERS):
    def __init__(self, button_a, button_b, currentPos, ID):
        self.button_a = button_a
        self.button_b = button_b
        self.currentPos = currentPos
        self.ID = ID

    def members():
        while True:
            membersList = []
        while True:
            radioDistance = getRadioDistance(Attacker)
            radio.on()
            radio.config(power = 4)
            radioDistance.playersSearch(Attacker)
            radio.send_bytes("10111")
            if radio.receive() == "JAILHOUSE":
                membersList.append(Attacker)
                jail = Image("90009:""09090:""00900:""09090:""90009:")
                display.show(jail)

class Masterbit(PLAYERS):
    def __init__(self, button_a, button_b, currentPos, ID):
        self.button_a = button_a
        self.button_b = button_b
        self.currentPos = currentPos
        self.ID = ID

    def storeScoreA():
        if button_a.is_pressed():
            display.show(score(A))  # NEEDS TO BE MADE
        elif button_b.is_pressed():
            display.show(score(B))  # NEEDS TO BE MADE



a1A = Attacker(PLAYERS(ID="1"))
a2A = Attacker(PLAYERS(ID="2"))
a3A = Attacker(PLAYERS(ID="3"))
d1A = Defender(PLAYERS(ID="4"))
d2A = Defender(PLAYERS(ID="5"))
d3A = Defender(PLAYERS(ID="6"))
a1B = Attacker(PLAYERS(ID="7"))
a2B = Attacker(PLAYERS(ID="8"))
a3B = Attacker(PLAYERS(ID="9"))
d1B = Defender(PLAYERS(ID="10"))
d2B = Defender(PLAYERS(ID="11"))
d3B = Defender(PLAYERS(ID="12"))