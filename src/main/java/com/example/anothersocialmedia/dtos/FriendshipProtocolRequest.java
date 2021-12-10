package com.example.anothersocialmedia.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FriendshipProtocolRequest {
    @NotNull
    private String method;
    @NotNull
    private String sender;
    @NotNull
    private String srcHost;
    @NotNull
    private String recipient;
    @NotNull
    private String rcpHost;
    @NotNull
    private Integer version;

    @Override
    public String toString() {
        return method+" "+sender+" "+srcHost+" "+recipient+" "+rcpHost+" "+version;
    }

}
