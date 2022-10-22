
package com.example.backendbase.user.entity.request;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String token;

    private int[] permission;

    private boolean isDeactivate;

    private Set<String> role;
}
