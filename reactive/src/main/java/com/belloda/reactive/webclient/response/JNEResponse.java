package com.belloda.reactive.webclient.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JNEResponse {
    
    public List<Object> data;
    public int type;
    public String message;
    public String messageLog;
    public boolean success;
    public int aditional;
    public Object extravalue;
    
}
