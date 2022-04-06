package purchasingmanagementsystem

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class ShoppingOrderController {

    ShoppingOrderService shoppingOrderService
    ArticleService articleService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond shoppingOrderService.list(params), model:[shoppingOrderCount: shoppingOrderService.count()]
    }

    def show(Long id) {
        respond shoppingOrderService.get(id)
    }

    def edit(Long id) {
        respond shoppingOrderService.get(id)
    }

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
