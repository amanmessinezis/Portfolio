from microbit import *
import radio
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