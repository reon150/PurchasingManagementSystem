package purchasingmanagementsystem

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class StatusController {

    StatusService statusService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond statusService.list(params), model:[statusCount: statusService.count()]
    }

    def show(Long id) {
        respond statusService.get(id)
    }

    def create() {
        respond new Status(params)
    }

    def save(Status status) {
        if (status == null) {
            notFound()
            return
        }

        try {
            statusService.save(status)
        } catch (ValidationException e) {
            respond status.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'status.label', default: 'Status'), status.id])
                redirect status
            }
            '*' { respond status, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond statusService.get(id)
    }

    def update(Status status) {
        if (status == null) {
            notFound()
            return
        }

        try {
            statusService.save(status)
        } catch (ValidationException e) {
            respond status.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'status.label', default: 'Status'), status.id])
                redirect status
            }
            '*'{ respond status, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        statusService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'status.label', default: 'Status'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'status.label', default: 'Status'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
