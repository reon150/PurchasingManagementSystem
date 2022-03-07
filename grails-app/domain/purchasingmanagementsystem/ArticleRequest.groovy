package purchasingmanagementsystem
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class ArticleRequest {
    Integer requestId
    Integer employeeId
    Date requestDate
    Integer articleId
    Integer quantity
    Integer measurementUnitId
    String isActive
    Integer statusId
    static constraints = {
    }
}
