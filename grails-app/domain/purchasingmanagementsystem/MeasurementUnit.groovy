package purchasingmanagementsystem
/**
 * The MeasurementUnit entity.
 *
 * @author  Nancy Campusano  
 *
 *
 */
class MeasurementUnit {
    static mapping = {
         table 'MEASUREMENT_UNIT'
         // version is set to false, because this isn't available by default for legacy databases
         version false
         id generator:'identity', column:'UNIT_ID'
    }
    Integer unitId
    String description
    Boolean isActive

    static constraints = {
        unitId(max: 2147483647)
        description(size: 1..200, blank: false)
        isActive()
    }
    String toString() {
        return "${unitId}" 
    }
}
