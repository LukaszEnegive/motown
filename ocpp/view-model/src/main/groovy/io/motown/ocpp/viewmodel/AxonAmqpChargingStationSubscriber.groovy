/**
 * Copyright (C) 2013 Alliander N.V. (info@motown.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.motown.ocpp.viewmodel

import io.motown.domain.api.chargingstation.ChargingStationId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.annotation.Resource

@Component
class AxonAmqpChargingStationSubscriber implements ChargingStationSubscriber {

    @Autowired
    ChargingStationConfigurer amqpConfigurer

    @Resource(name = "clusterChargingStationSubscriber")
    ChargingStationSubscriber chargingStationSubscriber

    @Resource(name = "boundQueueChargingStationConfigurer")
    ChargingStationConfigurer chargingStationConfigurer

    @Override
    void subscribe(ChargingStationId chargingStationId) {
        chargingStationConfigurer.configure(chargingStationId)
        chargingStationSubscriber.subscribe(chargingStationId)
    }
}
