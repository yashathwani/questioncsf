package com.scaler.parking_lot.respositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.scaler.parking_lot.models.Gate;

public class GateRepositoryImp implements GateRepository{
    Map<Long,Gate> gmap;
    long id=0;
    GateRepositoryImp()
    {
        this.gmap=new HashMap<>();
    }
    @Override
    public Optional<Gate> findById(long gateId) {
        if(gmap.containsKey(gateId))
        {
            return Optional.of(gmap.get(gateId));
        }
        else
        {
            return Optional.empty();
        }
    }

    @Override
    public Gate save(Gate gate) {
        if(gate.getId()==0)
        {
            gate.setId(++id);
        }
        gmap.put(gate.getId(),gate);
        return gate;

    }
    
}
