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
package io.motown.vas.v10.soap.publisher;

import io.motown.vas.v10.soap.VasConversionService;
import io.motown.vas.v10.soap.schema.*;
import io.motown.vas.viewmodel.persistence.entities.*;
import io.motown.vas.viewmodel.persistence.repostories.ChargingStationRepository;
import io.motown.vas.viewmodel.persistence.repostories.SubscriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebParam;
import java.util.List;

@javax.jws.WebService(
        serviceName = "VasPublisherService",
        portName = "VasPublisherServiceSoap12",
        targetNamespace = MotownVasPublisherService.VAS_NAMESPACE,
        wsdlLocation = "WEB-INF/wsdl/VasPublisherService.wsdl",
        endpointInterface = "io.motown.vas.v10.soap.schema.VasPublisherService")
public class MotownVasPublisherService implements VasPublisherService {

    private static final Logger LOG = LoggerFactory.getLogger(MotownVasPublisherService.class);

    public static final String VAS_NAMESPACE = "urn://Vas/Cs/2010/12/";

    private SubscriptionRepository subscriptionRepository;

    private ChargingStationRepository chargingStationRepository;

    private VasConversionService vasConversionService;

    @Override
    public SubscribeResponse subscribe(@WebParam(partName = "parameters", name = "subscribeRequest", targetNamespace = VAS_NAMESPACE) SubscribeRequest parameters, @WebParam(partName = "SubscriberIdentity", name = "subscriberIdentity", targetNamespace = VAS_NAMESPACE, header = true) String subscriberIdentity) {
        LOG.info("Subscribing {}", subscriberIdentity);

        SubscribeResponse subscribeResponse = new SubscribeResponse();
        try {
            String deliveryAddress = parameters.getDeliveryAddress();

            if (subscriptionRepository.findBySubscriberIdentityAndDeliveryAddress(subscriberIdentity, deliveryAddress) == null) {
                Subscription subscription = new Subscription(subscriberIdentity, deliveryAddress);
                subscriptionRepository.insert(subscription);

                subscribeResponse.setSubscriptionId(subscription.getSubscriptionId());
                subscribeResponse.setStatus(SubscribeStatus.ACCEPTED);
            } else {
                subscribeResponse.setStatus(SubscribeStatus.DUPLICATE_IGNORED);
            }
        } catch (Exception e) {
            LOG.error("Subscription failed", e);
            subscribeResponse.setStatus(SubscribeStatus.REJECTED);
        }

        return subscribeResponse;
    }

    @Override
    public GetChargePointInfoResponse getChargePointInfo(@WebParam(partName = "parameters", name = "getChargePointInfoRequest", targetNamespace = VAS_NAMESPACE) GetChargePointInfoRequest parameters, @WebParam(partName = "SubscriberIdentity", name = "subscriberIdentity", targetNamespace = VAS_NAMESPACE, header = true) String subscriberIdentity) {
        LOG.info("GetChargePointInfo {}", subscriberIdentity);

        GetChargePointInfoResponse response = new GetChargePointInfoResponse();
        if (!subscriptionRepository.findBySubscriberIdentity(subscriberIdentity).isEmpty()) {
            List<ChargePoint> chargePoints = response.getChargePoints();
            for (ChargingStation chargingStation : chargingStationRepository.findAll()) {
                chargePoints.add(vasConversionService.getVasRepresentation(chargingStation));
            }
        }
        return response;
    }

    @Override
    public UnsubscribeResponse unsubscribe(@WebParam(partName = "parameters", name = "unsubscribeRequest", targetNamespace = VAS_NAMESPACE) UnsubscribeRequest parameters, @WebParam(partName = "SubscriberIdentity", name = "subscriberIdentity", targetNamespace = VAS_NAMESPACE, header = true) String subscriberIdentity) {
        LOG.info("Unsubscribing {}", subscriberIdentity);

        UnsubscribeResponse unsubscribeResponse = new UnsubscribeResponse();
        try {
            Subscription subscription = subscriptionRepository.findBySubscriptionId(parameters.getSubscriptionId());

            if (subscription != null) {
                subscriptionRepository.delete(subscription);
                unsubscribeResponse.setStatus(SubscribeStatus.ACCEPTED);
            } else {
                LOG.warn("Unable to unsubscribe unkown subscription {}", subscriberIdentity);
                unsubscribeResponse.setStatus(SubscribeStatus.REJECTED);
            }
        } catch (Exception e) {
            LOG.error("Unsubscribing of {} failed", subscriberIdentity, e);
            unsubscribeResponse.setStatus(SubscribeStatus.REJECTED);
        }

        return unsubscribeResponse;
    }

    public void setSubscriptionRepository(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public void setChargingStationRepository(ChargingStationRepository chargingStationRepository) {
        this.chargingStationRepository = chargingStationRepository;
    }

    public void setVasConversionService(VasConversionService vasConversionService) {
        this.vasConversionService = vasConversionService;
    }
}
