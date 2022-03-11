package purchasingmanagementsystem

class Article {
    static mapping = {
        table 'ARTICLE'
        // version is set to false, because this isn't available by default for legacy databases
        version false
        id generator:'identity', column:'ARTICLE_ID'
        measurementUnit column:'MEASUREMENT_UNIT_ID'
        brand column:'BRAND_ID'
    }
    String description
    Integer existance
    Boolean isActive
    BigDecimal price
    // Relation
    MeasurementUnit measurementUnit
    // Relation
    Brand brand

    static constraints = {
        description(size: 1..50, blank: false)
        existance(max: 2147483647)
        isActive()
        price()
        measurementUnit()
        brand()
    }

    @Override
    public String toString() { return description }
}
