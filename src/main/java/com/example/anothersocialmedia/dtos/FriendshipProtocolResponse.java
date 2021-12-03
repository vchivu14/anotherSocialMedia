package com.example.anothersocialmedia.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FriendshipProtocolResponse {
    private Integer version;
    private Integer status;
    private String phrase;

    @Override
    public String toString() {
        return version+" "+status+" "+phrase;
    }
}
