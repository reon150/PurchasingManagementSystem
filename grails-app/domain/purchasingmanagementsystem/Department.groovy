package purchasingmanagementsystem

class Department {
    static mapping = {
        table 'DEPARTMENT'
        version true
        id generator:'identity', column:'DEPARTMENT_ID'
    }
    String departmentName
    Boolean isActive

    static constraints = {
        departmentName(size: 1..100, blank: false)
        isActive()
    }

    @Override
    public String toString() { return departmentName }
}
