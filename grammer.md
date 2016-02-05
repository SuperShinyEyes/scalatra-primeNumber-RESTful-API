# http://www.scala-lang.org/docu/files/ScalaReference.pdf
## Iterator & Newline
```scala
// The following code designates an anonymous class
new Iterator[Int]
{
    private var x = 0
    def hasNext = true
    def next = { x += 1; x }
}
// With an additional newline character, the same code is interpreted as an object creation followed by a local block:
new Iterator[Int]

{
    private var x = 0
    def hasNext = true
    def next = { x += 1; x }
}
```

## Function types
```scala
case class Bird (val name: String) extends Object {
    def fly(height: Int) = ...
    ...
}

case class Plane ( val callsign: String) extends Object {
    def fly(height: Int) = ...
    ...
}

def takeoff(
    runway: Int,
    r: { val callsign: String; def fly(height: Int) }) = {
        tower.print(r.callsign + " requests take-off on runway " + runway)
        tower.read(r.callsign + " is clear for take-off")
        r.fly(1000)
}
val bird = new Bird("Polly the parrot"){ val callsign = name }
val a380 = new Plane("TZ-987")
takeoff(42, bird)
takeoff(89, a380)
```

## Method types
```scala
def a: Int
def b (x: Int): Boolean
def c (x: Int) (y: String, z: String): String
```

## Polymorphic Method Types
```scala
def empty[A]: List[A]
def union[A <: Comparable[A]] (x: Set[A], xs: Set[A]): Set[A])
```

## Variable Declaration and Definitions
```scala
class TimeOfDayVar {
private var h: Int = 0
private var m: Int = 0
private var s: Int = 0

def hours = h
def hours_= (h: Int) = if (0 <= h && h < 24) this .h = h
                        else throw new DateError()

def minutes            =  m
def minutes_= (m: Int) = if (0 <= m && m < 60) this.m = m
                        else throw new DateError()

def seconds            =  s
def seconds_= (s: Int) = if (0 <= s && s < 60) this.s = s
                        else throw new DateError()
}
val d = new TimeOfDayVar
d.hours = 8; d.minutes = 30; d.seconds = 0
d.hours = 25            // throws a DateError exception
```

## Declaration
```scala
val x: Pair[Int, String] = new Pair(1, "abc")
```

## Repeated Parameteres
```scala
def sum(args: Int * ) = {
    var result = 0
    for (arg <- args) result += arg * arg
    result
}

sum()   // 0
sum(1)   // 1
sum(1,2,3)   // 6

val xs = List(1,2,3)
sum(xs)     // ***** error: expected: Int, found: List[Int]
sum(xs: _*) // 6


def sum(xs: Int*) = (0 /: xs) ((x, y) => x + y)
sum(1,2,3,4)   // 10
sum(List(1,2,3,4): _*)   // 10
```

## Traits
```scala
trait Writer {
    def write(str: String)
}
object Terminal extends Writer {
    def write(str: String) { System.out.println(str) }
}


trait I {
    def factorial(x: Int): Int
}
class C extends I {
    def factorial(x: Int) = if (x == 0) 1 else x * factorial(x - 1)
}
```

## pg.68 Constructor Parameters
```scala
trait Greeting {
    val name: String
    val msg = "How are you, "+name
}
class C extends {
    val name = "Bob"
} with Greeting {
    println(msg)
}   // If name was initialized instead in C's normal class body, msg would be initialized to "How are you, <null>"
```

## pg.95 Matrix multiplication
```scala
def matmul(xss: Array[Array[Double]], yss: Array[Array[Double]]) = {
    val zss: Array[Array[Double]] = new Array(xss.length, yss(0).length)
    var i = 0
    while (i < xss.length) {
        var
        j = 0
        while (j < yss(0).length) {
            var acc = 0.0
            var k = 0
            while (k < yss.length) {
                acc = acc + xss(i)(k) * yss(k)(j)
                k += 1
            }
            zss(i)(j) = acc
            j += 1
        }
        i += 1
    }
    zss
}
```

## pg.98 Generator
```scala
for { i <- 1 until n
    j <- 1 until i
    if isPrime(i+j)
} yield (i, j)
```

## Factorial & Pattern matching
```scala
def fact(n: Int): Int =
    if (n == 0) 1
    else n * fact(n - 1)

def fact(n: Int): Int = n match {
    case 0 => 1
    case n => n * fact(n - 1)
 }

def fact(n) = (1 to n).foldLeft(1) { _ * _ }
```

## Pattern matching & List
```scala
def length[A](list : List[A]) : Int = {
    if (list.isEmpty) 0
    else 1 + length(list.tail)
}

def length[A](list : List[A]) : Int = list match {
    // a list with whatever head followed by a tail
    case _ :: tail => 1 + length(tail)
    case Nil => 0
}
```

## case class
```scala
sealed abstract class Expression
case class X() extends Expression
case class Const(value : Int) extends Expression
case class Add(left : Expression, right : Expression) extends Expression
case class Mult(left : Expression, right : Expression) extends Expression
case class Neg(expr : Expression) extends Expression

def eval(expression : Expression, xValue : Int) : Int = expression match {
    case X() => xValue
    case Const(cst) => cst
    case Add(left, right) => eval(left, xValue) + eval(right, xValue)
    case Mult(left, right) => eval(left, xValue) * eval(right, xValue)
    case Neg(expr) => - eval(expr, xValue)
}

val expr = Add(Const(1), Mult(Const(2), Mult(X(), X())))  // 1 + 2 * X*X
assert(eval(expr, 3) == 19)
```

## Iterator & Newline
```scala
```

## Iterator & Newline
```scala
```

## Iterator & Newline
```scala
```

## Iterator & Newline
```scala
```

## Iterator & Newline
```scala
```

## Iterator & Newline
```scala
```

## Iterator & Newline
```scala
```

## Iterator & Newline
```scala
```

## Iterator & Newline
```scala
```

## Iterator & Newline
```scala
```

## Iterator & Newline
```scala
```

## Iterator & Newline
```scala
```

## Iterator & Newline
```scala
```

## Iterator & Newline
```scala
```

## Iterator & Newline
```scala
```

## Iterator & Newline
```scala
```

## Iterator & Newline
```scala
```

## Iterator & Newline
```scala
```

## Iterator & Newline
```scala
```

## Iterator & Newline
```scala
```

## Iterator & Newline
```scala
```

## Iterator & Newline
```scala
```
