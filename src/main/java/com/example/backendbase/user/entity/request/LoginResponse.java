
package com.example.backendbase.user.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    @JsonProperty("account_id")
    private Long accountId;

    private String token;

    private int[] permission;

    private boolean isDeactivate;

    private Set<String> role;
}
