// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }


grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']


// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// enable query caching by default
grails.hibernate.cache.queries = true

// set per-environment serverURL stem for creating absolute links
environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        grails.serverURL = "http://www.commshare.net"  // TODO: You will need to change this URL
    }
}

// log4j configuration
log4j = {

    info "grails.app"

    //debug 'org.springframework.security'

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}

grails.plugins.twitterbootstrap.fixtaglib = true
// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'commshare.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'commshare.SecUserSecRole'
grails.plugins.springsecurity.authority.className = 'commshare.SecRole'
grails.plugins.springsecurity.userLookup.usernamePropertyName='username'

grails.plugins.springsecurity.ui.encodePassword = false
grails.plugins.springsecurity.ui.register.postRegisterUrl = '/group'
grails.plugins.springsecurity.ui.register.defaultRoleNames = ['ROLE_USER']
grails.plugins.springsecurity.ui.password.validationRegex = ".+?"
grails.plugins.springsecurity.ui.password.minLength = 8
grails.plugins.springsecurity.ui.password.maxLength = 30

/* make spring security ui backend accessible only to admin */
grails.plugins.springsecurity.controllerAnnotations.staticRules = [
        '/user/**': ['ROLE_ADMIN'],
        '/group/**': ['ROLE_ADMIN'],
        '/post/**': ['ROLE_ADMIN'],
        '/role/**': ['ROLE_ADMIN'],
        '/aclClass/**': ['ROLE_ADMIN'],
        '/aclEntry/**': ['ROLE_ADMIN'],
        '/aclObjectIdentity/**': ['ROLE_ADMIN'],
        '/aclSid/**': ['ROLE_ADMIN'],
        '/openIdLogin/**': ['ROLE_ADMIN'],
        '/persistentLogin/**': ['ROLE_ADMIN'],
        '/requestmap/**': ['ROLE_ADMIN'],
        '/securityInfo/**': ['ROLE_ADMIN']
]

environments {
    development {
        mail {
            host = "127.0.0.1"
            port = "25"
            props = ["mail.smtp.auth":"false",
                    "mail.smtp.socketFactory.port":"465",
                    "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
                    "mail.smtp.socketFactory.fallback":"true"]
        }
    }
    production {
        mail {
            host = "smtp.sendgrid.net"
            port = "587"
            username = System.getenv('SENDGRID_USERNAME')  //TODO: change this
            password = System.getenv('SENDGRID_PASSWORD')  //TODO: change this
            props = ["mail.smtp.auth":"true"]
        }

        sendgrid {
            username = System.getenv('SENDGRID_USERNAME')  //TODO: change this
            password = System.getenv('SENDGRID_PASSWORD')  //TODO: change this
        }
    }
}
