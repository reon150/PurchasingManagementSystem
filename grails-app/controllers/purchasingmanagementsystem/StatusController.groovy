package purchasingmanagementsystem

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class StatusController {

    StatusService statusService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
   
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

    def export() {
        def title = "Id, Description"
        def body = ""
        statusService.list().each {
            it -> {
                body += "${it.id},${it.description}"
                body += System.lineSeparator()
            }
        }
        def content = "sep=," + System.lineSeparator() + title + System.lineSeparator() + body;

        header "Content-disposition", "filename=Status.csv"
        render(text: content, contentType:"text/csv")
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
