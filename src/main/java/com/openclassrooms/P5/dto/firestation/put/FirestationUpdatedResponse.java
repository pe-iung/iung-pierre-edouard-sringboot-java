package com.openclassrooms.P5.dto.firestation.put;
import com.openclassrooms.P5.model.Firestation;

import lombok.Value;

@Value
public class FirestationUpdatedResponse {
    private String address;
    private int station;

    public FirestationUpdatedResponse(Firestation firestation){
        this.address = firestation.getAddress();
        this.station = firestation.getStation();
    }
}
