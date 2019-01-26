package com.sipi.groupOne.train;

import javax.xml.bind.annotation.XmlAttribute;

public class JAXBObject {
    String trainStation;

    @XmlAttribute
    public void setTrainStation(String trainStation) {
        this.trainStation = trainStation;
    }


}
