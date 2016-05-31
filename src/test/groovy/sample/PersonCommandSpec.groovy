package sample

import spock.lang.Unroll
import test.ConstraintUnitSpec

class PersonCommandSpec extends ConstraintUnitSpec {

    PersonCommand person = new PersonCommand()

    @Unroll
    def "validate: #field is #error when its value is #value.inspect()"() {
        given:
        bind(person, field, value)

        expect:
        validateConstraints(person, field, error)

        where:
        field  | error              | value
        "name" | "nullable"         | null
        "name" | "blank"            | ""
        "name" | "blank"            | " " * 1001
        "name" | "valid"            | "武田信玄"
        "name" | "valid"            | "x" * 1000
        "name" | "maxSize.exceeded" | "x" * 1001
        "age"  | "nullable"         | null
        "age"  | "nullable"         | ""
        "age"  | "nullable"         | " " * 1001
        "age"  | "typeMismatch"     | "NOT_INTEGER"
        "age"  | "min.notmet"       | "-1"
        "age"  | "valid"            | "0"
        "age"  | "valid"            | "17"
    }
}
