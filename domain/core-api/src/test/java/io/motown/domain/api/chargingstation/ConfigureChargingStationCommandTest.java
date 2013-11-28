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

import java.util.*;

public class ConfigureChargingStationCommandTest {

    @Test(expected = NullPointerException.class)
    public void nullPointerExceptionThrownWhenCreatingCommandWithChargingStationIdNullAndConnectors() {
        new ConfigureChargingStationCommand(null, 0);
    }

    @Test(expected = NullPointerException.class)
    public void nullPointerExceptionThrownWhenCreatingCommandWithConfigurationItemsNull() {
        new ConfigureChargingStationCommand(new ChargingStationId("CS-001"), 0, null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void unsupportedOperationExceptionThrownWhenModifyingConfigurationItems() {
        Map<String, String> configurationItems = new HashMap<>();

        ConfigureChargingStationCommand command = new ConfigureChargingStationCommand(new ChargingStationId("CS-001"), configurationItems);

        command.getConfigurationItems().put("configItem", "configValue");
    }
}
