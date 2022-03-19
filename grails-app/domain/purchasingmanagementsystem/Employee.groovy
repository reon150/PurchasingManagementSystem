package purchasingmanagementsystem

class Employee {
    static mapping = {
        table 'EMPLOYEE'
        version true
        id generator:'identity', column:'EMPLOYEE_ID'
        department column:'DEPARTMENT_ID'
    }
    Integer id
    String identificationNumber
    String firstName
    String lastName
    Boolean isActive
    // Relation
    Department department

    static constraints = {
        identificationNumber(size: 1..11, blank: false, unique: true)
        firstName(size: 1..60, blank: false)
        lastName(size: 1..60, blank: false)
        department()
        identificationNumber validator: {
            return ValidatorUtil.isAValidIdentityDocument(it)
        }
    }

    @Override
    public String toString() {
        return  firstName + " " + lastName
    }
}
