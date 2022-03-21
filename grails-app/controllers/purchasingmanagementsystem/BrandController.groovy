package purchasingmanagementsystem

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class BrandController {

    BrandService brandService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String q) {
        params.max = Math.min(max ?: 10, 100)

        if (q == null){
            respond brandService.list(params), model:[brandCount: brandService.count()]
        } else {
            respond brandService.list(params)
                .findAll{
                    it.description.toLowerCase().contains(q.toLowerCase()) ||
                    it.isActive.toString().toLowerCase().contains(q.toLowerCase())
                },
            model:[brandCount: brandService.count()]
        }
    }

    def show(Long id) {
        respond brandService.get(id)
    }

    def create() {
        respond new Brand(params)
    }

    def save(Brand brand) {
        if (brand == null) {
            notFound()
            return
        }

        try {
            brandService.save(brand)
        } catch (ValidationException e) {
            respond brand.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'brand.label', default: 'Brand'), brand.id])
                redirect brand
            }
            '*' { respond brand, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond brandService.get(id)
    }

    def update(Brand brand) {
        if (brand == null) {
            notFound()
            return
        }

        try {
            brandService.save(brand)
        } catch (ValidationException e) {
            respond brand.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'brand.label', default: 'Brand'), brand.id])
                redirect brand
            }
            '*'{ respond brand, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        brandService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'brand.label', default: 'Brand'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    def export() {
        def title = "Id,Description,Is Active,"
        def body = ""
        brandService.list().each {
            it -> {
                body += "${it.id},${it.description},${it.isActive}"
                body += System.lineSeparator()
            }
        }
        def content = "sep=," + System.lineSeparator() + title + System.lineSeparator() + body;

        header "Content-disposition", "filename=Brands.csv"
        render(text: content, contentType:"text/csv")
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'brand.label', default: 'Brand'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
