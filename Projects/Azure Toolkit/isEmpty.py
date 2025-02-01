# Checks if an input value is empty
def isEmpty(theInput: str):
    if not (theInput and theInput.strip()):
        return True
    else:
        return False

