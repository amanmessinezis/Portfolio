from microbit import *
import radio

"""
CLASS WITH SEVERAL FUNCTIONS
TO GET THE RADIO DISTANCE

"""
"""
WHAT MICROBIT YOU WILL BE SEARCHING FOR WILL
SWITCH TO A DIFFERENT RADIO CHANNEL WHICH IS
ARBITUARY

"""
"""
A WAY TO ENSURE THAT A
RADIO CONNECTION HAS BEEN
ESTABLISHED BETWEEN TWO MICROBITS

"""
"""
THERE IS AT LEAST ONE MICROBIT PRESENT

"""
class getRadioDistance:
    def __init__(self, microbit):  # DEFINING MY CLASS
        while True:
            self.microbit = microbit

    def playersSearch(microbit):
            if microbit == Attacker:
                return radio.config(group=0)
            elif microbit == Defender:
                return radio.config(group=1)
            elif microbit == Jailhouse:
                return radio.config(group=2)
            elif microbit == Base:
                return radio.config(group=3)

    def messageReceived(send):
            while True:
                if radio.receive() == "None":
                    return "None"
                else:
                    return send

    def radioFunction(microbit):
        while True:
            for i in range(1, 7):
                radio.config(power=i)
                radio.send_bytes("1111")
                if messageReceived(send)=="0000"and playersSearch(microbit):
                    if i == 1 or i == 2:
                        Image('00000:''00000:''00000:''00900:''00000:')
                    elif i == 3 or i == 4:
                        Image('00000:''00000:''00900:''00000:''00000:')
                    elif i == 5 or i == 6:
                        Image('00000:''00900:''00000:''00000:''00000:')
                    elif i == 7:
                        Image('00900:''00000:''00000:''00000:''00000:')