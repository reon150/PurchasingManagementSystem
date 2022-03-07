package purchasingmanagementsystem

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class Brands {
    Integer brandId
    String description
    String isActive
    static constraints = {
    }
}
