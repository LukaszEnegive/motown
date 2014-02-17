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
package io.motown.operatorapi.json.gson;

import com.google.gson.*;
import io.motown.domain.api.chargingstation.IdentifyingToken;
import io.motown.domain.api.chargingstation.TextualToken;

import java.lang.reflect.Type;

/**
 * Type adapter for the {@code TextualToken} JSON object.
 * <pre>
 * {@code
 * {
 *  token:  "string",
 *  status: "string"    // one of [ACCEPTED, BLOCKED, EXPIRED, INVALID, CONCURRENT_TX, DELETED]
 * }
 * }
 * </pre>
 */
public class TextualTokenTypeAdapter implements TypeAdapter<TextualToken> {

    @Override
    public Class<?> getAdaptedType() {
        return TextualToken.class;
    }

    @Override
    public TextualToken deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonObject obj;
        String token;

        try {
            obj = json.getAsJsonObject();
        } catch(ClassCastException | IllegalStateException e) {
            throw new JsonParseException("TextualToken must be a JSON object", e);
        }

        try {
            token = obj.getAsJsonPrimitive("token") != null ? obj.getAsJsonPrimitive("token").getAsString() : "";
        } catch(ClassCastException | IllegalStateException e) {
            throw new JsonParseException("token must be a JSON string", e);
        }

        JsonPrimitive authenticationStatus = obj.getAsJsonPrimitive("status");
        if (authenticationStatus != null) {

            String status;

            try {
                status = authenticationStatus.getAsString();
            } catch(ClassCastException | IllegalStateException e) {
                throw new JsonParseException("status must be a JSON string", e);
            }

            return new TextualToken(token, IdentifyingToken.AuthenticationStatus.valueOf(status));
        }

        return new TextualToken(token);
    }
}
