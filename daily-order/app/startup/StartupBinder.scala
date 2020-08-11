package startup

import com.google.inject.AbstractModule
import play.api.libs.concurrent.AkkaGuiceSupport

class StartupBinder extends AbstractModule with AkkaGuiceSupport {
  override protected def configure(): Unit = {
    bind(classOf[Startup]).asEagerSingleton
  }
}

