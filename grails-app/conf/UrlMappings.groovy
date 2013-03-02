class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		//"/"(view:"/index")
        //"/"(controller: 'Auth', action: 'login')
        "/"(view:  'initial')
		"500"(view:'/error')
        //"/$view"()

        //"/initial"(view: "initial")
        "/${view}.gspv"()
        "/${view}.gsp"(controller:  "Auth", action: "login")
	}
}
