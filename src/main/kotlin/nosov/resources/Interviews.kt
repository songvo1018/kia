package nosov.resources

import io.ktor.resources.*

@Resource("/interviews")
class Interviews (val sort: String? = "new") {

    @Resource("new")
    class New(val parent: Interviews = Interviews())

    @Resource("{id}")
    class Id(val parent: Interviews = Interviews(), val id: String) {
        @Resource("edit")
        class Edit(val parent: Id)
    }
}