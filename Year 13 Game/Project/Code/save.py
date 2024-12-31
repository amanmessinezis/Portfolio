#Base
from microbit import *
import radio
def scoring(score):#Procedure
    while game = True:#An infinite loop that persistently checks the score
        if score == 5:
            display.scroll("Team A Wins!")
            radio.on()
            radio.config(address=2,power=7)#Selects which microbit they are communicating with. In this case, it's the other base
            radio.send(endProgram())#Call the "endProgram" function in the base microbit on the other team
            radio.config(address=1,power=7)#Communicate with master microbit in charge of persistent data storage
            radio.send(persistentScore(score))#Calls the "persistentScore" function in the master microbit to send the scores which will be stored externally for future matches
            radio.off()