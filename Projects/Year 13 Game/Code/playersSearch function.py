def playersSearch(microbit):
    if microbit == Attacker:
        return radio.config(group=0)
    elif microbit == Defender:
        return radio.config(group=1)
    elif microbit == Flag:
        return radio.config(group=2)
    elif microbit == Base:
        return radio.config(group=3)
