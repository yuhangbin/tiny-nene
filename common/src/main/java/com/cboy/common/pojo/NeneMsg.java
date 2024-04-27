package com.cboy.common.pojo;

import com.cboy.common.exception.NeneException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NeneMsg implements NeneProtocol{

    private String msgId;
    private int msgType;

    private List<Long> receiverIds;

}
