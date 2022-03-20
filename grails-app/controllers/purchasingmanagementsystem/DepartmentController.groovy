package purchasingmanagementsystem

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class DepartmentController {

    DepartmentService departmentService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String q) {
        params.max = Math.min(max ?: 10, 100)

        if (q == null){
            respond departmentService.list(params), model:[departmentCount: departmentService.count()]
        } else {
            respond departmentService.list(params)
                .findAll{
                    it.departmentName.toLowerCase().contains(q.toLowerCase()) ||
                    it.isActive.toString().toLowerCase().contains(q.toLowerCase())
                },
            model:[departmentService: departmentService.count()]
        }
        
    }

    def show(Long id) {
        respond departmentService.get(id)
    }

    def create() {
        respond new Department(params)
    }

    def save(Department department) {
        if (department == null) {
            notFound()
            return
        }

        try {
            departmentService.save(department)
        } catch (ValidationException e) {
            respond department.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'department.label', default: 'Department'), department.id])
                redirect department
            }
            '*' { respond department, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond departmentService.get(id)
    }

    def update(Department department) {
        if (department == null) {
            notFound()
            return
        }

        try {
            departmentService.save(department)
        } catch (ValidationException e) {
            respond department.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'department.label', default: 'Department'), department.id])
                redirect department
            }
            '*'{ respond department, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        departmentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'department.label', default: 'Department'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'department.label', default: 'Department'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
