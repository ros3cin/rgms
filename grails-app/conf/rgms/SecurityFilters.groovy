package rgms

import org.apache.shiro.*

class SecurityFilters {

    def filters = {
        all(uri:"/**") {
            before = {
                accessControl()
            }
        }
    }
}
