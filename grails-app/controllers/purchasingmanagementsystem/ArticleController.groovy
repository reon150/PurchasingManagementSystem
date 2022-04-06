package purchasingmanagementsystem

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class ArticleController {

    ArticleService articleService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    @Secured(["ROLE_ADMIN", "ROLE_USER"])
    def index(Integer max, String q) {
        params.max = Math.min(max ?: 10, 100)

        if (q == null) {
            respond articleService.list(params), model: [articleCount: articleService.count()]
        } else {
            respond articleService.list(params)
                    .findAll {
                        it.description.toLowerCase().contains(q.toLowerCase()) ||
                                it.existence.toString().toLowerCase().contains(q.toLowerCase()) ||
                                it.isActive.toString().toLowerCase().contains(q.toLowerCase()) ||
                                it.price.toString().toLowerCase().contains(q.toLowerCase()) ||
                                it.measurementUnit.toString().toLowerCase().contains(q.toLowerCase()) ||
                                it.brand.toString().toLowerCase().contains(q.toLowerCase())
                    }, model: [articleCount: articleService.count()]
        }
    }

    def show(Long id) {
        respond articleService.get(id)
    }

    def create() {
        respond new Article(params)
    }

    def save(Article article) {
        if (article == null) {
            notFound()
            return
        }

        try {
            articleService.save(article)
        } catch (ValidationException e) {
            respond article.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'article.label', default: 'Article'), article.id])
                redirect article
            }
            '*' { respond article, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond articleService.get(id)
    }

    def update(Article article) {
        if (article == null) {
            notFound()
            return
        }

        try {
            articleService.save(article)
        } catch (ValidationException e) {
            respond article.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'article.label', default: 'Article'), article.id])
                redirect article
            }
            '*'{ respond article, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        articleService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'article.label', default: 'Article'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
    def export() {
        def title = "Id, Description, Existence, IsActive, Price, MeasurementUnit, Brand"
        def body = ""
        articleService.list().each {
            it -> {
                body += "${it.id}, ${it.description}, ${it.existence}, ${it.isActive}, ${it.price}, ${it.measurementUnit.toString()}, ${it.brand.toString()}"
                body += System.lineSeparator()
            }
        }
        def content = "sep=," + System.lineSeparator() + title + System.lineSeparator() + body;

        header "Content-disposition", "filename=Articles.csv"
        render(text: content, contentType:"text/csv")
    }
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'article.label', default: 'Article'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
