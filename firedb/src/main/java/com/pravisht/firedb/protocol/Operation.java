package com.pravisht.firedb.protocol;

import lombok.*;

/**
 * We may need to evolve Operations in the future.
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation  {
    @NonNull
    private String name;
}
