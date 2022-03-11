package purchasingmanagementsystem

class MeasurementUnit {
    static mapping = {
        table 'MEASUREMENT_UNIT'
        // version is set to false, because this isn't available by default for legacy databases
        version false
        id generator:'identity', column:'UNIT_ID'
    }
    String description
    Boolean isActive

    static constraints = {
        description(size: 1..200, blank: false)
        isActive()
    }

    @Override
    public String toString() { return description }
}
