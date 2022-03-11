package purchasingmanagementsystem

class ArticleRequest {
    static mapping = {
         table 'ARTICLE_REQUEST'
         // version is set to false, because this isn't available by default for legacy databases
         version false
         id generator:'identity', column:'REQUEST_ID'
         statusIdStatus column:'STATUS_ID'
         measurementUnitIdMeasurementUnit column:'MEASUREMENT_UNIT_ID'
         employeeIdEmployee column:'EMPLOYEE_ID'
         articleIdArticle column:'ARTICLE_ID'
    }
    Integer id
    Date requestDate
    Integer quantity
    Boolean isActive
    // Relation
    Status statusIdStatus
    // Relation
    MeasurementUnit measurementUnitIdMeasurementUnit
    // Relation
    Employee employeeIdEmployee
    // Relation
    Article articleIdArticle

    static constraints = {
        requestDate()
        quantity(max: 2147483647)
        isActive()
        statusIdStatus()
        measurementUnitIdMeasurementUnit()
        employeeIdEmployee()
        articleIdArticle()
    }

    @Override
    public String toString() {
        return "Request: " + id + "-" + articleIdArticle.toString()
    }
}
