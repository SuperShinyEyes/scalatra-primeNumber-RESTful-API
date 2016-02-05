import java.io.IOException


class Number {
    /*
        Number has only one item inside, x which is an Integer larger than or
        equal to 0. (i.e., x >= 0)
    */
    private var _value: Int = 0

    // ex) isNaturalNumber("10") => true
    // ex) isNaturalNumber("010") => false
    // ex) isNaturalNumber("0") => true
    // ex) isNaturalNumber("1000a") => false
    // ex) isNaturalNumber("-1") => false
    private def isDigit(s: String): Boolean = s.matches("^[0-9]*$")

    private def isValidDigitOrder(s: String): Boolean = s.length == 1 || s.charAt(0) != '0'

    private def isNaturalNumber(s: String): Boolean = isDigit(s) && isValidDigitOrder(s)

    def isPrime: Boolean = ((2 until value/2) forall (value % _ != 0))

    // def increment = this._value += 1
    def increment = value = value + 1

    def value = _value
    def value_= (x: String) = if (isNaturalNumber(x)) _value = x.toInt else throw new IOException
    def value_= (x: Int) = _value = x
}

val n = new Number
n.value = "7919"
println(n.value)
println(n.isPrime)
println(11/2)
n.increment
println(n.value)
println(n.isPrime)
