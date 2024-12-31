from microbit import *

def unsuccessful():
        while True:
            if button_a.is_pressed() or button_b.is_pressed():
                display.scroll("3", 50)
                display.scroll("2", 50)
                display.scroll("1", 50)
            else:
                break

class PLAYERS:
    def __init__(self, button_a, button_b, currentPos, ID):
        while True:
            self.ID = ID
            self.button_a = button_a
            self.button_b = button_b
            self.currentPos = currentPos

    def steal():
        while True:
            unsuccessful()
            radioObject = getRadioDistance(Defender)
            radioObject2 = getRadioDistance(Jailhouse)
            radioObject3 = getRadioDistance(Base)
            radio.on()
            radio.config(power=1)
            if playersSearch(Defender) or playersSearch(Jailhouse) or playersSearch(Base):
                if Defender == flag or Jailhouse == Attacker or Base == flag:
                    if button_a.is_pressed():
                        flag = self.ID
                        if playersSearch(Jailhouse):
                            playersSearch(Jailhouse)
                            radio.send(membersList.pop(0))


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

    def messageReceivedJAILHOUSE():
        while True:
            radio.on()
            radioObject = getRadioDistance(Jailhouse)
            if radio.receive_bytes() == "10111":
                radiObject.playersSearch(Jailhouse)
                radio.send("JAILHOUSE")