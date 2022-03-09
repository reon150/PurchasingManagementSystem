package purchasingmanagementsystem

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ShoppingOrderDetailController {

    ShoppingOrderDetailService shoppingOrderDetailService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond shoppingOrderDetailService.list(params), model:[shoppingOrderDetailCount: shoppingOrderDetailService.count()]
    }

    def show(Long id) {
        respond shoppingOrderDetailService.get(id)
    }

    def create() {
        respond new ShoppingOrderDetail(params)
    }

    def save(ShoppingOrderDetail shoppingOrderDetail) {
        if (shoppingOrderDetail == null) {
            notFound()
            return
        }

        try {
            shoppingOrderDetailService.save(shoppingOrderDetail)
        } catch (ValidationException e) {
            respond shoppingOrderDetail.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'shoppingOrderDetail.label', default: 'ShoppingOrderDetail'), shoppingOrderDetail.id])
                redirect shoppingOrderDetail
            }
            '*' { respond shoppingOrderDetail, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond shoppingOrderDetailService.get(id)
    }

    def update(ShoppingOrderDetail shoppingOrderDetail) {
        if (shoppingOrderDetail == null) {
            notFound()
            return
        }

        try {
            shoppingOrderDetailService.save(shoppingOrderDetail)
        } catch (ValidationException e) {
            respond shoppingOrderDetail.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'shoppingOrderDetail.label', default: 'ShoppingOrderDetail'), shoppingOrderDetail.id])
                redirect shoppingOrderDetail
            }
            '*'{ respond shoppingOrderDetail, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        shoppingOrderDetailService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'shoppingOrderDetail.label', default: 'ShoppingOrderDetail'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'shoppingOrderDetail.label', default: 'ShoppingOrderDetail'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
