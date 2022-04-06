package purchasingmanagementsystem

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

class ShoppingOrderController {

    ShoppingOrderService shoppingOrderService
    ArticleService articleService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(["ROLE_ADMIN", "ROLE_USER"])
    def index(Integer max, String q) {
        params.max = Math.min(max ?: 10, 100)

        if (q == null){
            respond shoppingOrderService.list(params), model:[shoppingOrderCount: shoppingOrderService.count()]
        } else {
            respond shoppingOrderService.list(params)
                .findAll{
                    it.identifier.toLowerCase().contains(q.toLowerCase()) ||
                    it.orderDate.toString().toLowerCase().contains(q.toLowerCase()) ||
                    it.articleRequest.toString().toLowerCase().contains(q.toLowerCase())
                    it.status.toString().toLowerCase().contains(q.toLowerCase())
                },
            model:[shoppingOrderCount: shoppingOrderService.count()]
        }
    }

    @Secured(["ROLE_ADMIN", "ROLE_USER"])
    def show(Long id) {
        respond shoppingOrderService.get(id)
    }

    @Secured(["ROLE_ADMIN"])
    def edit(Long id) {
        respond shoppingOrderService.get(id)
    }

    @Secured(["ROLE_ADMIN"])
    def update(ShoppingOrder shoppingOrder) {
        if (shoppingOrder == null) {
            notFound()
            return
        }

        try {
            if (shoppingOrder.getStatus().description == Constants.APPROVED) {
                def article = Article.find {description == shoppingOrder.getArticleRequest().getArticle().getDescription() }
                article.existence += shoppingOrder.getArticleRequest().getQuantity()
                articleService.save(article)
                shoppingOrderService.save(shoppingOrder)
            } else {
                shoppingOrderService.save(shoppingOrder)
            }
        } catch (ValidationException e) {
            respond shoppingOrder.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'shoppingOrder.label', default: 'ShoppingOrder'), shoppingOrder.id])
                redirect shoppingOrder
            }
            '*'{ respond shoppingOrder, [status: OK] }
        }
    }

    @Secured(["ROLE_ADMIN"])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        shoppingOrderService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'shoppingOrder.label', default: 'ShoppingOrder'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    @Secured(["ROLE_ADMIN", "ROLE_USER"])
    def export() {
        def title = "Id, Order Date, Status, Article Request, Identifier"
        def body = ""
        shoppingOrderService.list().each {
            it -> {
                body += "${it.id},${it.orderDate},${it.status},${it.articleRequest},${it.identifier}"
                body += System.lineSeparator()
            }
        }
        def content = "sep=," + System.lineSeparator() + title + System.lineSeparator() + body;

        header "Content-disposition", "filename=ShoppingOrder.csv"
        render(text: content, contentType:"text/csv")
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'shoppingOrder.label', default: 'ShoppingOrder'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
