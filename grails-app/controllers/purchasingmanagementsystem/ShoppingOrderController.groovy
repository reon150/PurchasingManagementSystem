package purchasingmanagementsystem

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class ShoppingOrderController {

    ShoppingOrderService shoppingOrderService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond shoppingOrderService.list(params), model:[shoppingOrderCount: shoppingOrderService.count()]
    }

    def show(Long id) {
        respond shoppingOrderService.get(id)
    }

    def create() {
        respond new ShoppingOrder(params)
    }

    def save(ShoppingOrder shoppingOrder) {
        if (shoppingOrder == null) {
            notFound()
            return
        }

        try {
            shoppingOrderService.save(shoppingOrder)
        } catch (ValidationException e) {
            respond shoppingOrder.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'shoppingOrder.label', default: 'ShoppingOrder'), shoppingOrder.id])
                redirect shoppingOrder
            }
            '*' { respond shoppingOrder, [status: CREATED] }
        }
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
            shoppingOrderService.save(shoppingOrder)
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
