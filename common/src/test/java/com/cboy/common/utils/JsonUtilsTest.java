package com.cboy.common.utils;

import com.cboy.common.pojo.NeneMsg;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonUtilsTest {



    @Test
    void testToJson() {
        NeneMsg<?> req = new NeneMsg<>();
        req.setMsgId(System.currentTimeMillis()+"");
        System.out.println(JsonUtils.toJson(req));
    }

    @Test
    void testFromJson() {
        NeneMsg<?> object = new NeneMsg<>();
        object.setMsgId(System.currentTimeMillis()+"");
        String json = JsonUtils.toJson(object);
        NeneMsg<?> req = JsonUtils.fromJson(json, NeneMsg.class);
        Assertions.assertNotNull(req);
        NeneMsg<?> msg = JsonUtils.fromJson(json, NeneMsg.class);
        Assertions.assertNotNull(msg);
    }
}
