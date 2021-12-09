from random import randint
import numpy as np


#############   DISTANCES   #############

commune = [1, 1, 2, 2, 2, 2, 3, 3, 4, 5]

def gen_dist(i, j):
    if (i < j) : 
        if (abs(commune[i-1] - commune[j-1]) <= 2) :
            return randint(2, 8)
        else : 
            return randint(9, 15)
    
    return -1


distances = np.array([[gen_dist(i, j) for j in range(1, 11)] for i in range(1, 11)])

def get_dist(depart, arrive) : 
    if depart < arrive : 
        return distances[depart-1][arrive-1]
    else :
        return distances[arrive-1][depart-1]


print("\n Printing DISTANCES ... \n")



for i in range (0, 9):
    for j in range(i+1, 10):
        print("insert into DISTANCES values ({}, {}, {});".format(i+1, j+1, distances[i][j]))



#############   EMPRUNTS   #############

# velo 1 --> 108
# adherent 1 --> 50
# station 1 --> 10
# max dostance = 15

nb_velo = 108
nb_adh = 50
mois = ["JAN", "FEB", "MAR", "APR", "MAI", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"]
annee = [18, 19, 20, 21]

def gen_day(i):
    j = i+1
    if (j == 2):
        return randint(1, 28)
    elif (j in (1,3,5,7,8,10,12)):
        return randint(1, 31)
    elif (j in (4,6,9,11)):
        return randint(1, 30)
        

print("\n Printing DISTANCES ... \n")

ref = 0

for i in range(len(annee)):
    for j in range(len(mois)):
        for k in range(randint(3, 7)):
            hour = randint(8,22)
            minute = randint(5,59)
            date1 = "'{}-{}-{} {}.{}'".format(gen_day(j), mois[j], annee[i],hour-randint(0,2),minute-randint(5,minute))
            date2 = "'{}-{}-{} {}.{}'".format(gen_day(j), mois[j], annee[i],hour,minute)
            ref += 1
            rand_depart = randint(1, 10)
            rand_arrive = 0
            while True:
                rand_arrive = randint(1, 10)
                if rand_depart != rand_arrive :
                    break
            print("insert into EMPRUNTS values ({}, {}, {}, {}, {}, {}, {}, {});".format(ref, date1, date2, get_dist(rand_depart, rand_arrive), randint(1, nb_velo), rand_depart, rand_arrive, randint(1, 50)))
