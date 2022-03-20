package purchasingmanagementsystem

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class MeasurementUnitController {

    MeasurementUnitService measurementUnitService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, String q) {
        params.max = Math.min(max ?: 10, 100)

        if (q == null){
            respond measurementUnitService.list(params), model:[measurementUnitCount: measurementUnitService.count()]
        } else {
            respond measurementUnitService.list(params)
                .findAll{
                    it.description.toLowerCase().contains(q.toLowerCase()) ||
                    it.isActive.toString().toLowerCase().contains(q.toLowerCase())
                },
            model:[measurementUnitCount: measurementUnitService.count()]
        }
    }

    def show(Long id) {
        respond measurementUnitService.get(id)
    }

    def create() {
        respond new MeasurementUnit(params)
    }

    def save(MeasurementUnit measurementUnit) {
        if (measurementUnit == null) {
            notFound()
            return
        }

        try {
            measurementUnitService.save(measurementUnit)
        } catch (ValidationException e) {
            respond measurementUnit.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'measurementUnit.label', default: 'MeasurementUnit'), measurementUnit.id])
                redirect measurementUnit
            }
            '*' { respond measurementUnit, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond measurementUnitService.get(id)
    }

    def update(MeasurementUnit measurementUnit) {
        if (measurementUnit == null) {
            notFound()
            return
        }

        try {
            measurementUnitService.save(measurementUnit)
        } catch (ValidationException e) {
            respond measurementUnit.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'measurementUnit.label', default: 'MeasurementUnit'), measurementUnit.id])
                redirect measurementUnit
            }
            '*'{ respond measurementUnit, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        measurementUnitService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'measurementUnit.label', default: 'MeasurementUnit'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'measurementUnit.label', default: 'MeasurementUnit'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
