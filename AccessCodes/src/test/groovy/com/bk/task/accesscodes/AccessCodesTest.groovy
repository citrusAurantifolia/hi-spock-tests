package com.bk.task.accesscodes

import spock.lang.Specification;

public class AccessCodesTest extends Specification {
    def "check code combinations"() {
        given:
        def accessCodes = new AccessCodes()

        expect:
        accessCodes.answer(x) == result

        where:
                        x                 | result
        ["foo", "bar", "oof", "bar"]      | 2
        ["x", "y", "xy", "yy", "", "yx"]  | 5
        ["xyy", "yxy", "xy", "", "", "y"] | 5
        ["xxx", "xxx", "xxxx", "xxx"]     | 2
        [null, "xxx", "xxxx", "xxx", null]| 3
    }
}
