'''
Basic Braille Alphabet
Adopted from Charles Petzold's novel Code
Ch 3
'''
import pprint
empty = [['.' for x in range(2)] for x in range(3)] 

def converter(letter):
    # Oddball
    if(letter == 'w'):
        empty[0][1] = letter
        empty[1][0] = letter
        empty[1][1] = letter
        empty[2][1] = letter
        
    #a-j use only the top 4 slots in each cell
    if((letter >= 'a' and letter <= 'z') and letter != 'w'):    
        empty[0][0] = letter

        if(letter == 'b' or letter == 'l' or letter == 'v'):    
            empty[1][0] = letter
        if(letter == 'c' or letter == 'm' or letter == 'x'):
            empty[0][1] = letter
        if(letter == 'd' or letter == 'n' or letter == 'y'):
            empty[0][1] = letter
            empty[1][1] = letter
        if(letter == 'e' or letter == 'o' or letter == 'z'):
            empty[1][1] = letter
        if(letter == 'f' or letter == 'p' or letter == 'i' or letter == 's'):
            empty[0][1] = letter
            empty[1][0] = letter
            if(letter == 'i' or letter == 's'):
                empty[0][0] = '.'
        if(letter == 'g' or letter == 'q' or letter == 'j' or letter == 't'):
            empty[0][1] = letter
            empty[1][0] = letter
            empty[1][1] = letter
            if(letter == 'j' or letter == 't'):
                empty[0][0] = '.'
        if(letter == 'h' or letter == 'r'):
            empty[1][0] = letter
            empty[1][1] = letter
            
        #k-t duplicates a-j except [2][0] is raised
        if(letter > 'j' and letter < 'u'):
            empty[2][0] = letter
            
        #u-z duplicates k-o except [2][0] (excluding w) and [2][1] are raised
        if((letter > 't' and letter <= 'z') and letter != 'w'):
            empty[2][0] = letter
            empty[2][1] = letter

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