'''
Basic Braille Alphabet
Adopted from Charles Petzold's novel Code
Ch 3
Alternative version using numerical index (1-based)
'''
import pprint
empty = [['.' for x in range(2)] for x in range(3)] 

def converter(letter):
    oneIndex = 96
    lastDigit = (ord(letter)-oneIndex) % 10;
    
    #Appensions        
    #k-t duplicates a-j except [2][0] is raised
    if(letter >= 'k' and letter != 'w'):
        empty[2][0] = letter
            
        #u-z duplicates k-o except [2][0] (excluding w) and [2][1] are raised
        if(letter >= 'u'):
            empty[2][1] = letter
            
    #a-j use only the top 4 slots in each cell
    empty[0][0] = letter
    
    if(letter == 'a'):
        return
        
    #                2               12               22
    #elif(letter == 'b' or letter == 'l' or letter == 'v'):   
    elif(lastDigit == 2):    
        empty[1][0] = letter
        return
        
    #                3                13              24):
    #elif(letter == 'c' or letter == 'm' or letter == 'x'):
    elif((lastDigit == 3 or letter == 'x') and letter != 'w'):
        empty[0][1] = letter
        return
    #                4                14              25):        
    #elif(letter == 'd' or letter == 'n' or letter == 'y'):
    elif((lastDigit == 4 or letter == 'y') and letter != 'x'):
        empty[0][1] = letter
        empty[1][1] = letter
        return
        
    #                5                15              26):                
    #elif(letter == 'e' or letter == 'o' or letter == 'z'):
    elif((lastDigit == 5 or letter == 'z') and letter != 'y'):
        empty[1][1] = letter
        return
        
    #elif(letter == 'f' or letter == 'p' or letter == 'i' or letter == 's'):
    elif(lastDigit == 6 or lastDigit == 9):
        empty[0][1] = letter
        empty[1][0] = letter
        
        #if(letter == 'i' or letter == 's'):
        if(lastDigit == 9):
            empty[0][0] = '.'
        return
            
    #elif(letter == 'g' or letter == 'q' or letter == 'j' or letter == 't' or letter == 'w'):
    elif(lastDigit == 7 or lastDigit == 0 or letter == 'w'):
        empty[0][1] = letter
        empty[1][0] = letter
        empty[1][1] = letter
        
        #if(letter == 'j' or letter == 't' or letter == 'w'):
        if(lastDigit == 0 or letter == 'w'):
            empty[0][0] = '.'
            if(letter == 'w'):
                empty[2][1] = letter
        return 
       
    #elif(letter == 'h' or letter == 'r'):
    elif(lastDigit == 8):
        empty[1][0] = letter
        empty[1][1] = letter
        return

def clear():            
    empty[0][0] = '.'
    empty[0][1] = '.'
    empty[1][0] = '.'
    empty[1][1] = '.'
    empty[2][0] = '.'
    empty[2][1] = '.'
    
def main():
    while True:
        clear()
        letter = input('Enter a lowercase letter: ')
        converter(letter)
        pprint.pprint(empty, width=20)
        
              
if __name__ == '__main__':
    main()