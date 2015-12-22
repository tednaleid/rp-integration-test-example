import com.naleid.MyIpService
import ratpack.groovy.template.MarkupTemplateModule
import ratpack.rx.RxRatpack
import ratpack.server.Service
import ratpack.server.StartEvent

import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack

ratpack {
  bindings {

    bindInstance Service, new Service() {
      @Override
      void onStart(StartEvent event) throws Exception {
        RxRatpack.initialize()
      }
    }

    module MarkupTemplateModule
    bindInstance(MyIpService, new MyIpService())
  }

  handlers {
    get {
      render groovyMarkupTemplate("index.gtpl", title: "My Ratpack App")
    }

    files { dir "public" }
  }
}
