package purchasingmanagementsystem
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class Suppliers {
    Integer supplierId
    String description
    String identificationNumber
    String comercialName
    String isActive
    static constraints = {
    }
}
