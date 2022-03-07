package purchasingmanagementsystem

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class Articles {
    Integer articleId
    String description
    Integer brandId
    Integer measurementUnitId
    Integer existance
    String isActive
    Double price;
    static constraints = {
    }
}
