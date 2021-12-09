-- ============================================================
--                  STATISTIQUES
-- ============================================================

--moyenne de nombre d'usagers par vélos par jour XXX

SELECT AVG(NB_USAGER) AS MOYENNE
FROM (SELECT to_char(DATE_DE_DEBUT, 'DD-MM-YY'), COUNT(distinct NUMERO_ADHERENT) AS NB_USAGER
      FROM EMPRUNTS
      GROUP BY to_char(DATE_DE_DEBUT, 'DD-MM-YY')
     );
--moyenne des distances parcourues par les vélos sur une semaine
select avg(DISTANCE_SEMAINE)
from (SELECT NUMERO_REFERENCE,DATE_DE_DEBUT, sum(DISTANCE_PARCOURUE) as DISTANCE_SEMAINE
      from EMPRUNTS
      where DATE_DE_DEBUT between to_date('17-05-18 00:00:00','DD-MM-YY H24:MI:SS') and to_date('03-05-21','DD-MON-YY H24:MI:SS')
      group by NUMERO_REFERENCE,DATE_DE_DEBUT;
      );


--moyenne des distances parcourures par vélo par semaine.
select avg(DISTANCE_PARCOURUE)
from EMPRUNTS
group by NUMERO_REFERENCE;
--having DATE_DE_DEBUT>=XX and DATE_DE_FIN<=YY



--classement des stations par nombre de places disponibles par commune
select STATIONS.NUMERO_COMMUNE,NOM_COMMUNE, NUMERO_STATION, NB_BORNES
from STATIONS
inner join COMMUNES
on COMMUNES.NUMERO_COMMUNE = STATIONS.NUMERO_COMMUNE
order by STATIONS.NUMERO_COMMUNE asc, NB_BORNES desc;

--classement des vélos les plus chargés par station
select NUMERO_STATION, NUMERO_REFERENCE, NIVEAU_DE_BATTERIE
from VELOS
where NUMERO_STATION is not null
group by NUMERO_STATION,NUMERO_REFERENCE
order by NUMERO_STATION, NIVEAU_DE_BATTERIE desc;
