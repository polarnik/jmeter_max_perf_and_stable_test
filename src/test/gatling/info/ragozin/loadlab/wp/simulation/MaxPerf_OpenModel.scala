package info.ragozin.loadlab.wp.simulation

import io.gatling.core.structure.ScenarioBuilder
import info.ragozin.loadlab.wp.process.SimpleScenario
import info.ragozin.loadlab.wp.setting.Protocol

import scala.concurrent.duration._
import io.gatling.core.Predef._

class MaxPerf_OpenModel extends Simulation {

  // Сценарий работы виртульного пользователя -
  // одна итерация
  val userOpenMainPage : ScenarioBuilder =
    scenario(Protocol.cfg.title())
      .exec(
        SimpleScenario.simpleScenario()
      )

  val maxTPS = Protocol.cfg.tps()

  // Длительность теста
  val duration_sec = Protocol.cfg.duration()

  setUp(
    userOpenMainPage
      .inject(
        rampUsersPerSec(0) to (maxTPS) during (duration_sec seconds)
      )
      .protocols(Protocol.httpConf)
  )
}
