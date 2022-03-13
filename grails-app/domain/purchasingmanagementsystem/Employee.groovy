package purchasingmanagementsystem

class Employee {
    static mapping = {
        table 'EMPLOYEE'
        // version is set to false, because this isn't available by default for legacy databases
        version false
        id generator:'identity', column:'EMPLOYEE_ID'
        department column:'DEPARTMENT_ID'
    }
    String identificationNumber
    String firstName
    String lastName
    String isactive
    // Relation
    Department department

    static constraints = {
        identificationNumber(size: 1..11, blank: false, unique: true)
        firstName(size: 1..60, blank: false)
        lastName(size: 1..60, blank: false)
        isactive(size: 1..1, blank: false)
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
