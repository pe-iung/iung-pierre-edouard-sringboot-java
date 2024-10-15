package com.openclassrooms.P5.controller.dto;
import com.openclassrooms.P5.model.Firestation;

import lombok.Data;
import lombok.Value;

@Value
public class FirestationAddedResponse {
        private String address;
        private int station;

        public FirestationAddedResponse(Firestation firestation){
                this.address = firestation.getAddress();
                this.station = firestation.getStation();
        }
}
