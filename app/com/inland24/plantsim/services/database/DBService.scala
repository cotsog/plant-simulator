/*
 * Copyright (c) 2017 joesan @ http://github.com/joesan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.inland24.plantsim.services.database

import com.inland24.plantsim.config.DBConfig
import com.inland24.plantsim.services.database.models.PowerPlantRow

import scala.concurrent.{ExecutionContext, Future}


class DBService private (dbConfig: DBConfig)
  (implicit ec: ExecutionContext) { self =>

  private val schema = DBSchema(dbConfig.slickDriver)
  private val database = dbConfig.database

  /** Note: These imports should be here! Do not move it */
  import schema._
  import schema.driver.api._

  def allPowerPlants(fetchOnlyActive: Boolean = false): Future[Seq[PowerPlantRow]] = {
    val query =
      if (fetchOnlyActive)
        PowerPlantTable.activePowerPlants
      else
        PowerPlantTable.all

    database.run(query.result)
  }

  def powerPlantById(id: Int): Future[Option[PowerPlantRow]] = {
    database.run(PowerPlantTable.powerPlantById(id).result.headOption)
  }
}
object DBService {

  def apply(dbCfg: DBConfig)(implicit ec: ExecutionContext) =
    new DBService(dbCfg)(ec)
}