#Attacker
from microbit import *
import radio
def getX():
    while True:

def unsuccessful():
    while True:
        if button_a.is_pressed() or button_b.is_pressed():
            display.scroll("3")
            display.scroll("2")
            display.scroll("1")
def stealFlag():
    unsuccessful()
    if attacker