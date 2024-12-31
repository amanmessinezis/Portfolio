from microbit import *
import radio
import sys
#Master microbit
class Master:
    def __init__(self, button_a, button_b):
        self.button_a = button_a
        self.button_b = button_b

    def pressButtonMaster():
        while True:
            if button_a.is_pressed():
                displayScoreA()
            elif button_b.is_pressed():
                displayScoreB()

microbit = Master(button_a, button_b)
microbit.pressButtonMaster()