package com.compassouol.error;

import java.util.Map;

public interface MessageException {
    String getExceptionKey();
    Map<String, Object> getMapDetails();
}
