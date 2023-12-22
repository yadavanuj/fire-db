package com.pravisht.firedb.protocol;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Packet {
    @NonNull
    @Builder.Default
    private String version = "001";
    private Header[] headers;
    @NonNull
    private Operation operation;
    private Serializable data;
    private StatusCodes status;
    private ErrorDetail errorDetail;
}
