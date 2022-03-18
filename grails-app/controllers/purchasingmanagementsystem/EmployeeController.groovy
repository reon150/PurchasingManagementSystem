package purchasingmanagementsystem

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

class EmployeeController {

    EmployeeService employeeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(["ROLE_ADMIN", "ROLE_USER"])
    def index(Integer max, String q) {
        params.max = Math.min(max ?: 10, 100)

        if (q == null) {
            respond employeeService.list(params), model:[employeeCount: employeeService.count()]
        } else {
            respond employeeService.list(params)
                    .findAll {
                        it.identificationNumber.toLowerCase().contains(q.toLowerCase()) ||
                        it.firstName.toLowerCase().contains(q.toLowerCase()) ||
                        it.lastName.toLowerCase().contains(q.toLowerCase()) ||
                        it.department.toString().toLowerCase().contains(q.toLowerCase()) ||
                        it.isActive.toString().toLowerCase().contains(q.toLowerCase())
                    },
            model:[employeeCount: employeeService.count()]
        }
    }

    @Secured(["ROLE_ADMIN", "ROLE_USER"])
    def show(Long id) {
        respond employeeService.get(id)
    }
    
    @Secured(["ROLE_ADMIN"])
    def create() {
        respond new Employee(params)
    }
    
    @Secured(["ROLE_ADMIN"])
    def save(Employee employee) {
        if (employee == null) {
            notFound()
            return
        }

        try {
            employeeService.save(employee)
        } catch (ValidationException e) {
            respond employee.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'employee.label', default: 'Employee'), employee.id])
                redirect employee
            }
            '*' { respond employee, [status: CREATED] }
        }
    }
    
    @Secured(["ROLE_ADMIN"])
    def edit(Long id) {
        respond employeeService.get(id)
    }
    
    @Secured(["ROLE_ADMIN"])
    def update(Employee employee) {
        if (employee == null) {
            notFound()
            return
        }

        try {
            employeeService.save(employee)
        } catch (ValidationException e) {
            respond employee.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'employee.label', default: 'Employee'), employee.id])
                redirect employee
            }
            '*'{ respond employee, [status: OK] }
        }
    }
    
    @Secured(["ROLE_ADMIN"])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        employeeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'employee.label', default: 'Employee'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'employee.label', default: 'Employee'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
