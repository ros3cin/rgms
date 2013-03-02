class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		//"/"(view:"/index")
        //"/"(controller: 'Auth', action: 'login')

        //"/"(view:  'initial')
        "/"(controller: "Auth", action: "index")
		"500"(view:'/error')


        //"/initial"(view: "initial")

        "/${view}.gspv"()
        //"/${view}.gsp"(controller:  "Auth", action: "index")
	}
}
