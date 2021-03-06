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

package com.inland24.plantsim.models


sealed trait PowerPlantType
object PowerPlantType {

  case object OnOffType   extends PowerPlantType
  case object RampUpType  extends PowerPlantType
  case object UnknownType extends PowerPlantType

  def toString(powerPlantType: PowerPlantType): String = powerPlantType match {
    case OnOffType  => "OnOffType"
    case RampUpType => "RampUpType"
    case _          => "Unknown"
  }

  def fromString(powerPlantTypeStr: String): PowerPlantType = powerPlantTypeStr match {
    case "OnOffType"  => OnOffType
    case "RampUpType" => RampUpType
    case _            => UnknownType
  }
}