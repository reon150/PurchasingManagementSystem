package purchasingmanagementsystem

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class StatusController {

    StatusService statusService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    @Secured(["ROLE_ADMIN", "ROLE_USER"])
    def index(Integer max, String q) {
        params.max = Math.min(max ?: 10, 100)
        if (q == null) {
            respond statusService.list(params), model: [statusCount: statusService.count()]
        } else {
            respond statusService.list(params)
                    .findAll {
                        it.description.toLowerCase().contains(q.toLowerCase())
                    }, model: [statusCount: statusService.count()]
        }
    }

    def show(Long id) {
        respond statusService.get(id)
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
