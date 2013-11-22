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

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ReceivedChargingStationConfigurationTest {

    @Test(expected = NullPointerException.class)
    public void nullPointerExceptionThrownWhenCreatingCommandWithChargingStationIdAndConnectorsNull() {
        new ConfigureChargingStationCommand(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void nullPointerExceptionThrownWhenCreatingCommandWithConnectorsNull() {
        new ConfigureChargingStationCommand(new ChargingStationId("CS-001"), null);
    }

    @Test(expected = NullPointerException.class)
    public void nullPointerExceptionThrownWhenCreatingEventWithChargingStationIdAndConnectorsNull() {
        new ChargingStationConfiguredEvent(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void nullPointerExceptionThrownWhenCreatingEventWithConnectorsNull() {
        new ChargingStationConfiguredEvent(new ChargingStationId("CS-001"), null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void unsupportedOperationExceptionThrownWhenModifyingAttributes() {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("vendor", "VENDOR");
        attributes.put("model", "MODEL");

        ChargingStationBootedEvent command = new ChargingStationBootedEvent(new ChargingStationId("CS-001"), attributes);

        command.getAttributes().put("vendor", "ANOTHER_VENDOR");
    }
}
