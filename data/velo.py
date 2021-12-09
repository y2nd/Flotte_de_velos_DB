from random import randint

models = ["24SEVEN INSPIRON 10", "24SEVEN PAVILLION", "MOMABIKE STRIX 700", "MOMABIKE ROG", "RADRHINO LEGION 5", "SILVERWHEEL XPS", "LIGHTSTRIKE ALIEN", "ECORIDE QUORA", "SILVERWHEEL x2"]
fournisseurs = [ 2, 2, 1, 1, 4, 6, 7, 5, 6]
prix = [1.4, 1.7, 2.0, 1.1, 1.5, 1.9, 0.5, 1.8, 1.0]
dates = ["11-JAN-2012", "28-FEB-2014", "10-MAR-2017", "4-APR-2018", "20-MAY-2016", "19-JUN-2015", "11-DEC-2020"]
etats = ["bon", "moyen", "mauvais"]
stations = [1, 2, 3, 4, 5, 6, 7, 8, 9]

ref = 0
for i in range(len(fournisseurs)) :
    nb_velo = randint(10, 15)
    for j in range(nb_velo) :
      ref += 1 
      print("insert into VELOS values ({}, '{}', '{}', {}, '{}', {}, {}, {}, {});".format(ref, models[i], dates[i%7], randint(3000, 7000), etats[i%3], prix[i], randint(10, 99), fournisseurs[i], stations[i]))

for i in range(1,6):
  for j in range(1,6):
    if (j<i):
      print("insert into DISTANCES values (",i,", " ,j,", ", i*6+j,")")