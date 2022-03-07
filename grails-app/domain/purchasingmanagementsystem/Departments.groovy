package purchasingmanagementsystem
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class Departments {
    Integer departmentId
    String departmentName
    String isActive
    static constraints = {
    }
}
