package com.bk.task.lostdates

import spock.lang.Specification;

class DateFinderTest extends Specification{
    def "check-dates"() {
        given:
        def dateFinder = new DateFinder()

        expect:
        dateFinder.answer(x, y, z) == result

        where:
        x << [1, 29, 99]
        y << [1, 2, 12]
        z << [1, 2, 3]

        result << ["1/1/1", "2/2/29", "Ambiguous"]
    }

    def "check one date"() {
        given:
        def dateFinder = new DateFinder()

        when:
        def result = dateFinder.answer(1, 1, 1);

        then:
        result == "1/1/1";
    }

    def "check dates from table"() {
        given:
        def dateFinder = new DateFinder()

        expect:
        dateFinder.answer(x, y, z) == result

        where:
        x  |  y  |  z  | result
        1  |  1  |  1  | "1/1/1"
        2  |  2  |  29 | "2/2/29"
        99 |  12 |  3  | "Ambiguous"
    }
}
