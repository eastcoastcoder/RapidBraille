'''
Subtraction without borrowing
Adopted from Charles Petzold's novel Code
Ch 13

Brief refresher of mathematical terms:
  Minuend
- Subtrahend
= Difference
'''
def nineComplement(minuend, subtrahend):
    nineComp = ''
    question = ''
    for x in range(len(str(subtrahend))):   
        nineComp += '9'
    for x in range(len(str(minuend))):   
        question += '?'
        
    print (" ",minuend,"\n-", subtrahend,"\n=",question) 
    
    result = int(nineComp) - subtrahend
    print("\n ",nineComp,"\n-",subtrahend,"\n=",result)
    
    resultTwo = minuend + result
    print("\n ",minuend,"\n+",result,"\n=",resultTwo)
    
    final = resultTwo + 1 - (int(nineComp)+1)
    print("\n ",resultTwo,"\n+ 1 \n-",(int(nineComp)+1),"\n=",final)

def main():
    min = int(input('Enter a minuend: '))
    sub = int(input('Enter a subtrahend: '))
    nineComplement(min, sub)

if __name__ == '__main__':
    main()