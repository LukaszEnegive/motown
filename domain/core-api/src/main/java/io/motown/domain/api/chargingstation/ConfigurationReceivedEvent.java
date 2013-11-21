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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Holds the retrieved Chargingstation configuration information.
 */
public class ConfigurationReceivedEvent {

    @TargetAggregateIdentifier
    private final ChargingStationId chargingStationId;

    private final List<Connector> connectors;

    public ConfigurationReceivedEvent(ChargingStationId chargingStationId, List<Connector> connectors) {
        checkArgument(chargingStationId != null);
        checkArgument(connectors != null);

        this.chargingStationId = chargingStationId;
        this.connectors = ImmutableList.copyOf(connectors);
    }

    public ChargingStationId getChargingStationId() {
        return chargingStationId;
    }

    public List<Connector> getConnectors() {
        return ImmutableList.copyOf(connectors);
    }

}