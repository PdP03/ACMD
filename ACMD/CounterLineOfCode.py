import os

#metodo per contare le linee di un file
def countLines(dirpath, file):
    x = 0
    count = 0
    with open(dirpath+"\\"+file) as file:
        if(".java" in file.name):
            for count, line in enumerate(file):
                pass
            return count
    
    return x

#cicli per navigare nella struttura delle directory
mainCount = 0
testCount = 0
for dirpath, dirs, files in os.walk(os.getcwd()):
    for file in files:
        if("main" in dirpath):
            mainCount += countLines(dirpath, file)
        elif("test" in dirpath):
            testCount += countLines(dirpath, file)
        


print(f"Righe di codice nella cartella main: {mainCount}")
print(f"Righe di codice nella cartella test: {testCount}")
print(f"Totale righe di codice scritte: {mainCount+testCount}")


