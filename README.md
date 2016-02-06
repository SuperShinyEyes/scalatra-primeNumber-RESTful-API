# SBT-Scalatra skeleton

## Features

* Scala 2.10
* Scalatra 2.2.0
* SBT IDEA 1.3.0

## Usage

# Start the web server and reload it on file change
./sbt "~;container:start; container:reload /"

Let's curl!

    curl localhost:8080

## Generate the IDEA project files

    ./sbt gen-idea

## Tests

    ./sbt test

## Description
Toteuta Scalalla REST-rajapinta, joka ottaa vastaan kaksi parametria. Ensimmäinen on lista numeroita ja toinen on lista operaatioita. Ensimmäisen listan numerot ovat kokonaislukuja väliltä 0-10000. Lista operaatioista voi sisältää 0, 1, tai 2 kpl seuraavista operaatioista: inc, removePrimes. Operaatio "inc" kasvattaa jokaista numerolistan alkiota yhdellä ja "removePrimes"-operaatio poistaa numerolistasta kaikki alkuluvut. Operaatiot suoritetaan annetussa järjestyksessä.

#### Esimerkkejä:
* (1, 2, 3), ("inc") -> (2, 3, 4)
* (1, 2, 3), ("removePrimes") -> (1)
* (1, 2, 3), ("inc", "removePrimes") -> (4)

Haluaisimme, että käytät kielelle tyypillisiä ominaisuuksia sovelluksen toteuttamisessa ja että syvennyt kielen käyttämiseen hieman. Jos sinulla on kysyttävää tai törmäät ylitsepääsemättömiin esteisiin, voit ottaa yhteyttä minuun sähköpostilla.

Toivoisimme, että et laita koodia tai tehtäväkuvausta mihinkään julkiselle palvelimelle tai levitä niitä muille. Valmiit koodit voit toimittaa minulle pakattuna sähköpostin liitteenä tai salasanalla suojattuna linkkinä esimerkiksi BitBucketiin tai Dropboxiin. Toivoisimme, että palautat vastauksen tehtävään kahden viikon kuluessa, eli viimeistään 22.2.2016. Tehtävään ei kuitenkaan kannata käyttää loputtomasti aikaa. Voit halutessasi käyttää seuraavaa Scala+Scalatra-runkoa toteutuksesi pohjana: https://github.com/laurilehmijoki/sbt-scalatra-skeleton

Keywords: rest api, inc and removePrimes operations, list of of numbers, idiomatic functional Scala
