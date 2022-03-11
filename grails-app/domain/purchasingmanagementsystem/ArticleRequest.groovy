package purchasingmanagementsystem

class ArticleRequest {
    static mapping = {
        table 'ARTICLE_REQUEST'
        // version is set to false, because this isn't available by default for legacy databases
        version false
        id generator:'identity', column:'REQUEST_ID'
        status column:'STATUS_ID'
        measurementUnit column:'MEASUREMENT_UNIT_ID'
        employee column:'EMPLOYEE_ID'
        article column:'ARTICLE_ID'
    }
    Integer id
    Date requestDate
    Integer quantity
    Boolean isActive
    // Relation
    Status status
    // Relation
    MeasurementUnit measurementUnit
    // Relation
    Employee employee
    // Relation
    Article article

    static constraints = {
        requestDate()
        quantity(max: 2147483647)
        isActive()
        status()
        measurementUnit()
        employee()
        article()
    }

    @Override
    public String toString() {
        return "Request: " + id + "-" + article.toString()
    }
}
