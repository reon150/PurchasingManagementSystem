package purchasingmanagementsystem
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class Employees {
    Integer employeeId
    String identificationNumber
    String firstName
    String lastName
    Integer departmentId
    String isactive
    static constraints = {
    }
}
