package purchasingmanagementsystem

class Department {
    static mapping = {
         table 'DEPARTMENT'
         // version is set to false, because this isn't available by default for legacy databases
         version false
         id generator:'identity', column:'DEPARTMENT_ID'
    }
    String departmentName
    Boolean isActive

    static constraints = {
        departmentName(size: 1..100, blank: false)
        isActive()
    }
}
