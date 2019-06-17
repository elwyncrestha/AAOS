package com.elvin.aaos.core.model.dto;

import com.elvin.aaos.core.model.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {

    private String message;

    private MessageType messageType;

    private Object object;

    private String status;

}
