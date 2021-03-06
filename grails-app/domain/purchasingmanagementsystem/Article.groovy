package purchasingmanagementsystem

class Article {
    static mapping = {
        table 'ARTICLE'
        version true
        id generator:'identity', column:'ARTICLE_ID'
        measurementUnit column:'MEASUREMENT_UNIT_ID'
        brand column:'BRAND_ID'
    }
    String description
    Integer existence
    Boolean isActive
    BigDecimal price
    // Relation
    MeasurementUnit measurementUnit
    // Relation
    Brand brand

    static constraints = {
        description(size: 1..50, blank: false)
        existence(min: 0, max: 2147483647)
        isActive()
        price(min: 1.0)
        measurementUnit()
        brand()
    }

    @Override
    public String toString() { return description }
}
