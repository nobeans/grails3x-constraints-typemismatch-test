package sample

import grails.validation.Validateable

class PersonCommand implements Validateable {

    String name
    Integer age

    static constraints = {
        name blank: false, maxSize: 1000
        age min: 0
    }
}
