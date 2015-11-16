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

public enum AuthorizationResultStatus {
    ACCEPTED("Accepted"),
    BLOCKED("Blocked"),
    EXPIRED("Expired"),
    INVALID("Invalid"),
    CONCURRENT_TX("ConcurrentTx");
    private final String value;

    AuthorizationResultStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AuthorizationResultStatus fromValue(String v) {
        for (AuthorizationResultStatus c: AuthorizationResultStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
