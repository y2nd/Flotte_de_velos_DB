-- ============================================================
--          CONSULTATION DES TABLES
-- ============================================================

--informations sur les vélos, stations et adhérents

select * from VELOS;
select * from STATIONS;
select * from ADHERENTS;

--liste des vélos par station

select STATIONS.NUMERO_STATION, VELOS.NUMERO_REFERENCE, VELOS.MODEL
from VELOS
inner join STATIONS
on VELOS.NUMERO_STATION = STATIONS.NUMERO_STATION;

--liste des vélos en cours d'utilisation
select NUMERO_REFERENCE, MODEL
from VELOS
where NUMERO_STATION is null;

--liste des stations dans une commune donnée
select COMMUNES.NUMERO_COMMUNE, COMMUNES.NOM_COMMUNE, STATIONS.NUMERO_STATION
from STATIONS
inner join COMMUNES
on STATIONS.NUMERO_COMMUNE = COMMUNES.NUMERO_COMMUNE;
--where NOM_COMMUNE='Talence';


--liste des adhérents qui ont emprunté plusieurs au moins deux vélos différents pour un jour donné

select ADHERENTS.NUMERO_ADHERENT, ADHERENTS.NOM_ADHERENT, ADHERENTS.PRENOM_ADHERENT, count(distinct EMPRUNTS.NUMERO_REFERENCE) as NOMBRE_EMPRUNTS 
from ADHERENTS inner join EMPRUNTS on ADHERENTS.NUMERO_ADHERENT=EMPRUNTS.NUMERO_ADHERENT 
where DATE_DE_DEBUT between to_date('16-DEC-21 00:00:00', 'DD-MM-YYYY  HH24 : MI : SS') and to_date('16-DEC-21 23:59:59', 'DD-MM-YYYY  HH24 : MI : SS')
group by ADHERENTS.NUMERO_ADHERENT, ADHERENTS.NOM_ADHERENT, ADHERENTS.PRENOM_ADHERENT 
having count(distinct EMPRUNTS.NUMERO_REFERENCE)>=2;