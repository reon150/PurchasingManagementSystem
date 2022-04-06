package purchasingmanagementsystem

import auth.User
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class ArticleRequestController {

    ArticleRequestService articleRequestService
    ShoppingOrderService shoppingOrderService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String q) {
        params.max = Math.min(max ?: 10, 100)

        if (q == null){
            respond articleRequestService.list(params).sort { it.status }, model:[articleRequestCount: articleRequestService.count()]
        } else {
            respond articleRequestService.list(params)
                .findAll{
                    it.requestDate.toString().toLowerCase().contains(q.toLowerCase()) ||
                    it.quantity.toString().toLowerCase().contains(q.toLowerCase()) ||
                    it.isActive.toString().toLowerCase().contains(q.toLowerCase()) ||
                    it.status.toString().toLowerCase().contains(q.toLowerCase()) ||
                    it.measurementUnit.toString().toLowerCase().contains(q.toLowerCase()) ||
                    it.employee.toString().toLowerCase().toString().contains(q.toLowerCase()) ||
                    it.article.toString().toLowerCase().toString().contains(q.toLowerCase()) 
                },
            model:[articleRequestCount: articleRequestService.count()]
        }
    }

    def show(Long id) {
        respond articleRequestService.get(id)
    }

    def create() {
        respond new ArticleRequest(params)
    }

    def save(ArticleRequest articleRequest) {
        if (articleRequest == null) {
            notFound()
            return
        }

        try {
            if (articleRequest.getStatus().description == Constants.APPROVED) {
                articleRequestService.save(articleRequest)
                def shoppingOrder = new ShoppingOrder()
                shoppingOrder.setOrderDate(new Date())
                shoppingOrder.setStatus(Status.find{ description == Constants.ACTIVE })
                shoppingOrder.setArticleRequest(articleRequest)
                shoppingOrder.setIdentifier(UUID.randomUUID().toString())
                shoppingOrderService.save(shoppingOrder)
            } else {
                articleRequestService.save(articleRequest)
            }
        } catch (ValidationException e) {
            respond articleRequest.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'articleRequest.label', default: 'ArticleRequest'), articleRequest.id])
                redirect articleRequest
            }
            '*' { respond articleRequest, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond articleRequestService.get(id)
    }

    def update(ArticleRequest articleRequest) {
        if (articleRequest == null) {
            notFound()
            return
        }

        try {
            if (articleRequest.getStatus().description == Constants.APPROVED) {
                articleRequestService.save(articleRequest)
                def shoppingOrder = new ShoppingOrder()
                shoppingOrder.setOrderDate(new Date())
                shoppingOrder.setStatus(Status.find{ description == Constants.ACTIVE })
                shoppingOrder.setArticleRequest(articleRequest)
                shoppingOrder.setIdentifier(UUID.randomUUID().toString())
                shoppingOrderService.save(shoppingOrder)
            } else {
                articleRequestService.save(articleRequest)
            }
        } catch (ValidationException e) {
            respond articleRequest.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'articleRequest.label', default: 'ArticleRequest'), articleRequest.id])
                redirect articleRequest
            }
            '*'{ respond articleRequest, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        articleRequestService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'articleRequest.label', default: 'ArticleRequest'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    def export() {
        def title = "Id, Request Date, Quantity, Is Active, Status, Measurement Unit, Employee, Article"
        def body = ""
        articleRequestService.list().each {
            it -> {
                body += "${it.id},${it.requestDate},${it.isActive},${it.status},${it.measurementUnit},${it.employee},${it.article},${it.supplier}"
                body += System.lineSeparator()
            }
        }
        def content = "sep=," + System.lineSeparator() + title + System.lineSeparator() + body;

        header "Content-disposition", "filename=ArticleRequest.csv"
        render(text: content, contentType:"text/csv")
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'articleRequest.label', default: 'ArticleRequest'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
