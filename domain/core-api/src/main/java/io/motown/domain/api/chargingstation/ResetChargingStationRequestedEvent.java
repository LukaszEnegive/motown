/**
 * Copyright (C) 2013 Motown.IO (info@motown.io)
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
package io.motown.domain.api.chargingstation;

/**
 * {@code ResetChargingStationRequestedEvent} is the event which is published when a request has been made to reset a
 * charging station. Protocol add-ons should respond to this event or one of its subclasses (if applicable) and request
 * a charging station to reset.
 */
public interface ResetChargingStationRequestedEvent extends CommunicationWithChargingStationRequestedEvent {

    /**
     * Gets the charging station identifier.
     *
     * @return the charging station identifier.
     */
    @Override
    ChargingStationId getChargingStationId();

    /**
     * Gets the protocol identifier.
     *
     * @return the protocol identifier.
     */
    @Override
    String getProtocol();

}
