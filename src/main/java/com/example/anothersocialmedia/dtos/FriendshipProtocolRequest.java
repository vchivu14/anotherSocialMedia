package com.example.anothersocialmedia.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FriendshipProtocolRequest {
    private String method;
    private String sender;
    private String srcHost;
    private String recipient;
    private String rcpHost;
    private Integer version;

    @Override
    public String toString() {
        return method+" "+sender+" "+srcHost+" "+recipient+" "+rcpHost+" "+version;
    }
}
