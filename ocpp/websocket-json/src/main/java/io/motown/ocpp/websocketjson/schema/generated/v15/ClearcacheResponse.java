
package io.motown.ocpp.websocketjson.schema.generated.v15;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;


/**
 * ClearCacheResponse
 * <p>
 * 
 * 
 */
@Generated("org.jsonschema2pojo")
public class ClearcacheResponse {

    @Expose
    private ClearcacheResponse.Status status;

    public ClearcacheResponse.Status getStatus() {
        return status;
    }

    public void setStatus(ClearcacheResponse.Status status) {
        this.status = status;
    }

    @Generated("org.jsonschema2pojo")
    public static enum Status {

        ACCEPTED("Accepted"),
        REJECTED("Rejected");
        private final String value;
        private static Map<String, ClearcacheResponse.Status> constants = new HashMap<String, ClearcacheResponse.Status>();

        static {
            for (ClearcacheResponse.Status c: ClearcacheResponse.Status.values()) {
                constants.put(c.value, c);
            }
        }

        private Status(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static ClearcacheResponse.Status fromValue(String value) {
            ClearcacheResponse.Status constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
