# RemovePrim&Increment RESTful API using SBT-Scalatra

## Author
Seyoung Park<br>
[seyoung.park@aalto.fi](seyoung.park@aalto.fi)

## Requirements
* Scala 2.10
* Scalatra 2.2.0
* SBT IDEA 1.3.0
* ScalaTest 2.2.6

# Start the web server and reload it on file change
```bash
./sbt "~;container:start; container:reload /"

# Let's curl!
curl localhost:8080/numbers=1,2,7\&commands=inc,removePrime,inc,removePrime
# Returns {"numbers":"9"}

# If you are using a browser remove \ before &
localhost:8080/numbers=1,2,7&commands=inc,removePrime,inc,removePrime
# Returns {"numbers":"9"}
```

## Generate the IDEA project files
```bash
./sbt gen-idea
```

## Tests
```bash
./sbt test
```

## Assignment Description
Toteuta Scalalla REST-rajapinta, joka ottaa vastaan kaksi parametria. Ensimmäinen on lista numeroita ja toinen on lista operaatioita. Ensimmäisen listan numerot ovat kokonaislukuja väliltä 0-10000. Lista operaatioista voi sisältää 0, 1, tai 2 kpl seuraavista operaatioista: inc, removePrimes. Operaatio "inc" kasvattaa jokaista numerolistan alkiota yhdellä ja "removePrimes"-operaatio poistaa numerolistasta kaikki alkuluvut. Operaatiot suoritetaan annetussa järjestyksessä.

#### Esimerkkejä:
* (1, 2, 3), ("inc") -> (2, 3, 4)
* (1, 2, 3), ("removePrimes") -> (1)
* (1, 2, 3), ("inc", "removePrimes") -> (4)

Haluaisimme, että käytät kielelle tyypillisiä ominaisuuksia sovelluksen toteuttamisessa ja että syvennyt kielen käyttämiseen hieman. Jos sinulla on kysyttävää tai törmäät ylitsepääsemättömiin esteisiin, voit ottaa yhteyttä minuun sähköpostilla.

Toivoisimme, että et laita koodia tai tehtäväkuvausta mihinkään julkiselle palvelimelle tai levitä niitä muille. Valmiit koodit voit toimittaa minulle pakattuna sähköpostin liitteenä tai salasanalla suojattuna linkkinä esimerkiksi BitBucketiin tai Dropboxiin. Toivoisimme, että palautat vastauksen tehtävään kahden viikon kuluessa, eli viimeistään 22.2.2016. Tehtävään ei kuitenkaan kannata käyttää loputtomasti aikaa. Voit halutessasi käyttää seuraavaa Scala+Scalatra-runkoa toteutuksesi pohjana: https://github.com/laurilehmijoki/sbt-scalatra-skeleton

Keywords: rest api, inc and removePrimes operations, list of of numbers, idiomatic functional Scala


## Value properties
### 1. TDD
```scala
// src/test/scala/InputProcessorSpec.scala
"Commands" can "be multiple" in {
    Given("inc, removePrime, inc, removePrime and List(3,6,23,5520,7041)")
    val ip1 = new InputProcessor(List(3,6,23,5520,7041), List("inc, removePrime, inc, removePrime"))

    When("increment is called")
    ip1.increment

    Then("=> %s".format(ip1.inputIntegers.mkString(",")))
    assertResult(true, "The number value is not right") {
        ip1.inputIntegers == List(4,7,24,5521,7042)
    }

    When("removePrimes is called")
    ip1.removePrimes

    Then("=> %s".format(ip1.inputIntegers.mkString(",")))
    assertResult(true, "The number value is not right") {
        ip1.inputIntegers == List(4,24,7042)
    }

    When("increment is called")
    ip1.increment

    Then("=> %s".format(ip1.inputIntegers.mkString(",")))
    assertResult(true, "The number value is not right") {
        ip1.inputIntegers == List(5,25,7043)
    }

    When("removePrimes is called")
    ip1.removePrimes

    Then("=> %s".format(ip1.inputIntegers.mkString(",")))
    assertResult(true, "The number value is not right") {
        ip1.inputIntegers == List(25)
    }
}
```
### 2. DRY principle
```scala
// src/test/scala/HelloServletSpec.scala
private def testGet(params: String, reply: String) = {
    s"Get $params" should {
        s"return $reply" in {
            get(params) {
                body must equalTo(s"""$reply""")
            }
        }
    }
}

/* Test invalid inputs */
testGet("/numbers=&commands=removePrime", """{"error":"No numbers"}""")
testGet("/numbers=1&commands=", """{"error":"No commands"}""")
testGet("/numbers=1.1,1a,-3.2&commands=inc", """{"error":"Invalid numbers: 1a, -3.2, 1.1. "}""")
testGet("/numbers=1&commands=incc", """{"error":"Invalid commands: incc."}""")
}
```

### 3. Concise code
```scala
// src/main/scala/InputValidator.scala
def isPrime(n: Int): Boolean = ((2 until (n+2)/2) forall (n % _ != 0)) && n > 1
def removePrimes = inputIntegers = inputIntegers.filterNot(isPrime(_))
def increment: Unit = inputIntegers = inputIntegers.map(_ + 1)
```

### 4. Scalability & Scope
```scala
// src/main/scala/InputProcessor.scala
private var _inputIntegers: List[Int]
def inputIntegers = _inputIntegers
def inputIntegers_= (newList: List[Int]) = _inputIntegers = newList
```

### 5. Readability
```scala
// src/main/scala/InputValidator.scala
def getInvalidNumber: String = {
    /*
       validNumbers = { x | for all x which is an Integer  }
       ex).
            "1" => 1
            "1a" => fail
            "01" => 1
            "1.1" => fail
            "-3" => -3
    */
    val invalids: Set[String] = Set()
    for (number <- inputNumbers) {
        try {
            number.toInt
        } catch {
            case ex: NumberFormatException => invalids += number
        }
    }
    return invalids.mkString(", ")
}

def getInvalidCommands: String = {
    val uniqueCommands = inputCommands.toSet
    val invalidCommands = uniqueCommands diff allowedCommands.toSet
    return invalidCommands.mkString(", ")
}

def getErrorMessage: String = {
    var msg = ""
    if (! isValidNumbers) { msg = "Invalid numbers: %s. ".format(getInvalidNumber)}
    if (! isValidCommands) { msg += "Invalid commands: %s.".format(getInvalidCommands)}
    return msg
}
```


### 6. Functional Scala???
```scala

```
