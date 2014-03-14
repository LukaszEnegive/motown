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
package io.motown.ocpp.viewmodel;

import io.motown.domain.api.chargingstation.*;
import org.axonframework.common.annotation.MetaData;

public interface OcppRequestHandler {

    public void handle(ConfigurationRequestedEvent event);

    public void handle(StopTransactionRequestedEvent event, @MetaData(CorrelationToken.KEY) CorrelationToken statusCorrelationToken);

    public void handle(SoftResetChargingStationRequestedEvent event, @MetaData(CorrelationToken.KEY) CorrelationToken statusCorrelationToken);

    public void handle(HardResetChargingStationRequestedEvent event, @MetaData(CorrelationToken.KEY) CorrelationToken statusCorrelationToken);

    public void handle(StartTransactionRequestedEvent event, @MetaData(CorrelationToken.KEY) CorrelationToken statusCorrelationToken);

    public void handle(UnlockEvseRequestedEvent event, @MetaData(CorrelationToken.KEY) CorrelationToken statusCorrelationToken);

    public void handle(ChangeChargingStationAvailabilityToInoperativeRequestedEvent event, @MetaData(CorrelationToken.KEY) CorrelationToken statusCorrelationToken);

    public void handle(ChangeChargingStationAvailabilityToOperativeRequestedEvent event, @MetaData(CorrelationToken.KEY) CorrelationToken statusCorrelationToken);

    public void handle(DataTransferEvent event, @MetaData(CorrelationToken.KEY) CorrelationToken statusCorrelationToken);

    public void handle(ChangeConfigurationEvent event, @MetaData(CorrelationToken.KEY) CorrelationToken statusCorrelationToken);

    public void handle(DiagnosticsRequestedEvent event, @MetaData(CorrelationToken.KEY) CorrelationToken statusCorrelationToken);

    public void handle(ClearCacheRequestedEvent event, @MetaData(CorrelationToken.KEY) CorrelationToken statusCorrelationToken);

    public void handle(FirmwareUpdateRequestedEvent event);

    public void handle(AuthorizationListVersionRequestedEvent event, @MetaData(CorrelationToken.KEY) CorrelationToken statusCorrelationToken);

    public void handle(SendAuthorizationListRequestedEvent event, @MetaData(CorrelationToken.KEY) CorrelationToken statusCorrelationToken);

    public void handle(ReserveNowRequestedEvent event, @MetaData(CorrelationToken.KEY) CorrelationToken statusCorrelationToken);

}
