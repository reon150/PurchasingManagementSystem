package purchasingmanagementsystem

import auth.Role
import auth.User
import auth.UserRole
import grails.gorm.transactions.Transactional

class BootStrap {
    def init = { servletContext ->
        addInitialUsers()
        addStatuses()
    }

    def destroy = {
    }

    @Transactional
    void addInitialUsers() {
        def userRole = Role.find { authority ==  'ROLE_USER'} ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
        def adminRole = Role.find { authority ==  'ROLE_ADMIN'}?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)

        def testUser = User.findByUsername('test') ?: new User(
                username: 'test',
                password: '123',
                enabled: true).save(failOnError: true)
        def adminUser = User.findByUsername('admin') ?: new User(
                username: 'admin',
                password: '123',
                enabled: true).save(failOnError: true)

        if (!adminUser.authorities.contains(adminRole)) {
            UserRole.create(adminUser, adminRole)
        }

        if (!testUser.authorities.contains(userRole)) {
            UserRole.create(testUser, userRole)
        }
    }

    @Transactional
    void addStatuses() {
        Status.find { description ==  Constants.ACTIVE} ?: new Status(description: Constants.ACTIVE).save(failOnError: true)
        Status.find { description ==  Constants.APPROVED} ?: new Status(description: Constants.APPROVED).save(failOnError: true)
        Status.find { description ==  Constants.CANCELED}?: new Status(description: Constants.CANCELED).save(failOnError: true)
    }
}
