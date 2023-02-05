
def please(action) {
    [ the : { what ->
        [of : { n -> action(what(n))}]
    }]
}

show = { println it }
squareRoot = { Math.sqrt it }

please show the squareRoot of 4

def goTo() {
    [ home: { println "Home $it" }, away: {println "Away $it"}]
}

goTo() home "Now"
goTo() away "Soon"

var mapped = goTo()

mapped.home "Now"
mapped.away "There"

println ( 1 <=> 2 )

var list = [1,2,3,4]
println( list [0..2])
println( list [1..2])

list[1..2] = [88,99,77]
println( list )

var mylist = [1,2,3]
println (mylist[4])

class MyMegaCategory {
    static String sugarMe(String me, String other) {
        "$me and your sweet $other"
    }
}

use (MyMegaCategory) {
    println ("HelloWorld".sugarMe("And your sweet babe"))
}